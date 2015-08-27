package ours.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The InstructorSchedule class is the Schedule class for an Instructor
 * at the University. This class contains the listing of all the Courses that
 * the Instructor teaches.
 *
 * @author John Harris
 */
public class InstructorSchedule implements Schedule{
    
    private ArrayList<Course> courseList;
    private final Instructor instructor;
    
    /**
     * Main Constructor
     *
     * @param instructor The Instructor to create a Schedule for.
     * @throws IOException
     */
    public InstructorSchedule(Instructor instructor) throws IOException{
        
        this.instructor = instructor;
        ArrayList<Course> tempCourseList = new ArrayList<>();
        DepartmentSchedule departmentSchedule = 
                new DepartmentSchedule(this.instructor.getDepartment());
        
        //Iterate through the department's schedule to fetch the instructor's schedule
        for(Course course : departmentSchedule.getCourseList()){
            
            //If the instructor's userID for the course is the same as the provided instructor's userID
            if(course.getInstructor().getUserID()
                    .equals(this.instructor.getUserID())){
                
                tempCourseList.add(course); //Add the course to the instructor's schedule;
                
            }
            
        }
        
        this.courseList = tempCourseList;
        
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
            
            writer.addCourse(course); //Write the Course to the file
            setCourseList(reader.readCourseFile()); // Update the list of courses from the file
            
        } 
        
        catch (IOException ex) {
            
            Logger.getLogger(InstructorSchedule.class.getName())
                    .log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    @Override
    public void addCourses(ArrayList<Course> courseList) {
        
        //Iterate through the provided list of Courses
        for(Course course : courseList){
            
            //Call the addCourses method that adds only a single Course for each Course in the list.
            addCourses(course);
            
        }
        
    }

    @Override
    public void removeCourses(Course course) {
        
        //File I/O resources
        Write writer = new Write();
        Read  reader = new Read();
        
        boolean exists = false;
        
        //Iterate through the current list of Courses to determine if the 
        //Instructor is actually associated with the supplied Course
        for(Course c : courseList){
            
            //If the Course ID for the current Course and the supplied Course match
            if(c.getCourseID() == course.getCourseID()){
                
                //Then set exists to true and exit the loop
                exists = true;
                break;
                
            }
            
        }
        
        //If the Course exists in the Instructor's list of Courses
        if(exists){
            
            //Set the Instructor of the Course to null
            course.setInstructor(null);
            
            try {
                
                writer.addCourse(course);//Write the Course to the file
                setCourseList(reader.readCourseFile());//Update the list of Courses from the file
                
            } 
            
            catch (IOException ex) {
                
                Logger.getLogger(InstructorSchedule.class.getName())
                        .log(Level.SEVERE, null, ex);
                
            }
            
        }
        
    }
    
    @Override
    public void removeCourses(ArrayList<Course> courseList) {
        
        //Iterate through the provided list of Courses
        for(Course course : courseList){
            
            //Call the removeCourses method that removes only a single Course for each Course in the list.
            removeCourses(course);
            
        }
        
    }
    
    @Override
    public void setCourseList(ArrayList<Course> courseList) {
        
        ArrayList<Course> tempCourseList = new ArrayList<>();
        
        //Iterate through the provided list of Courses
        for(Course course : courseList){
            
            //If the userID for the Course's Instructor and this Instructor match
            if(course.getInstructor().getUserID()
                    .equals(instructor.getUserID())){
                
                //Then add the Course to the Instructor's schedule
                tempCourseList.add(course);
                
            }
            
        }
        
        this.courseList = tempCourseList;
        
    }
    
}
