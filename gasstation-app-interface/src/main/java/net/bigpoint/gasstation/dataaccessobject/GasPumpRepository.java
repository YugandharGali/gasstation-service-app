package net.bigpoint.gasstation.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.gasstation.domainobject.GasPumpDO;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface GasPumpRepository extends CrudRepository<GasPumpDO, Long> {

	GasPumpDO findByGasType(GasType gasType);

}
