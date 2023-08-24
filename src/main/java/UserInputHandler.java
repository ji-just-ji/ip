import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInputHandler {
    enum CommandsInternal {
        ECHO,
        OTSUPEKO,
        LIST,
        WRITE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        COPYPASTA

    }
    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Hololive san kisei no\n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";
    private static final String[] commands = new String[]
            {"echo","otsupeko", "list", "write", "mark", "unmark",
                    "todo", "deadline", "event", "delete","tell me a joke"};

    String input;
    CommandsInternal command;
    public UserInputHandler() {
        input = "";
    }
    public CommandsInternal getResponseValue(String s) {
        int output = 0;
        s = s.toLowerCase();
        for (int i = 0; i < commands.length; i++) {
            if (s.startsWith(commands[i])) {
                output = i;
                break;
            }
        }
        String temp = commands[output].toUpperCase().trim();
        System.out.println(temp);
        return CommandsInternal.valueOf(temp);
    }

    public void newInput() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        command = getResponseValue(input);
        input = input.split(" ",2)[1];
        System.out.println(lineBreak);
    }

    private void echo() {
        if (input.isBlank()) {
            System.out.println("You didn't say anything peko?");
        } else {
            System.out.println(input);
        }

        System.out.println(lineBreak);
    }
    private void readArray() {
        Peko.readArray();
    }
    private void addToArray() throws InvalidTaskException {
        Task t = new Task(input);
        Peko.addToArray(t);
        System.out.println(lineBreak);
    }
    private void mark(String s) throws NumberFormatException{
        int i = Integer.parseInt(s);
        Peko.setMarkArray(i);
        System.out.println(lineBreak);
    }
    private void unmark(String s) {
        try {
            int i = Integer.parseInt(s);
            Peko.setUnmarkArray(i);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        }
        System.out.println(lineBreak);
    }
    private void todo(String s) throws InvalidTaskException{
        Task t = new ToDos(input);
        Peko.addToArray(t);
        System.out.println(lineBreak);
    }
    private void deadline(String s) throws InvalidTaskException {
        Task t = new Deadline(input);
        Peko.addToArray(t);
        System.out.println(lineBreak);
    }
    private void Event(String s) throws InvalidTaskException {
        Task t = new Event(input);
        Peko.addToArray(t);
        System.out.println(lineBreak);
    }
    public void delete(String s) {
        int i = Integer.parseInt(s);
        Peko.setDelete(i);
    }


    public boolean run() {
        try {
            switch (command) {
                case ECHO:
                    echo();
                    break;
                case LIST:
                    readArray();
                    break;
                case WRITE:
                    addToArray();
                    break;
                case MARK:
                    mark(input);
                    break;
                case UNMARK:
                    unmark(input);
                    break;
                case TODO:
                    todo(input);
                    break;
                case DEADLINE:
                    deadline(input);
                    break;
                case EVENT:
                    Event(input);
                case DELETE:
                    delete(input);
                    break;
                case COPYPASTA:
                    try {
                        Peko.degen();
                    } catch (FileNotFoundException e) {
                        System.out.println("Hentai!");
                    } finally {
                        return false;
                    }

                case OTSUPEKO:
                    return false;


            }
            return true;
        } catch (InvalidTaskException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        } finally {
            return false;
        }
    }

}
