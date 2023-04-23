package model;

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

    public void loadData() {
        File projectDir = new File(System.getProperty("user.dir"));

        FileReader archivo = null;
        BufferedReader lector = null;

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
            createPlane(infoPlane);
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
    }

    public void createPlane(String planeInfo) {
        String[] lines = planeInfo.split("\n");
        int[] numbers = new int[4];
        for (int i = 1; i < lines.length - 2; i++) {
            String[] info = lines[i].split("::");
            numbers[i] = Integer.parseInt(info[0]);
        }
        plane = new Plane(lines[0], numbers[1], numbers[2], numbers[3]);
    }

    public void addPassengersToHashtable(String passengersInfo) {
        String[] lines = passengersInfo.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] infoPassenger = lines[i].split("::");
            Passenger passenger = new Passenger(infoPassenger[0], infoPassenger[1], Integer.parseInt(infoPassenger[2]),
                    infoPassenger[3], Boolean.parseBoolean(infoPassenger[4]), Boolean.parseBoolean(infoPassenger[5]),
                    Boolean.parseBoolean(infoPassenger[6]), Integer.parseInt(infoPassenger[7]));
            plane.getPassengersInfo().add(passenger.getId(), passenger);
        }
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
