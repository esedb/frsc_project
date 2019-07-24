package classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ese
 */
@ManagedBean(name="al_beans", eager=true)
@RequestScoped
public class AdminLoginBeans implements Serializable {
    String login_name;
    String login_password;

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
    
    public String subMit(){
        
        FacesContext ctx=FacesContext.getCurrentInstance();
        String adminname=ctx.getExternalContext().getInitParameter("admin_name");
        String adminpassword=ctx.getExternalContext().getInitParameter("admin_pass");
        if(login_name.equalsIgnoreCase(adminname) && login_password.equalsIgnoreCase(adminpassword))
        {
            return "/reg_employee";
        }
        return "/admin_login";
    }
     
    public AdminLoginBeans() {
    }
    
}
