package com.sakadream.jsf.func;

import com.sakadream.jsf.bean.Employee;
import com.sakadream.jsf.bean.Multimedia;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phan Ba Hai on 17/07/2017.
 */
public class Functions {
    private Connection conn;
    private List<Multimedia> video;
	private Multimedia e;

	 private Connection conn2;
    private void connect() throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
    	//Class.forName("com.mysql.jdbc.Driver");
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            //String username = "root";
            //String password = "";
            //String url = "jdbc:mysql://127.0.0.1:3306/sof305_offline";
        	String username = "sa";
            String password = "as";
            String url = "jdbc:sqlserver://10.1.26.93:1433;databaseName=SOF305_Offline";


            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://10.1.43.236:3306/sof305_offline";

            conn = DriverManager.getConnection(url, username, password);
        }
    }

    private void cleanConnection() throws SQLException {
        conn.close();
    }

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        boolean b = true;

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME LIKE ? AND PASSWORD LIKE ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) setSessionUsername(username);
        else b = false;

        cleanConnection();

        return b;
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    private void setSessionUsername(String username) {
        HttpSession session = getSession();
        session.setAttribute("username", username);
    }

    public List<Employee> showAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> listEmp = new ArrayList<Employee>();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getInt(6));
            listEmp.add(employee);
        }

        cleanConnection();

        return listEmp;
    }

    public Employee getEmployeeById(int id) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE ID = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                resultSet.getInt(6));

        cleanConnection();

        return employee;
    }

    public Employee getEmployeeByName(String name) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE FULLNAME LIKE ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                resultSet.getInt(6));

        cleanConnection();

        return employee;
    }

    public void addEmployee(String fullName, String address, String email, String phone, int salary)
            throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO EMPLOYEES" +
                "(FULLNAME, ADDRESS, EMAIL, PHONE, SALARY) " +
                "VALUES " +
                "(?, ?, ?, ?, ?)");
        preparedStatement.setNString(1, fullName);
        preparedStatement.setNString(2, address);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, salary);
        preparedStatement.execute();

        cleanConnection();
    }

    public void editEmloyee(int id, String fullName, String address, String email, String phone, int salary)
            throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE EMPLOYEES " +
                "SET FULLNAME = ?, ADDRESS = ?, EMAIL = ?, PHONE = ?, SALARY = ? " +
                "WHERE ID = ?");
        preparedStatement.setNString(1, fullName);
        preparedStatement.setNString(2, address);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, salary);
        preparedStatement.setInt(6, id);
        preparedStatement.execute();

        cleanConnection();
    }

    public void deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM EMPLOYEES WHERE ID = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        cleanConnection();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getParameterByName(String name) {
        HttpServletRequest req = getRequest();
        return req.getParameter(name);
    }
    
    public List<Multimedia> showAllEmployees2() throws SQLException, ClassNotFoundException {
        List<Employee> listEmp = new ArrayList<Employee>();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE EMPLOYEES.SALARY =  1000");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getInt(6));
            listEmp.add(employee);
        }
        cleanConnection();
        return connectionAC();
    }
    public List<Employee> showAllEmployees3() throws SQLException, ClassNotFoundException {
        List<Employee> listEmp = new ArrayList<Employee>();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE EMPLOYEES.SALARY =  1000");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getInt(6));
            listEmp.add(employee);
        }
        cleanConnection();
        connectionAC();
        return listEmp;
    }
    
    public List<Multimedia> connectionAC() {
    	List<Multimedia> multVideo= new ArrayList<Multimedia>();
    	System.out.println("Daniel inicio restdd");
		try {
			
			URL url = new URL("http://10.1.26.162:8090/r");
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if (urlConnection instanceof HttpURLConnection) {
				connection = (HttpURLConnection) urlConnection;
			} else {
				System.out.println("Please enter an HTTP URL.");
				return null;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String urlString = "";
			String current;
			while ((current = in.readLine()) != null) {
				urlString += current;
			}
		
			//stringJson(urlString);
			return multVideo=stringJson(urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		


		/*JSONObject myJson = new JSONObject(
				"{ \"number_list\": [ 1.9, 2.9, 3.4, 3.5 ], \"extra_data\": {}, \"name\": \"Carlos\", \"last_name\": \"Carlos\", \"bank_account_balance\": 20.2, \"age\": 21, \"is_developer\": true }");
		System.out.print(myJson.get("number_list"));
		video = new ArrayList<Multimedia>();
		e = new Multimedia(10L, "000.mp4", "http://10.1.0.1");
		video.add(e);*/
	}
    public List<Multimedia> stringJson(String st) {
    	List<Multimedia> multVideo= new ArrayList<Multimedia>();
    	JSONArray myArray =new JSONArray(st);
		JSONObject myJson;
		for (Object object : myArray) {
			myJson = new JSONObject(object.toString());
			System.out.println("name:"+myJson.get("name"));
			//System.out.println(object);
			int num=Integer.parseInt(myJson.get("num").toString());
			String name=(String)myJson.get("name");
			String url=(String)myJson.get("url");
			String size=(String)myJson.get("size");
			Multimedia mult = new Multimedia(num,name,url,size);
			multVideo.add(mult);
		}
    	return multVideo;    	
    }
    
    private void connect2() throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
    	//Class.forName("com.mysql.jdbc.Driver");
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            //String username = "root";
            //String password = "";
            //String url = "jdbc:mysql://127.0.0.1:3306/sof305_offline";
        	String username = "sa";
            String password = "as";
            String url = "jdbc:sqlserver://10.1.26.93:1433;databaseName=IQData";


            conn2 = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://10.1.43.236:3306/sof305_offline";

            conn2 = DriverManager.getConnection(url, username, password);
        }
    }

    private void cleanConnection2() throws SQLException {
        conn2.close();
    }
}
