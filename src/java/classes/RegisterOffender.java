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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;

/**
 *
 * @author Ese
 */
@ManagedBean(name="offender", eager=true)
@SessionScoped
public class RegisterOffender implements Serializable{
     String platenumber;
     String crime_committed;
     Date date_of_crime;
     Date birth_date;
     String firstname;
     String othername;
     String national_id;
     
     String sex;
     String crime;
     String lga;
     String address;
     
     String selectedsex="Male";
     private Part file; 
    private String image_url;

    public int getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }
     int phone_no;
     String state_of_origin;

    public String getOfficer_name() {
        return officer_name;
    }

    public void setOfficer_name(String officer_name) {
        this.officer_name = officer_name;
    }

    public String getOfficer_Id() {
        return officer_Id;
    }

    public void setOfficer_Id(String officer_Id) {
        this.officer_Id = officer_Id;
    }
     String officer_name;
     String officer_Id;
     String officer_rank;

    public String getOfficer_rank() {
        return officer_rank;
    }

    public void setOfficer_rank(String officer_rank) {
        this.officer_rank = officer_rank;
    }
     
     

    public String getState_of_origin() {
        return state_of_origin;
    }

    public void setState_of_origin(String state_of_origin) {
        this.state_of_origin = state_of_origin;
    }
    
    private LinkedHashSet<String> lists;
    private String ser_id;

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }

    public String getCrime_committed() {
        return crime_committed;
    }

    public void setCrime_committed(String crime_committed) {
        this.crime_committed = crime_committed;
    }

    public Date getDate_of_crime() {
        return date_of_crime;
    }

    public void setDate_of_crime(Date date_of_crime) {
        this.date_of_crime = date_of_crime;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
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

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSelectedsex() {
        return selectedsex;
    }

    public void setSelectedsex(String selectedsex) {
        this.selectedsex = selectedsex;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public LinkedHashSet<String> getLists() {
        return lists;
    }

    public void setLists(LinkedHashSet<String> lists) {
        this.lists = lists;
    }
     
    
    public RegisterOffender(){
        
    }
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
            return null;
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
        String hash=date + "" + i + "PR";
        this.ser_id=hash;
       


    }
    public String  getSer_id()
    {
        setSer_id();
        return ser_id;
    }
    
    public String subMit(){
        try{
            System.out.println("who aaa......");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/frs_dbase", "root", "password@1");
             PreparedStatement statement=con.prepareStatement("INSERT INTO frs_dbase.offender (firstname, othername, "
                     + "national_id, phone_no, sex, state_of_origin, "
                     + "lga, birth_date, perm_address, officer_name, "
                     + "officer_rank, officer_id, crime_committed, "
                     + "date_of_entry, plate_number, serial_id, image_url) \n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
             System.out.println("firstname is " + firstname);
             statement.setString(1, firstname);
             statement.setString(2, othername);
             statement.setString(3, national_id);
             statement.setInt(4, phone_no);
             statement.setString(5, selectedsex);
             statement.setString(6, state_of_origin);
             statement.setString(7, lga);
             statement.setString(8, birth_date.toString());
             statement.setString(9, address);
             statement.setString(10, officer_name);
             statement.setString(11, officer_rank);
             statement.setString(12, officer_Id);
             statement.setString(13, crime);
             statement.setString(14, date_of_crime.toString());
             statement.setString(15, platenumber);
             statement.setString(16, getSer_id());
             statement.setString(17, saveFile());
             int i=statement.executeUpdate();
             if(i==1)
             {
                 System.out.println("Operation successful");
                 
                 return "/print_details.xhtml";
             }   
             
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
           return "reg_offender.xhtml";
        }
        return "reg_offender.xhtml";
    }
    
    public String updateDetails()
    {
        try{
            System.out.println("Update Command..... ");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/frs_dbase", "root", "password@1");
             PreparedStatement statement=con.prepareStatement("UPDATE frs_dbase.offender SET firstname=?, othername=?, "
                     + "national_id=?, phone_no=?, sex=?, state_of_origin=?, "
                     + "lga=?, birth_date=?, perm_address=?, officer_name=?, "
                     + "officer_rank=?, officer_id=?, crime_committed=?, "
                     + "date_of_entry=?, plate_number=?, serial_id=?, image_url=? \n" +
                        "where plate_number=?");
             System.out.println("firstname is " + firstname);
             statement.setString(1, firstname);
             statement.setString(2, othername);
             statement.setString(3, national_id);
             statement.setInt(4, phone_no);
             statement.setString(5, selectedsex);
             statement.setString(6, state_of_origin);
             statement.setString(7, lga);
             statement.setString(8, birth_date.toString());
             statement.setString(9, address);
             statement.setString(10, officer_name);
             statement.setString(11, officer_rank);
             statement.setString(12, officer_Id);
             statement.setString(13, crime);
             statement.setString(14, date_of_crime.toString());
             statement.setString(15, platenumber);
             statement.setString(16, getSer_id());
             statement.setString(17, saveFile());
             statement.setString(18, platenumber);
             int i=statement.executeUpdate();
             if(i==1)
             {
                 System.out.println("Operation successful");
                 
                 return "/print_details.xhtml";
             }   
             
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
           return "/edit_records";
        }
        return "/edit_records";
        
    }
    
    
}
