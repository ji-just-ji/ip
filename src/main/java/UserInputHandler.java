import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInputHandler {
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Hololive san kisei no\n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";

    private Commands command;
    private Scanner scanner = new Scanner(System.in);
    private String description;
    public UserInputHandler() {
        new StorageHandler();
    }
    public void newInput() {
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Parser parser = new Parser(input);
        command = parser.getResponseValue();
        description = parser.getDescription();
    }

    public boolean processInput() {
        TaskHandler taskHandler = new TaskHandler(command, description);
        return taskHandler.run();
    }

}