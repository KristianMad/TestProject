package com.ibm.project.TestProject;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.ibm.project.TestProject.beans.Proveedor;
import com.ibm.project.TestProject.utils.ConnectionUtils;
import com.ibm.project.TestProject.utils.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {
    	String txtDir = System.getProperty("user.home");
//    	Session session = HibernateUtil.getSessionFactory().openSession();
    	ConnectionUtils cnx = new ConnectionUtils();
    	
        ResultSet resultSet = null;

        try (Connection connection = cnx.conectar();
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement
        	try {
        		if(args[0]!=null) {
        			Integer cliente = Integer.valueOf(args[0]);
                    String selectSql = "SELECT p.* FROM test.proveedores p WHERE cliente = '"+cliente+"'";
                    resultSet = statement.executeQuery(selectSql);
    				
                    if(resultSet.next()==false){
                    	System.out.println("Client doesn't have providers...");
                    }else{
                    	PrintWriter out = new PrintWriter(txtDir+"\\output-file.txt");
                    	do {
                    		out.println(resultSet.getString(1)+";"+resultSet.getString(2)+";"+resultSet.getString(3)+";"+resultSet.getString(4));
                    	} while (resultSet.next());
                    	out.close();
                    }
        		}
        	}catch(IndexOutOfBoundsException e) {
        		System.out.println("Not Parameters, try again...");
        		e.printStackTrace();
        	} catch (FileNotFoundException e) {
        		System.out.println("Problems with file, try again ...");
        		e.printStackTrace();
        	}

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    	
//    	session.beginTransaction();
//    	Proveedor p1 = createProveedor(1, "Coca-cola",5);
//    	Proveedor p2 = createProveedor(2, "Pepsi", 5);
//    	Proveedor p3 = createProveedor(3, "Redbull", 6);
//
//    	session.save(p1);
//    	session.save(p2);
//    	session.save(p3);
//    	session.getTransaction().commit();

//    	Proveedor res = session.get(Proveedor.class, args[0]);
//    	List<Proveedor> proveedoresLst = loadAllData(Proveedor.class, session);
//    	try {
//    		if(args[0]!=null) {
//    			Integer cliente = Integer.valueOf(args[0]);
//    			List<Proveedor> res = proveedoresLst.parallelStream().filter(prv -> prv.getCliente()==cliente.intValue()).collect(Collectors.toList());
//    			if(res!=null && !res.isEmpty()) {
//    				PrintWriter out = new PrintWriter(txtDir+"\\output-file.txt");
//    				res.forEach(line -> out.println(line.toString()));
//    				out.close();
//    			}
//    			else {
//    				System.out.println("Not Found Client...");
//    			}
//    		}
//    	}catch(IndexOutOfBoundsException e) {
//    		System.out.println("Not Parameters, try again...");
//    		e.printStackTrace();
//    	} catch (FileNotFoundException e) {
//    		System.out.println("Problems with file, try again ...");
//    		e.printStackTrace();
//    	}
    }
    
    
    private static Proveedor createProveedor(int idProv, String name, int cliente) {
        java.util.Date d = new java.util.Date();
        return new Proveedor(idProv, name, new Date(d.getTime()), cliente);
    }
    
    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
      }
    
    
}
