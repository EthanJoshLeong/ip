package walnut;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the main JavaFX application for Walnut.
 *
 * <p>This class initializes the application window, loads the main FXML layout,
 * and connects the {@link Walnut} instance to the {@link MainWindow} controller.</p>
 */
public class Main extends Application {

    private Walnut walnut = new Walnut();

    /**
     * Starts the JavaFX application and displays the main window.
     *
     * @param stage the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWalnut(walnut);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
