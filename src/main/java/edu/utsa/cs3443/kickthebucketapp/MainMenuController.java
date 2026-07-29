package edu.utsa.cs3443.kickthebucketapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import java.io.IOException;

public class MainMenuController {
    @FXML
    private Label welcomeText;


    @FXML
    private void handleViewGoalsButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/goals-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void handleJournalButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/journal-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void handleProfileButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/profile-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void handlePhotoAlbumButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/photoalbum-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}
