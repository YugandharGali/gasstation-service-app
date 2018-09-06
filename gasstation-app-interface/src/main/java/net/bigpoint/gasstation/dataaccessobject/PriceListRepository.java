package net.bigpoint.gasstation.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import net.bigpoint.gasstation.domainobject.PriceListDO;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface PriceListRepository extends CrudRepository<PriceListDO, Long> {

}
