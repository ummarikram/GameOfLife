package bin.UI.Console;

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

  // create menu here
  public void Display() {
    
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

  public void StartGameLoop()
  {
      start();

      Thread GameLoop = new Thread(new Runnable() {
        public void run() {
            while (isRunning()) {
              
                Display();
                next();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cls();
            }
        }
    });

    GameLoop.start();
  }

}
