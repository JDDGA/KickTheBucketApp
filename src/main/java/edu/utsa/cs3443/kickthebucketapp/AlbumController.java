package edu.utsa.cs3443.kickthebucketapp;

import edu.utsa.cs3443.kickthebucketapp.model.AlbumData;
import edu.utsa.cs3443.kickthebucketapp.model.Photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * controls the photo album screen and manages photos
*/
public class AlbumController {

    @FXML
    private FlowPane albumContainer;

    /**
     * Refreshes the current album so all currently stored photos are displayed
    */
    @FXML
    public void initialize() {
        refreshAlbum();
    }

    /**
     * Return to menu button (arrow in the top left of every screen except the main screen)
     * Throws IOException if the main-menu FXML file cannot be loaded (most likely a file pathing issue)
     */
    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/main-menu.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Will open a file chooser which will give the user an option to select an
     * image and an option to enter a caption. When a photo is added the album
     * display will refresh.
     */
    @FXML
    private void handleAddPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Photo");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) {
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Photo");
        dialog.setHeaderText("Add a caption (optional)");
        dialog.setContentText("Caption:");

        Optional<String> result = dialog.showAndWait();
        String caption = result.map(String::trim).orElse("");

        AlbumData.photos.add(new Photo(selectedFile.toURI().toString(), caption));
        refreshAlbum();
    }

    /**
    * will clear and rebuild the photo album display using all current photos
     * stored inside albumData, and will display an instruction message if
     * the photo album is currently empty
    */
    private void refreshAlbum() {
        albumContainer.getChildren().clear();

        if (AlbumData.photos.isEmpty()) {
            Label emptyLabel = new Label("No photos yet, tap \"Add Photo\" to get started!");
            emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #A0A0A0;");
            albumContainer.getChildren().add(emptyLabel);
            return;
        }

        for (Photo photo : AlbumData.photos) {
            createPhotoTile(photo);
        }
    }

    /**
    * creates the visual tile for a photo containing the image, caption, and
     * a delete button. The completed tile of the image is added to album container
    */
    private void createPhotoTile(Photo photo) {

        ImageView imageView = new ImageView();
        imageView.setFitWidth(140);
        imageView.setFitHeight(140);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        try {
            imageView.setImage(new Image(photo.getImagePath(), 140, 140, true, true));
        } catch (Exception ignored) {
            // if image can't be loaded, just leave the thumbnail blank
        }

        Label captionLabel = new Label(photo.getCaption() == null || photo.getCaption().isEmpty() ? " " : photo.getCaption());
        captionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");
        captionLabel.setWrapText(true);
        captionLabel.setMaxWidth(140);

        Button deleteButton = new Button("X");
        deleteButton.setStyle("-fx-background-color: #B85B5B; -fx-text-fill: white; -fx-background-radius: 8;");
        deleteButton.setOnAction(event -> {
            AlbumData.photos.remove(photo);
            refreshAlbum();
        });

        VBox tile = new VBox(6, imageView, captionLabel, deleteButton);
        tile.setAlignment(Pos.CENTER);
        tile.setStyle("-fx-background-color: #4A4A4A;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: #6A6A6A;" + "-fx-border-width: 2;" + "-fx-padding: 10;");
        tile.setPrefWidth(150);

        albumContainer.getChildren().add(tile);
    }
}
