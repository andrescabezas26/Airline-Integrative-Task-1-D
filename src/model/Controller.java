package model;

import dataStructures.*;
import exceptions.KeyIsSmaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controller
 */
public class Controller {

    private Plane plane;

    public Controller() {

    }

    public String printListPassengers() {

        String msj = "";

        if (plane == null) {
            return "No loaded data";
        }

        Node<String, Passenger>[] table = plane.getPassengersInfo().getTable();
        for (int i = 0; i < plane.getPassengersInfo().getSizeTable(); i++) {
            Node<String, Passenger> current = table[i];
            while (current != null) {
                msj += "<< Passenger >> \n" + current.getValue().toString() + "\n\n";
                current = current.getNext();
            }

        }

        return msj;

    }

    public String loadData() {
        File projectDir = new File(System.getProperty("user.dir"));

        FileReader archivo = null;
        BufferedReader lector = null;
        String msj = "No se ha podido cargar la informacion";

        try {
            archivo = new FileReader(projectDir + "/data/data.txt");
            lector = new BufferedReader(archivo);

            String linea = lector.readLine();
            String infoPlane = "";
            String passengersInfo = "";
            while (!linea.equals("Passengers")) {
                infoPlane += linea + "\n";
                linea = lector.readLine();
            }

            msj = createPlane(infoPlane);

            while (linea != null) {
                passengersInfo += linea + "\n";
                linea = lector.readLine();
            }
            addPassengersToDataStructures(passengersInfo);
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

        return msj;
    }

    public String createPlane(String planeInfo) {
        String[] lines = planeInfo.split("\n");
        int[] numbers = new int[4];
        for (int i = 1; i < lines.length - 2; i++) {
            String[] info = lines[i].split("::");
            numbers[i] = Integer.parseInt(info[0]);
        }
        plane = new Plane(lines[0], numbers[1], numbers[2], numbers[3]);

        String msj = String.format(
                "The plane information is: \nAirline: %s \nNumber of rows: %s \nNumber of first class rows: %s \nNumber of chairs per row: %s",
                plane.getAirline(), plane.getNumRows(), plane.getNumFirstClass(), plane.getChairsForRow());

        return msj;
    }

    public void addPassengersToDataStructures(String passengersInfo) {
        String[] lines = passengersInfo.split("\n");
        String passengersArrivalOrder = readPassengersArrivalOrder();
        plane.setTotalPassengers(lines.length - 1);
        plane.createBoardingArrivalOrder();
        for (int i = 1; i < lines.length; i++) {
            String[] infoPassenger = lines[i].split("::");
            Passenger passenger = new Passenger(infoPassenger[0], infoPassenger[1], Integer.parseInt(infoPassenger[2]),
                    infoPassenger[3], Boolean.parseBoolean(infoPassenger[4]), Boolean.parseBoolean(infoPassenger[5]),
                    Boolean.parseBoolean(infoPassenger[6]), Integer.parseInt(infoPassenger[7]));
            /// Calcula la prioridad de abordaje de cada pasajero///
            passenger.setPriorityBoarding(calculateBoardingPriority(passenger, passengersArrivalOrder));
            ////////////////////////////////////////////////////////
            plane.getPassengersInfo().add(passenger.getId(), passenger);

            /// Calcula la prioridad de desbordaje de cada pasajero///
            passenger.setPriorityDisembarking(calculateDisembarkationPriority(passenger, passengersArrivalOrder));
            ////////////////////////////////////////////////////////

            try {
                plane.getBoardingArrivalOrder().maxHeapInsert(plane.getBoardingArrivalOrder().getArray(),
                        new Couple<>(passenger.getPriorityBoarding(), passenger.getId()));
            } catch (KeyIsSmaller e) {
                System.out.println("No Sirvio");
            }

        }
        //

    }

    public int calculateBoardingPriority(Passenger passenger, String passengersArrivalOrder) {

        String[] orderList = passengersArrivalOrder.split("\n");
        int priority = 0;
        for (int i = 0; i < orderList.length; i++) {
            if (orderList[i].equals(passenger.getId())) {
                priority += plane.getTotalChairs() - i;
                break;
            }
        }
        if (passenger.getFirstClass()) {
            priority += 10; // La prioridad de ser primera clase
            if (passenger.getPregnant()) {
                priority += 20;
            }
            if (passenger.getOldAge()) {
                priority += 15;
            }
            priority += passenger.getAccumulatedMiles();
        }

        return priority;
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

    public int calculateDisembarkationPriority(Passenger passenger, String passengersArrivalOrder) {
        String[] orderList = passengersArrivalOrder.split("\n");
        int chairForRow = plane.getChairsForRow();
        int priority = -1 * passenger.getRow();
        int passengerChair = letterToInt(passenger.getChair());

        for (int i = 0; i < orderList.length; i++) {
            if (orderList[i].equals(passenger.getId())) {
                priority -= i;
                break;
            }
        }

        if (chairForRow % 2 == 0) {
            int half1 = chairForRow / 2;
            int half2 = half1 + 1;
            if (passengerChair >= half2) {
                priority -= passengerChair - half2;
            } else {
                priority -= half1 - passengerChair;
            }
        } else {
            Double half = Math.ceil(chairForRow / 2);
            if (passengerChair >= half) {
                priority -= passengerChair - half;
            } else {
                priority -= half - passengerChair;
            }
        }

        return priority;
    }

    /**
     * intToLetter: Uses the ASCII code to get the value of a letter in the alphabet
     * 
     * @param String = The letter of the alphabet
     * @return num : The letter converted to int
     */
    private int letterToInt(String letter) {
        int num = ((char) (letter.charAt(0))) - 64;
        return num;
    }

    /**
     * @return Plane return the plane
     */
    public Plane getPlane() {
        return plane;
    }

    /**
     * @param plane the plane to set
     */
    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String printListBoarding() {
        if (plane == null) {
            return "No loaded data";
        } else {
            return plane.printListBoardingArrivalOrder();
        }

    }

}
