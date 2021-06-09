import java.awt.image.ImageProducer;
import java.util.*;

public class Main {
    public static void main(String[] args){
        CountryDAO countryDAO = new CountryDAO();
        List<Country> countries =countryDAO.readCountriesFromCSV("C:\\Users\\omarh\\IdeaProjects\\CountriesList\\src\\countries.csv");

        CityDAO cityDAO = new CityDAO();
        List<City> cities = cityDAO.readCitiesFromCSV("C:\\Users\\omarh\\IdeaProjects\\CountriesList\\src\\newCities.csv");

        Map<String, List<City>> map = new HashMap<>();

        for (Country c: countries){
            List<City> tempCityList = new ArrayList<>();
            String code = c.getCode();

            for (City c1 : cities){
                if (c1.getCountryId().equals(code))
                    tempCityList.add(c1);
            }

            tempCityList.sort(Comparator.comparing(City::getPopulation));
            map.put(code, tempCityList);
        }
        
        System.out.printf("Finish");
    }
}
