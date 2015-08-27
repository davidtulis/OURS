package ours.model;

import java.util.ArrayList;

/**
 * The Course Class is used to hold all of the necessary data for a 
 * Course at the University
 *
 * @author John Harris
 */
public class Course {
    
    private String courseName;
    private final int courseID;
    private int courseSection;
    private String courseDays;
    private String courseTime;
    private int courseCapacity;
    private Instructor instructor;
    private String department;
    private ArrayList<Student> enrolledStudents;
    
    /**
     * This Constructor is used to create a Course that is not read from
     * a file and needs a Course ID to be assigned.
     *
     * @param courseName The name of the Course.
     * @param courseSection The section number of the Course.
     * @param courseDays The days that the Course occurs on.
     * @param courseTime The time of day that the Course occurs on.
     * @param courseCapacity The maximum number of Students the Course can hold.
     * @param instructor The Instructor of the Course.
     * @param department The department that offers the Course.
     */
    public Course(String courseName, int courseSection, String courseDays, 
            String courseTime,int courseCapacity, Instructor instructor, 
            String department){
        
        this.courseName = courseName;
        this.courseID = (int) (100000 + (Math.random() * 
                ((999999 - 100000) + 1))); //Randomly generate a Course ID between 100000 and 999999
        this.courseSection = courseSection;
        this.courseDays = courseDays;
        this.courseTime = courseTime;
        this.courseCapacity = courseCapacity;
        this.instructor = instructor;
        this.department = department;
        enrolledStudents = new ArrayList<>();
        
    }
    
    /**
     * This Constructor is used to create a Course from an entry from 
     * a file. A Course that is created in this way already has a Course ID 
     * that is read from the file.
     *
     * @param courseID The ID number for the Course.
     * @param courseName The name of the Course.
     * @param courseSection The section number of the Course.
     * @param courseDays The days that the Course occurs on.
     * @param courseTime The time of day that the Course occurs on.
     * @param courseCapacity The max number of Students the Course can hold.
     * @param instructor The Instructor of the Course.
     * @param department The department that offers the Course.
     * @param enrolledStudents The list of Students that are enrolled in the Course.
     */
    public Course(int courseID, String courseName, int courseSection, 
            String courseDays, String courseTime, int courseCapacity, 
            Instructor instructor, String department, 
            ArrayList<Student> enrolledStudents){
        
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseSection = courseSection;
        this.courseDays = courseDays;
        this.courseTime = courseTime;
        this.courseCapacity = courseCapacity;
        this.instructor = instructor;
        this.department = department;
        this.enrolledStudents = enrolledStudents;
        
    }
    
    /**
     * Gets the name of the Course.
     *
     * @return The name of the Course.
     */
    public String getCourseName(){
        
        return courseName;
        
    }
    
    /**
     * Gets the Course ID number.
     *
     * @return The Course ID number.
     */
    public int getCourseID(){
        
        return courseID;
        
    }
    
    /**
     * Gets the section number of the Course.
     *
     * @return The section number of the Course.
     */
    public int getCourseSection(){
        
        return courseSection;
        
    }
    
    /**
     * Gets the days that the Course occurs on.
     *
     * @return The days that the Course occurs on.
     */
    public String getCourseDays(){
        
        return courseDays;
        
    }
    
    /**
     * Gets the time of day that the Course occurs on.
     *
     * @return The time of day that the Course occurs on.
     */
    public String getCourseTime(){
        
        return courseTime;
        
    }
    
    /**
     * Gets the max number of Students that the Course can hold.
     *
     * @return The max number of Students that the Course can hold.
     */
    public int getCourseCapacity(){
        
        return courseCapacity;
        
    }
    
    /**
     * Gets the Instructor of the Course.
     *
     * @return The Instructor of the Course.
     */
    public Instructor getInstructor(){
        
        return instructor;
        
    }
    
    /**
     * Gets the department that offers the Course.
     *
     * @return The department that offers the Course.
     */
    public String getDepartment(){
        
        return department;
        
    }
    
    /**
     * Gets the list of Students that are enrolled in the Course.
     *
     * @return The list of Students that are enrolled in the Course.
     */
    public ArrayList<Student> getEnrolledStudents(){
        
        return enrolledStudents;
        
    }
    
    /**
     * Sets the name of the Course.
     *
     * @param courseName The new name of the Course.
     */
    public void setCourseName(String courseName){
        
        this.courseName = courseName;
        
    }
    
    /**
     * Sets the new section number of the Course.
     *
     * @param courseSection The new section number of the Course.
     */
    public void setCourseSection(int courseSection){
        
        this.courseSection = courseSection;
        
    }
    
    /**
     * Sets the new days that the Course occurs on.
     *
     * @param courseDays The new days the Course occurs on.
     */
    public void setCourseDays(String courseDays){
        
        this.courseDays = courseDays;
        
    }
    
    /**
     * Sets the new time of day that the Course occurs on.
     *
     * @param courseTime The new time of day that the Course occurs on.
     */
    public void setCourseTime(String courseTime){
        
        this.courseTime = courseTime;
        
    }
    
    /**
     * Sets the new max number of Students that the Course can hold.
     *
     * @param courseCapacity The new max number of Students that the Course can hold.
     */
    public void setCourseCapacity(int courseCapacity){
        
        this.courseCapacity = courseCapacity;
        
    }
    
    /**
     * Sets the new Instructor of the Course.
     *
     * @param instructor The new Instructor of the Course.
     */
    public void setInstructor(Instructor instructor){
        
        this.instructor = instructor;
        
    }
    
    /**
     * Sets the new department that offers the Course.
     *
     * @param department The new department that offers the Course.
     */
    public void setDepartment(String department){
        
        this.department = department;
        
    }
    
    /**
     * Sets the new list of Students that are enrolled in the Course.
     *
     * @param enrolledStudents The new list of Students that are enrolled in the Course.
     */
    public void setEnrolledStudents(ArrayList<Student> enrolledStudents){
        
        this.enrolledStudents = enrolledStudents;
        
    }

    @Override
    public String toString() {
        return  "ID: " + courseID + " | Course: " + courseName + " | Section: " 
                + courseSection;
    }
    
}
