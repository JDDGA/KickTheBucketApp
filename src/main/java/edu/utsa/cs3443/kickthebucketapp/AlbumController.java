package edu.utsa.cs3443.kickthebucketapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class AlbumController {
// test 1 commit
    @FXML
    private void handleReturn(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/main-menu.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

}
