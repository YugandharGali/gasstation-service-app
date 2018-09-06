package net.bigpoint.gasstation.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import net.bigpoint.gasstation.controller.mapper.GasPumpMapper;

@RestController
@RequestMapping("v1/gasstation")
public class GasStationController {

	private final GasStation gasStation;

	@Autowired
	public GasStationController(final GasStation gasStation) {
		this.gasStation = gasStation;
	}

	@PostMapping("/addGasPump")
	@ResponseStatus(HttpStatus.CREATED)
	public String addGasPump(@RequestParam GasType gasType, @RequestParam double amount) {
		gasStation.addGasPump(GasPumpMapper.makeGasPump(gasType,amount));
		return "GasStation Added Successfully.";
	}

	@GetMapping("/getGasPumps")
	public Collection<GasPump> getGasPumps() {
		return gasStation.getGasPumps();
	}

	@PostMapping("/buyGas/{amountInLiters}/{maxPricePerLiter}")
	public double buyGas(GasType type, @Valid @PathVariable double amountInLiters,
			@Valid @PathVariable double maxPricePerLiter) throws NotEnoughGasException, GasTooExpensiveException {
		return 0;
	}

	@GetMapping("/getRevenue")
	public double getRevenue() {
		return 0;
	}

	@GetMapping("/getNumberOfSales")
	public int getNumberOfSales() {
		return 0;
	}

	@GetMapping("/getNumberOfCancellationsNoGas")
	public int getNumberOfCancellationsNoGas() {
		return 0;
	}

	@GetMapping("/getNumberOfCancellationsTooExpensive")
	public int getNumberOfCancellationsTooExpensive() {
		return 0;
	}

	@PostMapping("/getPrice")
	public double getPrice(GasType type) {
		return 0;
	}

	@PostMapping("/setPrice/{price}")
	public void setPrice(GasType type, double price) {

	}

}
