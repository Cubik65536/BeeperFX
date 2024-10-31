package top.cubik65536.beeperfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import top.cubik65536.beeperfx.controllers.MainController;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, LineUnavailableException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        fxmlLoader.setController(new MainController());
        Scene scene = new Scene(fxmlLoader.load(), 320, 512);
        stage.setTitle("FX Beeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
