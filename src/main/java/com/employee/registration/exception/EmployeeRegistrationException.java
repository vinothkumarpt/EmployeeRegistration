/**
 * 
 */
package com.employee.registration.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.employee.registration.controller.EmpRegistController;

/**
 * @author Vinothkumar P T
 *
 */
public class EmployeeRegistrationException extends RuntimeException {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpRegistController.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -5049544992990489821L;

	private String message = null;
	
	private Exception exception = null;

	
	public EmployeeRegistrationException(String message, Exception exception){
		this.message = message;
		this.exception = exception;
		log();
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
	public void log(){
		LOGGER.error(message, exception);
	}

}
