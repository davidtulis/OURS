package ours.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Instructor Class contains all of the information for an 
 * Instructor at the University.
 *
 * @author John Harris
 */
public class Instructor implements User {
    
    private String name;
    private String password;
    private String userID;
    private String department;
    private InstructorSchedule schedule;
    
    /**
     * Main Constructor
     *
     * @param name The name of the Instructor.
     * @param password The password for the Instructor.
     * @param userID The user ID for the Instructor.
     * @param department The department that the Instructor belong to.
     * @throws IOException
     */
    public Instructor(String name, String password, String userID, 
            String department) throws IOException{
        
        this.name = name;
        this.password = password;
        this.userID = userID;
        this.department = department;
        
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
        
        try {
            
            schedule = new InstructorSchedule(this);
            
        } 
        
        catch (IOException ex) {
            Logger.getLogger(Instructor.class.getName())
                    .log(Level.SEVERE, null, ex);
            
        }
        
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
        
        this.schedule = (InstructorSchedule) schedule;
        
    }

    @Override
    public String toString() {
        return name;
    }
    
}
