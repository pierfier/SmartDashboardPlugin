package source;


import source.CrossHair;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSaver {

    //the array to hold all of the crosshairs
    ArrayList<CrossHair> crossHairs;
    //used to save the crosshaird coordinates
    BufferedReader in;
    BufferedWriter out;
    //the file object
    File file;
    String filePath;

    public FileSaver(String s) {

        this.filePath = s;
        crossHairs = new ArrayList<CrossHair>();
        file = new File(filePath);
    }

    public void writeCrossHair(CrossHair crossHair) {
        try {

            out = new BufferedWriter(new FileWriter(file));
            out.write(Integer.toString(crossHair.x));
            out.write(",");
            out.write(Integer.toString(crossHair.y));
            out.write(",");
            out.write(Integer.toString(crossHair.color.getRed()));
            out.write(",");
            out.write(Integer.toString(crossHair.color.getGreen()));
            out.write(",");
            out.write(Integer.toString(crossHair.color.getBlue()));
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeCrossHairArray(ArrayList<CrossHair> temp) {
        try {
            out = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < temp.size(); i++) {

                CrossHair crossHair = temp.get(i);

                out.write(Integer.toString(crossHair.x));
                out.write(",");
                out.write(Integer.toString(crossHair.y));
                out.write(",");
                out.write(Integer.toString(crossHair.color.getRed()));
                out.write(",");
                out.write(Integer.toString(crossHair.color.getGreen()));
                out.write(",");
                out.write(Integer.toString(crossHair.color.getBlue()));
                out.newLine();
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<CrossHair> readInCrossHairArray() {
        
        ArrayList<CrossHair> temp = new ArrayList<CrossHair>();

        try {
            in = new BufferedReader(new FileReader(file));
            String line;

            while ((line = in.readLine()) != null) {
                String[] entry = line.split(",");

                CrossHair crossHair = new CrossHair();

                crossHair.x = Integer.parseInt(entry[0]);
                crossHair.y = Integer.parseInt(entry[1]);
                crossHair.color = new Color(Integer.parseInt(entry[2]), Integer.parseInt(entry[3]), Integer.parseInt(entry[4]));

                temp.add(crossHair);
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return temp;
    }

    public CrossHair readInCrossHair() {
        CrossHair crossHair = new CrossHair();
        try {

            in = new BufferedReader(new FileReader(file));

            String[] entry = in.readLine().split(",");
            crossHair.x = Integer.parseInt(entry[0]);
            crossHair.y = Integer.parseInt(entry[1]);
            crossHair.color = new Color(Integer.parseInt(entry[2]), Integer.parseInt(entry[3]), Integer.parseInt(entry[4]));

            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return crossHair;
    }
}
