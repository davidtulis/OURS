package ours.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import ours.model.Student;
import ours.model.StudentSchedule;

/**
 * FXML Controller class
 * Controls the StudentAdminScreen.fxml file
 * @author John Harris
 * @author David Tulis Group 5
 */
public class StudentAdminScreenController implements Initializable {

    private static final Student student = (Student) LoginController.user;
    public static StudentSchedule schedule;
    private Course selectedCourse;

    @FXML
    static ListView<Course> studentScheduleView;
    @FXML
    Button dropCourseButton;
    @FXML
    Button registerButton;
    @FXML
    Button logoutButton;
    @FXML
    Label courseName;
    @FXML
    Label courseSection;
    @FXML
    Label courseDepartment;
    @FXML
    Label courseInstructor;
    @FXML
    Label courseID;
    @FXML
    Label courseCapacity;
    @FXML
    Label courseDays;
    @FXML
    Label courseTime;
    @FXML
    Button printButton;

    /**
     * This method sets the labels at the bottom of the screen to display the
     * currently selected course's info.
     *
     * @param event The user clicks on an item in the ListView
     */
    public void handleListViewSelection(MouseEvent event) {

        selectedCourse = studentScheduleView.getSelectionModel()
                .getSelectedItem();

        if (courseID.isVisible() == false) {
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
     * Opens the registration screen so the student can add a class
     *
     * @param event clicking the button
     * @throws IOException in case the registration screen is not found
     */
    public void handleRegisterButton(ActionEvent event) throws IOException {

        String path = System.getProperty("user.dir")
                + "-src-ours-view-RegistrationScreen.fxml".replace("-",
                        System.getProperty("file.separator"));

        File file = new File(path);

        Parent root = FXMLLoader.load(file.toURI().toURL());

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Edit Course");
        stage.show();

    }

    /**
     * After detecting what class is selected, it removes the course from the
     * schedule
     *
     * @param event action for button event
     */
    public void handleDropCourseButton(ActionEvent event) {

        selectedCourse = studentScheduleView.getSelectionModel()
                .getSelectedItem();

        schedule.removeCourses(selectedCourse);

        studentScheduleView.setItems(FXCollections
                .observableArrayList(schedule.getCourseList()));

    }

    /**
     * Loads the login screen
     *
     * @param event event for when button is clicked
     * @throws MalformedURLException in case the file is not found
     * @throws IOException in case the file is not found
     */
    public void handleLogoutButton(ActionEvent event)
            throws MalformedURLException, IOException {

        String path = System.getProperty("user.dir")
                + "-src-ours-view-LoginScreen.fxml".replace("-",
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
     * reloads the schedule
     *
     * @throws IOException in case file is not found
     */
    public static void refresh() throws IOException {

        schedule = new StudentSchedule(student);

        studentScheduleView.setItems(FXCollections.
                observableArrayList(schedule.getCourseList()));

    }

    /**
     * Initializes the controller class. Populates the ListView, and
     * sets label visibility to false
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            schedule = new StudentSchedule(student);

        } catch (IOException ex) {

            Logger.getLogger(StaffAdminScreenController.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        studentScheduleView.setItems(FXCollections.
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

    /**
     * displays a window that says it is printed
     *
     * @param event when button is clicked
     */
    public void handlePrintButton(ActionEvent event) {

        JOptionPane.showMessageDialog(null, "Printing...");

    }
}
