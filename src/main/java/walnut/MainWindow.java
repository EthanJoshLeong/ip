package walnut;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 *
 * <p>Handles user input, displays Walnut's responses, and manages
 * the dialog boxes shown in the main window.</p>
 */
public class MainWindow extends AnchorPane {
    private Ui ui = new Ui();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Walnut walnut;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image walnutImage = new Image(this.getClass().getResourceAsStream("/images/walnut.jpg"));

    /**
     * Initializes the main window after its FXML components have been loaded.
     *
     * <p>Binds the scroll pane's vertical position to the height of the
     * dialog container so that the latest messages remain visible.</p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Walnut instance used by this controller and displays
     * Walnut's banner, greeting, and any startup error message.
     *
     * @param w the Walnut instance to be used by the controller
     */
    public void setWalnut(Walnut w) {
        walnut = w;

        String welcome = ui.showBanner() + ui.showGreeting();

        dialogContainer.getChildren().add(
                DialogBox.getWalnutDialog(welcome, walnutImage)
        );

        String startupMessage = walnut.getStartupMessage();
        if (startupMessage != null) {
            dialogContainer.getChildren().add(
                    DialogBox.getWalnutDialog(startupMessage, walnutImage)
            );
        }
    }

    /**
     * Processes the user's input and displays both the user's message
     * and Walnut's response in the dialog container.
     *
     * <p>The user input field is cleared after the message has been processed.</p>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = walnut.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWalnutDialog(response, walnutImage)
        );
        userInput.clear();
    }
}

