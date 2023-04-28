package model;

import java.util.PriorityQueue;

import dataStructures.*;
import exceptions.HeapUnderflow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Plane {

    private String airline;
    private int numRows;
    private int numFirstClass;
    private int chairsForRow;
    private int totalChairs;
    private int totalPassengers;
    private HashTable<String, Passenger> passengersInfo;
    private Heap<Integer, String> boardingArrivalOrder;
    private Heap<Integer, String> disembarkationOrder;

    public Plane(String airline, int numRows, int numFirstClass, int chairsForRow) {
        this.airline = airline;
        this.numRows = numRows;
        this.numFirstClass = numFirstClass;
        this.chairsForRow = chairsForRow;
        this.totalChairs = chairsForRow * numRows;
        this.passengersInfo = new HashTable<>(totalChairs);

    }

    public void createBoardingArrivalOrder() {
        this.boardingArrivalOrder = new Heap<>(this.totalPassengers);
        this.disembarkationOrder = new Heap<>(this.totalPassengers);
    }

    public String printListBoardingArrivalOrder() {
        String msj = "\n<< BOARDING ARRIVAL LIST >> \n";
        Couple<Integer, String> cp = null;
        for (int index = 0; index < boardingArrivalOrder.getArray().length; index++) {

            try {
                cp = boardingArrivalOrder.heapExtracMax(boardingArrivalOrder.getArray());
            } catch (HeapUnderflow e) {
                System.out.println("Heap");
            }
            if (boardingArrivalOrder.getArray()[index] != null) {
                msj += "" + (index + 1) + ") " + cp.getObject() + "\t"
                        + cp.getKey() + "\n";
            }
        }

        return msj;
    }

    public String printListDisembarkationOrder() {
        String msj = "\n<< DISEMBARKATION LIST >> \n";
        Heap<Integer, String> dd = disembarkationOrder;

        Couple<Integer, String> cp = null;
        Couple<Integer, String> cp2 = null;
        for (int index = 0; index < disembarkationOrder.getArray().length; index += 2) {

            if (index == 29) {
                break;
            }
            try {
                cp = disembarkationOrder.heapExtracMax(dd.getArray());
                cp2 = disembarkationOrder.heapExtracMax(dd.getArray());

                /////////////

                if (dd.getArray()[index] != null) {
                    if (cp.getKey().equals(cp2.getKey())) {
                        Passenger passenger1 = passengersInfo.getValue(cp.getObject());
                        Passenger passenger2 = passengersInfo.getValue(cp2.getObject());
                        if (compareArrival(passenger1, passenger2) == 1) {
                            msj += "" + (index + 1) + ") " + cp.getObject() + "\t"
                                    + cp.getKey() + "\n";
                            msj += "" + (index + 2) + ") " + cp2.getObject() + "\t"
                                    + cp2.getKey() + "\n";
                        } else {
                            msj += "" + (index + 1) + ") " + cp2.getObject() + "\t"
                                    + cp2.getKey() + "\n";
                            msj += "" + (index + 2) + ") " + cp.getObject() + "\t"
                                    + cp.getKey() + "\n";
                        }
                    } else {
                        msj += "" + (index + 1) + ") " + cp.getObject() + "\t"
                                + cp.getKey() + "\n";
                        msj += "" + (index + 2) + ") " + cp2.getObject() + "\t"
                                + cp2.getKey() + "\n";
                    }
                }

                //////// 7
            } catch (HeapUnderflow e) {
                System.out.println("Heap");
            }
        }

        return msj;
    }

    public int calculatePassengerArrival(Passenger passenger) {
        String passengersArrivalOrder = readPassengersArrivalOrder();
        String[] orderList = passengersArrivalOrder.split("\n");
        int pos = 0;
        for (int i = 0; i < orderList.length; i++) {
            if (orderList[i].equals(passenger.getId())) {
                pos = i;
            }
        }
        return pos;
    }

    public int compareArrival(Passenger p1, Passenger p2) {
        int p1Arrival = calculatePassengerArrival(p1);
        int p2Arrival = calculatePassengerArrival(p2);
        if (p1Arrival < p2Arrival) {
            return 1;
        }
        if (p1Arrival > p2Arrival) {
            return -1;
        }

        return 0;
    }

    public String readPassengersArrivalOrder() {
        File projectDir = new File(System.getProperty("user.dir"));
        FileReader archivo = null;
        BufferedReader lector = null;

        String passengersArrivalOrder = "";
        try {
            archivo = new FileReader(projectDir + "/data/order.txt");
            lector = new BufferedReader(archivo);

            String linea = lector.readLine();
            while (linea != null) {
                passengersArrivalOrder += linea + "\n";
                linea = lector.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lector != null)
                    lector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return passengersArrivalOrder;
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

    /**
     * @return int return the totalPassengers
     */
    public int getTotalPassengers() {
        return totalPassengers;
    }

    /**
     * @param totalPassengers the totalPassengers to set
     */
    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    /**
     * @return Heap<Integer, String> return the boardingArrivalOrder
     */
    public Heap<Integer, String> getBoardingArrivalOrder() {
        return boardingArrivalOrder;
    }

    /**
     * @param boardingArrivalOrder the boardingArrivalOrder to set
     */
    public void setBoardingArrivalOrder(Heap<Integer, String> boardingArrivalOrder) {
        this.boardingArrivalOrder = boardingArrivalOrder;
    }

    /**
     * @return Heap<Integer,String> return the disembarkationOrder
     */
    public Heap<Integer, String> getDisembarkationOrder() {
        return disembarkationOrder;
    }

    /**
     * @param disembarkationOrder the disembarkationOrder to set
     */
    public void setDisembarkationOrder(Heap<Integer, String> disembarkationOrder) {
        this.disembarkationOrder = disembarkationOrder;
    }

}