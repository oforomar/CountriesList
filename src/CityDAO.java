import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    ArrayList<City> cityList;

    public CityDAO() {
    }

    public List<City> readCitiesFromCSV(String fileName) {
        cityList = new ArrayList<>();

        // Open the cities csv file
        File citiesFile = new File(fileName);

        // create new list to store the read lines
        List<String> lines = new ArrayList<>();

        // read all the lines from the csv file to the list
        try {
            lines = Files.readAllLines(citiesFile.toPath());
        } catch (Exception e) {
            System.out.println("Error Reading File !");
            e.printStackTrace();
        }

        // extract data from the list and create cities object from each line
        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {

            String line = lines.get(lineIdx);

            String[] fields = line.split(",", -1);

            cityList.add(createCity(fields));
        }
        return cityList;
    }

    public City createCity(String[] metadata) {
        for (int fieldIdx = 0; fieldIdx < metadata.length; fieldIdx++)
            metadata[fieldIdx] = metadata[fieldIdx].trim();

        return new City(metadata[0], metadata[1], metadata[2], metadata[3], metadata[4]);
    }
}
