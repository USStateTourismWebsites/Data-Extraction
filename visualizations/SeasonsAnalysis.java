/**
US Tourism - Seasons Analysis
Filter a csv file of headers and timestamps into 
    4 different csv files based on season.
 */
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class SeasonsAnalysis {
    private static final String seasons[] = {
        "Winter", "Winter", "Spring", "Spring", "Spring", "Summer", 
        "Summer", "Summer", "Autumn", "Autumn", "Autumn", "Winter"
    };

    public static void main(String [] args) {
        // the path to the csv file of headers and timestamps -- you can change this!
        String pathToCsv = "combined filtered headers and timestamp.csv";
        try {
            analyzeSeasons(pathToCsv);
        }
        catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    public static void analyzeSeasons(String pathToCsv) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));

        // make output csv for each season
        FileWriter csvWriterSpring = new FileWriter("spring.csv");
        csvWriterSpring.append("Header");
        csvWriterSpring.append(",");
        csvWriterSpring.append("Timestamp");
        csvWriterSpring.append("\n");
        FileWriter csvWriterSummer = new FileWriter("summer.csv");
        csvWriterSummer.append("Header");
        csvWriterSummer.append(",");
        csvWriterSummer.append("Timestamp");
        csvWriterSummer.append("\n");
        FileWriter csvWriterAutumn = new FileWriter("autumn.csv");
        csvWriterAutumn.append("Header");
        csvWriterAutumn.append(",");
        csvWriterAutumn.append("Timestamp");
        csvWriterAutumn.append("\n");
        FileWriter csvWriterWinter = new FileWriter("winter.csv");
        csvWriterWinter.append("Header");
        csvWriterWinter.append(",");
        csvWriterWinter.append("Timestamp");
        csvWriterWinter.append("\n");

        // loop through the input file to read all the headers
        String row = "";
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");

            // skip the first header row
            if (data[0].equals("Header")) {
                continue;
            }

            // check the season
            String strDate = data[1];
            Date currDate = new SimpleDateFormat("MM/dd/yyyy").parse(strDate);  
            String season = checkSeason(currDate);

            // add to the correct csv based on season
            if (season.equals("Spring")) {
                csvWriterSpring.append(data[0]);
                csvWriterSpring.append(",");
                csvWriterSpring.append(strDate);
                csvWriterSpring.append("\n");
                continue;
            }
            else if (season.equals("Summer")) {
                csvWriterSummer.append(data[0]);
                csvWriterSummer.append(",");
                csvWriterSummer.append(strDate);
                csvWriterSummer.append("\n");
                continue;
            }
            else if (season.equals("Autumn")) {
                csvWriterAutumn.append(data[0]);
                csvWriterAutumn.append(",");
                csvWriterAutumn.append(strDate);
                csvWriterAutumn.append("\n");
                continue;
            }
            else {
                csvWriterWinter.append(data[0]);
                csvWriterWinter.append(",");
                csvWriterWinter.append(strDate);
                csvWriterWinter.append("\n");
            }
        }

        csvWriterSpring.flush();
        csvWriterSpring.close();
        csvWriterSummer.flush();
        csvWriterSummer.close();
        csvWriterAutumn.flush();
        csvWriterAutumn.close();
        csvWriterWinter.flush();
        csvWriterWinter.close();

        csvReader.close();
    }

    // Return the season of the given date
    public static String checkSeason(Date date) {
        return seasons[date.getMonth()];
    }
}