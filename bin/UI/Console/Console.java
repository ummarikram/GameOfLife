package bin.UI.Console;

import java.util.ArrayList;
import java.util.Scanner;

import bin.Interfaces.GridInterface.*;
import bin.Interfaces.StateInterface.*;
import bin.Interfaces.StorageInterface.*;
import bin.Interfaces.UserInterface.*;

import java.io.*;

public class Console extends UserInterface {

  public Console(StateInterface stateHandler, GridInterface gridHandler, StorageInterface storageHandler) {
    setStateHandler(stateHandler);
    setGridHandler(gridHandler);
    setStorageHandler(storageHandler);
  }

  public void cls() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception E) {
      System.out.println(E);
    }
  }

  public void submenu() {

    while (true) {
      String choice;
      Scanner myObj = new Scanner(System.in); // Create a Scanner objectf for string
      Scanner myObj1 = new Scanner(System.in); // Create a Scanner object for integer

      // choice = myObj.nextLine(); // Read user input
      // choice = 0;
      System.out.println("\n        ------#  Menu #---------- ");
      System.out.print("\n 1. To Save Current state "); // call save adn input
      System.out.print("\n 2. to Update Current state "); // set cells
      System.out.print("\n 3. To continue "); // set cells
      System.out.print("\n 4. To Retuen main menu "); // set cells

      System.out.print("\n Enter # --> ");

      // Scanner myObj = new Scanner(System.in); // Create a Scanner object
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

      final String ANSI_RESET = "\u001B[0m";
      // Declaring the background color
      final String ANSI_RED_BACKGROUND = "\u001B[41m";

      cls();
      System.out.println(ANSI_RED_BACKGROUND + "\n        ------#  Menu #----------        " + ANSI_RESET);
      System.out.print("\n 1. Show saved states ");
      System.out.print("\n 2. Create a new Grid ");
      System.out.print("\n 3. Quit ");

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

  public void PrintGrid() {

    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < getColumns(); j++) {

        if (getCellState(i, j)) {
          System.out.print(" + ");
        } else {
          System.out.print(" - ");
        }
      }

      System.out.print("\n");
    }

    // System.out.print(" Generation : " + getGeneration());
    System.out.print("\n\n");
  }

  public void StartGameLoop() {
    // Create a Scanner object

    start();

    Thread GameLoop = new Thread(new Runnable() {
      public void run() {

        Scanner myObj = new Scanner(System.in);

        Thread InputGame = new Thread(new Runnable() {
          public void run() {

            if (myObj.hasNext()) {
              String userName = myObj.nextLine(); // Read user input
              stop();

            }
          }
        });

        InputGame.start();

        while (isRunning()) {
          cls();
          PrintGrid();
          next();

          System.out.print("\n Enter any key to stop..! ");

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
