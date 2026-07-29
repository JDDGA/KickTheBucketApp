package edu.utsa.cs3443.kickthebucketapp;

import edu.utsa.cs3443.kickthebucketapp.model.JournalData;
import edu.utsa.cs3443.kickthebucketapp.model.JournalEntry;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class JournalController {


    @FXML
    private VBox journalContainer;

    @FXML
    public void initialize() {
        JournalData.syncFromGoals();
        refreshJournal();
    }

    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/main-menu.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    private void refreshJournal() {
        journalContainer.getChildren().clear();

        if (JournalData.entries.isEmpty()) {
            Label emptyLabel = new Label("Completed goals will show up here!");
            emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #A0A0A0;");
            journalContainer.getChildren().add(emptyLabel);
            return;
        }

        for (JournalEntry entry : JournalData.entries) {
            createJournalRow(entry);
        }
    }

    private void createJournalRow(JournalEntry entry) {

        Label goalLabel = new Label(entry.getGoalText());
        goalLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");
        goalLabel.setWrapText(true);
        goalLabel.setMaxWidth(300);

        Label dateLabel = new Label("Completed " + entry.getFormattedDate());
        dateLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #A0A0A0;");

        Label reflectionLabel = new Label();
        reflectionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #D0D0D0; -fx-font-style: italic;");
        reflectionLabel.setWrapText(true);
        reflectionLabel.setMaxWidth(300);
        reflectionLabel.setText(entry.hasReflection() ? entry.getReflection() : "");
        reflectionLabel.setManaged(entry.hasReflection());
        reflectionLabel.setVisible(entry.hasReflection());

        Button noteButton = new Button(entry.hasReflection() ? "Edit Note" : "Add Note");
        noteButton.setStyle("-fx-background-color: #4A4A4A; -fx-text-fill: white; -fx-background-radius: 8;");
        noteButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog(entry.getReflection());
            dialog.setTitle("Reflection");
            dialog.setHeaderText("How did it go?");
            dialog.setContentText("Note:");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                entry.setReflection(result.get().trim());
                refreshJournal();
            }
        });

        Button deleteButton = new Button("X");
        deleteButton.setStyle("-fx-background-color: #B85B5B; -fx-text-fill: white; -fx-background-radius: 8;");
        deleteButton.setOnAction(event -> {
            JournalData.removeEntry(entry);
            refreshJournal();
        });

        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER_LEFT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonRow.getChildren().addAll(noteButton, spacer, deleteButton);

        VBox entryCard = new VBox(6);
        entryCard.setStyle("-fx-background-color: #4A4A4A;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: #6A6A6A;" + "-fx-border-width: 2;" + "-fx-padding: 12;");
        entryCard.getChildren().addAll(goalLabel, dateLabel, reflectionLabel, buttonRow);

        journalContainer.getChildren().add(entryCard);
    }
}
