/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author pridemai
 */
public class LogFormatter {
    private String checkOutStr;
    private String checkInStr;
    private String newItemStr;

    
    /**
     * Method to format the log string when an item is created
     * @param item the title of the item being created
     * @param format the format of the item being created
     * @param status the status of the item being created
     * @return the log entry for item created
     * */
    public String logItemCreated(String title, String format, String status)
    {
        setNewItemStr("Item "+title+" of Format "+format+" created. Status set to "+status);
        return newItemStr;
    }
    
    
    /**
     * Method to format the log string when an item is checked out
     * @param id the id number of the item being checked out
     * @param title the title of the item being checked out
     * @return  the log entry of the item checked out
     */
    public String logItemCheckedOut(String id, String title)
    {
        setCheckOutStr("Item number "+id+" title: "+title+" status changed to checked-out");
        return checkOutStr;
    }
    
    /**
     * Method to format the log string when an item is checked in
     * @param id the id of the item being checked in
     * @param title the title of the item being checked in
     * @return the log entry of the item being checked in
     */
    public String logItemCheckedIn(String id, String title)
    {
        setCheckInStr("Item number "+id+" title: "+title+" status changed to checked-out");
        return checkInStr;
    }
    
    
    
    
    /**
     * @return the checkOutStr
     */
    public String getCheckOutStr() {
        return checkOutStr;
    }

    /**
     * @param checkOutStr the checkOutStr to set
     */
    public void setCheckOutStr(String checkOutStr) {
        this.checkOutStr = checkOutStr;
    }

    /**
     * @return the checkInStr
     */
    public String getCheckInStr() {
        return checkInStr;
    }

    /**
     * @param checkInStr the checkInStr to set
     */
    public void setCheckInStr(String checkInStr) {
        this.checkInStr = checkInStr;
    }

    /**
     * @return the newItemStr
     */
    public String getNewItemStr() {
        return newItemStr;
    }

    /**
     * @param newItemStr the newItemStr to set
     */
    public void setNewItemStr(String newItemStr) {
        this.newItemStr = newItemStr;
    }
    
    
    
    
}
