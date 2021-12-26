package src.Interfaces.UserInterface;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import src.Interfaces.GridInterface.*;
import src.Interfaces.JSONInterface.JSONInterface;
import src.Interfaces.StateInterface.*;
import src.Interfaces.StorageInterface.*; 


public abstract class UserInterface {

  // All of these are interfaces
  private StateInterface m_stateHandler;
  private GridInterface m_gridHandler;
  private StorageInterface m_storageHandler;
  protected JSONInterface m_JSONConverter;

  public abstract void Display();

  protected JSONObject getRows() {

    return m_gridHandler.getRows();

  }

  protected JSONObject getColumns() {

    return m_gridHandler.getColumns();

  }

  protected JSONObject getCellState(JSONObject RowNCol) {
    return m_gridHandler.getCellState(RowNCol);
  }

  protected void ChangeDimensions(JSONObject RowNCol) {
    if (m_gridHandler != null) {
      m_gridHandler.ChangeDimensions(RowNCol);
    }
  }

  protected void setCellState(JSONObject RowNCol) {
    if (m_gridHandler != null) {
      m_gridHandler.setCellState(RowNCol);
    }
  }

  protected JSONObject viewStates() {

    if (m_storageHandler != null) {
      return m_storageHandler.viewStates();
    }

    return null;
  }

  protected void loadState(JSONObject StateName) {

    if (m_gridHandler != null && m_storageHandler != null) {
      m_gridHandler.setGrid(m_storageHandler.loadState(StateName));
    }
  }

  protected void saveState(JSONObject StateName) {
    if (m_gridHandler != null && m_storageHandler != null) {
      JSONObject State = m_JSONConverter.concatjson(StateName, "StateName", m_gridHandler.getGrid(), "Grid");
      m_storageHandler.saveState(State);
    }
  }

  protected void deleteState(JSONObject StateName) {

    if (m_storageHandler != null) {
      m_storageHandler.deleteState(StateName);
    }
  }

  protected void start() {
    if (m_stateHandler != null) {
      m_stateHandler.startState();
    }
  }

  protected void stop() {
    if (m_stateHandler != null) {
      m_stateHandler.stopState();
    }
  }

  protected void reset() {
    if (m_stateHandler != null) {
      m_stateHandler.resetState();
    }
  }

  protected void next() {
    if (m_stateHandler != null) {
      m_stateHandler.nextState();
    }
  }

  protected void clear() {
    if (m_stateHandler != null) {
      m_stateHandler.clearState();
    }
  }

  protected JSONObject getGeneration() {

    return m_stateHandler.getGeneration();

  }

  protected JSONObject isRunning() {
    return m_stateHandler.isRunning();
  }

  public void setStateHandler(StateInterface m_stateHandler) {
    this.m_stateHandler = m_stateHandler;
  }

  public void setGridHandler(GridInterface m_gridHandler)
  {
    this.m_gridHandler = m_gridHandler;
  }

  public void setStorageHandler(StorageInterface m_storageHandler)
  {
    this.m_storageHandler = m_storageHandler;
  }

  public void setJSONParser(JSONInterface Parser)
  {
    this.m_JSONConverter = Parser;
  }
}
