package net.bigpoint.gasstation.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "price_list")
public class PriceListDO {

	@Id
	@GeneratedValue
	private Long priceid;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	@NotNull(message = "Selling Price can not be null!")
	private double sellingPrice;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "priceListDO")
	private GasPumpDO gasPumpDO;

	public PriceListDO() {
	}

	public PriceListDO(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Long getPriceid() {
		return priceid;
	}

	public void setPriceid(Long priceid) {
		this.priceid = priceid;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public GasPumpDO getGasPumpDO() {
		return gasPumpDO;
	}

	public void setGasPumpDO(GasPumpDO gasPumpDO) {
		this.gasPumpDO = gasPumpDO;
	}

}
