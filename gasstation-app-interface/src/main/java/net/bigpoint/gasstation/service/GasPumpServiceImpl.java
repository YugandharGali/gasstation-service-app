package net.bigpoint.gasstation.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityNotFoundException;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import net.bigpoint.gasstation.controller.mapper.GasPumpMapper;
import net.bigpoint.gasstation.dataaccessobject.GasPumpRepository;
import net.bigpoint.gasstation.dataaccessobject.PriceListRepository;
import net.bigpoint.gasstation.dataaccessobject.SalesListRepository;
import net.bigpoint.gasstation.domainobject.GasPumpDO;
import net.bigpoint.gasstation.domainobject.PriceListDO;
import net.bigpoint.gasstation.domainobject.SalesListDO;

@Service
public class GasPumpServiceImpl implements GasStation {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(GasPumpServiceImpl.class);

	private final GasPumpRepository gasPumpRepository;
	private final PriceListRepository priceListRepository;
	private final SalesListRepository salesListRepository;

	public GasPumpServiceImpl(final GasPumpRepository gasPumpRepository, final PriceListRepository priceListRepository,
			final SalesListRepository salesListRepository) {
		this.gasPumpRepository = gasPumpRepository;
		this.priceListRepository = priceListRepository;
		this.salesListRepository = salesListRepository;
	}

	@Override
	public void addGasPump(GasPump gasPump) {
		GasPumpDO gasPumpDO = new GasPumpDO(gasPump.getGasType(), gasPump.getRemainingAmount());
		gasPumpRepository.save(gasPumpDO);
	}

	@Override
	public Collection<GasPump> getGasPumps() {
		Iterable<GasPumpDO> gasPumpDOs = gasPumpRepository.findAll();
		Collection<GasPump> gasPumps = new ArrayList<GasPump>();
		GasPump gasPump = null;
		for (Iterator iterator = gasPumpDOs.iterator(); iterator.hasNext();) {
			GasPumpDO gasPumpDO = (GasPumpDO) iterator.next();
			gasPump = new GasPump(gasPumpDO.getGastype(), gasPumpDO.getAmountInLiters());
			gasPumps.add(gasPump);
		}
		return gasPumps;
	}

	@Override
	public double buyGas(GasType type, double amountInLiters, double maxPricePerLiter)
			throws NotEnoughGasException, GasTooExpensiveException {
		GasPumpDO gasPumpDO = checkGasType(type);
		PriceListDO priceListDO = gasPumpDO.getPriceListDO();
		if (priceListDO == null) {
			throw new EntityNotFoundException("Could not find PriceList.");
		}
		SalesListDO salesDo = null;

		// Check gas is selling at the requested price.
		if (maxPricePerLiter != priceListDO.getSellingPrice()) {
			salesDo = new SalesListDO(amountInLiters, false, true, gasPumpDO);
			salesListRepository.save(salesDo);
			throw new GasTooExpensiveException("Gas is not sold at the requested price.");
		}

		// Check has Enough Gas to sell.
		double availableGas = gasPumpDO.getAmountInLiters();
		if (availableGas < amountInLiters) {
			salesDo = new SalesListDO(amountInLiters, true, false, gasPumpDO);
			salesListRepository.save(salesDo);
			throw new NotEnoughGasException("Not enough gas of this type.");
		}

		// All validations passed pump the Gas..
		salesDo = new SalesListDO(amountInLiters, false, false, gasPumpDO);
		salesListRepository.save(salesDo);
		//
		GasPump gasPump = GasPumpMapper.makeGasPump(type, availableGas);
		gasPump.pumpGas(amountInLiters);
		double remainingAmount = gasPump.getRemainingAmount();
		gasPumpDO.setAmountInLiters(remainingAmount);
		gasPumpRepository.save(gasPumpDO);

		return (amountInLiters * maxPricePerLiter);
	}

	private GasPumpDO checkGasType(GasType type) {
		GasPumpDO gasPumpDO = gasPumpRepository.findByGasType(type);
		if (gasPumpDO == null) {
			throw new EntityNotFoundException("Could not find GasType.");
		}
		return gasPumpDO;
	}

	@Override
	public double getRevenue() {
		Iterable<SalesListDO> salesListDOs = salesListRepository.findAll();
		double revenue = 0;
		for (SalesListDO salesListDO : salesListDOs) {
			GasPumpDO gasPumpDO = salesListDO.getGasSales();
			double price = gasPumpDO.getPriceListDO().getSellingPrice();
			revenue *= price;
		}
		return revenue;
	}

	@Override
	public int getNumberOfSales() {
		return (int) salesListRepository.count();
	}

	@Override
	public int getNumberOfCancellationsNoGas() {
		Long count = salesListRepository.countByNoGas(true);
		return count.intValue();
	}

	@Override
	public int getNumberOfCancellationsTooExpensive() {
		Long count = salesListRepository.countByPriceExceed(true);
		return count.intValue();
	}

	@Override
	public double getPrice(GasType type) {
		GasPumpDO gasPumpDO = checkGasType(type);
		if (gasPumpDO.getPriceListDO() == null) {
			throw new EntityNotFoundException("Could not find PriceList.");
		}
		return gasPumpDO.getPriceListDO().getSellingPrice();
	}

	@Override
	public void setPrice(GasType type, double sellingPrice) throws EntityNotFoundException {
		GasPumpDO gasPumpDO = checkGasType(type);
		//
		PriceListDO priceListDO = new PriceListDO(gasPumpDO, sellingPrice);
		priceListRepository.save(priceListDO);
	}
}
