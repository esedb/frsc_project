package classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Ese on 12/2/2015.
 */
public class DataBase{
    

    /*String url, password, username;
    public DataBase (String username, String password, String url){
        this.username=username;
        this.password=password;
        this.url=url;
    }
    Properties properties=new Properties();
    public Connection getConnection() throws java.sql.SQLException
    {
        return DriverManager.getConnection( url,username, password);
    }
    @Override
    public void close() throws IOException {
        try{
            this.getConnection().close();
        }
        catch(Exception ex)
        {
            
        }
        
    }*/
    
    public static boolean createDataBase(){
        String sd="CREATE DATABASE IF NOT EXISTS prison_dbase;\n";
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password@1");
            PreparedStatement statment=con.prepareStatement(sd);
            statment.execute();
            
        }
            catch(Exception ex){
                    ex.printStackTrace();
                    return false;
                    }
        return true;
    }
    public static boolean createTable1()
    {
        if(createDataBase())
        {
            String sql="CREATE TABLE IF NOT EXISTS prisoner(\n" +
            "id int primary key,\n" +
            "serial int not null,\n" +
            "national_id varchar(200) not null,\n" +
            "first_name varchar(200) not null,\n" +
            "other_name varchar(200) not null, \n" +
            "title varchar(100),\n" +
            "sex varchar(100),\n" +
            "marital_status varchar(100),\n" +
            "date_of_entry varchar(100),\n" +
            "date_of_exit varchar(100),\n" +
            "phone_number int not null,\n" +
            "next_of_kin varchar(200),\n" +
            "name_of_kin varchar(200),\n" +
            "phone_of_kin int not null,\n" +
            "perm_address varchar(200),\n" +
            "image_url varchar(200),\n" +
            "state_of_origin varchar(100),\n" +
            "lga varchar(100),\n" +
            "religion varchar(100),\n" +
            "crime varchar(1000),\n" +
            "rel_convicts varchar(500));";
            
            try{
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
            PreparedStatement statment=con.prepareStatement(sql);
            statment.execute();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        }       
            return true;
        }
    public static boolean createTable2()
    {
        if(createDataBase())
        {
            String sql="CREATE TABLE IF NOT EXISTS personnel(\n" +
"id int primary key,\n" +
"first_name varchar(200) not null,\n" +
"other_name varchar(200) not null,\n" +
"longin_name varchar(200),\n" +
"login_password varchar(200),\n" +
"title varchar(100),\n" +
"date_of_emp varchar(100),\n" +
"sex varchar(100),\n" +
"id_card_no varchar(100),\n" +
"phone_no int not null,\n" +
"marital_status varchar(100),\n" +
"ext_of_kin varchar(200),\n" +
"phone_of_kin int not null,\n" +
"perm_address varchar(200),\n" +
"name_of_kin varchar(200),\n" +
"image_url varchar(200),\n" +
"state_of_origin varchar(100),\n" +
"religion varchar(100),\n" +
"lga varchar(100),\n" +
"department varchar(100),\n" +
"speciality varchar(100));";
            
            try{
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
            PreparedStatement statment=con.prepareStatement(sql);
            statment.execute();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        }       
            return true;
        
    }
    public static boolean dataFit()
    {
       if(createDataBase()&& createTable1() && createTable2())
       {
           return true;
       }
       return false;
    }
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password@1");
        
    }
    
       
    public static void main(String args[])
    {
        String sd="CREATE DATABASE IF NOT EXISTS prison_dbase;\n";
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password@1");
            PreparedStatement statment=con.prepareStatement(sd);
            statment.execute();
            
        }
            catch(Exception ex){
                    ex.printStackTrace();
                    }
        boolean createTable = dataFit();
        System.out.println("successful: " + createTable);
    }
        
        
        
    }
        
    

    
