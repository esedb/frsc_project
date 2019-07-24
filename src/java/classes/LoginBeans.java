package classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Ese
 */
@ManagedBean(name="login_b", eager=true)
@RequestScoped
public class LoginBeans implements Serializable{
    String dbfirstname;
    String dbpassword;
    
    String successful;

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }
    
    String login_name;
    String login_password;
    

    
    public String subMit(){
        System.out.println("submit ran");
        System.out.println("urser name is " + login_name);
        System.out.println("Password is " + login_password);
         try{
             String sql = "SELECT * from officers WHERE login_name=? and login_password=?";
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/frs_dbase", "root", "password@1");
            PreparedStatement statement=con.prepareStatement(sql);
            
            statement.setString(1, login_name);
            statement.setString(2, login_password);
            ResultSet rs=statement.executeQuery();
            System.out.println("successful");
            while(rs.next())
            {   System.out.println("rs run ..... ");            
                dbfirstname= rs.getString("login_name");
                dbpassword=rs.getString("login_password");
                
                System.out.println("login name is " + rs.getString("login_name"));
                
                
            }
            
            if (getLogin_name().equalsIgnoreCase(dbfirstname) && getLogin_password().equalsIgnoreCase(dbpassword) ) {
                return "/reg_offender";                
           } 
        }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
       
        
return "/home";
    }
        
    
    
    public LoginBeans() {
             
        
    }
    
}
