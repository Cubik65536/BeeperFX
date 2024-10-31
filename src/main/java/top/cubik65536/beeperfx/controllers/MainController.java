package top.cubik65536.beeperfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import top.cubik65536.beeperfx.model.Wave;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class MainController {
    private AddWaveDialog dialogBoxController;

    @FXML
    private CheckBox playSoundCheckBox;

    @FXML
    private TableView<Wave> wavesTableView;
    @FXML
    private TableColumn<Wave, String> typeColumn;
    @FXML
    private TableColumn<Wave, Double> frequencyColumn;
    @FXML
    private TableColumn<Wave, Double> amplitudeColumn;

    @FXML
    private Button addWave;

    @FXML
    public void initialize() throws LineUnavailableException, IOException {
        SoundController soundController = new SoundController();

        addWave.setOnAction(event -> {
            dialogBoxController = new AddWaveDialog();
            dialogBoxController.showAndWait();
            Wave newWave = dialogBoxController.getWave();
            System.out.println("newWave: " + newWave);
            if (newWave != null) {
                wavesTableView.getItems().add(newWave);
                try {
                    soundController.addWave(newWave);
                } catch (LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("waveType"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        amplitudeColumn.setCellValueFactory(new PropertyValueFactory<>("amplitude"));

        playSoundCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                soundController.start();
            } else {
                soundController.stop();
            }
        });
    }
}
