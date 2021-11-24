package Interfaces.UserInterface;

import Interfaces.GridHandler.*;
import Interfaces.StateHandler.*; 
import Interfaces.StorageHandler.*; 


public abstract class UserInterface {

  // All of these are interfaces
  private StateHandler m_stateHandler;
  private GridHandler m_gridHandler;
  private StorageHandler m_storageHandler;

  public abstract void Display();

  public int getRows() {

    return m_gridHandler.getRows();

  }

  public int getColumns() {

    return m_gridHandler.getColumns();

  }

  protected boolean getCellState(int row, int col) {
    return m_gridHandler.getCellState(row, col);
  }

  public void ChangeDimensions(int rows, int cols) {
    if (m_gridHandler != null) {
      m_gridHandler.ChangeDimensions(rows, cols);
    }
  }

  public void setCellState(int row, int col, boolean value) {
    if (m_gridHandler != null) {
      m_gridHandler.setCellState(row, col, value);
    }
  }

  public String viewStates() {

    if (m_storageHandler != null) {
      return m_storageHandler.viewStates();
    }

    return null;
  }

  public void loadState(String StateName) {

    if (m_gridHandler != null && m_storageHandler != null) {
      m_gridHandler.setGrid(m_storageHandler.loadState(StateName));
    }
  }

  public void saveState(String StateName) {
    if (m_gridHandler != null && m_storageHandler != null) {
      m_storageHandler.saveState(m_gridHandler.getGrid(), StateName);
    }
  }

  public void deleteState(String StateName) {

    if (m_storageHandler != null) {
      m_storageHandler.deleteState(StateName);
    }
  }

  public void start() {
    if (m_stateHandler != null) {
      m_stateHandler.startState();
    }
  }

  public void stop() {
    if (m_stateHandler != null) {
      m_stateHandler.stopState();
    }
  }

  public void reset() {
    if (m_stateHandler != null) {
      m_stateHandler.resetState();
    }
  }

  public void next() {
    if (m_stateHandler != null) {
      m_stateHandler.nextState();
    }
  }

  public void clear() {
    if (m_stateHandler != null) {
      m_stateHandler.clearState();
    }
  }

  protected int getGeneration() {

    return m_stateHandler.getGeneration();

  }

  protected boolean isRunning() {
    return m_stateHandler.isRunning();
  }

  public void setStateHandler(StateHandler m_stateHandler) {
    this.m_stateHandler = m_stateHandler;
  }

  public void setGridHandler(GridHandler m_gridHandler)
  {
    this.m_gridHandler = m_gridHandler;
  }

  public void setStorageHandler(StorageHandler m_storageHandler)
  {
    this.m_storageHandler = m_storageHandler;
  }

}
