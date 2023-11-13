package utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class CsvAdapter {
    private static CsvAdapter single_instance = new CsvAdapter();

    private CsvAdapter() {
    }

    public static CsvAdapter getInstance() {
        return single_instance;
    }

    public ArrayList<ArrayList<String>> readCSVasArray(String filename) {
        String line = "";
        String splitBy = ",";

        // Create an ArrayList of ArrayLists of Strings
        ArrayList<ArrayList<String>> users = new ArrayList<>();

        // parsing a CSV file into BufferedReader class constructor
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            // while end of file has not been reached
            while ((line = br.readLine()) != null) {
                ArrayList<String> indiv = new ArrayList<>();

                // populate ArrayList with each user's details
                for (String value : line.split(splitBy)) {
                    indiv.add(value);
                }

                // Add the indiv ArrayList to the users ArrayList
                users.add(indiv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void writeCSVfromArray(ArrayList<ArrayList<String>> arrayList, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){    
            for (ArrayList<String> row : arrayList) {
                Iterator<String> iterator = row.iterator();
                while(iterator.hasNext()) {
                    bw.write(iterator.next());
                    if(iterator.hasNext())
                    bw.write(",");
                }
                bw.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
