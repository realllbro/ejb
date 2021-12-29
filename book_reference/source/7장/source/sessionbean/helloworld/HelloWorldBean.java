package kr.co.hanbitbook.ejb.examples;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloWorldBean implements SessionBean{
	private SessionContext ctx;

	public void setSessionContext(SessionContext c){
		ctx = c;
	}

	public String helloWorld(){
		return "Hello World";
	}

	public void ejbCreate(){}
	public void ejbRemove(){}
	public void ejbActivate(){}
	public void ejbPassivate(){}
}