/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Smiley
 */
@Stateless
public class LoginControlBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    /**
     * Method to log a user in to the application
     * @param username The username being entered
     * @param password  The password being entered
     * @return String   Returns a string that contains a message describing whether the login succeeded or not
     * @throws ServletException 
     */
    public void login(String username, String password) throws ServletException {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(username, password);
       
        
        
        
    }
    
    /**
     * Method to log a user out of the application
     * @return String the logout message
     * @throws ServletException
     */
    public void logout()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try
        {
            request.logout();
        }catch(ServletException ex)
        {
            ex.getStackTrace();
        }
       
    }
    
    /**
     * Method to determine if a user is not logged into the application 
     * @return Boolean Return true if the user is not logged in
     */
    public Boolean isNotLoggedIn()
    {
         FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
      String user = request.getRemoteUser();
      if(user == null){
            return true;
        }
      else
      {
            return false;
        }
    }
    
    
    /**
     * Method to determine if a user is logged into the application
     * @return Boolean Return true if the user is logged in
     */
    public Boolean isLoggedIn()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
      String user = request.getRemoteUser();
      if(user != null){
            return true;
        }
      else
      {
            return false;
        }
    }
    
    /**
     * Method to determine if a user is an administrator 
     * @return Boolean Return true if the user is in the "Librarian" role, return false otherwise
     */
    public Boolean isAdmin()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole("Librarian");
    }
}
