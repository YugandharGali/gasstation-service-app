package net.bigpoint.gasstation.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import net.bigpoint.gasstation.domainobject.SalesListDO;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface SalesListRepository extends CrudRepository<SalesListDO, Long> {

	Long countByNoGas(boolean noGas);

	Long countByPriceExceed(boolean priceExceed);

}
