/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.sql.DataSource;

/**
 *
 * @author Ese
 */

@ManagedBean(name="pers_reg", eager=true)
@SessionScoped
public class PersonnelRegBean implements Serializable{
        
    public String firtname;
    String login_pass;
    public String other_name;
    long id_card_no;

    
    public String date_of_birth;
    String title;
    String sex;
    public String date_of_employment;
    public String marital_status="Single";
    long phone_no;

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public void setPhone_of_kin(long phone_of_kin) {
        this.phone_of_kin = phone_of_kin;
    }
    String next_of_kin;
    long phone_of_kin;
    String perm_address;
    String image_url;
    String edu_qual;
    String specialty;
    String state_of_origin;
    String lga;
    String religion;
    String department;
    String rank;
    String selectedsex="Male";
    public Set<String> lists;
    private String ser_id;
    private Part file;
    Date empdate;
    Date birthdate;

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    String login_name;
    

    public Set<String> mstatus;


    public Date getEmpdate() {
        return empdate;
    }

    public void setEmpdate(Date empdate) {
        this.empdate = empdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    public Set<String> getLists() {
        return lists;
    }
    
    @PostConstruct
    public void init(){
        
     lists = new LinkedHashSet<String>(Arrays.asList("Male", "Female"));   
     mstatus=new LinkedHashSet<String>(Arrays.asList("Single", "Married"));
    }
    
    public void setId_card_no(long id_card_no) {
        this.id_card_no = id_card_no;
    }
    public Set<String> getMstatus() {
        return mstatus;
    }
    

    public String getSelectedsex() {
        return selectedsex;
    }

    public void setSelectedsex(String selectedsex) {
        this.selectedsex = selectedsex;
    }

    
    public void sexChanged(ValueChangeEvent event) {
        selectedsex=event.getNewValue().toString();
    }
    
    public void msChanged(ValueChangeEvent event){
        marital_status=event.getNewValue().toString();
       
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
        saveFile();
    }
    
    private DateTimeConverter convertDate;

    
    public String getFirtname() {
        return firtname;
    }

    public void setFirtname(String firtname) {
        this.firtname = firtname;
    }

    public String getLogin_pass() {
        return login_pass;
    }

    public void setLogin_pass(String login_pass) {
        this.login_pass = login_pass;
    }

    public String getOther_name() {
        return other_name;
    }

    public void setOther_name(String other_name) {
        this.other_name = other_name;
    }

    public long getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(int id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getDate_of_birth() {
        return birthdate.toString();
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = birthdate.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate_of_employment() {
        return empdate.toString();
    }

    public void setDate_of_employment(String date_of_employment) {
        this.date_of_employment = empdate.toString();
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }

    public String getNext_of_kin() {
        return next_of_kin;
    }

    public void setNext_of_kin(String next_of_kin) {
        this.next_of_kin = next_of_kin;
    }

    public long getPhone_of_kin() {
        return phone_of_kin;
    }

    public void setPhone_of_kin(int phone_of_kin) {
        this.phone_of_kin = phone_of_kin;
    }

    public String getPerm_address() {
        return perm_address;
    }

    public void setPerm_address(String perm_address) {
        this.perm_address = perm_address;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = getFirtname()+getSer_id();
    }

    public String getEdu_qual() {
        return edu_qual;
    }

    public void setEdu_qual(String edu_qual) {
        this.edu_qual = edu_qual;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getState_of_origin() {
        return state_of_origin;
    }

    public void setState_of_origin(String state_of_origin) {
        this.state_of_origin = state_of_origin;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
   

    public DateTimeConverter getConvertDate() {
        return convertDate;
    }
    
    public void setConvertDate(DateTimeConverter convertDate) {
        convertDate.setPattern("EEEEEEEE, MMM dd, yyyy");
        this.convertDate = convertDate;
        }

    
    public PersonnelRegBean() {
        
    }
    public void saveFile()
    {
        File fl;
        Date namedate=new Date();
        String nmd=namedate.toString().replace(" ", "").replace(":","");
        String filename=file.getName();
        System.out.println("file name is " + file.getName());
        
        if(filename.endsWith(".jpg")){
            fl=new File("C:\\images_im\\personnel\\" + firtname + nmd+".jpg");
        }
        else if(filename.endsWith(".png")){
            fl=new File("C:\\images_im\\personnel\\" + firtname + nmd+".png");
        }
        else{
            fl=new File("C:" + File.separator + "images_im" + File.separator + "personnel" + File.separator + firtname + nmd+".jpg");
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
                image_url=fl.toPath().toString();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public String regPersonnel(){
        return "Hello " + next_of_kin;
    }
     public void firtListener(ValueChangeEvent event) {
        firtname=event.getNewValue().toString();
    }
     public String ddname(){
         return firtname;
     }
     
    public void setSer_id()
    {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(today);
        DATE_FORMAT = new SimpleDateFormat("ddMMyySS");
        date = DATE_FORMAT.format(today);
        int i=(int)(1000*Math.random());
        String hash=date + "" + i;
        this.ser_id=hash;
       


    }
    public String  getSer_id()
    {
        setSer_id();
        return ser_id;
    }
    public void conForm(ActionEvent event){
        String found=null;
        String sql="SELECT * FROM PERSONNEL WHERE login_name=?";
               
        try{
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1, login_name);
        ResultSet rs=stmt.executeQuery();
        while(rs.next())
        {
            found=rs.getString("login_name");
            System.out.println("Found is " + found);
        }
        if(found!=null)
        {
            login_name=null;
            throw new AbortProcessingException("Chose another Login Name");
            
        }
        }
        catch(SQLException | AbortProcessingException ex)
        {
            ex.printStackTrace();
            
        }
        
    }
     public String subMit(){
         try{
            /*Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/mysqltest");
            Connection con = ds.getConnection();
                    */
             
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
            PreparedStatement stmt=con.prepareStatement("INSERT INTO prison_dbase.personnel (id, first_name, other_name, "
                    + "login_name, login_password, title, date_of_emp, sex, id_card_no, "
                    + "phone_no, marital_status, ext_of_kin, phone_of_kin, perm_address, "
                    + "name_of_kin, image_url, state_of_origin, religion, lga, department, speciality, rank, date_of_birth) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
            stmt.setString(1, getSer_id());
            stmt.setString(2, firtname);
            stmt.setString(3, other_name);
            stmt.setString(4, login_name);
            stmt.setString(5, login_pass);
            stmt.setString(6, title);
            stmt.setString(7, empdate.toString());
            stmt.setString(8, selectedsex);
            stmt.setLong(9, id_card_no);
            stmt.setLong(10, phone_no);
            stmt.setString(11, marital_status);
            stmt.setString(12, next_of_kin);
            stmt.setLong(13, phone_of_kin);
            stmt.setString(14, perm_address);
            stmt.setString(15, next_of_kin);
            stmt.setString(16, image_url);
            stmt.setString(17, state_of_origin);
            stmt.setString(18, religion);
            stmt.setString(19, lga);
            stmt.setString(20, department);
            stmt.setString(21, specialty);
            stmt.setString(22, rank);
            stmt.setString(23, birthdate.toString());
            
            int result=stmt.executeUpdate();
            if(result==1)
            {
                System.out.println("Execution was successful");
                return "home";
            }
                        
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "personnel_reg";
            
        }
         return "personnel_reg";
     }
}
