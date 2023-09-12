package peko;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import peko.memory.SaveHandler;
import peko.memory.StorageHandler;

public class Peko extends Application {
    private UserInputHandler userInputHandler;
    private StorageHandler storageHandler;


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image peko = new Image(this.getClass().getResourceAsStream("/Pics/tumblr_67c47d22da73ac2ba89e1e97bce6e525_76dfa232_400.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/Pics/tumblr_cd531dc8ea0423c248426f9f8cf65f72_1a341469_1280.png"));


    public static void main(String[] args) {
        //Application.launch(GUIController.class, args);
        new Peko().run();

    }

    public void run() {
        Output.intro();
        while (true) {
            userInputHandler.newInput();
            if (!userInputHandler.processInput()) {
                break;
            }
            SaveHandler.saveTo();
        }
        System.out.println("End");
        Output.exit();

    }

    public String getResponse(String s) {
        userInputHandler.newInput(s);
        return userInputHandler.getResponse();
    }
    public Peko() {
        userInputHandler = new UserInputHandler();
        storageHandler = new StorageHandler();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("PekoBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    private void handleUserInput() {
        String text = userInput.getText();
        Label userText = new Label(text);
        userInputHandler.newInput(text);
        userInputHandler.processInput();
        Label dukeText = new Label(getResponse(userInput.getText()));
        DialogBox userDB = DialogBox.getUserDialog(userText, new ImageView(user));
        DialogBox pekoDB = DialogBox.getPekoDialog(dukeText, new ImageView(peko));
        dialogContainer.getChildren().addAll(
                userDB,
                pekoDB
        );
        userInput.clear();
    }

}
