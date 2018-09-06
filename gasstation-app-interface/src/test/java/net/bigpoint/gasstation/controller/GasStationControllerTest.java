package net.bigpoint.gasstation.controller;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import net.bigpoint.gasstation.datatransferobject.GasPumpDTO;
import net.bigpoint.gasstation.domainobject.GasPumpDO;
import net.bigpoint.gasstation.domainobject.PriceListDO;
import net.bigpoint.gasstation.domainobject.SalesListDO;

public class GasStationControllerTest {

	private GasStationController gasStationController;
	private GasStation gasStation;
	GasPumpDO gasPumpDO;
	PriceListDO priceListDO;
	SalesListDO salesListDO;
	GasPumpDTO gasPumpDTO;
	GasPump pump;

	@Before
	public void setUp() throws Exception {
		gasStation = Mockito.mock(GasStation.class);
		gasPumpDTO = new GasPumpDTO((long) 1, GasType.REGULAR, 100);
		gasPumpDO = new GasPumpDO(GasType.REGULAR, 100);
		priceListDO = new PriceListDO(gasPumpDO, 20);
		salesListDO = new SalesListDO(10, false, false, gasPumpDO);
		pump = new GasPump(GasType.REGULAR, 200);
		gasStationController = new GasStationController(gasStation);
	}

	@After
	public void tearDown() throws Exception {
		gasPumpDTO = null;
		priceListDO = null;
		salesListDO = null;
		gasPumpDO = null;
	}

	@Test
	public void testAddGasPump() throws Exception {
		this.gasStationController.addGasPump(GasType.REGULAR, 100);
	}

	@Test
	public void testGetGasPumps() {
		Collection<GasPump> gasPumps = new ArrayList<GasPump>();
		gasPumps.add(pump);
		Collection<GasPump> gasPumpsTest = gasStationController.getGasPumps();
		Assert.assertNotNull(gasPumpsTest);
	}

	@Test
	public void testBuyGas() throws NotEnoughGasException, GasTooExpensiveException {
		given(this.gasStation.buyGas(GasType.REGULAR, 5, 20)).willReturn((double) 50);
		double val = gasStationController.buyGas(GasType.REGULAR, 5, 20);
		Assert.assertEquals(new Double(50), new Double(val));
	}

	@Test
	public void testGetRevenue() {
		given(this.gasStation.getRevenue()).willReturn((double) 10);
		double val = gasStationController.getRevenue();
		Assert.assertEquals(new Double(10), new Double(val));
	}

	@Test
	public void testGetNumberOfSales() {
		given(this.gasStation.getNumberOfSales()).willReturn(10);
		int val = gasStationController.getNumberOfSales();
		Assert.assertEquals(new Integer(10), new Integer(val));
	}

	@Test
	public void testGetNumberOfCancellationsNoGas() {
		given(this.gasStation.getNumberOfCancellationsNoGas()).willReturn(10);
		int val = gasStationController.getNumberOfCancellationsNoGas();
		Assert.assertEquals(new Integer(10), new Integer(val));
	}

	@Test
	public void testGetNumberOfCancellationsTooExpensive() {
		given(this.gasStation.getNumberOfCancellationsTooExpensive()).willReturn(10);
		int val = gasStationController.getNumberOfCancellationsTooExpensive();
		Assert.assertEquals(new Integer(10), new Integer(val));
	}

	@Test
	public void testGetPrice() {
		given(this.gasStation.getPrice(GasType.REGULAR)).willReturn((double) 100);
		double val = gasStationController.getPrice(GasType.REGULAR);
		Assert.assertEquals(new Double(100), new Double(val));
	}

	@Test
	public void testSetPrice() {
		this.gasStationController.setPrice(GasType.REGULAR, 20);
	}

}
