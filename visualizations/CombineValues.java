/**
US Tourism - Combine Values
 */
import java.util.*;
import java.io.*;

public class CombineValues {
    public static void main(String [] args) {
        // the path to the csv file; you can change this!
        String pathToCsv = "all headers and freq.csv";
        try {
            combineValues(pathToCsv);
        }
        catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    public static void combineValues(String pathToCsv) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));

        // make output file
        FileWriter csvWriter = new FileWriter("output.csv");
        csvWriter.append("Words");
        csvWriter.append(",");
        csvWriter.append("Frequency");
        csvWriter.append("\n");

        // make hashmap to store data
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        // loop through the input file to read all the headers
        String row = "";
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String freqWord = data[0];

            // skip the first header row
            if (freqWord.equals("Words")) { // can change this based on first row header
                continue;
            }

            int freq = Integer.parseInt(data[1]);

            // check if the words are in the hash map
            if (hm.containsKey(freqWord)) {
                int oldFreq = hm.get(freqWord);
                int newFreq = oldFreq + freq;
                hm.put(freqWord, newFreq);
            }
            else {
                hm.put(freqWord, freq);
            }
        }
        csvReader.close();

        // print the hashmap to the csv
        for (Map.Entry<String, Integer> en : hm.entrySet()) { 
            csvWriter.append(en.getKey());
            csvWriter.append(",");
            String freq = Integer.toString(en.getValue());
            csvWriter.append(freq);
            csvWriter.append("\n");
        } 

        csvWriter.flush();
        csvWriter.close();
    }
}