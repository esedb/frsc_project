package classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Ese
 */
@ManagedBean(name="visitor", eager=true)
@SessionScoped
public class VisitorsBean implements  Serializable {
    
    String visitorname;
    long id_no;
    String relationship;
    Date vi_date;
    String serial_id;
    long phone_no;
    String name_of_prison;
    String sex="Male";
    String vi_address;

    public String getVi_address() {
        return vi_address;
    }

    public void setVi_address(String vi_address) {
        this.vi_address = vi_address;
    }
    
    public void serialChanged(ValueChangeEvent event) throws AbortProcessingException{
        String found=null;
       
        String newvalue=event.getNewValue().toString();
            try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
                PreparedStatement stmt=con.prepareStatement("select serial from prisoner where serial=?");)
                    {
                        stmt.setString(1, newvalue);
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next()){
                            found=rs.getString("serial");

                        }
            
            
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        if(found==null)
        {
            VisitorsBean visit= (VisitorsBean) FacesContext.getCurrentInstance().
            getExternalContext().getSessionMap().get("visitor");
            visit.setSerial_id("");
        }
        else{     
            serial_id=event.getNewValue().toString();
            }
        
    }

    
    Set<String> sexl;
    
    @PostConstruct
    public void init(){
     
        
     sexl = new LinkedHashSet<String>(Arrays.asList("Male", "Female"));   
     
    }
    
    public Set<String> getSexl() {
        return sexl;
    }
    public void sexChanged(ValueChangeEvent event) {
        sex=event.getNewValue().toString();
    }

    public String getVisitorname() {
        return visitorname;
    }

    public void setVisitorname(String visitorname) {
        this.visitorname = visitorname;
    }

    public long getId_no() {
        return id_no;
    }

    public void setId_no(long id_no) {
        this.id_no = id_no;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Date getVi_date() {
        return vi_date;
    }

    public void setVi_date(Date vi_date) {
        this.vi_date = vi_date;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public String getName_of_prison() {
        return name_of_prison;
    }

    public void setName_of_prison(String name_of_prison) {
        this.name_of_prison = name_of_prison;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String subMit(){
        try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prison_dbase", "root", "password@1");
            PreparedStatement stmt=con.prepareStatement("INSERT INTO prison_dbase.visitors (vname, id_no, serial, "
                    + "v_gender, date_visited, phone, address) \n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?)"))
        {            
            stmt.setString(1, visitorname);
            stmt.setString(2, serial_id);
            stmt.setString(3, sex);
            stmt.setString(4, vi_date.toString());
            stmt.setLong(5, phone_no);
            stmt.setString(6, vi_address);
            stmt.executeUpdate();
        }
        catch(Exception ex){
        return "/visitors";
        }
        
        return "/home";
    }

    public VisitorsBean() {
    }

    
    
}
