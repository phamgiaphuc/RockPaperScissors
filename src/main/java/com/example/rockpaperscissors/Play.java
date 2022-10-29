package com.example.rockpaperscissors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Play implements Initializable {
    @FXML
    private Label player;
    @FXML
    private Label playerScore;
    @FXML
    private Label computerScore;
    @FXML
    private Label totalRound;

    //Variables
    int computerNumberChoice;
    int playerNumberChoice;
    int p_score = 0;
    int c_score = 0;
    int round = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player.setText(Player.name + " score:");
        computerChoice();
    }

    public void computerChoice() {
        totalRound.setText(String.valueOf(round));
        int[] array = {1, 2, 3}; // {rock, paper, scissors}
        computerNumberChoice = array[new Random().nextInt(array.length)];
        System.out.println("Computer choice: " + computerNumberChoice);
    }

    /**
     * Player Choice
     */
    //Rock
    public void onRockClick(ActionEvent event) {
        round+= 1;
        playerNumberChoice = 1;
        System.out.println("Player choice: " + playerNumberChoice);
        if (computerNumberChoice == 1) {
            tie(event);
        } else if (computerNumberChoice == 2) {
            c_score += 1;
            lose(event);
        } else {
            p_score += 1;
            win(event);
        }
    }

    //Paper
    public void onPaperClick(ActionEvent event) {
        round += 1;
        playerNumberChoice = 2;
        System.out.println("Player choice: " + playerNumberChoice);
        if (computerNumberChoice == 2) {
            tie(event);
        } else if (computerNumberChoice == 3) {
            c_score += 1;
            lose(event);
        } else {
            p_score += 1;
            win(event);
        }
    }

    //Scissors
    public void onScissorsClick(ActionEvent event) {
        round += 1;
        playerNumberChoice = 3;
        System.out.println("Player choice: " + playerNumberChoice);
        if (computerNumberChoice == 3) {
            tie(event);
        } else if (computerNumberChoice == 1) {
            c_score += 1;
            lose(event);
        } else {
            p_score += 1;
            win(event);
        }
    }

    /**
     * Result Situations
     */
    public void tie(ActionEvent event) {
        Alert lose = new Alert(Alert.AlertType.CONFIRMATION, "Both tie" , ButtonType.OK);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        lose.setContentText("Both tie!");
        lose.initModality(Modality.APPLICATION_MODAL);
        lose.initOwner(stage);
        lose.showAndWait();
        if (lose.getResult() == ButtonType.OK) {
            computerChoice();
        }
    }

    public void lose(ActionEvent event) {
        computerScore.setText(String.valueOf(c_score));
        Alert lose = new Alert(Alert.AlertType.CONFIRMATION, "Computer win" , ButtonType.OK);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        lose.setContentText("You lose. Computer win!");
        lose.initModality(Modality.APPLICATION_MODAL);
        lose.initOwner(stage);
        lose.showAndWait();
        if (lose.getResult() == ButtonType.OK) {
            computerChoice();
        }
    }

    public void win(ActionEvent event) {
        playerScore.setText(String.valueOf(p_score));
        Alert win = new Alert(Alert.AlertType.CONFIRMATION, "Player win" , ButtonType.OK);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        win.setContentText("Computer lose. You win!");
        win.initModality(Modality.APPLICATION_MODAL);
        win.initOwner(stage);
        win.showAndWait();
        if (win.getResult() == ButtonType.OK) {
            computerChoice();
        }
    }

    /**
     * Functions
     */
    //Back Button
    public void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("hello-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    public void totalPlayerPoint(ActionEvent event) throws IOException {
        if (round > 1) {
            round -= 1;
        }
        Alert summary = new Alert(Alert.AlertType.CONFIRMATION, "Player summary", ButtonType.OK);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        summary.setContentText(Player.name + " scores " + p_score + " after " + round + " rounds.");
        summary.initModality(Modality.APPLICATION_MODAL);
        summary.initOwner(stage);
        summary.showAndWait();
        if (summary.getResult() == ButtonType.OK) {
            onBackButtonClick(event);
        }
    }
}
