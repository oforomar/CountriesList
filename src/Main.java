import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    final static String countriesPath = "C:\\Users\\omarh\\IdeaProjects\\CountriesList\\src\\countries.csv";
    final static String citiesPath = "C:\\Users\\omarh\\IdeaProjects\\CountriesList\\src\\cities.csv";

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static void main(String[] args){
        CountryDAO countryDAO = new CountryDAO();
        List<Country> countries =countryDAO.readCountriesFromCSV(countriesPath);

        // Filter duplicates
        countries = countries.stream().filter(distinctByKey(Country::getCode)).collect(Collectors.toList());

        CityDAO cityDAO = new CityDAO();
        List<City> cities = cityDAO.readCitiesFromCSV(citiesPath);

        // Create map with Country code as key and its cities list as a value
        Map<String, List<City>> map1 = new HashMap<>();

        for (Country c: countries){
            List<City> tempCityList = new ArrayList<>();
            String code = c.getCode();

            for (City c1 : cities){
                if (c1.getCountryId().equals(code))
                    tempCityList.add(c1);
            }

            tempCityList.sort(Comparator.comparing(City::getPopulation));
            map1.put(code, tempCityList);
        }

        // Find the highest population city for each country
        for (Map.Entry<String, List<City>> entry : map1.entrySet()){
            System.out.println(entry.getKey());

            // Each country city list is already sorted from lowest to highest
            // Print last city in each list
            if (!entry.getValue().isEmpty())
                System.out.println(entry.getValue().get(entry.getValue().size() - 1));
        }

        // OR
        // Find the highest population city for each country using streams
        Map<String, List<City>> map2 = cities.stream()
                                                .collect(Collectors.groupingBy(City::getCountryId));

        // Find highest population city capital
        cities.stream()
                .filter(c -> c.getCapital().equals("primary"))
                .sorted(Comparator.comparing(City::getPopulation))
                .forEach(System.out::println);

        
        System.out.println("Finish");
    }
}
