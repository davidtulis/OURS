package ours.model;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Write class is used to write information to files that are 
 * required by the other classes.
 *
 * @author John Harris
 */
public class Write {
    
    final private String directory;
    
    /**
     * Main Constructor
     *
     */
    public Write(){
        
        directory = System.getProperty("user.dir")+"-src-ours-model-".
                replace("-", System.getProperty("file.separator"));
        
    }
    
    /**
     * Updates a User entry in the users.txt file.
     *
     * @param userID The user ID of the User to be modified.
     * @param information The updated information for the User.
     * @throws IOException
     */
    public void updateUser(String userID, HashMap information) throws 
            IOException{
        
        //Necessary Files
        File file = new File(directory + "users.txt");
        File backup = new File(directory + "users.txt.bak");
        File temp = new File(directory + "usersTemp.txt");
        
        Read reader = new Read();
        
        //Read the current file
        ArrayList<HashMap<String, String>> table = reader.readUserFile();
        
        int selectedLine = -1;
        
        //Iterate through the user information
        for (HashMap<String, String> row : table){
            
            //Find the user's information
            if (row.get("userID").equals(userID)){
                
                selectedLine = table.indexOf(row);
                break;
                
            }
            
        }
        
        //If the user's information was found
        if (selectedLine != -1){
            
            //Remove the current information and add the new information
            table.remove(selectedLine);
            table.add(information);
            
            //Format the user information
            ArrayList<String> entries = generateUserEntry(table);
           
           //Check if a backup exists
           if(backup.exists()){
               
               //If yes, then delete it
               backup.delete();
               
           }
           
           //Backup the current file
           file.renameTo(backup);
           
           //File writing resources
           FileWriter fw = new FileWriter(temp);
           BufferedWriter writer = new BufferedWriter(fw);
           
           //Iterate through the list of entries
           for(String entry : entries){
               
               //Write each entry to the file
               writer.write(entry + "\n");
               
           }
           
           //close the writer
           writer.close();
           
           //Set the temporary file to the users.txt file
           temp.renameTo(file);
            
        } 
        
    }
    
    /**
     * Adds a Course to the courses.txt File.
     *
     * @param course The Course to add to the courses.txt File.
     * @throws IOException
     */
    public void addCourse(Course course) throws IOException{
        
        //Necessary Files
        File file = new File(directory + "courses.txt");
        File temp = new File(directory + "coursesTemp.txt");
        File backup = new File(directory + "courses.bak");
        
        Read reader = new Read();
        
        //Read the current file into a list of Courses
        ArrayList<Course> courseList = reader.readCourseFile();
        
        boolean exists = false;
        int index = -1;
        
        //Iterate through the list of Courses
        for(Course c : courseList){
            
            //Check if the Course to add already exists
            if(course.getCourseID() == c.getCourseID()){
                
                //If yes, then set exists to true, set index to the location of the Course, and exit the loop
                exists = true;
                index = courseList.indexOf(c);
                break;
                
            }
            
        }
        
        //If the Course already exists
        if(exists){
            
            //Then remove the Course from the list
            courseList.remove(index);
            
        }
        
        //Add the new Course to the list
        courseList.add(course);
        
        //Format the list of Courses for writing
        ArrayList<String> entries = generateCourseEntry(courseList);
        
        //Check if a backup already exists
        if(backup.exists()){
            
            //If yes, then delete it
            backup.delete();
            
        }
        
        //Backup the current file
        file.renameTo(backup);
        
        //File writing resources
        FileWriter fw = new FileWriter(temp);
        BufferedWriter writer = new BufferedWriter(fw);
        
        //Iterate through the list of entries
        for(String entry : entries){
            
            //Write each entry into the temp file
            writer.write(entry + "\n");
            
        }
        
        //Close the writer
        writer.close();
        
        //Set the temporary file to the courses.txt file
        temp.renameTo(file);
        
    }
    
    /**
     * Removes a Course from the courses.txt File.
     *
     * @param course The Course to remove from the courses.txt File.
     * @throws IOException
     */
    public void removeCourse(Course course) throws IOException{
        
        //Necessary Files
        File file = new File(directory + "courses.txt");
        File temp = new File(directory + "coursesTemp.txt");
        File backup = new File(directory + "courses.bak");
        
        Read reader = new Read();
        
        //Read the current file into a list of Courses
        ArrayList<Course> courseList = reader.readCourseFile();
        
        boolean exists = false;
        int index = -1;
        
        //Iterate through the list of Courses
        for(Course c : courseList){
            
            //Find the Course to be removed
            if(c.getCourseID() == course.getCourseID()){
                
                //If found, set exists to true, index to the location of the Course, and exit the loop
                exists = true;
                index = courseList.indexOf(c);
                break;
                
            }
            
        }
        
        //If the Course exists
        if(exists){
            
            //Then remove the Course from the list
            courseList.remove(index);
            
            //Format the Courses in the list
            ArrayList<String> entries = generateCourseEntry(courseList);
            
            //Check if a backup exists
            if(backup.exists()){
                
                //If yes, then delete it
                backup.delete();
                
            }
            
            //Backup the current file
            file.renameTo(backup);
            
            //File writing resources
            FileWriter fw = new FileWriter(temp);
            BufferedWriter writer = new BufferedWriter(fw);
            
            //Iterate through the list of entries
            for(String entry : entries){
                
                //Write each entry to the file
                writer.write(entry + "\n");
                
            }
            
            //Close the writer
            writer.close();
            
            //Set the temporary file to the courses.txt file
            temp.renameTo(file);
            
        }
        
    }
    
    private ArrayList<String> generateCourseEntry
        (ArrayList<Course> courseList){
        
        ArrayList<String> entries = new ArrayList<>();
        
        for(Course course : courseList){
            
            String entry = 
                    String.valueOf(course.getCourseID()) + ":" + 
                    course.getCourseName() + ":" +
                    String.valueOf(course.getCourseSection()) + ":" +
                    course.getCourseDays() + ":" + 
                    String.valueOf(course.getCourseTime()) + ":" + 
                    String.valueOf(course.getCourseCapacity()) + ":";
            
            if(course.getInstructor() == null){
                
                entry += "TBA:";
                
            }
            
            else{
                
                entry += course.getInstructor().getUserID() + ":";
            
            }
            
            entry += course.getDepartment() + ":";
            
            for(Student student : course.getEnrolledStudents()){
                
                entry += student.getUserID() + ":";
                
            }
            
            entries.add(entry);
            
        }
        
        return entries;
        
    }
    
    private ArrayList<String> generateUserEntry
        (ArrayList<HashMap<String, String>> table){
        
        ArrayList<String> entries = new ArrayList<>();
        
        for (HashMap<String, String> row : table){
            
            String entry = 
                    row.get("userID") + ":" + 
                    row.get("password") + ":" +
                    row.get("name") + ":" +
                    row.get("department");
            
            entries.add(entry);
            
        }
        
        return entries;
        
    }

        
}