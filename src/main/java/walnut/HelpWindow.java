package walnut;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HelpWindow {

    public static void show() {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(HelpWindow.class.getResource("/view/HelpWindow.fxml"));

            AnchorPane helpPane = fxmlLoader.load();

            Stage helpStage = new Stage();
            helpStage.setTitle("Walnut Help");
            helpStage.setScene(new Scene(helpPane));
            helpStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
