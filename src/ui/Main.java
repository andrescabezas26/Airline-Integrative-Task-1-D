package ui;

import java.util.Scanner;
import model.*;

public class Main {

    private Scanner reader;
    private Controller controller;

    public Main() {
        reader = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {
        Main main = new Main();
        int option = 0;

        do {
            option = main.getOptionShowMenu();
            main.executeOption(option);
        } while (option != 0);

        main.getReader().close();
    }

    /**
     * getOptionMenu: Get the option of the menu
     *
     * @return int
     */
    public int getOptionShowMenu() {
        int option = -1;
        while (option == -1) {
            System.out.println("<<<<< Welcome to Integrative Airline >>>>>");
            System.out.println(
                    "1. Load data of passengers\n" +
                            "2. \n" +
                            "3. \n" +
                            "4. \n" +
                            "0. Salir\n");

            option = validateIntegerOption();
        }

        return option;
    }

    /**
     * executeOption: Execute the option
     *
     * @param option int - The choosen option
     */
    public void executeOption(int option) {
        switch (option) {
            case 1:
                controller.loadData();
                System.out.println("Passengers Data loaded");
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                System.out.println("Program Finish");
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    /**
     * validateIntegerOption: This method checks if a number is an integer
     *
     * @return option - int: Returns the entered number if it is an integer or
     *         returns -1 if it is not an integer
     */
    public int validateIntegerOption() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    /**
     * @return Scanner return the reader
     */
    public Scanner getReader() {
        return reader;
    }
}
