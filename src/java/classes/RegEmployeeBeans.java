/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;

/**
 *
 * @author Ese
 */
@ManagedBean(name="reg_off", eager=true)
@SessionScoped
public class RegEmployeeBeans implements Serializable{
    Part file;
    String firstname;
    String othername;
    String login_name;
    String login_password;
    String national_id;
    String rank;
    long phone_number;
    String sex;
    Date birth_date;
    Date date_of_emp;
    
    String selectedsex="Male";
    private String image_url;
    private String ser_id;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

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

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getDate_of_emp() {
        return date_of_emp;
    }

    public void setDate_of_emp(Date date_of_emp) {
        this.date_of_emp = date_of_emp;
    }

    public String getSelectedsex() {
        return selectedsex;
    }

    public void setSelectedsex(String selectedsex) {
        this.selectedsex = selectedsex;
    }

    public LinkedHashSet<String> getLists() {
        return lists;
    }

    public void setLists(LinkedHashSet<String> lists) {
        this.lists = lists;
    }
    private LinkedHashSet<String> lists;
    @PostConstruct
    public void init(){
        
     lists = new LinkedHashSet<String>(Arrays.asList("Male", "Female")); 
     
    }
    public void sexChanged(ValueChangeEvent event) {
        selectedsex=event.getNewValue().toString();
    }
    
    public String saveFile(){
        File fl;
        Date namedate=new Date();
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(today);
        DATE_FORMAT = new SimpleDateFormat("ddMMyySS");
        date = DATE_FORMAT.format(today);
        
        String nmd=namedate.toString().replace(" ", "").replace(":","");
        String filename=file.getName();
        System.out.println("file name is " + file.getName());
        
        if(filename.endsWith(".jpg")){
            fl=new File("C:\\images_im\\" + firstname + date+".jpg");
        }
        else if(filename.endsWith(".png")){
            fl=new File("C:\\images_im\\" + firstname + date+".png");
        }
        else{
            fl=new File("C:" + File.separator + "images_im" + File.separator + firstname + date+".jpg");
            System.out.println("inside else run");
            System.out.println(fl.toString());
        }
        System.out.println("after else run");
        try{
            if(!fl.exists())
            {            
                fl.createNewFile();
            }
            
                BufferedInputStream bin=new BufferedInputStream(file.getInputStream());
                Files.copy(bin, fl.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("this part of the program ran");
                image_url=fl.getName();
                
                
                
                
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return fl.getName();
    }
    
    public void setSer_id()
    {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(today);
        DATE_FORMAT = new SimpleDateFormat("ddMMyySS");
        date = DATE_FORMAT.format(today);
        int i=(int)(1000*Math.random());
        String hash=date + "" + i + "FRS";
        this.ser_id=hash;
       


    }
    public String  getSer_id()
    {
        setSer_id();
        return ser_id;
    }
    
    public String subMit()
    {
        String img_url=saveFile();
        try{
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/frs_dbase", "root", "password@1");
            PreparedStatement statement=con.prepareStatement("INSERT INTO frs_dbase.officers (image_url, `ser_Id`,"
                    + " firstname, othername, login_name, login_password, "
                    + "national_id, rank, phone_no, sex, birthdate, date_of_emp) \n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
            statement.setString(1, img_url);
            statement.setString(2, getSer_id());
            statement.setString(3, firstname);
            statement.setString(4, othername);
            statement.setString(5, login_name);
            statement.setString(6, login_password);
            statement.setString(7, national_id);
            statement.setString(8, rank);
            statement.setLong(9, phone_number);
            statement.setString(10, selectedsex);
            statement.setString(11, birth_date.toString());
            statement.setString(12, date_of_emp.toString());
            
            int i=statement.executeUpdate();
            if(i==1)
            {
                System.out.println("Operation successful");
                return "/home";
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
        return "/reg_employee";
    }
  
    public RegEmployeeBeans() {
    }
    
}
