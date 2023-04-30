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

    /**
     * Imprime el ToString de todos los pasajeros del Avión
     * 
     * @return El toString de los pasajeros del Avión
     */
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

    /**
     * Lee el archivo data.txt que contiene la información del avión y de los
     * pasajeros del mismo y retorna un mensaje con toda la información del avión
     * 
     * @return La información del Avión
     */
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

    /**
     * Este método crea un avión con la información suministrada y devuelve un
     * mensaje con la información del Avión creado
     * 
     * @param planeInfo información del Avión sumistrada por el LoadData
     * @return mensaje con los datos del Avión
     */
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

    /**
     * Añade a los pasajeros del Avión a las estructuras de datos (Hastable y Colas
     * de Prioridad)
     * 
     * @param passengersInfo información de los pasajeros sumistrada por LoadData
     */
    public void addPassengersToDataStructures(String passengersInfo) {
        String[] lines = passengersInfo.split("\n");
        plane.setTotalPassengers(lines.length - 1);
        plane.createBoardingAndDisembarkationOrder();
        for (int i = 1; i < lines.length; i++) {
            String[] infoPassenger = lines[i].split("::");
            Passenger passenger = new Passenger(infoPassenger[0], infoPassenger[1], Integer.parseInt(infoPassenger[2]),
                    infoPassenger[3], Boolean.parseBoolean(infoPassenger[4]), Boolean.parseBoolean(infoPassenger[5]),
                    Boolean.parseBoolean(infoPassenger[6]), Integer.parseInt(infoPassenger[7]));
            /// Calcula la prioridad de abordaje de cada pasajero///
            passenger.setPriorityBoarding(calculateBoardingPriorityNew(passenger));
            ////////////////////////////////////////////////////////
            plane.getPassengersInfo().add(passenger.getId(), passenger);

            /// Calcula la prioridad de desbordaje de cada pasajero///
            passenger.setPriorityDisembarking(calculateDisembarkationPriority(passenger));
            ////////////////////////////////////////////////////////

            try {
                plane.getBoradingOrder().maxHeapInsert(plane.getBoradingOrder().getArray(),
                        new Node<>(passenger.getPriorityBoarding(), passenger.getId()));
            } catch (KeyIsSmaller e) {
                System.out.println("No Sirvio");
            }

            try {
                plane.getDisembarkationOrder().maxHeapInsert(plane.getDisembarkationOrder().getArray(),
                        new Node<>(passenger.getPriorityDisembarking(), passenger.getId()));
            } catch (KeyIsSmaller e) {
                System.out.println("No Sirvio");
            }

        }
        //

    }

    /**
     * Calcula la prioridad de abordaje de un pasajero teniendo en cuenta su fila,
     * y otros datos extras en caso de ser primera clase
     * 
     * @param passenger pasajero al que se le calculará la prioridad
     * @return prioridad de abordaje del pasajero
     */
    public int calculateBoardingPriorityNew(Passenger passenger) {

        int priority = passenger.getRow();

        if (passenger.isFirstClass()) {
            priority += plane.getNumRows(); // La prioridad de ser primera clase
            if (passenger.isPregnant()) {
                priority += 20;
            }
            if (passenger.isOldAge()) {
                priority += 15;
            }
            priority += passenger.getAccumulatedMiles();
        }

        return priority;
    }

    /**
     * Calcula la prioridad de desabordaje de un pasajero teniendo en cuenta su fila
     * y su cercania al pasillo
     * 
     * @param passenger pasajero al que se le calculará la prioridad
     * @return prioridad de desabordaje de un pasajero
     */
    public int calculateDisembarkationPriority(Passenger passenger) {
        int chairForRow = plane.getChairsForRow();
        int priority = -5 * passenger.getRow();
        int passengerChair = letterToInt(passenger.getChair());

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

    /**
     * Imprime la el orden de abordaje de los pasajeros del Avión
     * 
     * @return
     */
    public String printListBoarding() {
        if (plane == null) {
            return "No loaded data";
        } else if (plane.getBoradingOrder().getHeapSize() == 0) {
            return plane.getArrivalList();
        } else {
            return plane.printListBoardingOrder();
        }

    }

    /**
     * Imprimer el orden de desabordaje de los pasajeros del Avión
     * 
     * @return
     */
    public String printListDisembarkation() {
        if (plane == null) {
            return "No loaded data";
        } else if (plane.getDisembarkationOrder().getHeapSize() == 0) {
            return plane.getDesembarkationList();
        } else {
            return plane.printListDisembarkationOrder();
        }

    }

}
