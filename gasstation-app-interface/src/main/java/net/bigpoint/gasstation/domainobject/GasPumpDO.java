package net.bigpoint.gasstation.domainobject;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import net.bigpoint.assessment.gasstation.GasType;

@Entity
@Table(name = "gaspump", uniqueConstraints = @UniqueConstraint(name = "uc_gastype", columnNames = { "gastype" }))
public class GasPumpDO {

	@Id
	@GeneratedValue
	private Long pumpid;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private GasType gasType;

	@Column(nullable = false)
	@NotNull(message = "Amount can not be null!")
	private double amountInLiters;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "gasPumpDO")
	private PriceListDO priceListDO;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "gasSales")
	private List<SalesListDO> salesListDO;

	private GasPumpDO() {
	}

	public GasPumpDO(GasType gasType, double amountInLiters) {
		this.gasType = gasType;
		this.amountInLiters = amountInLiters;
	}

	public Long getPumpid() {
		return pumpid;
	}

	public void setPumpid(Long pumpid) {
		this.pumpid = pumpid;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public GasType getGastype() {
		return gasType;
	}

	public void setGastype(GasType gasType) {
		this.gasType = gasType;
	}

	public double getAmountInLiters() {
		return amountInLiters;
	}

	public void setAmountInLiters(double amountInLiters) {
		this.amountInLiters = amountInLiters;
	}

	public PriceListDO getPriceListDO() {
		return priceListDO;
	}

	public void setPriceListDO(PriceListDO priceListDO) {
		this.priceListDO = priceListDO;
	}

	public List<SalesListDO> getSalesListDO() {
		return salesListDO;
	}

	public void setSalesListDO(List<SalesListDO> salesListDO) {
		this.salesListDO = salesListDO;
	}
}
