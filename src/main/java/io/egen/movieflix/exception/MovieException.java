package io.egen.movieflix.exception;

public class MovieException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieException(String message) {
		super(message);
	}
	
	public MovieException(String message, Throwable cause) {
		super(message,cause);
	}
}
