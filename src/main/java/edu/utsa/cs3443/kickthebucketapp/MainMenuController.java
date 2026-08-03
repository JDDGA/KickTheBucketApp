package edu.utsa.cs3443.kickthebucketapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * controls the main menu and handles navigation to the other screens
*/
public class MainMenuController {

    /**
     *  Sends user to the View Goals screen (GoalsController and goals-screen)
    */

    @FXML
    private void handleViewGoalsButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/goals-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *  Sends user to the Journal screen (JournalController and journal-screen)
     */
    @FXML
    private void handleJournalButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/journal-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *  Sends user to the Profile screen (ProfileController and profile-screen)
     */
    @FXML
    private void handleProfileButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/profile-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *  Sends user to the Photo Album screen (AlbumController and photoalbum-screen)
     */
    @FXML
    private void handlePhotoAlbumButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/photoalbum-screen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}
