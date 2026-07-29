package edu.utsa.cs3443.kickthebucketapp;

import edu.utsa.cs3443.kickthebucketapp.model.AlbumData;
import edu.utsa.cs3443.kickthebucketapp.model.GoalData;
import edu.utsa.cs3443.kickthebucketapp.model.ProfileData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ProfileController {

    @FXML
    private ImageView avatarImageView;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea bioField;

    @FXML
    private Label activeGoalsLabel;

    @FXML
    private Label completedGoalsLabel;

    @FXML
    private Label photoCountLabel;


    @FXML
    public void initialize() {
        nameField.setText(ProfileData.name);
        bioField.setText(ProfileData.bio);
        loadAvatar();
        refreshStats();
    }

    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/main-menu.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleChangePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Profile Photo");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            ProfileData.avatarPath = selectedFile.toURI().toString();
            loadAvatar();
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        ProfileData.name = nameField.getText() == null ? "" : nameField.getText().trim();
        ProfileData.bio = bioField.getText() == null ? "" : bioField.getText().trim();

        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Profile Saved");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Your profile has been updated!");
        confirmation.showAndWait();
    }

    private void loadAvatar() {
        if (ProfileData.avatarPath != null) {
            try {
                avatarImageView.setImage(new Image(ProfileData.avatarPath, 140, 140, true, true));
            } catch (Exception ignored) {
                // fall back to an empty avatar if the image can't be loaded
            }
        }
    }

    private void refreshStats() {
        activeGoalsLabel.setText(String.valueOf(GoalData.activeGoals.size()));
        completedGoalsLabel.setText(String.valueOf(GoalData.completedGoals.size()));
        photoCountLabel.setText(String.valueOf(AlbumData.photos.size()));
    }
}
