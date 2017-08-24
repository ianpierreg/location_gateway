/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.db;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionH2
{
    private static Connection cn;   
    
    public static Connection getConnection()
    {
        try 
        {
            if (cn == null) 
            {
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
                Class.forName("org.h2.Driver");
                //cn = DriverManager.getConnection("jdbc:h2:C:\\Users\\User\\Documents\\NetBeansProjects\\h2test\\lib\\h2tst","sa","");  
                cn = DriverManager.getConnection("jdbc:h2:~/test","sa","");  
            } 
            return cn;
        }
        catch (Exception ex) 
        {
           ex.printStackTrace();
           throw new RuntimeException("Error", ex);
        }
         
    }
    
     static class MiShDwnHook extends Thread 
    {
        @Override
        public void run() 
        {
                try 
            {
                Connection cn = ConnectionH2.getConnection();
                cn.close();
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }
 
}
