package ours.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ours.model.Course;
import ours.model.StudentSchedule;

/**
 * Controls registration screen where the master schedule is displayed this is
 * only used by students
 *
 * @author John Harris
 * @author David Tulis Group 5
 */
public class RegistrationScreenController implements Initializable {

    ObservableList<Course> availableCourseList = null;
    private Course selectedCourse;
    private final StudentSchedule schedule = StudentAdminScreenController.schedule;

    @FXML
    ListView<Course> availableCourseView;
    @FXML
    Button registerButton;
    @FXML
    Button exitButton;
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

    /**
     * handles when the user clicks a class from the Listview sets label
     * visiblity to true and gets course information to display
     *
     * @param event when class is clicked
     */
    public void handleListViewSelection(MouseEvent event) {

        selectedCourse = availableCourseView.getSelectionModel()
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
     * add courses to the schedule and refreshes the schedule
     *
     * @param event when button is clicked
     * @throws IOException in case file is not found
     */
    public void handleRegisterButton(ActionEvent event) throws IOException {

        schedule.addCourses(selectedCourse);

        refresh();

    }

    /**
     * closes the screen and returns when to the previous window
     *
     * @param event when button is clicked
     */
    public void handleExitButton(ActionEvent event) {

        Stage current = (Stage) exitButton.getScene().getWindow();
        current.close();

    }

    /**
     * refreshes class list
     *
     * @throws IOException in case fxml file is not found
     */
    public void refresh() throws IOException {

        availableCourseList = FXCollections
                .observableArrayList(StudentAdminScreenController.schedule
                        .getAvailableCourses(schedule.getCourseList()));

        availableCourseView.setItems(availableCourseList);

        StudentAdminScreenController.refresh();

    }

    /**
     * Initializes the controller class. set visibility of labels to false, and
     * populates the listview
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            availableCourseList = FXCollections
                    .observableArrayList(StudentAdminScreenController.schedule
                            .getAvailableCourses(schedule.getCourseList()));

            availableCourseView.setItems(availableCourseList);

            courseID.setVisible(false);
            courseName.setVisible(false);
            courseSection.setVisible(false);
            courseDays.setVisible(false);
            courseTime.setVisible(false);
            courseCapacity.setVisible(false);
            courseInstructor.setVisible(false);
            courseDepartment.setVisible(false);

        } catch (IOException ex) {

            Logger.getLogger(RegistrationScreenController.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

    }

}
