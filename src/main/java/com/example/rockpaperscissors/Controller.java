package com.example.rockpaperscissors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField textFieldName;
    public String playerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldName.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 15));
    }

    /**
     * Functions
     */
    // Play Button
    public void onPlayButtonClick(ActionEvent event) throws IOException {
        if (textFieldName.getText().equals("")) {
            Alert missingPlayerName = new Alert(Alert.AlertType.WARNING, "Missing player name stage", ButtonType.OK);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            missingPlayerName.setHeaderText("Confirming to enter the name");
            missingPlayerName.setContentText("Missing the player name. Please enter the name!");
            Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/e/ec/Error-icon.png");
            ImageView imageView = new ImageView(image);
            missingPlayerName.setGraphic(imageView);
            missingPlayerName.initModality(Modality.APPLICATION_MODAL);
            missingPlayerName.initOwner(stage);
            missingPlayerName.showAndWait();
        } else {
            playerName = textFieldName.getText().trim();
            Player.name = playerName;
            Alert startToPlay = new Alert(Alert.AlertType.CONFIRMATION, "Starting the play stage", ButtonType.OK, ButtonType.CANCEL);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            startToPlay.setHeaderText("Confirming to play");
            startToPlay.setContentText("Ok " + playerName + ". Are you ready to start the game?");
            Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/f/f4/Check-icon.png");
            ImageView imageView = new ImageView(image);
            startToPlay.setGraphic(imageView);
            startToPlay.initModality(Modality.APPLICATION_MODAL);
            startToPlay.initOwner(stage);
            startToPlay.showAndWait();
            if (startToPlay.getResult() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(Controller.class.getResource("play-view.fxml"));
                Parent addParent = loader.load();
                Scene addScene = new Scene(addParent);
                stage.setScene(addScene);
            }
        }
    }

    // Exit Button
    public void onExitButtonClick(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Exit confirmation", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        exitAlert.setTitle("Exit Stage");
        exitAlert.setContentText("Are you sure you want to exit?");
        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/3/3a/Map_marker_icon_%E2%80%93_Nicolas_Mollet_%E2%80%93_Exit_%E2%80%93_Offices_%E2%80%93_Gradient.png");
        ImageView imageView = new ImageView(image);
        exitAlert.setGraphic(imageView);
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();
        if (exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        } else {
            exitAlert.close();
        }
    }

    // Acus Project Link
    public void onLinkProfileClick() {
        try {
            URL url = new URL("https://github.com/AcusPGP");
            Desktop.getDesktop().browse(url.toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}