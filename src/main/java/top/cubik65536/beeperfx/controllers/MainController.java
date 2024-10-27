package top.cubik65536.beeperfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import javax.sound.sampled.LineUnavailableException;

public class MainController {
    @FXML
    private CheckBox playSoundCheckBox;

    @FXML
    public void initialize() throws LineUnavailableException {
        SoundController soundController = new SoundController();
        soundController.setUpSound();

        playSoundCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                soundController.start();
            } else {
                soundController.stop();
            }
        });
    }
}
