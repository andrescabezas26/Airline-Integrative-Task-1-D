package model;

import dataStructures.*;

public class Plane {

    private String airline;
    private int numRows;
    private int numFirstClass;
    private int chairsForRow;
    private int totalChairs;
    private HashTable<String, Passenger> passengersInfo;

    public Plane(String airline, int numRows, int numFirstClass, int chairsForRow) {
        this.airline = airline;
        this.numRows = numRows;
        this.numFirstClass = numFirstClass;
        this.chairsForRow = chairsForRow;
        this.totalChairs = chairsForRow * numRows;
        this.passengersInfo = new HashTable<>(totalChairs);
    }

    /**
     * @return String return the airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * @param airline the airline to set
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * @return int return the numRows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * @param numRows the numRows to set
     */
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    /**
     * @return int return the numFirstClass
     */
    public int getNumFirstClass() {
        return numFirstClass;
    }

    /**
     * @param numFirstClass the numFirstClass to set
     */
    public void setNumFirstClass(int numFirstClass) {
        this.numFirstClass = numFirstClass;
    }

    /**
     * @return int return the chairsForRow
     */
    public int getChairsForRow() {
        return chairsForRow;
    }

    /**
     * @param chairsForRow the chairsForRow to set
     */
    public void setChairsForRow(int chairsForRow) {
        this.chairsForRow = chairsForRow;
    }

    /**
     * @return int return the totalChairs
     */
    public int getTotalChairs() {
        return totalChairs;
    }

    /**
     * @param totalChairs the totalChairs to set
     */
    public void setTotalChairs(int totalChairs) {
        this.totalChairs = totalChairs;
    }

    /**
     * @return HashTable<String, Passenger> return the passengersInfo
     */
    public HashTable<String, Passenger> getPassengersInfo() {
        return passengersInfo;
    }

    /**
     * @param passengersInfo the passengersInfo to set
     */
    public void setPassengersInfo(HashTable<String, Passenger> passengersInfo) {
        this.passengersInfo = passengersInfo;
    }

}