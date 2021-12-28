package com.sakadream.jsf.controller;

import com.sakadream.jsf.bean.Multimedia;
import com.sakadream.jsf.func.Functions;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phan Ba Hai on 17/07/2017.
 */

@ManagedBean(name = "login", eager = true)
@RequestScoped
public class LoginController implements Serializable {
	private List<String> items;
	private Multimedia e;
	private List<Multimedia> video;
	private Functions func = new Functions();
    

    public String login() throws SQLException, ClassNotFoundException {
        FacesContext context = FacesContext.getCurrentInstance();
        String username = func.getParameterByName("username");
        String password = func.getParameterByName("password");
        boolean valid = func.checkLogin(username, password);
        if(valid) 
        	//return "management/configvideo.xhtml";
        	return "management/home.xhtml";
        else {
            context.addMessage("loginForm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid, please try again!", ""));
            return null;
        }
    }
    
    public String login2() {
        
        return "video";
       
    }
    

    public String logout() {
        HttpSession session = func.getSession();
        session.invalidate();
        return "login";
    }
    
    
	@PostConstruct
	public void init() {
		setItems(null);
		setVideo(null);
		// searc();
		//connect();
		
		//delete();
	}
	public void setItems(List<String> itemss) {
		itemss = new ArrayList<String>();
		itemss.add("shirt");
		itemss.add("skirt");
		itemss.add("trouser");
		itemss.add("daniel");
		itemss.add("trouser");
		itemss.add("daniel");
		itemss.add("daniel");
		itemss.add("trouser");
		itemss.add("daniel");
		this.items = itemss;
	}
	
	public void setVideo(List<Multimedia> video) {
		video = new ArrayList<Multimedia>();
		/*e = new Multimedia(10, "000.mp4", "http://10.1.0.1");
		video.add(e);
		e = new Multimedia(11, "001.mp4", "http://10.1.0.1");
		video.add(e);
		e = new Multimedia(12, "003.mp4", "http://10.1.0.1");
		video.add(e);
		e = new Multimedia(12, "003.mp4", "http://10.1.0.1");
		video.add(e);
		e = new Multimedia(10, "000.mp4", "http://10.1.0.1");
		video.add(e);
		e = new Multimedia(11, "001.mp4", "http://10.1.0.1");
		video.add(e);
		e = new Multimedia(12, "003.mp4", "http://10.1.0.1");
		video.add(e);
		e = new Multimedia(12, "003.mp4", "http://10.1.0.1");
		video.add(e);*/

		this.video = video;
	}
	
	 public void delete()
	    {
	    	//searc();
	    	System.out.println("delete butonn");
	    }
}
