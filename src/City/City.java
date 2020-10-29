package City;

import Branch.BranchList;

public class City {
    String cityName;
    Branch.BranchList branchList;
    
    //Constructor-----------------------------------

    public City(String cityName, BranchList branchList) {
        this.cityName = cityName;
        this.branchList = branchList;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }
    
    
    
    //Getter and Setter------------------------------------

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BranchList getBranchList() {
        return branchList;
    }

    public void setBranchList(BranchList branchList) {
        this.branchList = branchList;
    }
    
}
