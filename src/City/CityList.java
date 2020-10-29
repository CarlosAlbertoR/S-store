package City;

public class CityList {

    CityNode cityHead;
    int citySize;

    //Constructor-----------------------------------------
    public CityList(CityNode cityHead, int citySize) {
        this.cityHead = cityHead;
        this.citySize = citySize;
    }

    public CityList() {
        
    }

    //Getter and Setter------------------------------------------
    public CityNode getCityHead() {
        return cityHead;
    }

    public void setCityHead(CityNode cityHead) {
        this.cityHead = cityHead;
    }

    public int getCitySize() {
        return citySize;
    }

    public void setCitySize(int citySize) {
        this.citySize = citySize;
    }

    //Add new city method------------------------------
    public boolean addCity(City cityData) {
        boolean ok=false;
        if (cityData == null) {
            ok=false;
        }
        if (cityHead == null) {
            cityHead = new CityNode();
            cityHead.cityData = cityData;
        } else {
            CityNode tmp = cityHead;
            while (tmp.nextCity != null) {
                tmp = tmp.nextCity;
            }
            if (searchCity(tmp.getCityData().cityName) == null) {
                CityNode newNode = new CityNode();
                newNode.cityData = cityData;
                tmp.nextCity = newNode;
                citySize++;
                ok=true;
            }

        }
        return  ok;
    }

    // Search city method--------------------------------------------------------------
    public CityNode searchCity(String citySearch) {
        CityNode temp = null;
        int count = 0;
        if (!(cityHead == null)) {
            temp = this.getCityHead();
            while ((count < this.getCitySize())
                    && !(temp.getCityData().getCityName()==citySearch)) {
                temp = temp.getNextCity();
                count++;
            }
            if (count == this.getCitySize()) {
                temp = null;
            }
        }
        return temp;
    }
}
