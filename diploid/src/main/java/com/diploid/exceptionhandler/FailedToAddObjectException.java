/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.exceptionhandler;

public class FailedToAddObjectException extends ServiceCallFailedException {
   

	/**
	 * 
	 */
	private static final long serialVersionUID = -7635044432047010046L;

	public FailedToAddObjectException(String msg) {
        super(msg);
    }

	/**
	 * 
	 */
	public FailedToAddObjectException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FailedToAddObjectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public FailedToAddObjectException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
