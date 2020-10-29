package Product;
import java.util.Date;
import javax.swing.JOptionPane;

public class Product{
    Date productExpirationDate;
    int produtQuantity;
    float productPrice;
    
    //Product Constructor-----------------------------------------------------------------
    
    public Product(Date productExpirationDate, int produtQuantity, float productPrice){
        this.productExpirationDate = productExpirationDate;
        this.produtQuantity = produtQuantity;
        this.productPrice = productPrice;
    }

    //Product Getter and Setter-----------------------------------------------------------

    public Date getProductExpirationDate() {
        return productExpirationDate;
    }

    public void setProductExpirationDate(Date productExpirationDate) {
        this.productExpirationDate = productExpirationDate;
    }

    public int getProdutQuantity() {
        return produtQuantity;
    }

    public void setProdutQuantity(int produtQuantity) {
        this.produtQuantity = produtQuantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
    
    //Product is about to expire method---------------------------------------------
    public boolean isAboutToExpire(Date referenceDate, int numberDays) {
        Date date1=referenceDate;
        Date date2=productExpirationDate;
 
        int days = (int) ((date2.getTime()-date1.getTime())/86400000);
        //JOptionPane.showMessageDialog(null,days);
        if (days<numberDays) {
            return true;
        }
        else{
            return false;
        }
    }
}