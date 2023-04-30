package model;

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
    private Heap<Integer, String> boardingOrder;
    private Heap<Integer, String> disembarkationOrder;
    private String arrivalList;
    private String desembarkationList;

    public Plane(String airline, int numRows, int numFirstClass, int chairsForRow) {
        this.airline = airline;
        this.numRows = numRows;
        this.numFirstClass = numFirstClass;
        this.chairsForRow = chairsForRow;
        this.totalChairs = chairsForRow * numRows;
        this.passengersInfo = new HashTable<>(totalChairs);
        this.arrivalList = "";
        this.desembarkationList = "";

    }

    /**
     * Crea la cola de prioridad de abordaje y desabordaje del Avión
     * 
     * @return
     */
    public void createBoardingAndDisembarkationOrder() {
        this.boardingOrder = new Heap<>(this.totalPassengers);
        this.disembarkationOrder = new Heap<>(this.totalPassengers);
    }

    /**
     * Imprime la lista de Abordaje tomando en cuenta si hay dos prioridades
     * iguales y poniendo primero al haya llegado primero
     * 
     * @return lista de orden de abordaje de los pasajeros
     */
    public String printListBoardingOrder() {
        String msj = "\n<< BOARDING ARRIVAL LIST >> \n";
        Heap<Integer, String> ba = boardingOrder;

        Node<Integer, String> ps = null;
        Node<Integer, String> ps2 = null;
        for (int index = 0; index < ba.getArray().length; index += 2) {

            if (index == 29) {
                break;
            }
            try {
                ps = ba.heapExtracMax(ba.getArray());
                ps2 = ba.heapExtracMax(ba.getArray());

                /////////////

                if (ba.getArray()[index] != null) {
                    if (ps.getKey().equals(ps2.getKey())) {
                        Passenger passenger1 = passengersInfo.getValue(ps.getValue());
                        Passenger passenger2 = passengersInfo.getValue(ps2.getValue());
                        if (compareArrival(passenger1, passenger2) == 1) {
                            msj += "" + (index + 1) + ") " + ps.getValue() + "\t"
                                    + ps.getKey() + "\n";
                            msj += "" + (index + 2) + ") " + ps2.getValue() + "\t"
                                    + ps2.getKey() + "\n";
                        } else {
                            msj += "" + (index + 1) + ") " + ps2.getValue() + "\t"
                                    + ps2.getKey() + "\n";
                            msj += "" + (index + 2) + ") " + ps.getValue() + "\t"
                                    + ps.getKey() + "\n";
                        }
                    } else {
                        msj += "" + (index + 1) + ") " + ps.getValue() + "\t"
                                + ps.getKey() + "\n";
                        msj += "" + (index + 2) + ") " + ps2.getValue() + "\t"
                                + ps2.getKey() + "\n";
                    }
                }

                //////// 7
            } catch (HeapUnderflow e) {
                System.out.println("Heap");
            }
        }

        setArrivalList(msj);

        return msj;
    }
    /**
     * Imprime la lista de Desbordaje tomando en cuenta si hay dos prioridades
     * iguales y poniendo primero al haya llegado primero
     * 
     * @return lista de orden de desabordaje de los pasajeros
     */
    public String printListDisembarkationOrder() {
        String msj = "\n<< DISEMBARKATION LIST >> \n";
        Heap<Integer, String> dd = disembarkationOrder;

        Node<Integer, String> ps = null;
        Node<Integer, String> ps2 = null;
        for (int index = 0; index < disembarkationOrder.getArray().length; index += 2) {

            if (index == 29) {
                break;
            }
            try {
                ps = dd.heapExtracMax(dd.getArray());
                ps2 = dd.heapExtracMax(dd.getArray());

                /////////////

                if (dd.getArray()[index] != null) {
                    if (ps.getKey().equals(ps2.getKey())) {
                        Passenger passenger1 = passengersInfo.getValue(ps.getValue());
                        Passenger passenger2 = passengersInfo.getValue(ps2.getValue());
                        if (compareArrival(passenger1, passenger2) == 1) {
                            msj += "" + (index + 1) + ") " + ps.getValue() + "\t"
                                    + ps.getKey() + "\n";
                            msj += "" + (index + 2) + ") " + ps2.getValue() + "\t"
                                    + ps2.getKey() + "\n";
                        } else {
                            msj += "" + (index + 1) + ") " + ps2.getValue() + "\t"
                                    + ps2.getKey() + "\n";
                            msj += "" + (index + 2) + ") " + ps.getValue() + "\t"
                                    + ps.getKey() + "\n";
                        }
                    } else {
                        msj += "" + (index + 1) + ") " + ps.getValue() + "\t"
                                + ps.getKey() + "\n";
                        msj += "" + (index + 2) + ") " + ps2.getValue() + "\t"
                                + ps2.getKey() + "\n";
                    }
                }

                //////// 7
            } catch (HeapUnderflow e) {
                System.out.println("Heap");
            }
        }

        setDesembarkationList(msj);

        return msj;
    }
    /**
     * Calcula el número de llegada de un pasajero al vuelo
     * @param passenger pasajero que será calculado
     * @return número de llegada del pasajero al avión
     */
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
    /**
     * Compara la llegada de dos pasajeros del avión y devuelve un valor dependiendo de quien haya llegado primero
     * @param p1 pasajero 1
     * @param p2 pasajero 2
     * @return 1 si el pasajero 1 llegó primero que el 2, y -1 si es lo contrario.
     */
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
    /**
     * Lee el orden de llegada de los pasajeros del archivo order.txt y retorna un String con el orden
     * @return orden de llegada de los pasajeros
     */
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
     * @return Heap<Integer, String> return the boardingOrder
     */
    public Heap<Integer, String> getBoradingOrder() {
        return boardingOrder;
    }

    /**
     * @param boardingOrder the boardingOrder to set
     */
    public void setBoradingOrder(Heap<Integer, String> boardingOrder) {
        this.boardingOrder = boardingOrder;
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

    /**
     * @return String return the arrivalList
     */
    public String getArrivalList() {
        return arrivalList;
    }

    /**
     * @param arrivalList the arrivalList to set
     */
    public void setArrivalList(String arrivalList) {
        this.arrivalList = arrivalList;
    }

    /**
     * @return String return the desembarkationList
     */
    public String getDesembarkationList() {
        return desembarkationList;
    }

    /**
     * @param desembarkationList the desembarkationList to set
     */
    public void setDesembarkationList(String desembarkationList) {
        this.desembarkationList = desembarkationList;
    }

}