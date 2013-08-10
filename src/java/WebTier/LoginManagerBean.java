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
private Boolean loginFailureMessage;

@EJB
private LoginControlBean control;
    /**
     * Creates a new instance of LoginManagerBean
     */
    public LoginManagerBean() {
        //loginFailureMessage = false;
    }


/**
     * Method to determine if a user is not logged into the application 
     * @return Boolean Return true if the user is not logged in
     */

    public Boolean isNotLoggedIn()
    {
        return getControl().isNotLoggedIn();
        
    }
    
    
    public void redirectNotLoggedIn()
    {
        if(!this.isLoggedIn())
        {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getApplication().getNavigationHandler().handleNavigation(context, null, "/error/login_failed_error.xhtml");
        }
    }
      /**
     * Method to determine if a user is logged into the application
     * @return Boolean Return true if the user is logged in
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
   try{
      
      getControl().login(this.getUsername(), this.getPassword());
       FacesContext context = FacesContext.getCurrentInstance();
      context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml");
      
    } catch (ServletException ex) {
      FacesContext context = FacesContext.getCurrentInstance();
      context.getApplication().getNavigationHandler().handleNavigation(context, null, "/error/login_failed_error.xhtml");
         
        
    }
    }
    
    /**
     * Method to logout of the application
     */
    public void logout()
    {   
        if(getControl().isAdmin())
        {
         getControl().logout();
           FacesContext context = FacesContext.getCurrentInstance();
             context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml");
        }
        else
        {
            getControl().logout();
        }
     
     {
     }
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

    /**
     * @return the loginFailureMessage
     */
    public Boolean getLoginFailureMessage() {
        return loginFailureMessage;
    }

    /**
     * @param loginFailureMessage the loginFailureMessage to set
     */
    public void setLoginFailureMessage(Boolean loginFailureMessage) {
        this.loginFailureMessage = loginFailureMessage;
    }

}

