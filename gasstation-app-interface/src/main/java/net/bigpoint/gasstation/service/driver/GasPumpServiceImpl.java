package net.bigpoint.gasstation.service.driver;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.bigpoint.gasstation.dataaccessobject.GasPumpRepository;

@Service
public class GasPumpServiceImpl implements GasPumpService {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(GasPumpServiceImpl.class);

	private final GasPumpRepository gasPumpRepository;
	// private final CarRepository carRepository;

	public GasPumpServiceImpl(final GasPumpRepository gasPumpRepository) {
		this.gasPumpRepository = gasPumpRepository;
	}

}
