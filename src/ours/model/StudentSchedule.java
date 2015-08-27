package ours.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The StudentSchedule class is the Schedule for a Student of the 
 * University. This class contains a listing of all of the Courses that the
 * Student is enrolled in.
 *
 * @author John Harris
 */
public class StudentSchedule implements Schedule {
    
    private ArrayList<Course> courseList;
    private final Student student;
    
    /**
     * Main Constructor
     *
     * @param student The Student that the StudentSchedule is for.
     * @throws IOException
     */
    public StudentSchedule(Student student) throws IOException{
        
        MasterSchedule m = new MasterSchedule();
        this.student = student;
        
        courseList = new ArrayList<>();
        
        //Iterate through a list of all of the Courses at the University
        for(Course course : m.getCourseList()){
            
            //Iterate through the list of enrolled Students for each Course
            for(Student s : course.getEnrolledStudents()){
                
                //Check if the Student is in the list
                if(student.getUserID().equals(s.getUserID())){
                    
                    //If yes, then add them to the list of Courses
                    courseList.add(course);
                    
                }
                
            }
            
        }
        
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
        
        //Check if the Student is able to register for this Course
        if(courseList.size() < 5 || course.getCourseCapacity() == 
                course.getEnrolledStudents().size()){
            
            ArrayList<Student> enrolledStudents = course.getEnrolledStudents();
            
            //If yes, then add the Student to the list of enrolled Students of the Course
            enrolledStudents.add(student);
            
            course.setEnrolledStudents(enrolledStudents);
            
            try {
                
                writer.addCourse(course);//Write the Course to the file
                setCourseList(reader.readCourseFile());//Update the list of Courses from the file
                
            } 
            
            catch (IOException ex) {
                
                Logger.getLogger(StudentSchedule.class.getName())
                        .log(Level.SEVERE, null, ex);
                
            }
            
        }
        
    }

    @Override
    public void addCourses(ArrayList<Course> courseList) {
        
        //Iterate through the provided list of Courses
        for(Course course : courseList){
            
            //Call the addCourses method that adds a single Course for each Course in the list
            addCourses(course);
            
        }
        
    }

    @Override
    public void removeCourses(Course course) {
        
        //File I/O resources
        Write writer = new Write();
        Read reader = new Read();
        
        int index = -1;
        boolean exists = false;
        
        //Check if the Student is able to remove this Course
        if(courseList.size() > 3){
            
            //If yes, then Iterate through the list of Courses
            for(Course c : courseList){
                
                //Check if the Student is enrolled in the Course
                if(c.getCourseID() == course.getCourseID()){
                    
                    //If yes, then set exists to true, set index to the location of the Course in the list, and exit the loop
                    exists = true;
                    index = courseList.indexOf(c);
                    break;
                    
                }
                
            }
            
            //If the Student was enrolled in the Course
            if(exists){
                
                //Then remove the Course from the list of Courses
                courseList.remove(index);
                
                ArrayList<Student> enrolledStudents = 
                        course.getEnrolledStudents();
                
                //And Iterate through the list of enrolled Students for the Course
                for(Student s : enrolledStudents){
                    
                    //Find the Student in the list of enrolled Students of the Course to be removed
                    if(s.getUserID().equals(student.getUserID())){
                        
                        index = enrolledStudents.indexOf(s);
                        break;
                        
                    }
                    
                }
                
                //And remove the Student from that list
                enrolledStudents.remove(index);
                
                course.setEnrolledStudents(enrolledStudents);
                
                try {
                    
                    writer.addCourse(course);//Write the modified Course to the file
                    setCourseList(reader.readCourseFile());//Update the list of Courses from the file
                    
                } 
                
                catch (IOException ex) {
                    
                    Logger.getLogger(StudentSchedule.class.getName())
                            .log(Level.SEVERE, null, ex);
                    
                }
                
            }
            
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
        
        ArrayList<Course> tempCourseList = new ArrayList<>();
        
        //Iterate through the provided list of Courses
        for(Course course : courseList){
            
            //Iterate through the list of enrolled Students for each Course
            for(Student s : course.getEnrolledStudents()){
                
                //Check if the Student is enrolled
                if(s.getUserID().equals(student.getUserID())){
                    
                    //If yes, then add the Course to the list
                    tempCourseList.add(course);
                    
                }
                
            }
            
        }
        
        this.courseList = tempCourseList;
        
    }
    
    /**
     * Gets all of the Courses that the Student is not currently 
     * enrolled in. These Courses are Courses that the Student can register 
     * for.
     *
     * @param enrolledCourseList The list of Courses that the Student is 
     * currently enrolled in.
     * @return The list of Courses that are available for the Student to 
     * register for. 
     * @throws IOException
     */
    public ArrayList<Course> getAvailableCourses
        (ArrayList<Course> enrolledCourseList) throws IOException{
        
        ArrayList<Course> availableCourseList = new ArrayList<>();
        
        MasterSchedule m = new MasterSchedule();
        
        boolean exists = false;
        
        //Iterate through the list of all of the Courses in the University
        for(Course course : m.getCourseList()){
            
            //Iterate through the list of all of the Courses that the Student is enrolled in
            for(Course c : enrolledCourseList){
                
                //Check if the course ID from the MasterSchedule and the provided list of Courses are the same
                if(course.getCourseID() == c.getCourseID()){
                    
                    //If so then set exists to true and exit the loop
                    exists = true;
                    break;
                    
                }
                
                //If they are not equal
                else{
                    
                    //Then set exists to false
                    exists = false;
                    
                }
                
            }
            
            //If the Student is not enrolled in the Course
            if(!exists){
                
                //Then add the Course to the list of available Courses
                availableCourseList.add(course);
                
            }
            
        }
        
        return availableCourseList;
        
    }
    
}
