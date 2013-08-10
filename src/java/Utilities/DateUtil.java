/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *POJO utility class to simplify the retrevial of dates
 * @author Smiley
 */
public class DateUtil {

    private String strDate;
    private String time;

    /**
     * Method to get the current date in format yyyy-mm-dd
     * @return String a string containing a formated version of today's date
     */
    public String getTodaysDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        this.setStrDate(dateFormat.format(date));
        return getStrDate();
    }

    /**
     * Method to get the current time 
     * in the format HH:MM:SS
     * @return String containing a formated version of the current time
     */
    public String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        this.setTime(dateFormat.format(date));
        return getTime();
    }

    /**
     * 
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
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
}
