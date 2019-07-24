package classes;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;

/**
 *
 * @author Ese
 */
@ManagedBean(name="prisoner", eager=true)
@SessionScoped
public class PrisonerRegBeans implements Serializable{

    
    private Part file;    
    
    public String other_name;
    long id_card_no;
    
    String title;
    String sex;
    
    public String marital_status="Single";
    long phone_no;
    String next_of_kin;
    long phone_of_kin;
    String perm_address;
    String image_url;
    String edu_qual;
    String state_of_origin;
    String lga;
    String religion;
    
    String relay_convicts;
    
    Date dateofentry;
    Date dateofexit;
    Date date_of_birth;
    
    private String ser_id;
    
    String firstname;
    String crime;
    
    String selectedsex="Male";
    public Set<String> lists;
    
    String prison;
    
    public Set<String> mstatus;
    
    public Set<String> allprison;

    public Set<String> getAllprison() {
        return allprison;
    }       

    public String getPrison() {
        return prison;
    }

    public void setPrison(String prison) {
        this.prison = prison;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setId_card_no(long id_card_no) {
        this.id_card_no = id_card_no;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public void setPhone_of_kin(long phone_of_kin) {
        this.phone_of_kin = phone_of_kin;
    }
    
    
    public Date getDateofentry() {
        return dateofentry;
    }

    public void setDateofentry(Date dateofentry) {
        this.dateofentry = dateofentry;
    }

    public Date getDateofexit() {
        return dateofexit;
    }

    public void setDateofexit(Date dateofexit) {
        this.dateofexit = dateofexit;
    }

    public Set<String> getMstatus() {
        return mstatus;
    }

    public Set<String> getLists() {
        return lists;
    }
    
    public PrisonerRegBeans() {
    }
    
    @PostConstruct
    public void init(){
     mstatus=new LinkedHashSet<String>(Arrays.asList("Singled", "Married"));
        
     lists = new LinkedHashSet<String>(Arrays.asList("Male", "Female")); 
     allprison=new LinkedHashSet<>();
     try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
            PreparedStatement stmt=con.prepareStatement("select name_of_prison from prison_struc");
            ResultSet rs=stmt.executeQuery();){
         while(rs.next()){
             allprison.add(rs.getString("name_of_prison"));
                     
         }
            
            
     }
     catch(Exception ex){
         ex.printStackTrace();
     }
     
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
    
    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
    public void mStatusChanged(ValueChangeEvent event){
        marital_status=event.getNewValue().toString();
        
    }
    
    public void prisonChanged(ValueChangeEvent event){
        prison=event.getNewValue().toString();
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
        this.image_url = image_url;
    }

    public String getEdu_qual() {
        return edu_qual;
    }

    public void setEdu_qual(String edu_qual) {
        this.edu_qual = edu_qual;
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

    public String getCrime() {        
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getRelay_convicts() {
        return relay_convicts;
    }

    public void setRelay_convicts(String relay_convicts) {
        this.relay_convicts = relay_convicts;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
        saveFile();
    }
    
    public void saveFile(){
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
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
            PreparedStatement stmt=con.prepareStatement("INSERT INTO prison_dbase.prisoner (serial, national_id, first_name, other_name, title, sex, marital_status, date_of_entry, date_of_exit, phone_number,"
                    + " next_of_kin, name_of_kin, phone_of_kin, perm_address, image_url, state_of_origin,"
                    + " lga, religion, crime, rel_convicts, date_of_birth, name_of_prison)" +
"           VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
            stmt.setString(1, getSer_id());
            stmt.setLong(2, id_card_no);
            stmt.setString(3, firstname);
            stmt.setString(4, other_name);
            stmt.setString(5, title);
            stmt.setString(6, selectedsex);
            stmt.setString(7, marital_status);
            stmt.setString(8, dateofentry.toString());
            stmt.setString(9, dateofexit.toString());
            stmt.setLong(10, phone_no);
            stmt.setString(11, next_of_kin);
            stmt.setString(12, next_of_kin);
            stmt.setLong(13, phone_of_kin);
            stmt.setString(14, perm_address);
            stmt.setString(15, image_url);
            stmt.setString(16, state_of_origin);
            stmt.setString(17, lga);
            stmt.setString(18, religion);
            stmt.setString(19, crime);
            stmt.setString(20, relay_convicts);
            stmt.setString(21, date_of_birth.toString());
            stmt.setString(22, prison);
  
            
            int result=stmt.executeUpdate();
            if(result==1)
            {
                System.out.println("who do");
                return "home";
            }
                        
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "prison_reg";
            
        }
         return "prison_reg";
     }
    
    
}
