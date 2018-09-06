package net.bigpoint.gasstation.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.bigpoint.assessment.gasstation.GasType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GasPumpDTO {
	@JsonIgnore
	private Long id;

	@NotNull(message = "GasType can not be null!")
	private GasType gasType;

	@NotNull(message = "AmountInLiters can not be null!")
	private double amountInLiters;

	private GasPumpDTO() {
	}

	public GasPumpDTO(Long id, GasType gasType, double amountInLiters) {
		this.id = id;
		this.gasType = gasType;
		this.amountInLiters = amountInLiters;
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public GasType getGasType() {
		return gasType;
	}

	public void setGasType(GasType gasType) {
		this.gasType = gasType;
	}

	public double getAmountInLiters() {
		return amountInLiters;
	}

	public void setAmountInLiters(double amountInLiters) {
		this.amountInLiters = amountInLiters;
	}

}
