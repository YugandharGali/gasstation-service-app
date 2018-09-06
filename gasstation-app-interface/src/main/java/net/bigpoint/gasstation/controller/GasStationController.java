package net.bigpoint.gasstation.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import net.bigpoint.gasstation.service.driver.GasPumpService;

@RestController
@RequestMapping("v1/gesstation")
public class GasStationController implements GasStation {

	private final GasPumpService gasPumpService;

	@Autowired
	public GasStationController(final GasPumpService gasPumpService) {
		this.gasPumpService = gasPumpService;
	}

	@Override
	@PostMapping("/addGasPump")
	public void addGasPump(GasPump pump) {
			
	}

	@Override
	@GetMapping("/getGasPumps")
	public Collection<GasPump> getGasPumps() {
		return null;
	}

	@Override
	@PostMapping("/buyGas/{amountInLiters}/{maxPricePerLiter}")
	public double buyGas(GasType type, @Valid @PathVariable double amountInLiters,
			@Valid @PathVariable double maxPricePerLiter) throws NotEnoughGasException, GasTooExpensiveException {
		return 0;
	}

	@Override
	@GetMapping("/getRevenue")
	public double getRevenue() {
		return 0;
	}

	@Override
	@GetMapping("/getNumberOfSales")
	public int getNumberOfSales() {
		return 0;
	}

	@Override
	@GetMapping("/getNumberOfCancellationsNoGas")
	public int getNumberOfCancellationsNoGas() {
		return 0;
	}

	@Override
	@GetMapping("/getNumberOfCancellationsTooExpensive")
	public int getNumberOfCancellationsTooExpensive() {
		return 0;
	}

	@Override
	@PostMapping("/getPrice")
	public double getPrice(GasType type) {
		return 0;
	}

	@Override
	@PostMapping("/setPrice/{price}")
	public void setPrice(GasType type, double price) {

	}

}
