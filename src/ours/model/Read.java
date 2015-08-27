package ours.model;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Read Class is used to read files that the other classes require.
 *
 * @author John Harris
 */
public class Read {
    
    final private String directory;
    private File file;
    
    /**
     * Main Constructor
     *
     */
    public Read(){
        
        //Sets the directory where the necessary files are located
        directory = System.getProperty("user.dir")+"-src-ours-model-".
                replace("-", System.getProperty("file.separator"));
        
    }
    
    /**
     * Reads the user.txt File into a table. The rows of the table are the 
     * Users (HashMaps that contain the User's information) and the columns of
     * the Table are the data fields (the keys for the HashMap), userID, name,
     * password, and department.
     *
     * @return The table that contains all of the user data.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ArrayList<HashMap<String, String>> readUserFile() throws 
            FileNotFoundException, IOException{
        
        //Sets the File to the users.txt file
        file = new File(directory + "users.txt");
        
        FileReader fr = new FileReader(file);
        ArrayList<String> lines;//The list to contain the Strings read from the file
        ArrayList<HashMap<String, String>> userTable;//The list to contain the table of User information
        
        try (BufferedReader reader = new BufferedReader(fr)) {
            
            lines = new ArrayList();
            userTable = new ArrayList<>();
            String currentLine;
            
            //Read the file into the lines list
            while((currentLine = reader.readLine()) != null){
                
                lines.add(currentLine);
                
            }
            
            //Close the File reader
            reader.close();
            
        }
        
        //Iterate through the lines of the file
        for(String line : lines){
            
            //Split the line into fields
            String [] split = line.split(":");
            HashMap<String, String> fields = new HashMap<>();
            
            //Assign the values to the keys of the HashMap
            fields.put("userID", split[0]);
            fields.put("password", split[1]);
            fields.put("name", split[2]);
            fields.put("department", split[3]);
            
            //Add the HashMap to the list
            userTable.add(fields);
            
        }
        
        return userTable;
        
    }
    
    /**
     * Reads each line of the courses.txt file into a list of Courses.
     *
     * @return The list of Courses from the file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ArrayList<Course> readCourseFile() throws FileNotFoundException, 
            IOException{
        
        //Set the File to the courses.txt file
        file = new File(directory + "courses.txt");
        
        FileReader fr = new FileReader(file);
        ArrayList<String> lines;
        ArrayList<Course> courseList;
        
        //Create the reader
        try (BufferedReader reader = new BufferedReader(fr)) {
            
            lines = new ArrayList<>();
            courseList = new ArrayList<>();
            
            String currentLine;
            
            //Read each line of the file into a list
            while((currentLine = reader.readLine()) != null){
                
                lines.add(currentLine);
                
            }
            
            //Close the reader
            reader.close();
            
        }
        
        //Iterate through the list of lines from the file
        for(String line : lines){
            
            //Split the line into fields
            String[] split = line.split(":");
            ArrayList<String> studentIDList = new ArrayList<>();
            ArrayList<Student> enrolledStudents = new ArrayList();
            
            //Add all of the Student user ID's to a list
            for (int i = 8; i < split.length; i++) {
                
                studentIDList.add(split[i]);
                
            }
            
            
            ArrayList<HashMap<String, String>> userTable = readUserFile();
            Instructor instructor = null;
            
            //Create the Instructor from the user ID in the file
            for(HashMap<String, String> row : userTable){
                
                if(row.get("userID").equals(split[6])){
                    
                    instructor = new Instructor(row.get("name"), 
                            row.get("password"), 
                            row.get("userID"), 
                            row.get("department"));
                    
                }
                
            }
            
            //Create the Students that are enrolled in the Course from the user ID's from the file
            for(String id : studentIDList){
                
                for(HashMap<String, String> row : userTable){
                    
                    if(row.get("userID").equals(id)){
                        
                        enrolledStudents.add(new Student(row.get("userID"), 
                                row.get("name"), 
                                row.get("password"), 
                                row.get("department")));
                        
                        break;
                        
                    }
                    
                }
                
            }
            
            //Create the new Course from the information from the file and add it to the list of Courses
            courseList.add(new Course(Integer.parseInt(split[0]), split[1], 
                    Integer.parseInt(split[2]), split[3], 
                    split[4], Integer.parseInt(split[5]), 
                    instructor, split[7], enrolledStudents));
            
        }
        
        return courseList;
        
    }
    
}