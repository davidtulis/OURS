package ours.model;

/**
 * The User interface defines all of the required methods for a User of
 * the Online University Registration System (OURS).
 *
 * @author John Harris
 */
public interface User {
    
    /**
     * Gets the name of the User.
     *
     * @return The name of the User.
     */
    public String getName();
    
    /**
     * Gets the password of the User.
     *
     * @return The password of the User.
     */
    public String getPassword();
    
    /**
     * Gets the user ID of the User.
     *
     * @return The user ID of the User.
     */
    public String getUserID();
    
    /**
     * Gets the department that the User belongs to.
     *
     * @return The department that the User belongs to.
     */
    public String getDepartment();
    
    /**
     * Gets the Schedule of the User.
     *
     * @return The Schedule of the User.
     */
    public Schedule getSchedule();
    
    /**
     * Sets the name of the User.
     *
     * @param name The new name of the User.
     */
    public void setName(String name);
    
    /**
     * Sets the password of the User.
     *
     * @param password The new password of the User.
     */
    public void setPassword(String password);
    
    /**
     * Sets the user ID of the User.
     *
     * @param userID The new user ID of the User.
     */
    public void setUserID(String userID);
    
    /**
     * Sets the department that the User belongs to.
     *
     * @param department The new department that the User belongs to.
     */
    public void setDepartment(String department);
    
    /**
     * Sets the Schedule for the User.
     *
     * @param schedule The new Schedule for the User.
     */
    public void setSchedule(Schedule schedule);
    
}