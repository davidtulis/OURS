package ours.model;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Login Class is used by the User to access their respective 
 * system.
 *
 * @author John Harris
 */
public class Login {

    private String name;
    private String department;
    final private String userID;
    final private String password;
    final private Pattern INSTRUCTOR_USERID_PATTERN;
    final private Pattern STUDENT_USERID_PATTERN;
    final private Pattern STAFF_USERID_PATTERN;
    private User user = null;
    
    /**
     * Main Constructor
     *
     * @param userID The user ID of the User that is logging in
     * @param password The password of the User that is logging in.
     */
    public Login(String userID, String password){
        
        this.userID = userID;
        this.password = password;
        INSTRUCTOR_USERID_PATTERN = Pattern.compile("[a-c]{3}[0-9]{3}");
        STUDENT_USERID_PATTERN = Pattern.compile("[d-f]{3}[0-9]{3}");
        STAFF_USERID_PATTERN = Pattern.compile("[x-z]{3}[0-9]{3}");
        
    }
    
    /**
     * Determines if the User exists, what kind of User it is, and if
     * the correct password was supplied.
     *
     * @return A status code that indicates if the login was successful and, if so, what type of User logged in.
     * @throws IOException
     */
    public int login() throws IOException{
        
        int statusCode;
        boolean found = false;
        
        //File reading resource
        Read reader = new Read();
        
        //ArrayList of HashMaps that contain the User information from each line of the file.
        ArrayList<HashMap<String, String>> table = reader.readUserFile();
        
        //Iterate through ArrayList
        for(HashMap<String, String> row : table){
            
            //Check if the User exists and entered the correct password
            if(userID.equals(row.get("userID")) && 
                    password.equals(row.get("password"))){
                
                found = true;
                name = row.get("name");
                department = row.get("department");
                break;
                
            }
            
        }
        
        //If the User was not found then set the status code to 1.
        if(!found){
            
            statusCode = 1;
            
        }
        
        //The User exists
        else{
            
            //If the User is a Student set the status code to 10 and create the Student
            if(userID.matches(STUDENT_USERID_PATTERN.pattern())){
                
                user = new Student(userID, name, password, department);
                statusCode = 10;
                
            }
            
            //If the User is an Instructor set the status code to 20 and create the Instructor
            else if(userID.matches(INSTRUCTOR_USERID_PATTERN.pattern())){
                
                user = new Instructor(name, password, userID, department);
                statusCode = 20;
                
            }
            
            //If the User is Staff set the status code to 30 and create the Staff User
            else if(userID.matches(STAFF_USERID_PATTERN.pattern())){
                
                user = new Staff(userID, name, password, department);
                statusCode = 30;
                
            }
            
            //If the user ID does not fit any of the patterns set the status code to 40 (mostly for debugging purposes)
            else{
                
                statusCode = 40;
                
            }
            
        }
        
        return statusCode;
        
    }
    
    /**
     * Gets the user ID of the User that is logging in.
     *
     * @return The user ID of the User that is logging in.
     */
    public String getUserID(){
        
        return userID;
        
    }
    
    /**
     * Gets the User that was created by the login method.
     *
     * @return The user that was created by the login method.
     */
    public User getUser(){
        
        return user;
        
    }
    
}