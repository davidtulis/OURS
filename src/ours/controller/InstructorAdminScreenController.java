package ours.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import ours.model.Course;
import ours.model.Instructor;
import ours.model.InstructorSchedule;

/**
 * FXML Controller class
 *
 * @author John Harris
 * @author David Tulis
 * Group 5
 */
public class InstructorAdminScreenController implements Initializable {
    
    private final Instructor instructor = (Instructor) LoginController.user;
    private InstructorSchedule schedule;
    private Course selectedCourse;
    
    @FXML ListView<Course> instructorScheduleView;
    @FXML Label courseID;
    @FXML Label courseName;
    @FXML Label courseSection;
    @FXML Label courseDays;
    @FXML Label courseTime;
    @FXML Label courseCapacity;
    @FXML Label courseInstructor;
    @FXML Label courseDepartment;
    @FXML Button printButton;
    @FXML Button logoutButton;
    
    /**
     * sets visibility of info labels to true, and sets the info label text
     * @param event when a class is clicked in listview 
     */
    public void handleListViewSelection(MouseEvent event){
        
        selectedCourse = instructorScheduleView.getSelectionModel()
                .getSelectedItem();
        
        if (courseID.isVisible()==false) { 
            courseID.setVisible(true);
            courseName.setVisible(true);
            courseSection.setVisible(true);
            courseDays.setVisible(true);
            courseTime.setVisible(true);
            courseCapacity.setVisible(true);
            courseInstructor.setVisible(true);
            courseDepartment.setVisible(true);  
        } 
        
        courseID.setText(String.valueOf(selectedCourse
                .getCourseID()));
        courseName.setText(selectedCourse.getCourseName());
        courseSection.setText(String
                .valueOf(selectedCourse.getCourseSection()));
        courseDays.setText(selectedCourse.getCourseDays());
        courseTime.setText(selectedCourse.getCourseTime());
        courseCapacity.setText(String
                .valueOf(selectedCourse.getCourseCapacity()));
        courseInstructor.setText(selectedCourse
                .getInstructor().getName());
        courseDepartment.setText(selectedCourse
                .getDepartment());
        
    }
    /**
     * displays a window that says it is printed
     * @param event when button is clicked
     */
    public void handlePrintButton(ActionEvent event){
        
        JOptionPane.showMessageDialog(null, "Printing...");
        
    }
    
    /**
     * returns the user to the login screen
     * @param event when button is clicked
     * @throws IOException in case file is not found
     */
    public void handleLogoutButton(ActionEvent event) throws IOException{
        
        String path = System.getProperty("user.dir") + 
                "-src-ours-view-LoginScreen.fxml".replace("-", 
                        System.getProperty("file.separator"));
        
        File file = new File(path);
        Stage stage = new Stage();
        Stage current = (Stage) logoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(file.toURI().toURL());
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("OURS Login");
        
        current.close();
        stage.show();
        
    }

    /**
     * Initializes the controller class.
     * populates the listview, and initially sets info label visibility to false
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
            schedule = new InstructorSchedule(instructor);
            
        } 
        
        catch (IOException ex) {
            
            Logger.getLogger(StaffAdminScreenController.class.getName())
                    .log(Level.SEVERE, null, ex);
            
        }
        
        instructorScheduleView.setItems(FXCollections.
                observableArrayList(schedule.getCourseList()));
        
        courseID.setVisible(false);
        courseName.setVisible(false);
        courseSection.setVisible(false);
        courseDays.setVisible(false);
        courseTime.setVisible(false);
        courseCapacity.setVisible(false);
        courseInstructor.setVisible(false);
        courseDepartment.setVisible(false);
        
    }    
    
}
