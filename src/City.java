public class City {
    private int id;
    private String name;
    private String countryId;
    private String capital;
    private int population;

    public City(String id, String name, String countryId, String capital, String population){
        this.name = name;
        this.countryId = countryId;
        this.capital = capital;

        try {
            this.id = Integer.parseInt(id);
        }catch (Exception e){
            this.id = 0;
        }

        try {
            this.population = Integer.parseInt(population);
        }catch (Exception e){
            this.population = 0;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryId='" + countryId + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                '}';
    }
}
