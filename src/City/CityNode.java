package City;

public class CityNode {
    City cityData;
    CityNode nextCity;

    //Getter and Setter--------------------------------------

    public City getCityData() {
        return cityData;
    }

    public void setCityData(City cityData) {
        this.cityData = cityData;
    }

    public CityNode getNextCity() {
        return nextCity;
    }

    public void setNextCity(CityNode nextCity) {
        this.nextCity = nextCity;
    }
}
