package main.java.solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import main.java.dto.Offer;
import main.java.dto.Product;
import main.java.dto.ProductPurchased;

public class Solution {
  
  private static File file;
  
  public static Map<Product,Double> getInventoryProductPrices(){
    Map<Product,Double> result = new HashMap<>();
    
    try {
      file = new File("C:\\Users\\aerhan\\eclipse-workspace\\FruitShop\\src\\main\\resources\\ProductsPricesInventory");

      BufferedReader reader = new BufferedReader(new FileReader(file));
      List<String> listLines = new ArrayList<>();
      
      String line;
      while((line = reader.readLine()) != null) {
        listLines.add(line);
      }
      
      //i=1, skipping first line
      for(int i = 1; i < listLines.size(); i++) {
        System.out.println(listLines.get(i));
        String[] lineInfo = listLines.get(i).split(",");
        
        Product product = Product.valueOf(lineInfo[0]);
        double price = Double.parseDouble(lineInfo[1]);
        
        result.put(product, price);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return result;
  }
  
  public static Map<Product,Integer> getInventoryProductQuantity(){
    Map<Product,Integer> result = new HashMap<>();
    
    try {
      file = new File("C:\\Users\\aerhan\\eclipse-workspace\\FruitShop\\src\\main\\resources\\ProductsQuantityInventory");

      BufferedReader reader = new BufferedReader(new FileReader(file));
      List<String> listLines = new ArrayList<>();
      
      String line;
      while((line = reader.readLine()) != null) {
        listLines.add(line);
      }
      
      //i=1, skipping first line
      for(int i = 1; i < listLines.size(); i++) {
        System.out.println(listLines.get(i));
        String[] lineInfo = listLines.get(i).split(",");
        
        Product product = Product.valueOf(lineInfo[0]);
        int quantity = Integer.parseInt(lineInfo[1]);
        
        result.put(product, quantity);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return result;
  }
  
  public static double getPriceProducts() {
    double result = 0;
    
    Map<Product, Double> mapInventoryProductPrices = getInventoryProductPrices();
    
    System.out.println();
    
    Map<Product, Integer> mapInventoryProductQuantity = getInventoryProductQuantity();
    
    System.out.println();
    
    Iterator<Map.Entry<Product, Double>> entrySetPrice = mapInventoryProductPrices.entrySet().iterator();
    Iterator<Map.Entry<Product, Integer>> entrySetQuantity = mapInventoryProductQuantity.entrySet().iterator();

    while (entrySetPrice.hasNext() && entrySetQuantity.hasNext()) {
      Entry<Product, Double> entryPrice = entrySetPrice.next();
      Entry<Product, Integer> entryQuantity = entrySetQuantity.next();
      
      double price = entryPrice.getValue();
      int quantity = entryQuantity.getValue();
      
      result = result + (quantity*price);
    }
    
    System.out.println("Result: " + result);
    
    return result;
  }
  
  public static List<ProductPurchased> getProductsPurchased(){
    List<ProductPurchased> result = new ArrayList<>();
    ProductPurchased productPurchased;
    
    Map<Product, Integer> mapInventoryProductQuantity = getInventoryProductQuantity();

    Iterator<Map.Entry<Product, Integer>> entrySetQuantity = mapInventoryProductQuantity.entrySet().iterator();

    while (entrySetQuantity.hasNext()) {
      Entry<Product, Integer> entryQuantity = entrySetQuantity.next();
      
      productPurchased = new ProductPurchased();
      productPurchased.setProduct(entryQuantity.getKey());
      productPurchased.setQuantity(entryQuantity.getValue());
      
      result.add(productPurchased);
    }
    
    for(ProductPurchased p: result) {
      System.out.println("Product bought: " + p.getProduct());
      System.out.println("Quantity bought: " + p.getQuantity());
      System.out.println();
    }
    
    return result;
  }
  
  public static double getOfferBuyThreeApplesPayingTwo() {
    double result = 0;
    
    List<ProductPurchased> listProductsPurchased = getProductsPurchased();
    double priceWithoutOffer = getPriceProducts();
    
    for(int i = 0; i < listProductsPurchased.size(); i++) {
      ProductPurchased productPurchased = listProductsPurchased.get(i);
      if(productPurchased.getProduct().equals(Product.Apple)) {
        double priceExcludingApples = priceWithoutOffer - 
              (productPurchased.getQuantity() * getPrice(productPurchased.getProduct()));
        result = priceExcludingApples + 
            ((productPurchased.getQuantity() / 3 * 2) * getPrice(productPurchased.getProduct()));
      }
    }
    
    System.out.println("aux: " + result);
    
    return result;
  }
  
  public static double getPrice(Product product) {
    double result = 0;
    Map<Product, Double> mapInventoryProductPrices = getInventoryProductPrices();

    Iterator<Map.Entry<Product, Double>> entrySetPrice = mapInventoryProductPrices.entrySet().iterator();

    while (entrySetPrice.hasNext()) {
      Entry<Product, Double> entryPrice = entrySetPrice.next();
      
      if(entryPrice.getKey().equals(product)) {
        result = entryPrice.getValue();
        break;
      }
    }
    
    return result;
  }

  public static void main(String[] args) {
    //1
    System.out.println(getPriceProducts());
    
    //2
    getProductsPurchased();
    
    //3 offer apples
    getOfferBuyThreeApplesPayingTwo();
  }
}
