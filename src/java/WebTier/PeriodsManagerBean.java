/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebTier;

import EBJ.PeriodControlBean;
import Utilities.MediaTypes;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pridemai
 */
@ManagedBean
@ViewScoped
public class PeriodsManagerBean {

    private String type;
    private int numberOfDays;
    private double lateFee;
    private boolean renewable;
    private boolean updatePeriod;
    private boolean createPeriod;
    @EJB
    private PeriodControlBean pControl = new PeriodControlBean();

    /**
     * Creates a new instance of PeriodsManagerBean
     */
    public PeriodsManagerBean() {
    }

    public void createPeriod() {
        getpControl().createPeriod(this.getType(), this.getNumberOfDays(), this.getLateFee(), this.isRenewable());
    }
    
    
    public void updatePeriod()
    {
           getpControl().updatePeriod(this.getType(), this.getNumberOfDays(), this.getLateFee(), this.isRenewable());
    }
    /**
     * Method to select which action should be taken regarding periods. Corresponds to two different panel grids
     */
 public void selectAction()
    {
        
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the numberOfDays
     */
    public int getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * @param numberOfDays the numberOfDays to set
     */
    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    /**
     * @return the lateFee
     */
    public double getLateFee() {
        return lateFee;
    }

    /**
     * @param lateFee the lateFee to set
     */
    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    /**
     * @return the pControl
     */
    public PeriodControlBean getpControl() {
        return pControl;
    }

    /**
     * @param pControl the pControl to set
     */
    public void setpControl(PeriodControlBean pControl) {
        this.pControl = pControl;
    }

    /**
     * @return he renewable
     */
    public boolean isRenewable() {
        return renewable;
    }

    /**
     * @param renewable the renewable to set
     */
    public void setRenewable(boolean renewable) {
        this.renewable = renewable;
    }

    /**
     * @return the updatePeriod
     */
    public boolean isUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * @param updatePeriod the updatePeriod to set
     */
    public void setUpdatePeriod(boolean updatePeriod) {
        this.updatePeriod = updatePeriod;
    }

    /**
     * @return the createPeriod
     */
    public boolean isCreatePeriod() {
        return createPeriod;
    }

    /**
     * @param createPeriod the createPeriod to set
     */
    public void setCreatePeriod(boolean createPeriod) {
        this.createPeriod = createPeriod;
    }
}
