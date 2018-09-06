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
		gasStation.addGasPump(GasPumpMapper.makeGasPump(gasType, amount));
		return "GasStation Added Successfully.";
	}

	@GetMapping("/getGasPumps")
	public Collection<GasPump> getGasPumps() {
		return gasStation.getGasPumps();
	}

	@PostMapping("/buyGas/{amountInLiters}/{maxPricePerLiter}")
	public double buyGas(@RequestParam GasType type, @RequestParam double amountInLiters,
			@RequestParam double maxPricePerLiter) throws NotEnoughGasException, GasTooExpensiveException {
		return gasStation.buyGas(type, amountInLiters, maxPricePerLiter);
	}

	@GetMapping("/getRevenue")
	public double getRevenue() {
		return gasStation.getRevenue();
	}

	@GetMapping("/getNumberOfSales")
	public int getNumberOfSales() {
		return gasStation.getNumberOfSales();
	}

	@GetMapping("/getNumberOfCancellationsNoGas")
	public int getNumberOfCancellationsNoGas() {
		return gasStation.getNumberOfCancellationsNoGas();
	}

	@GetMapping("/getNumberOfCancellationsTooExpensive")
	public int getNumberOfCancellationsTooExpensive() {
		return gasStation.getNumberOfCancellationsTooExpensive();
	}

	@PostMapping("/getPrice")
	public double getPrice(@RequestParam GasType type) {
		return gasStation.getPrice(type);
	}

	@PostMapping("/setPrice/{price}")
	@ResponseStatus(HttpStatus.CREATED)
	public String setPrice(@RequestParam GasType type, @RequestParam double sellingPrice) {
		gasStation.setPrice(type, sellingPrice);
		return "Price set Successfully.";
	}

}
