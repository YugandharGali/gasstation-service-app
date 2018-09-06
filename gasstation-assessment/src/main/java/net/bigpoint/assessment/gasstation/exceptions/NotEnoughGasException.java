package net.bigpoint.assessment.gasstation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown whenever gas could not be bought because not enough was available
 * 
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST) // , reason = "Not Enough Gas."
public class NotEnoughGasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4577139900795204370L;

	public NotEnoughGasException(String message) {
		super(message);
	}
}
