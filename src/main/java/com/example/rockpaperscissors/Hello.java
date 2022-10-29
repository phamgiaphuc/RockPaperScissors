package com.example.rockpaperscissors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class Hello extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        music();
        FXMLLoader fxmlLoader = new FXMLLoader(Hello.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rock Paper Scissors Game");
        stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Rock-paper-scissors_de.svg/640px-Rock-paper-scissors_de.svg.png"));
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void music() {
        String s = "src/main/resources/com/example/rockpaperscissors/music/music.mp3";
        Media media = new Media(Paths.get(s).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.5);
    }

    public static void main(String[] args) {
        launch();
    }
}