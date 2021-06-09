import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
        ArrayList<Country> countryList;

        public CountryDAO() {
        }

        public List<Country> readCountriesFromCSV(String fileName) {
            countryList = new ArrayList<Country>();

            // Open the countries csv file
            File countriesFile = new File(fileName);

            // create new list to store the read lines
            List<String> lines = new ArrayList<>();

            // read all the lines from the csv file to the list
            try {
                lines = Files.readAllLines(countriesFile.toPath());
            } catch (Exception e) {
                System.out.println("Error Reading File !");
                System.out.println(e.toString());
            }

            // extract data from the list and create countries object from each line
            for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {

                String line = lines.get(lineIdx);

                String[] fields = line.split(",");

                countryList.add(createCountry(fields));
            }
            return countryList;
        }

        public Country createCountry(String[] metadata) {
            for (int fieldIdx = 0; fieldIdx < metadata.length; fieldIdx++)
                metadata[fieldIdx] = metadata[fieldIdx].trim();

            return new Country(metadata[1], metadata[0]);
        }
    }
