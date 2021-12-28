package com.sakadream.jsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.NamedEvent;



@ManagedBean
@NamedEvent
@RequestScoped
public class PollView implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int number;

	    public void increment() {
	        number++;
	    }

	    public int getNumber() {
	        return number;
	    }

}
