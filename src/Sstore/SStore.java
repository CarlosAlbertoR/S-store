package Sstore;

import Branch.*;
import Product.*;
import City.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;

public class SStore {

    public static BranchList branchList = new BranchList();
    public static ProductList productList = new ProductList();
    public static CityList cityList = new CityList();
    public static void assignData(String fileName) throws ParseException {

        int numberBranch;
        Date referenceDate;
        int numberDays;
        
        FileReader fr;
        String line;
        String[] data;
        try {
            
            fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            
            //Extraccion de numero de sucursales
            line = bf.readLine();
            data = line.split(" ");
            numberBranch = Integer.parseInt(data[0]);
            
            //Extractión of reference date
            line = bf.readLine();
            data = line.split(" ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            referenceDate = dateFormat.parse(data[0]+ "-" + data[1] + "-" + data[2]);
            
            //Extraction of number of days
            line = bf.readLine();
            data = line.split(" ");
            numberDays = Integer.parseInt(data[0]);
            
            //Add of branches
            for (int i = 0; i < numberBranch; i++) {
                line = bf.readLine();
                data = line.split(" ");
                branchList.addBranch(new Branch(data[0],data[1],Integer.parseInt(data[2])));
                cityList.addCity(new City(data[1]));
            }
            
            //Add of products
            BranchNode tempBN = branchList.getBranchHead();
            for (int i = 0; i < numberBranch; i++) {  
                for (int j = 0; j < tempBN.getBranchData().getBranchProductQuantity(); j++) {
                    line = bf.readLine();
                    data = line.split(" ");
                    dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date expirationDate =dateFormat.parse(data[0]+ "-" + data[1] + "-" + data[2]);
                    tempBN.getBranchData().getProducList().addProduct(new Product(expirationDate, Integer.parseInt(data[3]), Float.parseFloat(data[4])));
                }
                tempBN = tempBN.getNextBranch();
            }
            fr.close();
            
            printData(referenceDate, numberDays);
            averageCostOfUpcomingProductsToExpire(referenceDate, numberDays);
            branchThatWorstManagesTheProducts(referenceDate, numberDays);
            cityThatBestManagesProducts(referenceDate, numberDays);
        }  
        catch (IOException e) {
        }
        
    }
    
    
    //print data method-------------------------------------------------------------
    
    public static void printData(Date referenceDate, int numberDays){
        BranchNode tempBN;
        ProductNode tempPN;
        
        CityNode tempCN = cityList.getCityHead(); 
        for (int i = 0; i < cityList.getCitySize(); i++) {
            System.out.println(tempCN.getCityData().getCityName());
            tempBN = branchList.getBranchHead();
            for (int j = 0; j < branchList.getBranchSize(); j++) {
                if (tempBN.getBranchData().getCityName().equals(tempCN.getCityData().getCityName())) {
                    System.out.print(tempBN.getBranchData().getBranchName());
                    System.out.print("        ");
                    tempPN = tempBN.getBranchData().getProducList().getProductHead();
                    for (int k = 0; k < tempBN.getBranchData().getProducList().getProductSize(); k++) {
                        if (tempPN.getProductData().isAboutToExpire(referenceDate, numberDays)) {
                            System.out.print("#");
                        }
                        else {
                            System.out.print("-");
                        }
                        tempPN = tempPN.getNextProduct();
                    }
                }
                tempBN = tempBN.getNextBranch();
                System.out.println("");
            }
            tempCN = tempCN.getNextCity();
        }
    }
    
    //Average cost of upcoming products to expire method-------------------------------------------------
    
    public static void averageCostOfUpcomingProductsToExpire(Date referenceDate, int numberDays){
        int average;
        int sum=0;
        BranchNode tempBN;
        ProductNode tempPN;
        
        tempBN = branchList.getBranchHead();
        for (int i = 0; i < branchList.getBranchSize(); i++) {
            tempPN = tempBN.getBranchData().getProducList().getProductHead();
            for (int j = 0; j < tempBN.getBranchData().getProducList().getProductSize(); j++) {
                if (tempPN.getProductData().isAboutToExpire(referenceDate, numberDays)) {
                    sum +=((tempPN.getProductData().getProductPrice())*(tempPN.getProductData().getProdutQuantity()));
                }
                tempPN = tempPN.getNextProduct();
            }
            tempBN = tempBN.getNextBranch();
        }
        average=sum/branchList.getBranchSize();
        System.out.println("");
        System.out.println(average);
    }
    
    //Branch that worst manages the products method-------------------------------------
    public static void branchThatWorstManagesTheProducts(Date referenceDate, int numberDays) {
        BranchNode tempBN;
        ProductNode tempPN;
        
        int quantityOfProductsToBeWonByBranch;
        int totalQuantityOfProducts;
        float percentageBranch;
        float percentage = 0;
        String branchNameWorstManager = "";
        tempBN = branchList.getBranchHead();
        for (int i = 0; i < branchList.getBranchSize(); i++) {
            quantityOfProductsToBeWonByBranch = 0;
            totalQuantityOfProducts = 0;
            tempPN = tempBN.getBranchData().getProducList().getProductHead();
            for (int j = 0; j < tempBN.getBranchData().getProducList().getProductSize(); j++) {
                if (tempPN.getProductData().isAboutToExpire(referenceDate, numberDays)) {
                    quantityOfProductsToBeWonByBranch += tempPN.getProductData().getProdutQuantity();
                    totalQuantityOfProducts += tempPN.getProductData().getProdutQuantity();
                }
                else{
                    totalQuantityOfProducts += tempPN.getProductData().getProdutQuantity();
                }
                tempPN = tempPN.getNextProduct();
            }
            percentageBranch = (quantityOfProductsToBeWonByBranch*100)/totalQuantityOfProducts;
//            System.out.println(percentageBranch);
            
            if(percentageBranch>percentage){
                percentage = percentageBranch;
                branchNameWorstManager = tempBN.getBranchData().getBranchName()+"   "+tempBN.getBranchData().getCityName();
            }
            tempBN = tempBN.getNextBranch();
        }
        System.out.println(" ");
        //System.out.println(percentage);
        System.out.println(branchNameWorstManager);
    }
    
    //City ​​that best manages products method-------------------------------------------------
    public static void cityThatBestManagesProducts(Date referenceDate, int numberDays){
        BranchNode tempBN;
        ProductNode tempPN;
        
        int count;
        int quantityOfProductsToBeWonByBranch;
        int totalQuantityOfProducts;
        float percentageBranch;
        float percentage = 100;
        float percentageCity;
        float sumPercentageBranch;
        String cityNameBestManager = "";
        
        CityNode tempCN = cityList.getCityHead(); 
        for (int i = 0; i < cityList.getCitySize(); i++) {
            count = 0;
            percentageCity=0;
            sumPercentageBranch = 0;
            tempBN = branchList.getBranchHead();
            for (int j = 0; j < branchList.getBranchSize(); j++) {
                if (tempBN.getBranchData().getCityName().equals(tempCN.getCityData().getCityName())) {
                    quantityOfProductsToBeWonByBranch = 0;
                    totalQuantityOfProducts = 0;
                    tempPN = tempBN.getBranchData().getProducList().getProductHead();
                    for (int k = 0; k < tempBN.getBranchData().getProducList().getProductSize(); k++) {
                        if (tempPN.getProductData().isAboutToExpire(referenceDate, numberDays)) {
                            quantityOfProductsToBeWonByBranch += tempPN.getProductData().getProdutQuantity();
                            totalQuantityOfProducts += tempPN.getProductData().getProdutQuantity();
                        }
                        else{
                            totalQuantityOfProducts += tempPN.getProductData().getProdutQuantity();
                        }
                        tempPN = tempPN.getNextProduct();
                    }   
                    percentageBranch = (quantityOfProductsToBeWonByBranch*100)/totalQuantityOfProducts;
                    //System.out.println(percentageBranch);
                    sumPercentageBranch += percentageBranch;
                    count++;
                }
                tempBN = tempBN.getNextBranch();
                percentageCity = sumPercentageBranch/count;
                //System.out.println("  ");
                //System.out.println(percentageCity);
            }
            if(percentageCity<percentage){
                //percentage = percentageCity;
                cityNameBestManager = tempCN.getCityData().getCityName();
            }
            tempCN = tempCN.getNextCity();
        }
        System.out.println(cityNameBestManager);
    }
    
    public static void main(String[] args) throws ParseException {
        //donde se va a llamar
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(null);
        String fileName=file.getSelectedFile().getPath();

        assignData(fileName);
    }    
    
}
