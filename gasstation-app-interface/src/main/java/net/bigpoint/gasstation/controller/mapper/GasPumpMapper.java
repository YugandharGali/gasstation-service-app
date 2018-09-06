package net.bigpoint.gasstation.controller.mapper;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasType;

public class GasPumpMapper {

	public static GasPump makeGasPump(GasType gasType, double amount) {
		return new GasPump(gasType, amount);
	}

}
