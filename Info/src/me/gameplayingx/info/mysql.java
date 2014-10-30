package me.gameplayingx.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;


public class mysql
{
  public static Connection con;
  
  public static void connect()
  {
	
    try
    {
    	

    	

    	
    	
     
     con = DriverManager.getConnection("jdbc:mysql://îp:3306/datenbank", "benutzer", "pw");
    
      Bukkit.getConsoleSender().sendMessage("[Info] Mysql Verbindung hergestellt!");
    		
    	
    }
    catch (SQLException exc)
    {
      Bukkit.getConsoleSender().sendMessage("[Info] Fehler beim Aufbau der Mysql Verbindung!");
      Bukkit.getConsoleSender().sendMessage(exc + "");
    }
  }
  
  public static void close()
  {
    try
    {
      if (con != null) {
        con.close();
      }
    }
    catch (Exception localException) {}
  }
  
  public static Statement ste()
  {
	  checkc();
    Statement statement = null;
    try
    {
      statement = con.createStatement();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return statement;
  }
  
  public int check(String qry)
  {
	  checkc();
    int check = 0;
    try
    {
      Statement st = mysql.con.createStatement();
      ResultSet rs = st.executeQuery("SELECT count(*) FROM " + qry);
      while (rs.next()) {
        check = rs.getInt(1);
      }
      st.close();
      rs.close();
    }
    catch (Exception localException) {}
    return check;
  }
  
  public void befehl(String b)
  {
	  checkc();
    try
    {
    	Statement st = con.createStatement();
    	st.executeUpdate(b);
    	st.close();
      
    }
    catch (Exception exc)
    {
      System.out.println("[Info] ---BEGINN DER ERRORMELDUNG---");
      System.out.println("-----------------------------------------------");
      System.out.println("Befehl: " + b);
      System.out.println("-----------------------------------------------");
      System.out.println("Die ErrorMeldung ist folgende:");
      System.out.println("-----------------------------------------------");
      System.out.println(exc);
      System.out.println("-----------------------------------------------");
      System.out.println("[Info] ---ENDE DER ERRORMELDUNG---");
    }
  }
  
  
  public static void checkc(){
	  PreparedStatement st = null;
	  ResultSet valid = null;
	  try
	  {
		  st = con.prepareStatement("SELECT 1 FROM Dual");
		  valid = st.executeQuery();
		  if(valid.next()){
			  
		  }
		
	  }
	  catch(SQLException e){
		  System.out.println("Mysql Verbindung ist nicht mehr aktiv. Neu verbinden...");
		
		  connect();
	  }
	  finally
	  {
		
		  try {
			
			  if(!(valid == null)){
			  
				valid.close();
			  }
			  
			  if(!(st == null)){
			 
				st.close();
			  }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }
}

