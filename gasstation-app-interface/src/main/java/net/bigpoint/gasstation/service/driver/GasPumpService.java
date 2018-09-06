package net.bigpoint.gasstation.service.driver;

import java.util.Collection;

import net.bigpoint.assessment.gasstation.GasPump;

public interface GasPumpService {

	void addGasPump(GasPump pump);

	Collection<GasPump> getGasPumps();

}
