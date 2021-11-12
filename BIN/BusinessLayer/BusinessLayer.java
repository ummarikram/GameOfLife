package BusinessLayer;

import Grid.Grid;

public class BusinessLayer {
   private Grid Grid;

   public BusinessLayer() {

   }

   public void Start() {

   }

   public void Stop() {

   }

   public void next() {
      applyrules();
   }

   public void reset() {

     
   }

   private void applyrules() {
      // Any live cell with two or three live neighbours survives.
      // Any dead cell with three live neighbours becomes a live cell.
      /// All other live cells die in the next generation. Similarly, all other dead
      // cells stay dead.
      // RULE 1

      for (int i = 0; i < Grid.getRows(); i++) {
         for (int j = 0; j < Grid.getColumns(); j++) {
            // Any live cell with two or three live neighbours survives.

            if (Grid.getCellState(i, j) == true) {
               if (Grid.getCellNeighbours(i, j) < 2) {
                  Grid.setCellState(i, j, false);
               } else if (Grid.getCellNeighbours(i, j) > 3) {
                  Grid.setCellState(i, j, false);
               }

            } else {
               if (Grid.getCellNeighbours(i, j) == 3) {
                  Grid.setCellState(i, j, true);
               }
            }
         }

      }
   }

}