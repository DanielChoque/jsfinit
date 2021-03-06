package com.sakadream.jsf.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.primefaces.model.UploadedFile;

/*import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.util.EscapeUtils;*/

@ManagedBean
@NamedEvent
@RequestScoped
public class FileUploadView {
	private final String urltes="http://10.1.26.93:39384/test/listar";
	//private UploadedFile file;
    //private UploadedFiles files;
    //private String dropZoneText = "Drop zone p:inputTextarea demo.";
    
    private UploadedFile file;
    //private UploadedFiles files;
    private String dropZoneText = "Drop zone p:inputTextarea demo.";

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        String output2="";
		try {
	         
	        URL url = new URL("http://10.1.26.162:8090/uploadMultipleFiles");
	        //URL url=new URL("http://localhost/pru/u.php");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "multipart/form-data");
	 
	        String input = "";/*"{ \"nit\" : \"\", \"login\" : \""
	        		+ nombreS
	        		+ "\", \"password\" : \""
	        		+ passwordS
	        		+ "\", \"tipoUsuarioId\": 861}";*/
	 
    
    
    
	        System.out.println("01 Before  from Server .... \n");
	       OutputStream os = conn.getOutputStream();
	        byte[] bytes = file.getContents();
	        os.write(bytes);
	        os.flush();
	        System.out.println("get set:" + conn.getResponseCode());
	 
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                + conn.getResponseCode());
	        }
	 
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream()), "UTF-8"));
	 
	        String output="";
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	        	output2=output;
	            System.out.println("output:"+output);
	        }
	 
	        conn.disconnect();
	        
	        System.out.println("output2:" +output2);
	       // token(output2);
	        
	 
	      } catch (MalformedURLException e) {
	 
	        e.printStackTrace();
	 
	      } catch (IOException e) {
	 
	        e.printStackTrace();
	 
	     }
    	    	
    	System.out.println("sen video");    	
    
    }
   
    public void upoone() {
    	//byte[] bytes = file.getContents();
    	HttpEntity entity = MultipartEntityBuilder.create()
                .addPart("file", new FileBody(new File("d:\\Recurso2.png")))
                .build();

		HttpPost request = new HttpPost("http://10.1.26.162:8090/uploadMultipleFiles");
		request.setEntity(entity);
		
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpResponse response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
/*//////////////////////////////////*/
    /*public void uploadMultiple() {
        if (files != null) {
            for (UploadedFile f : files.getFiles()) {
                FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
*/
   /* public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }*/

    /*public void handleFileUploadTextarea(FileUploadEvent event) {
        String jsVal = "PF('textarea').jq.val";
        String fileName = EscapeUtils.forJavaScript(event.getFile().getFileName());
        PrimeFaces.current().executeScript(jsVal + "(" + jsVal + "() + '\\n\\n" + fileName + " uploaded.')");
    }*/

    /*public void handleFilesUpload(FilesUploadEvent event) {
        for (UploadedFile f : event.getFiles().getFiles()) {
            FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }*/

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /*public UploadedFiles getFiles() {
        return files;
    }*/

    /*public void setFiles(UploadedFiles files) {
        this.files = files;
    }*/

    public String getDropZoneText() {
        return dropZoneText;
    }

    public void setDropZoneText(String dropZoneText) {
        this.dropZoneText = dropZoneText;
    }

    

   /* public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
*/
    /*public void uploadMultiple() {
    	
    	
        if (files != null) {
            for (UploadedFile f : files.getFiles()) {
            	sendUrl(f);
                FacesMessage message = new FacesMessage("Cargado", f.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            
        }
    }*/
    
    

    /*public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }*/

    /*public void handleFileUploadTextarea(FileUploadEvent event) {
        String jsVal = "PF('textarea').jq.val";
        String fileName = EscapeUtils.forJavaScript(event.getFile().getFileName());
        PrimeFaces.current().executeScript(jsVal + "(" + jsVal + "() + '\\n\\n" + fileName + " uploaded.')");
    }*/

   /* public void handleFilesUpload(FilesUploadEvent event) {
        for (UploadedFile f : event.getFiles().getFiles()) {
            FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public String getDropZoneText() {
        return dropZoneText;
    }

    public void setDropZoneText(String dropZoneText) {
        this.dropZoneText = dropZoneText;
    }
    */
    /*public void sendUrl(UploadedFile file) {
    	
    	String output2="";
		try {
	         
	        URL url = new URL("http://10.1.26.162:8090/uploadMultipleFiles");
	        //URL url=new URL("http://localhost/pru/u.php");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	 
	        String input = "";/*"{ \"nit\" : \"\", \"login\" : \""
	        		+ nombreS
	        		+ "\", \"password\" : \""
	        		+ passwordS
	        		+ "\", \"tipoUsuarioId\": 861}";*/
	 
    
    
    
    
	     /*   OutputStream os = conn.getOutputStream();
	        byte[] bytes = file.getContent();
	        os.write(bytes);
	        os.flush();
	        System.out.println("get set:" + conn.getResponseCode());
	 
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                + conn.getResponseCode());
	        }
	 
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream()), "UTF-8"));
	 
	        String output="";
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	        	output2=output;
	            System.out.println("output:"+output);
	        }
	 
	        conn.disconnect();
	       // token(output2);
	        
	 
	      } catch (MalformedURLException e) {
	 
	        e.printStackTrace();
	 
	      } catch (IOException e) {
	 
	        e.printStackTrace();
	 
	     }
    	    	
    	System.out.println("sen video");    	
    }
 
   */

}
