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

    /**
     * Vbox inside the scroll pane where every created goal is placed
     */
    @FXML
    private VBox goalsContainer;

    /**
     *Runs when the goal screen is loaded up, also rebuilds the current visible
     * goal list from GoalData
     */
    @FXML
    public void initialize() {
        refreshGoals();
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
     * Add goal button, pops up a new box that can be typed in to add a goal
     * outer if statement necessary or will cause issues
     */
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

    /**
     * Clears the current display in the vbox and creates a row for every goal
     * inside GoalData.activeGoals
     */
    private void refreshGoals(){
        goalsContainer.getChildren().clear();

        for(String goal : GoalData.activeGoals){
            createGoalRow(goal);
        }
    }

    /**
     * Creates all the visual controls for each goal being the
     *  Checkbox, goal text, spacer and the delete button, and is finally added
     *  to the goal container
     */
    private void createGoalRow(String goal) {

        /*
         * Checkbox to complete goal and moves it from activeGoals to completedGoals
         */
        CheckBox completedBox = new CheckBox();

        /*
         * will display the goal text, with longer texts being shortened using
         * ellipsis unless the label is clicked on
         */
        Label goalLabel = new Label(goal);

        goalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        goalLabel.setPrefWidth(250);
        goalLabel.setMaxWidth(250);

        goalLabel.setWrapText(false);
        goalLabel.setTextOverrun(OverrunStyle.ELLIPSIS);

        /*
         * This is for collapsing or expanding the label, will wrap the text
         * when expanded and collapsed will keep it single line ending with "..." (Ellipsis)
         */
        goalLabel.setOnMouseClicked(event -> {
            boolean expanded = !goalLabel.isWrapText();

            goalLabel.setWrapText(expanded);

            if(expanded){
                goalLabel.setMaxHeight(Double.MAX_VALUE);
            } else {
                goalLabel.setMaxHeight(Region.USE_PREF_SIZE);
            }
        });

        /*
         *Delete button to remove a goal without adding to completed goals
         */

        Button deleteButton = new Button("X");
        deleteButton.setStyle("-fx-background-color: #B85B5B;" + "-fx-text-fill: white;" + "-fx-background-radius: 8;");

        /*
         * the full visual for one goal. Row automatically adjusts with height when
         * expanded or collapsed
         */
        HBox goalRow = new HBox(10);
        goalRow.setStyle("-fx-background-color: #4A4A4A;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: #6A6A6A;" + "-fx-border-width: 2;" + "-fx-padding: 10;");
        goalRow.setAlignment(Pos.CENTER_LEFT);
        goalRow.setMinHeight(Region.USE_COMPUTED_SIZE);
        goalRow.setPrefHeight(Region.USE_COMPUTED_SIZE);
        goalRow.setMaxHeight(Region.USE_COMPUTED_SIZE);

        /*
         * invisible space so that the X button appears on the far right at all times
         */
        Region spacer = new Region();
        HBox.setHgrow(spacer,Priority.ALWAYS);

        goalRow.getChildren().addAll(completedBox, goalLabel,spacer, deleteButton);

        /*
         * completing a goal using the checkbox sends it to GoalData.completedGoals
         * and removes it from GoalData.activeGoals
         */
        completedBox.setOnAction(event -> {
            GoalData.activeGoals.remove(goal);
            GoalData.completedGoals.add(goal);
            refreshGoals();
        });

        /*
         *Delete button to remove a goal without adding to completed goals/ journal
         */
        deleteButton.setOnAction(event -> {

            GoalData.activeGoals.remove(goal);

            refreshGoals();
        });
        /*
         * Adds the completed row to the Vbox inside the ScrollPane
         */
        goalsContainer.getChildren().add(goalRow);
    }
}
