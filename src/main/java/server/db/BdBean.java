/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.db;

import server.obj.DeviceModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.h2.jdbc.JdbcSQLException;

/**
 *
 * @author User
 */
public class BdBean 
{
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
  
    public BdBean() {  
        this.createSchema();
        this.createTable();
    }
    
    
    private String createSchema()
    {   
        try 
        {
            con = ConnectionH2.getConnection();         
            pstm = con.prepareStatement("CREATE SCHEMA IF NOT EXISTS device_location");                   
            pstm.executeUpdate();
           
            return "Schema 'device' created";
        }
        catch(JdbcSQLException e)
        {  
            e.printStackTrace();
            return "Schema already exists";            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;           
        }     
      
    }  
    
    private String createTable()
    {        
        try 
        {
            con = ConnectionH2.getConnection();         
            pstm = con.prepareStatement("CREATE TABLE IF NOT EXISTS device_location.gateway "
                    + "(id BIGINT AUTO_INCREMENT PRIMARY KEY, longitude DECIMAL(9, 7), latitude DECIMAL(10, 7))");                  
            pstm.executeUpdate();
            
         
    
            return "'Gateway' table created";
        }
        catch(JdbcSQLException e)
        {  
            e.printStackTrace();
            return "Table already exists";            
        }      catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;           
        }  
    } 
    
    public DeviceModel selectOne(String id) { 
        ResultSet rs;
        DeviceModel device;
        try 
        {
            con = ConnectionH2.getConnection();         
            pstm = con.prepareStatement("SELECT * FROM device_location.gateway WHERE id=?"); 
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            
            if(rs.next())
            {
                device = new DeviceModel();
                device.setId(rs.getLong("id"));
                device.setLatitude(rs.getFloat("latitude"));
                device.setLongitude(rs.getFloat("longitude"));
                return device;
            }
            else
            {
                return null;
            } 
        }
        catch(JdbcSQLException e)
        {  
            e.printStackTrace();
            return null;            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;           
        } 
    }
    
    public ArrayList<DeviceModel> selectAll()
    { 
        ResultSet rs;
        ArrayList<DeviceModel> ar = new ArrayList();
        try 
        {
            con = ConnectionH2.getConnection();         
            pstm = con.prepareStatement("SELECT * FROM device_location.gateway");
            rs = pstm.executeQuery(); 
            
            while(rs.next())
            {
                DeviceModel dm = new DeviceModel();
                dm.setId(rs.getLong("id"));
                dm.setLatitude(rs.getFloat("latitude"));
                dm.setLongitude(rs.getFloat("longitude")); 
                ar.add(dm);
            } 
            return ar;
        }
        catch(JdbcSQLException e)
        {  
            e.printStackTrace();
            return null;            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;           
        }    
    }
    
    public boolean delete(String id)
    {      
        try 
        {
            con = ConnectionH2.getConnection();         
            pstm = con.prepareStatement("DELETE FROM device_location.gateway WHERE id=?"); 
            pstm.setString(1, id);
            int r = pstm.executeUpdate();
            
            if(r>=1)
            {                
                return true;
            }
            else
            {
                return false;
            }            
        }       
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;           
        }   
    }
    
    public boolean insert(DeviceModel deviceModel)
    {     
        try 
        {
            con = ConnectionH2.getConnection();         
            pstm = con.prepareStatement("INSERT INTO device_location.gateway (latitude, longitude) VALUES(?, ?)"); 
            pstm.setFloat(1, deviceModel.getLatitude());
            pstm.setFloat(2, deviceModel.getLongitude());
            int r = pstm.executeUpdate();
            
           
        
            if(r==1)
            {
               return true; 
            }
            else
            {
                return false;
            }
        }        
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;           
        }  
    } 
    
    public boolean update(DeviceModel deviceModel)
    {        
        try 
        {
            con = ConnectionH2.getConnection();         
            pstm = con.prepareStatement("UPDATE device_location.gateway SET latitude=?, longitude=? WHERE id=?"); 
            pstm.setFloat(1, deviceModel.getLatitude());
            pstm.setFloat(2, deviceModel.getLongitude());
            pstm.setLong(3, deviceModel.getId());
            int r = pstm.executeUpdate();
            
            if(r==1)
            {
               return true; 
            }
            else
            {
                return false;
            }
        }        
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;           
        }    
    }
    
}
