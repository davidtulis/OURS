package ours.model;

import java.util.ArrayList;

/**
 * The Schedule interface defines all of the methods that a Schedule 
 * will require.
 *
 * @author John Harris
 */
public interface Schedule {
    
    /**
     * Gets the list of Courses that are stored in the Schedule.
     *
     * @return The list of Courses that are stored in the Schedule.
     */
    public ArrayList<Course> getCourseList();
    
    /**
     * Sets the list of Courses to be stored in the Schedule.
     *
     * @param courseList The list of Courses to be stored in the Schedule.
     */
    public void setCourseList(ArrayList<Course> courseList);
    
    /**
     * Adds a single Course to the Schedule.
     *
     * @param course The Course to add to the Schedule.
     */
    public void addCourses(Course course);
    
    /**
     * Adds a list of Courses to the Schedule.
     *
     * @param courseList The list of Courses to add to the Schedule.
     */
    public void addCourses(ArrayList<Course> courseList);
    
    /**
     * Removes a single Course from the Schedule.
     *
     * @param course The Course to remove from the Schedule.
     */
    public void removeCourses(Course course);
    
    /**
     * Removes a list of Courses from the Schedule.
     *
     * @param courseList The list of Courses to remove from the Schedule.
     */
    public void removeCourses(ArrayList<Course> courseList);
    
}
