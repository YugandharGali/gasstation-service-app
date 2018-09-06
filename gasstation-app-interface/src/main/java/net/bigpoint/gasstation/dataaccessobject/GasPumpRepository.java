package net.bigpoint.gasstation.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import net.bigpoint.gasstation.domainobject.GasPumpDO;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface GasPumpRepository extends CrudRepository<GasPumpDO, Long> {

}
