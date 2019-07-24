/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;

/**
 *
 * @author Ese
 */
public class PrisonerData {
    String firstname;
    String othername;
    String serial_id;
    long id;
    String img_url;
    String date_in;
    String date_out;
    String crime;
    String perm_address;

    public PrisonerData(String firstname, String othername, String serial_id, 
            long id, String img_url, String date_in, String date_out, String crime, String perm_address) {
        int i=0;
        
        this.firstname = firstname;
        this.othername = othername;
        this.serial_id = serial_id;
        this.id = id;
        
        this.img_url = img_url;
        this.date_in = date_in;
        this.date_out = date_out;
        this.crime = crime;
        this.perm_address = perm_address;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }
    

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDate_in() {
        return date_in;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    public String getDate_out() {
        return date_out;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getPerm_address() {
        return perm_address;
    }

    public void setPerm_address(String perm_address) {
        this.perm_address = perm_address;
    }
}
