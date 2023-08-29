package peko;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StorageHandler {
    private static Task[] todoList;
    private static int size;
    public StorageHandler() {
        todoList = SaveHandler.loadFrom();
        size = SaveHandler.size();
    }
    public static void readArray() {
        int i = 0;
        System.out.println("--------------LIST-PEKO------------------");
        while (todoList[i] != null) {
            System.out.println(i+1 + ". " + todoList[i]);
            i++;
        }
        if (i == 0) {
            System.out.println("You are FREE PEKO!!!!!");
        }
    }
    public static void addToArray(Task t) {
        todoList[size] = t;
        System.out.println("Added: \n   " + todoList[size].toString() + "\nPeko!");
        System.out.println("You have: " + (size+1) + " tasks now Peko");
        SaveHandler.saveTo();
    }
    public static void setMarkArray(int i) {
        todoList[i-1].setMark();
        System.out.println("Marked as done peko!");
        System.out.println("    " + todoList[i-1]);
        SaveHandler.saveTo();
    }
    public static void setUnmarkArray(int i) {
        todoList[i-1].setUnmark();
        System.out.println("You haven't done this yet peko?!");
        System.out.println("    " + todoList[i-1]);
        SaveHandler.saveTo();
        System.out.println("Added: \n   " + todoList[size].toString() + "\npeko.Peko!");
        System.out.println("You have: " + (size+1) + " tasks now peko.Peko");
        size++;
        SaveHandler.saveTo();
    }
    public static void setMarkArray(int i) {
        try {
            todoList[i-1].setMark();
            System.out.println("Marked as done peko!");
            System.out.println("    " + todoList[i-1]);
            SaveHandler.saveTo();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You don't have that many Tasks peko.Peko!");
        }
    }
    public static void setUnmarkArray(int i) {
        try {
            todoList[i-1].setUnmark();
            System.out.println("You haven't done this yet peko?!");
            System.out.println("    " + todoList[i-1]);
            SaveHandler.saveTo();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You don't have that many Tasks peko.Peko!");
        }
    }
    public static void setDelete(int i) {
        i--;
        while (i <= size) {
            todoList[i] = todoList[i+1];
            i++;
        }
        size--;
        SaveHandler.saveTo();
    }
    public static void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }

}
