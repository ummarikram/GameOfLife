package bin.UI.Console;

import java.util.ArrayList;
import java.util.Scanner;

import bin.Interfaces.GridInterface.*;
import bin.Interfaces.StateInterface.*;
import bin.Interfaces.StorageInterface.*;
import bin.Interfaces.UserInterface.*;

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
public void submenu()
{
 
  while (true) 
  {
    
    Scanner myObj = new Scanner(System.in); // Create a Scanner object


    System.out.print("\n        ------#  Menu #---------- ");
    System.out.print("\n 1. To Save Current state "); // call save adn input
    System.out.print("\n 2. to Update Current state "); // set cells
    System.out.print("\n 3. To continue "); // set cells
    System.out.print("\n 4. To Retuen main menu "); // set cells

    System.out.print("\n Enter # --> ");

   // Scanner myObj3 = new Scanner(System.in); // Create a Scanner object
    int choice = myObj.nextInt(); // Read user input

    if(choice == 1)
    {
      System.out.print(" --> Enter name : ");
      Scanner myObj1 = new Scanner(System.in); // Create a Scanner object
      String filena = myObj1.nextLine(); // Read user input

      saveState(filena);

    } 
    else if(choice == 2)
    {
      while (true) {
       
        System.out.print("\n --> Enter coordinates or -1 to stop\n");
        System.out.print("\n --> Enter row number : ");
        int ro = myObj.nextInt(); // Read user input
        if (ro == -1) {
          break;
        }
        System.out.print(" --> Enter columns number : ");
        int co = myObj.nextInt(); // Read user input
        if (co == -1) {
          break;
        }
        setCellState(ro, co, true);

      }
    }
    else if (choice == 3) {
      StartGameLoop();

    } 
    else if (choice == 4) {
      break;
    }
    else
    {
      continue;
    }
    choice = 0;
  }

} 

  // create menu here
  public void Display() {
    while (true) {
      cls();

      System.out.print("\n        ------#  Menu #---------- ");
      System.out.print("\n 1. Show saved states ");
      System.out.print("\n 2. Create a new Grid ");
      System.out.print("\n 3. Quit ");

      System.out.print("\n Enter # --> ");

      Scanner myObj = new Scanner(System.in); // Create a Scanner object
      int userName = myObj.nextInt(); // Read user input


      if (userName == 1) {
        ArrayList<String> arrlist = viewStates();

        if (arrlist.size() != 0) {
          System.out.print("\nList of Saves States. \n");
          for (int i = 0; i < arrlist.size(); i++) {
            System.out.println(i + 1 + " --> " + arrlist.get(i));
          }
          
          System.out.print("\n Enter 0 to Run state or 1 Delete state : ");
          int saveORdelete = myObj.nextInt(); // Read user input
        

          System.out.print("\n Enter State name : ");

          Scanner myObj1 = new Scanner(System.in); // Create a Scanner object
          String s = myObj1.nextLine(); // Read user input

          if(saveORdelete == 0)
          {
            loadState(s);
            
            StartGameLoop();

            submenu();
          }
          else if(saveORdelete == 1)
          {
            deleteState(s);
            continue;
          }
        }
        else {
          System.out.print("\n Sorry. You have no saved states to view. ");
        }
      } else if (userName == 2) {

        System.out.print("\n Enter the size of Grid. ");
        System.out.print("\n --> Enter number of rows : ");
        int r = myObj.nextInt(); // Read user input
        System.out.print(" --> Enter number of columns : ");
        int c = myObj.nextInt(); // Read user input

        ChangeDimensions(r, c);

        while (true) {
          cls();

          System.out.print("\n --> Enter coordinates or -1 to stop\n");
          System.out.print("\n --> Enter row number : ");
          int ro = myObj.nextInt(); // Read user input
          if (ro == -1) {
            break;
          }
          System.out.print(" --> Enter columns number : ");
          int co = myObj.nextInt(); // Read user input
          if (co == -1) {
            break;
          }
          setCellState(ro, co, true);

        }
        StartGameLoop(); 

        submenu();
      }
      else if (userName == 3) {
        break;
      }
      else
      {
        continue;
      }

    }
  }

  public void PrintGrid() {

    // Display();
    // cls();

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

    System.out.print(" Generation : " + getGeneration());
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
              // int userName = myObj.nextInt(); // Read user input
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
