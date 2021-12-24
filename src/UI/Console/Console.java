package src.UI.Console;

import java.util.ArrayList;
import java.util.Scanner;

import src.Interfaces.GridInterface.*;
import src.Interfaces.StateInterface.*;
import src.Interfaces.StorageInterface.*;
import src.Interfaces.UserInterface.*;

import java.io.*;

public class Console extends UserInterface {

  public Console(StateInterface stateHandler, GridInterface gridHandler, StorageInterface storageHandler) {
    setStateHandler(stateHandler);
    setGridHandler(gridHandler);
    setStorageHandler(storageHandler);
  }

  final String ANSI_RESET = "\u001B[0m";
  // Define color constants
  final String TEXT_RESET = "\u001B[0m";
  final String TEXT_BLACK = "\u001B[30m";
  final String TEXT_RED = "\u001B[31m";
  final String TEXT_GREEN = "\u001B[32m";
  final String TEXT_YELLOW = "\u001B[33m";
  final String TEXT_BLUE = "\u001B[34m";
  final String TEXT_PURPLE = "\u001B[35m";
  final String TEXT_CYAN = "\u001B[36m";
  final String TEXT_WHITE = "\u001B[37m";


  // Bold
  final String BLACK_BOLD = "\033[1;30m";  // BLACK
  final String RED_BOLD = "\033[1;31m";    // RED
  final String GREEN_BOLD = "\033[1;32m";  // GREEN
  final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
  final String BLUE_BOLD = "\033[1;34m";   // BLUE
  final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
  final String CYAN_BOLD = "\033[1;36m";   // CYAN
  final String WHITE_BOLD = "\033[1;37m";  // WHITE

  // Underline
  final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
  final String RED_UNDERLINED = "\033[4;31m";    // RED
  final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
  final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
  final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
  final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
  final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
  final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

  // Background
  final String BLACK_BACKGROUND = "\033[40m";  // BLACK
  final String RED_BACKGROUND = "\033[41m";    // RED
  final String GREEN_BACKGROUND = "\033[42m";  // GREEN
  final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
  final String BLUE_BACKGROUND = "\033[44m";   // BLUE
  final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
  final String CYAN_BACKGROUND = "\033[46m";   // CYAN
  final String WHITE_BACKGROUND = "\033[47m";  // WHITE


  private void cls() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception E) {
      System.out.println(E);
    }
  }

  private void submenu() {

    while (true) {
      String choice;
      Scanner myObj = new Scanner(System.in); // Create a Scanner objectf for string
      Scanner myObj1 = new Scanner(System.in); // Create a Scanner object for integer

      System.out.println(ANSI_RESET + RED_BACKGROUND +"\n        ------#  Menu #---------- "+ANSI_RESET);
      System.out.print(YELLOW_BOLD + "\n 1. To Save Current state "); // call save adn input
      System.out.print("\n 2. to Update Current state "); // set cells
      System.out.print("\n 3. To Continue "); // set cells
      System.out.print("\n 4. To Return to main menu "); // set cells

      System.out.print("\n Enter # --> ");

      choice = myObj.nextLine(); // Read user input

      if (choice.equals("1")) {

        ArrayList<String> arrlist = viewStates();
        Boolean check = false;
        String filena;

        do {
          System.out.print(" --> Enter name : ");
          filena = myObj.nextLine(); // Read user input
          check = false;

          for (int i = 0; i < arrlist.size(); i++) {
            if (arrlist.get(i).equals(filena)) {
              check = true;
              System.out.print(" --> A State of this name already exist    ");
            }
          }

        } while (check == true);
        
        saveState(filena);

      } else if (choice.equals("2")) {
        while (true) {

          System.out.print("\n --> Enter coordinates or -1 to stop\n");
          System.out.print("\n --> Enter row number : ");
          int ro = myObj1.nextInt(); // Read user input
          if (ro == -1) {
            break;
          }
          System.out.print(" --> Enter columns number : ");
          int co = myObj1.nextInt(); // Read user input
          if (co == -1) {
            break;
          }
          setCellState(ro, co, true);

        }
      } else if (choice.equals("3")) {
        StartGameLoop();

      } else if (choice.equals("4")) {
        break;
      } else {
      }
    }

  }

  // create menu here
  public void Display() {

    while (true) {

      cls();
      System.out.println(ANSI_RESET + RED_BACKGROUND + "\n        ------#  Menu #----------        " + ANSI_RESET);
      System.out.println(YELLOW_BOLD  + "\n 1. Show saved states " );
      System.out.print(YELLOW_BOLD + " 2. Create a new Grid ");
      System.out.print(YELLOW_BOLD + "\n 3. Quit ");

      System.out.print("\n Enter # --> ");

      Scanner myObj = new Scanner(System.in); // Create a Scanner object for strings
      Scanner myObj1 = new Scanner(System.in); // Create a Scanner object integers

      String userName = myObj.nextLine(); // Read user input

      if (userName.equals("1")) {
        ArrayList<String> arrlist = viewStates();

        if (arrlist.size() != 0) {
          System.out.print("\n-->List of Saves States. \n");
          for (int i = 0; i < arrlist.size(); i++) {
            System.out.println(i + 1 + " --> " + arrlist.get(i));
          }
          String saveORdelete;
          do {

            System.out.print("\n Enter 0 to Run state or 1 Delete state : ");
            saveORdelete = myObj.nextLine(); // Read user input

          } while (!saveORdelete.equals("0") && !saveORdelete.equals("1"));

          boolean flag = false;
          do {
            System.out.print("\n Enter State name : ");

            String s = myObj.nextLine(); // Read user input

            for (int i = 0; i < arrlist.size(); i++) {
              if (s.equals(arrlist.get(i))) {
                flag = true;
              }
            }

            if (flag == true) {
              if (saveORdelete.equals("0")) {

                loadState(s);
                StartGameLoop();
                submenu();

              } else if (saveORdelete.equals("1")) {

                deleteState(s);

              }
            } else if (flag == false) {

              System.out.print("\n Enter valid file name...! ");

            }

          } while (flag == false);
        } else {
          System.out.print("\n Sorry. You have no saved states to view. ");
        }
      } else if (userName.equals("2")) {

        System.out.print("\n Enter the size of Grid. ");
        System.out.print("\n --> Enter number of rows : ");
        int r = myObj1.nextInt(); // Read user input
        System.out.print(" --> Enter number of columns : ");
        int c = myObj1.nextInt(); // Read user input

        ChangeDimensions(r, c);

        while (true) {
          cls();

          System.out.print("\n --> Enter coordinates or -1 to stop\n");
          System.out.print("\n --> Enter row number : ");
          int ro = myObj1.nextInt(); // Read user input
          if (ro == -1) {
            break;
          }
          System.out.print(" --> Enter columns number : ");
          int co = myObj1.nextInt(); // Read user input
          if (co == -1) {
            break;
          }
          setCellState(ro, co, true);

        }
        StartGameLoop();

        submenu();
      } else if (userName.equals("3")) {
        break;
      } else {

      }

    }
  }

  private void PrintGrid() {

    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < getColumns(); j++) {

        if (getCellState(i, j)) {
          System.out.print(GREEN_BOLD+" + "+ANSI_RESET);
        } else {
          System.out.print(RED_BOLD + " - "+ANSI_RESET);
        }
      }

      System.out.print("\n");
    }

    System.out.print("\n\n");
  }

  private void StartGameLoop() {
    // Create a Scanner object

    start();

    Thread GameLoop = new Thread(new Runnable() {
      public void run() {

        Scanner myObj = new Scanner(System.in);

        Thread InputGame = new Thread(new Runnable() {
          public void run() {

            if (myObj.hasNext()) {
            //  String userName = myObj.nextLine(); // Read user input
              stop();

            }
          }
        });

        InputGame.start();

        while (isRunning()) {
          cls();
          PrintGrid();
          next();

          System.out.print("\n Enter any character to stop..! ");

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        }
      }
    });

    GameLoop.start();

  }

}
