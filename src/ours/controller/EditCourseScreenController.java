package ours.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ours.model.Course;
import ours.model.DepartmentSchedule;
import ours.model.Instructor;
import ours.model.Read;

/**
 * FXML Controller class
 *
 * @author John Harris
 * @author David Tulis Group 5
 */
public class EditCourseScreenController implements Initializable {

    private final Course course = StaffAdminScreenController.selectedCourse;
    private final DepartmentSchedule schedule = StaffAdminScreenController.schedule;
    private final ObservableList<Instructor> instructorsList
            = FXCollections.observableArrayList();

    @FXML
    Label courseName;
    @FXML
    Label courseSection;
    @FXML
    Label courseDays;
    @FXML
    Label courseTime;
    @FXML
    Label courseCapacity;
    @FXML
    Label courseInstructor;
    @FXML
    Label courseDepartment;
    @FXML
    TextField courseNameTextField;
    @FXML
    TextField courseSectionTextField;
    @FXML
    TextField courseDaysTextField;
    @FXML
    TextField courseTimeTextField;
    @FXML
    TextField courseCapacityTextField;
    @FXML
    ComboBox<Instructor> courseInstructorComboBox;
    @FXML
    TextField courseDepartmentTextField;
    @FXML
    Button cancelButton;
    @FXML
    Button finishButton;

    /**
     * this method will either add a new course the schedule or edit an existing
     * course it is only used by the Staff class
     *
     * @param event button is clicked
     * @throws IOException
     */
    public void handleFinishButton(ActionEvent event) throws IOException {

        //adding new course
        if (course == null) {

            schedule.addCourses(new Course(courseNameTextField.getText(),
                    Integer.parseInt(courseSectionTextField.getText()),
                    courseDaysTextField.getText(),
                    courseTimeTextField.getText(),
                    Integer.parseInt(courseCapacityTextField.getText()),
                    courseInstructorComboBox.getSelectionModel()
                    .getSelectedItem(),
                    courseDepartmentTextField.getText()));

        } //editing existing course
        else {

            course.setInstructor(courseInstructorComboBox.getValue());
            course.setCourseSection(Integer
                    .parseInt(courseSectionTextField.getText()));
            course.setCourseDays(courseDaysTextField.getText());
            course.setCourseName(courseNameTextField.getText());
            course.setCourseTime(courseTimeTextField.getText());
            course.setCourseCapacity(Integer.parseInt(courseCapacityTextField
                    .getText()));

            schedule.addCourses(course);

        }

        StaffAdminScreenController.refresh();

        Stage current = (Stage) finishButton.getScene().getWindow();
        current.close();

    }

    /**
     * makes no changes and returns the user to the previous screen
     *
     * @param event button clicked
     */
    public void handleCancelButton(ActionEvent event) {

        Stage current = (Stage) cancelButton.getScene().getWindow();
        current.close();

    }

    /**
     * Initializes the controller class.
     * search for all instructors and add them to the combobox list
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<HashMap<String, String>> table;
        try {

            table = new Read().readUserFile();
            for (HashMap<String, String> row : table) {

                if (row.get("userID").matches("[a-c]{3}[0-9]{3}")
                        && row.get("department").equals(schedule.getDepartment())) {

                    instructorsList.add(new Instructor(row.get("name"),
                            row.get("password"),
                            row.get("userID"),
                            row.get("department")));

                }

            }

        } catch (IOException ex) {

            Logger.getLogger(EditCourseScreenController.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        courseInstructorComboBox.setItems(instructorsList);

        //this happens when a course is selected and the user wants to edit a course value
        if (course != null) {

            courseNameTextField.setText(course.getCourseName());
            courseSectionTextField.setText(String.valueOf(course
                    .getCourseSection()));
            courseDaysTextField.setText(course.getCourseDays());
            courseTimeTextField.setText((course.getCourseTime()));
            courseCapacityTextField.setText(String.valueOf(course
                    .getCourseCapacity()));
            courseInstructorComboBox.setValue(course.getInstructor());
            courseDepartmentTextField.setText(course.getDepartment());

        }

    }

}
