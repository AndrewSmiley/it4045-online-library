/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebTier;

import EBJ.LoginControlBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author Smiley
 */
@ManagedBean
@SessionScoped
public class LoginManagerBean {
private String username;
private String password;

@EJB
private LoginControlBean control;
    /**
     * Creates a new instance of LoginManagerBean
     */
    public LoginManagerBean() {
    }


/**
 * Method to determine if a user is logged in or not
 * @return Boolean whether the user is logged in or not
 */

    public Boolean isLoggedIn()
    {
        return getControl().isLoggedIn();
        
    }
    
    /**
     * Method to login to the application
     */
    public void login()
    {
    try {
        getControl().login(this.getUsername(), this.getPassword());
    } catch (ServletException ex) {
        Logger.getLogger(LoginManagerBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    /**
     * Method to logout of the application
     */
    public void logout()
    {
         getControl().logout();
    }
    
    /**
     * Method to determine if the user is an admin or not
     */
    public Boolean isAdmin()
    {
        return getControl().isAdmin();
    }
   
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the control
     */
    public LoginControlBean getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(LoginControlBean control) {
        this.control = control;
    }

}

