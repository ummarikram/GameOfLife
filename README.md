# John Conway's Game Of Life

## BACKGROUND

The game of life is a cellular automation devised by the British mathematician John Horton Conway in 1970. It is a turing complete simulation that evolves based on certain rules.

## RULES

* Any live cell with two or three live neighbours survives.
* Any dead cell with three live neighbours becomes a live cell.
* All other live cells die in the next generation. Similarly, all other dead cells stay dead.

## INSTRUCTIONS

* For using MySQL, you must create the database inside the "sql" folder on your local machine. Then change the username and password variables inside "bin/Storage/Databasehandler/DatabaseHandler.java" according to your machine.

## DESIGN

![Image of Design](https://raw.githubusercontent.com/ummarikram/GameOfLife/main/design/John%20Conway%E2%80%99s%20Game%20of%20Life%20Design.png)


![Image of UML](https://raw.githubusercontent.com/ummarikram/GameOfLife/main/design/UML%20class.jpg)


## FUNCTIONALITIES

### Manually Select/Deselect Cells
  User can select or deselects cell(s) on grid by clicking on them to create different patterns.
  
### Start 
  User can start the game cycle. It stores the reset/initial state in the database/file and then starts to apply all the rules on the current state     and increments counter after every cycle.
  
### Next
  User can get to the next cycle by applying all the rules on the current state. It also increments counter by 1.
  
### Stop
  User can stop the game loop cycle and counter from incrementing.
  
### Reset
  User can reset the game cycle by loading reset/initial state from database/file to the grid and resetting the counter back to 0.

### Save state
  User can save the current state on the grid in a database/file using this function.

### Load state
  User can load any previously saved states from the database/file to the grid.

### Speed control
  User can control the speed of the game cycle by adding appropriate delay in rendering.

### Grid Zoom
  User can zoom in/out of the grid by changing individual cell size rendered on the grid.

### Counter/Generation
  User can use this to keep track of the cycle/generation passed until the initial state.

### View saved states
  User can see the previously saved states by loading them from the database/file.

### Delete saved states
  User can delete any previously saved states from the database/file.

### Clear
  User can clear the grid by removing all selected cells.

## CONTRIBUTORS

[NAWAL ALI](https://github.com/mnawalali4)

[JUNAID AFZAL](https://github.com/MuhammadJunaidAfzal)

[UMAR FAROOQ](https://github.com/Um827)

[HUMA KARIM](https://github.com/humakarim44)

[ABDUL MANNAN](https://github.com/Abdulmannan111)
