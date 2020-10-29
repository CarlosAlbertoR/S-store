package Branch;
import Product.*;
import java.util.Date;

public class Branch {
    String branchName;
    String cityName;
    int branchProductQuantity;
    ProductList producList;

    //Branch constructor----------------------------------------------------------------------------------
    
    public Branch(String branchName,String cityName, int branchProductQuantity) {
        this.branchName = branchName;
        this.cityName = cityName;
        this.branchProductQuantity = branchProductQuantity;
        this.producList = new ProductList();
    }

    //Branch Getter and Setter------------------------------------------------------------------------  
    
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getBranchProductQuantity() {
        return branchProductQuantity;
    }

    public void setBranchProductQuantity(int branchProductQuantity) {
        this.branchProductQuantity = branchProductQuantity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    
    public ProductList getProducList() {
        return producList;
    }

    public void setProducList(ProductList producList) {
        this.producList = producList;
    }
    
    
    //Insert new product method---------------------------------------
    
    public void insertNewProduct(Product newProduct){
        this.producList.addProduct(newProduct);
    }
    
    //Branch has products next to expire method---------------------------
    
    boolean HasProductsNextToExpire (Date date1, int numberDays){
        return (this.producList.countUpcomingProductsToExpire(date1, numberDays)>0);
    }

    //Branch is the City method---------------------------------------------
    boolean branchIsOfCity(String citySearch) { 
        if (citySearch == cityName) {
            return true;
        }
        else{
            return false;
        }
    }
}
