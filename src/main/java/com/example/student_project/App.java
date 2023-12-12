package com.example.student_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/Students.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Gestion des Etudiants");
        stage.setScene(scene);
        stage.show();
    }



    public static void main (String[] args){
        System.out.println("Etape -- 1");
        launch();



    }
}



