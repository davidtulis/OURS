package ours.model;

import java.io.IOException;

/**
 * The Staff class contains all of the relevant information about a 
 * member of an Academic Department's Administrative Staff.
 *
 * @author John Harris
 */
public class Staff implements User{
    
    private String userID;
    private String name;
    private String password;
    private String department;
    private DepartmentSchedule schedule = null;
    
    /**
     * Main Constructor
     *
     * @param userID The user ID for the Staff member.
     * @param name The name of the Staff member.
     * @param password The password of the Staff member.
     * @param department The department that the Staff member belongs to.
     * @throws IOException
     */
    public Staff(String userID, String name, String password, 
            String department) throws IOException{
        
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.department = department;
        schedule = new DepartmentSchedule(department);
        
    }

    @Override
    public String getName() {
        
        return name;
        
    }

    @Override
    public String getPassword() {
        
        return password;
        
    }

    @Override
    public String getUserID() {
        
        return userID;
        
    }

    @Override
    public String getDepartment() {
        
        return department;
        
    }

    @Override
    public Schedule getSchedule() {
        
        return schedule;
        
    }

    @Override
    public void setName(String name) {
        
        this.name = name;
        
    }

    @Override
    public void setPassword(String password) {
        
        this.password = password;
        
    }

    @Override
    public void setUserID(String userID) {
        
        this.userID = userID;
        
    }

    @Override
    public void setDepartment(String department) {
        
        this.department = department;
        
    }

    @Override
    public void setSchedule(Schedule schedule) {
        
        this.schedule = (DepartmentSchedule) schedule;
        
    }
    
}
