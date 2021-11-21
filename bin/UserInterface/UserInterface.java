package UserInterface;

import DatabaseHandler.DatabaseHandler;
import FileHandler.FileHandler;
import StateHandler.StateHandler;

public abstract class UserInterface {

  private StateHandler m_stateHandler;
  private FileHandler m_fileHandler;
  private DatabaseHandler m_databaseHandler;

  public abstract void Display();

  public void ChangeDimensions(int rows, int cols) {
    m_stateHandler.getGrid().ChangeDimensions(rows, cols);
  }

  protected int getRows() {
    return m_stateHandler.getGrid().getRows();
  }

  protected int getColumns() {
    return m_stateHandler.getGrid().getColumns();
  }

  public void setCellState(int row, int col, boolean value) {
    m_stateHandler.getGrid().setCellState(row, col, value);
  }

  protected boolean getCellState(int row, int col) {
    return m_stateHandler.getGrid().getCellState(row, col);
  }

  public String File_viewStates() {
    return m_fileHandler.viewStates();
  }

  public void File_loadState(String StateName) {

    m_stateHandler.setGrid(m_fileHandler.loadState(StateName));

  }

  public void File_saveState(String StateName) {
    m_fileHandler.saveState(m_stateHandler.getGrid(), StateName);
  }

  public void File_deleteState(String StateName) {
    m_fileHandler.deleteState(StateName);
  }

  public String DB_viewStates() {
      return m_databaseHandler.viewStates();
  }

  public void DB_loadState(String StateName) {
      m_stateHandler.setGrid(m_databaseHandler.loadState(StateName));
  }

  public void DB_saveState(String StateName) {
      m_databaseHandler.saveState(m_stateHandler.getGrid(), StateName);
  }

  public void DB_deleteState(String StateName) {
      m_databaseHandler.deleteState(StateName);
  }

  public void start() {
    m_stateHandler.startState();
  }

  public void stop() {
    m_stateHandler.stopState();
  }

  public void reset() {
    m_stateHandler.resetState();
  }

  public void next() {
    m_stateHandler.nextState();
  }

  public void clear() {
    m_stateHandler.clearState();
  }

  public int getGeneration() {
    return m_stateHandler.getGeneration();
  }

  public boolean isRunning() {
    return m_stateHandler.isRunning();
  }

  public void setStateHandler(StateHandler m_stateHandler) {
    this.m_stateHandler = m_stateHandler;
  }

  public void setFileHandler(FileHandler m_fileHandler) {
    this.m_fileHandler = m_fileHandler;
  }
}
