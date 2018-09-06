package net.bigpoint.gasstation.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sales_list")
public class SalesListDO {

	@Id
	@GeneratedValue
	private Long salesid;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	@NotNull(message = "Liters can not be null!")
	private String liters;

	@Column(nullable = false)
	private boolean noGas = false;

	@Column(nullable = false)
	private boolean priceExceed = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gaspump_id_fkb")
	private GasPumpDO gasSales;

	private SalesListDO() {
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getSalesid() {
		return salesid;
	}

	public void setSalesid(Long salesid) {
		this.salesid = salesid;
	}

	public String getLiters() {
		return liters;
	}

	public void setLiters(String liters) {
		this.liters = liters;
	}

	public boolean isNoGas() {
		return noGas;
	}

	public void setNoGas(boolean noGas) {
		this.noGas = noGas;
	}

	public boolean isPriceExceed() {
		return priceExceed;
	}

	public void setPriceExceed(boolean priceExceed) {
		this.priceExceed = priceExceed;
	}

	public GasPumpDO getGasSales() {
		return gasSales;
	}

	public void setGasSales(GasPumpDO gasSales) {
		this.gasSales = gasSales;
	}

}
