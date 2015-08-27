package ours.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The DepartmentSchedule Class is the Schedule for an Academic 
 * Department at the University.
 *
 * @author John Harris
 */
public class DepartmentSchedule implements Schedule {
    
    private ArrayList<Course> courseList = new ArrayList<>();
    private final String department;
    
    /**
     * Main Constructor
     *
     * @param department The department that this Schedule is for.
     * @throws IOException
     */
    public DepartmentSchedule(String department) throws IOException{
        
        //Get the MasterSchedule for the entire University
        MasterSchedule m = new MasterSchedule();
        
        this.department = department;
        
        //Iterate through the MasterSchedule Course list
        for(Course course : m.getCourseList()){
            
            //Check if the Course is in this department
            if(course.getDepartment().equals(department)){
                
                //Add the Course to the DepartmentSchedule Course list
                courseList.add(course);
                
            }
            
        }
        
    }
    
    public String getDepartment(){
        
        return department;
        
    }

    @Override
    public ArrayList<Course> getCourseList() {
        
        return courseList;
        
    }

    @Override
    public void addCourses(Course course) {
        
        //File I/O resources
        Write writer = new Write();
        Read reader = new Read();
        
        try {
            
            writer.addCourse(course); //Add the Course to the file
            setCourseList(reader.readCourseFile()); //Update the DepartmentSchedule Course list from the file
            
        } 
        
        catch (IOException ex) {
            
            Logger.getLogger(DepartmentSchedule.class.getName())
                    .log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public void addCourses(ArrayList<Course> courseList) {
        
        //Iterate through the supplied Course list
        for(Course course : courseList){
            
            addCourses(course); //Call the addCourses method above which only adds a single Course.
            
        }
        
    }

    @Override
    public void removeCourses(Course course) {
        
        //File I/O resources
        Write writer = new Write();
        Read reader = new Read();
        
        boolean exists = false;
        
        //Iterate throught the DepartmentSchedule Course list
        for(Course c : courseList){
            
            //Check if the supplied Course is in the DepartmentSchedule Course list
            if(c.getCourseID() == course.getCourseID()){
                
                exists = true;
                break;
                
            }
            
        }
        
        //If the Course exists then remove it
        if(exists){
        
            try {
                
                writer.removeCourse(course); //Remove the Course from the file.
                setCourseList(reader.readCourseFile()); //Update the DepartmentSchedule Course list from the file.

            } 

            catch (IOException ex) {

                Logger.getLogger(DepartmentSchedule.class.getName())
                        .log(Level.SEVERE, null, ex);

            }
        
        }
        
    }

    @Override
    public void removeCourses(ArrayList<Course> courseList) {
        
        //Iterate through the supplied Course list
        for(Course course : courseList){
            
            removeCourses(course); //Call the removeCourses method above which only removes a single Course.
            
        }
        
    }

    @Override
    public void setCourseList(ArrayList<Course> courseList) {
        
        //Create a temporary ArrayList to hold the Courses
        ArrayList<Course> tempCourseList = new ArrayList<>();
        
        //Iterate through the supplied Course list
        for(Course course : courseList){
            
            //Check if the Course is offered by the department
            if(course.getDepartment().equals(department)){
                
                //Add the Course to the temporary ArrayList
                tempCourseList.add(course);
                
            }
            
        }
        
        //Set the DepartmentSchedule Course list to the temporary one.
        this.courseList = tempCourseList;
        
    }
    
}
