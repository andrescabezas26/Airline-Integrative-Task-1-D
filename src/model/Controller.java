package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controller
 */
public class Controller {

    public void loadData() {
        File projectDir = new File(System.getProperty("user.dir"));

        FileReader archivo = null;
        BufferedReader lector = null;

        try {
            archivo = new FileReader(projectDir + "/data/data.txt");
            lector = new BufferedReader(archivo);

            String linea = lector.readLine();
            while (linea != null) {
                // procesa la línea leída
                System.out.println(linea);
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
    }
}
