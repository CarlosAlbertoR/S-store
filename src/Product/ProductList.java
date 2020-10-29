package Product;

import java.util.Date;

public class ProductList {
    ProductNode productHead;
    int productSize;
    
    //Getter and Setter------------------------------------

    public ProductNode getProductHead() {
        return productHead;
    }

    public void setProductHead(ProductNode productHead) {
        this.productHead = productHead;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }
    
    //Add new product method-------------------------------
    
    public boolean addProduct(Product productData){
        if (productData == null) {
            return false;
        }
        if(productHead == null){
            productHead = new ProductNode();
            productHead.productData = productData;
        }
        else{
            ProductNode tmp = productHead;
            while(tmp.nextProduct != null){
                tmp = tmp.nextProduct;
            }
            ProductNode newNode = new ProductNode();
            newNode.productData = productData;
            tmp.nextProduct = newNode;
        }
        productSize++;
        return true;
    }
    
    //Quantity of products coming soon method-------------------------------
    
    
    public int countUpcomingProductsToExpire(Date date1, int numberDays ){
        int quantityProductsComingSoon=0;
        ProductNode tmp = productHead;
        for (int i = 0; i < productSize; i++) {
            if (tmp.productData.isAboutToExpire(date1, numberDays)) {
                quantityProductsComingSoon++;
            }
        }
        return quantityProductsComingSoon; 
    }
}
