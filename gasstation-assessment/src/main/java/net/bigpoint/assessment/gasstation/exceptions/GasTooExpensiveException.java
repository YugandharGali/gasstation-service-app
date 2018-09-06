package net.bigpoint.assessment.gasstation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown whenever gas could not be bought because the price
 * was too high
 * 
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST) // , reason = "Gas Too Expensive."
public class GasTooExpensiveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2581151114207596829L;

	public GasTooExpensiveException(String message) {
		super(message);
	}

}
