package com.cg.anurag.b2.imsdrs.exception;

public class NoDataFoundException extends RuntimeException {
public NoDataFoundException(String msg)
{
	super(msg);
}
public NoDataFoundException(String msg,Throwable e)
{
	super(msg,e);
}
}
