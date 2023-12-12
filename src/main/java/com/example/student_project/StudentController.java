package com.example.student_project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    Connection con = null;
    PreparedStatement st=null;
    ResultSet rs = null;


    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tBirth;

    @FXML
    private TextField tCourse;

    @FXML
    private TextField tFName;

    @FXML
    private TextField tLastName;

    @FXML
    private TableColumn<Student, String> colBirthDate;

    @FXML
    private TableColumn<Student, String> colCourse;

    @FXML
    private TableColumn<Student, Integer> colId;

    @FXML
    private TableColumn<Student, String> colfName;

    @FXML
    private TableColumn<Student, String> collName;

    @FXML
    private TableView<Student> table;

    int id = 0; // pour l'update

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Etape -- 2");
         showStudents();
    }

    public ObservableList<Student> getStudents(){
         System.out.println("Etape -- 3");

        ObservableList<Student> students = FXCollections.observableArrayList();

        String querySelect = "select * from students ";
        con = DBConnexion.getCon();
        try {
            st = con.prepareStatement(querySelect);
            rs = st.executeQuery();
            while (rs.next()){
                Student st= new Student();
                st.setId(rs.getInt("id"));
                st.setFirstName(rs.getString("FirstName"));
                st.setLastName(rs.getString("LastName"));
                st.setBirthDate(rs.getString("BirthDate"));
                st.setCourse(rs.getString("Course"));
                students.add(st);
                System.out.println("*********st"+st.getBirthDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

       return students;


//return null;
    }

    public void showStudents(){
        ObservableList<Student> list= getStudents();
        table.setItems(list);
        colId.setCellValueFactory( new PropertyValueFactory<Student,Integer>("id"));
        colfName.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        collName.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<Student,String>("birthDate"));
        colCourse.setCellValueFactory(new PropertyValueFactory<Student,String>("course"));

    }

    @FXML
    void clearStudent(ActionEvent event) {
        clear();
    }

    @FXML
    void creatStudent(ActionEvent event) {
        String queryInsert = " insert into students (firstName, lastName, birthDate, course ) values (? , ? , ? , ?) ";
        con = DBConnexion.getCon();

        try {
            st = con.prepareStatement(queryInsert);
            st.setString(1, tFName.getText());
            st.setString(2, tLastName.getText());
            st.setString(3, tBirth.getText());
            st.setString(4, tCourse.getText());
            st.executeUpdate();
            clear();
            showStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getData(MouseEvent event) {
        Student student = table.getSelectionModel().getSelectedItem();
        id = student.getId();
        tFName.setText(student.getFirstName());
        tLastName.setText(student.getLastName());
        tBirth.setText(student.getBirthDate());
        tCourse.setText(student.getCourse());
        btnSave.setDisable(true);
    }

    void clear(){
        tFName.setText(null);
        tLastName.setText(null);
        tBirth.setText(null);
        tCourse.setText(null);
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        String queryDelete = " delete from students where id = ? ";
        con = DBConnexion.getCon();
        try {
            st = con.prepareStatement(queryDelete);
            st.setInt(1,id);
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateStudent(ActionEvent event) {
        String queryUpdate = " update students set firstName = ?, lastName = ?, birthDate = ? , course = ?  where id = ? ";
        con = DBConnexion.getCon();

        try {
            st = con.prepareStatement(queryUpdate);
            st.setString(1, tFName.getText());
            st.setString(2, tLastName.getText());
            st.setString(3, tBirth.getText());
            st.setString(4, tCourse.getText());
            st.setInt(5 , id);
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

