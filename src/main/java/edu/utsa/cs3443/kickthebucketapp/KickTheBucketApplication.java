package edu.utsa.cs3443.kickthebucketapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KickTheBucketApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KickTheBucketApplication.class.getResource("/edu/utsa/cs3443/kickthebucketapp/layouts/main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 393, 852);
        stage.setTitle("Kick The Bucket");
        stage.setScene(scene);
        stage.show();
    }
}
