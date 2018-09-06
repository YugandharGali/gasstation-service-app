package net.bigpoint.gasstation.service.driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import net.bigpoint.gasstation.dataaccessobject.GasPumpRepository;
import net.bigpoint.gasstation.domainobject.GasPumpDO;

@Service
public class GasPumpServiceImpl implements GasStation {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(GasPumpServiceImpl.class);

	private final GasPumpRepository gasPumpRepository;

	public GasPumpServiceImpl(final GasPumpRepository gasPumpRepository) {
		this.gasPumpRepository = gasPumpRepository;
	}

	@Override
	public void addGasPump(GasPump gasPump) {
		gasPumpRepository.save(makeGasPumpDO(gasPump));
	}

	private GasPumpDO makeGasPumpDO(GasPump gasPump) {
		GasPumpDO gasPumpDO = new GasPumpDO(gasPump.getGasType(), gasPump.getRemainingAmount());
		return gasPumpDO;
	}

	@Override
	public Collection<GasPump> getGasPumps() {
		Iterable<GasPumpDO> gasPumpDOs = gasPumpRepository.findAll();
		return makeGasPumps(gasPumpDOs);
	}

	private Collection<GasPump> makeGasPumps(Iterable<GasPumpDO> gasPumpDOs) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfSales() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfCancellationsNoGas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfCancellationsTooExpensive() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPrice(GasType type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPrice(GasType type, double price) {
		// TODO Auto-generated method stub

	}
}
