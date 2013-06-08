/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Smiley
 */
public class DateUtil {
    private String strDate;
    private Date date;

    
    public String createDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = new Date();
        strDate = date.toString();
        return strDate;
    }
    
    
    /**
     * @return the strDate
     */
    public String getStrDate() {
        return strDate;
    }

    /**
     * @param strDate the strDate to set
     */
    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
