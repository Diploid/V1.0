/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.exceptionhandler;

public class ServiceCallFailedException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4519688776215731246L;

	/**
	 * 
	 */
	public ServiceCallFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceCallFailedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ServiceCallFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ServiceCallFailedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
