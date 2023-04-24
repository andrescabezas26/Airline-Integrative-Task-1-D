package model;

import dataStructures.*;

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
                msj += "<<Passenger>> \n" + current.getValue().toString() + "\n\n";
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
            addPassengersToHashtable(passengersInfo);
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

    public void addPassengersToHashtable(String passengersInfo) {
        String[] lines = passengersInfo.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] infoPassenger = lines[i].split("::");
            Passenger passenger = new Passenger(infoPassenger[0], infoPassenger[1], Integer.parseInt(infoPassenger[2]),
                    infoPassenger[3], Boolean.parseBoolean(infoPassenger[4]), Boolean.parseBoolean(infoPassenger[5]),
                    Boolean.parseBoolean(infoPassenger[6]), Integer.parseInt(infoPassenger[7]));
            plane.getPassengersInfo().add(passenger.getId(), passenger);
            passenger.setPriorityBoarding(calculateBoardingPriority(passenger));
        }
    }

    public int calculateBoardingPriority(Passenger passenger) {
        File projectDir = new File(System.getProperty("user.dir"));
        FileReader archivo = null;
        BufferedReader lector = null;

        String passengersOrder = "";
        try {
            archivo = new FileReader(projectDir + "/data/order.txt");
            lector = new BufferedReader(archivo);

            String linea = lector.readLine();
            while (linea != null) {
                passengersOrder += linea + "\n";
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

        String[] orderList= passengersOrder.split("\n");
        int priority = 0;
        for (int i = 0; i < orderList.length; i++) {
            if (orderList[i].equals(passenger.getId())) {
                priority += orderList.length-i;
                break;
            }
        }
        if (passenger.getFirstClass()) {
            priority += 1; // La prioridad de ser primera clase
            if (passenger.getPregnant()) {
                priority+=1;
            }
            if (passenger.getOldAge()) {
                priority+=1;
            }
            priority += passenger.getAccumulatedMiles();
        }
        priority += passenger.getRow();

        return priority;
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

}
