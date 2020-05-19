package com.cg.anurag.b2.imsdrmo.exception;

public class NoOrderPlacedException extends RuntimeException{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NoOrderPlacedException(String msg)
{
	super(msg);
}
public NoOrderPlacedException(String msg,Throwable e)
{
	super(msg,e);
}
}
