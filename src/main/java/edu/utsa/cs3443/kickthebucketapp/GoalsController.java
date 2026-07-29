package edu.utsa.cs3443.kickthebucketapp;

import edu.utsa.cs3443.kickthebucketapp.model.GoalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class GoalsController {

    @FXML
    private VBox goalsContainer;

    @FXML
    public void initialize() {
        refreshGoals();
    }

    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/main-menu.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleAddGoal() {

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Add Goal");
        dialog.setHeaderText("Create a new goal");
        dialog.setContentText("Goal:");

        Optional<String> result = dialog.showAndWait();

        if(result.isPresent()) {
            String newGoal = result.get().trim();

            if (!newGoal.isEmpty()) {
                GoalData.activeGoals.add(newGoal);
                refreshGoals();
            }
        }
    }

    private void refreshGoals(){
        goalsContainer.getChildren().clear();

        for(String goal : GoalData.activeGoals){
            createGoalRow(goal);
        }
    }

    private void createGoalRow(String goal) {
        CheckBox completedBox = new CheckBox();

        Label goalLabel = new Label(goal);

        goalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        goalLabel.setPrefWidth(250);
        goalLabel.setMaxWidth(250);

        goalLabel.setWrapText(false);
        goalLabel.setTextOverrun(OverrunStyle.ELLIPSIS);

        goalLabel.setOnMouseClicked(event -> {
            boolean expanded = !goalLabel.isWrapText();

            goalLabel.setWrapText(expanded);

            if(expanded){
                goalLabel.setMaxHeight(Double.MAX_VALUE);
            } else {
                goalLabel.setMaxHeight(Region.USE_PREF_SIZE);
            }
        });

        Button deleteButton = new Button("X");
        deleteButton.setStyle("-fx-background-color: #B85B5B;" + "-fx-text-fill: white;" + "-fx-background-radius: 8;");

        HBox goalRow = new HBox(10);
        goalRow.setStyle("-fx-background-color: #4A4A4A;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: #6A6A6A;" + "-fx-border-width: 2;" + "-fx-padding: 10;");
        goalRow.setAlignment(Pos.CENTER_LEFT);
        goalRow.setMinHeight(Region.USE_COMPUTED_SIZE);
        goalRow.setPrefHeight(Region.USE_COMPUTED_SIZE);
        goalRow.setMaxHeight(Region.USE_COMPUTED_SIZE);


        Region spacer = new Region();
        HBox.setHgrow(spacer,Priority.ALWAYS);

        goalRow.getChildren().addAll(completedBox, goalLabel,spacer, deleteButton);

        completedBox.setOnAction(event -> {
            GoalData.activeGoals.remove(goal);
            GoalData.completedGoals.add(goal);
            refreshGoals();
        });

        deleteButton.setOnAction(event -> {

            GoalData.activeGoals.remove(goal);

            refreshGoals();
        });

        goalsContainer.getChildren().add(goalRow);
    }
}
