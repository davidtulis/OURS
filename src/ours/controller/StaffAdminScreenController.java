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
import ours.model.DepartmentSchedule;
import ours.model.Staff;

/**
 * Controls the staff admin screen where staff can add, edit, and view
 * departmental classes
 *
 * @author John Harris
 * @author David Tulis Group 5
 */
public class StaffAdminScreenController implements Initializable {

    private static final Staff staff = (Staff) LoginController.user;
    public static DepartmentSchedule schedule;
    public static Course selectedCourse = null;

    @FXML
    static ListView<Course> departmentScheduleView;
    @FXML
    Label courseID;
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
    Button logoutButton;
    @FXML
    Button addCourseButton;
    @FXML
    Button removeCourseButton;

    /**
     * when someone clicks on a class in the ListView, information displays
     * below set label visibilities to true, and gets class info for display
     * labels
     *
     * @param event clicking on course
     */
    public void handleListViewSelection(MouseEvent event) {

        selectedCourse = departmentScheduleView.getSelectionModel()
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
     * opens the screen where new classes can be added
     *
     * @param event click on button
     * @throws IOException when file is not found
     */
    public void handleAddCourseButton(ActionEvent event) throws IOException {

        String path = System.getProperty("user.dir")
                + "-src-ours-view-EditCourseScreen.fxml".replace("-",
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
     * removes the course from the departmental and the master schedule resets
     * the listview
     *
     * @param event click on button
     */
    public void handleRemoveCourseButton(ActionEvent event) {

        selectedCourse = departmentScheduleView.getSelectionModel()
                .getSelectedItem();

        schedule.removeCourses(selectedCourse);

        departmentScheduleView.setItems(FXCollections
                .observableArrayList(schedule.getCourseList()));

    }

    /**
     * puts the users to the admin screen
     *
     * @param event click on button
     * @throws MalformedURLException in case file is not found
     * @throws IOException in case file is not found
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
     * refresh the Listview
     *
     * @throws IOException in case file is not found
     */
    public static void refresh() throws IOException {

        schedule = new DepartmentSchedule(staff.getDepartment());

        departmentScheduleView.setItems(FXCollections
                .observableArrayList(schedule.getCourseList()));

    }

    /**
     * displays a window that says it is printed
     *
     * @param event when button is clicked
     */
    public void handlePrintButton(ActionEvent event) {

        JOptionPane.showMessageDialog(null, "Printing...");

    }

    /**
     * populates listview, and hides the labels until the user clicks on a class
     * from listview
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            schedule = new DepartmentSchedule(staff.getDepartment());

        } catch (IOException ex) {

            Logger.getLogger(StaffAdminScreenController.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        departmentScheduleView.setItems(FXCollections.
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
