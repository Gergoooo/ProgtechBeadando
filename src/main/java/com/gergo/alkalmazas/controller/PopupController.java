package com.gergo.alkalmazas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupController {
    @FXML
    private Label popupLabel;
    @FXML
    private Button okBtn;

    public void setPopupLabel(String value){
        popupLabel.setText(value);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
}
