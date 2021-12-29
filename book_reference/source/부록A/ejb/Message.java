
package net.sarang.sunny.ejb.exam;

import java.io.Serializable;


public class Message implements Serializable {
	private String message;


	public String getMessage() {
		return message;
	}


	public void setMessage(String string) {
		message = string;
	}

}
