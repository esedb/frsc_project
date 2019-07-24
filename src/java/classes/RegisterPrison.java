/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Ese
 */
@ManagedBean(name="new_prison", eager=true)
@SessionScoped
public class RegisterPrison implements Serializable{
    
    String name_of_prison;
    long pri_reg_no;
    String gender="Male";
    String full_address;
    Date open_date;
    long contact_no;
    long reg_no;
    long capacity;

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    Set<String> lists;

    public Set<String> getLists() {
        return lists;
    }
  
    @PostConstruct
    public void init(){
     
        
     lists = new LinkedHashSet<String>(Arrays.asList("Male", "Female"));   
     
    }
    public void sexChanged(ValueChangeEvent event) {
        gender=event.getNewValue().toString();
    }

    public long getReg_no() {
        return reg_no;
    }

    public void setReg_no(long reg_no) {
        this.reg_no = reg_no;
    }
    

    public String getName_of_prison() {
        return name_of_prison;
    }

    public void setName_of_prison(String name_of_prison) {
        this.name_of_prison = name_of_prison;
    }

    public long getPri_reg_no() {
        return pri_reg_no;
    }

    public void setPri_reg_no(long pri_reg_no) {
        this.pri_reg_no = pri_reg_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public Date getOpen_date() {
        return open_date;
    }

    public void setOpen_date(Date open_date) {
        this.open_date = open_date;
    }

    public long getContact_no() {
        return contact_no;
    }

    public void setContact_no(long contact_no) {
        this.contact_no = contact_no;
    }
    
    public String subMit(){
        try{
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
            PreparedStatement stmt=con.prepareStatement("INSERT INTO prison_dbase.prison_struc (name_of_prison, prison_reg_no, "
                    + "contact, gender, date_created, address, capacity)" +
"	VALUES (?, ?, ?, ?, ?, ?, ?)");
               
            stmt.setString(1, name_of_prison);
            stmt.setLong(2, reg_no);
            stmt.setLong(3, contact_no);
            stmt.setString(4, gender);
            stmt.setString(5, open_date.toString());
            stmt.setString(6, full_address);
            stmt.setLong(7, capacity);
            stmt.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "reg_prison";
        }
        return "home";
    }

    
    public RegisterPrison() {
    }
    
}
