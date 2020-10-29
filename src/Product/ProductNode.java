package Product;

public class ProductNode{
    Product productData;
    ProductNode nextProduct;
    
    //Getter and Setter--------------------------------------

    public Product getProductData() {
        return productData;
    }

    public void setProductData(Product productData) {
        this.productData = productData;
    }

    public ProductNode getNextProduct() {
        return nextProduct;
    }

    public void setNextProduct(ProductNode nextProduct) {
        this.nextProduct = nextProduct;
    }
    
    
}