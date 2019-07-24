package classes;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ese
 */
public class DsourceTest {
    public static void main(String args[]){
        
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/mysqltest");
            Connection con = ds.getConnection();
            PreparedStatement statement=con.prepareStatement("select * from personnel");
            ResultSet rs=statement.executeQuery();
            System.out.println("successful");
            while(rs.next())
            {
                System.out.println(rs.getString("lga"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }


    }
    
}
