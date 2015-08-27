package ours.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The MasterSchedule class is the Schedule for the entire University.
 *
 * @author John Harris
 */
public class MasterSchedule implements Schedule{
    
    private ArrayList<Course> courseList;
    
    /**
     * Main Constructor
     *
     * @throws IOException
     */
    public MasterSchedule() throws IOException{
        
        //File reading resource
        Read reader = new Read();
        
        //Add all of the Courses in the file to the schedule
        this.courseList = reader.readCourseFile();
        
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
            
            writer.addCourse(course);//Add the Course
            setCourseList(reader.readCourseFile());
            
        } 
        
        catch (IOException ex) {
            
            Logger.getLogger(MasterSchedule.class.getName())
                    .log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public void addCourses(ArrayList<Course> courseList) {
        
        //Iterate through the provided list of Courses
        for(Course course : courseList){
            
            //Call the addCourses method that adds only a single Course for each Course in the list
            addCourses(course);
            
        }
        
    }

    @Override
    public void removeCourses(Course course) {
        
        //File I/O resources
        Write writer = new Write();
        Read reader = new Read();
        
        try {
            
            writer.removeCourse(course);//Remove the Course from the file
            setCourseList(reader.readCourseFile());//Update the list of Courses from the file
            
        } 
        
        catch (IOException ex) {
            
            Logger.getLogger(MasterSchedule.class.getName())
                    .log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public void removeCourses(ArrayList<Course> courseList) {
        
        //Iterate through the provided list of Courses
        for(Course course : courseList){
            
            //Call the removeCourses method that removes only a single Course for each Course in the list
            removeCourses(course);
            
        }
        
    }

    @Override
    public void setCourseList(ArrayList<Course> courseList) {
        
        this.courseList = courseList;
        
    }
    
}
