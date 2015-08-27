package ours.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Student class contains all of the relevant information for a 
 * Student at the University.
 *
 * @author John Harris
 */
public class Student implements User{
    
private String name;
private String userID;
private String password;
private String department;
private StudentSchedule schedule;
    
    /**
     * Main Constructor
     *
     * @param userID The user ID of the Student.
     * @param name The name of the Student.
     * @param password The password of the Student.
     * @param department The department (major) of the Student.
     * @throws IOException
     */
    public Student(String userID, String name, String password, 
            String department) throws IOException{
        
        this.userID = userID;
        this.name = name;
        this.password = password;
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

            schedule = new StudentSchedule(this);

        } 

        catch (IOException ex) {

            Logger.getLogger(Student.class.getName())
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
        
        this.schedule = (StudentSchedule) schedule;
        
    } 
    
}
