package main.java.dto;

import java.util.List;

public class Receipt {

  private double totalPrice;
  private List<ProductPurchased> listProducts;
  private List<Offer> listOffers;
  
  public Receipt() {}
  public Receipt(double totalPrice, List<ProductPurchased> listProducts, List<Offer> listOffers) {
    this.totalPrice = totalPrice;
    this.listProducts = listProducts;
    this.listOffers = listOffers;
  }
  public double getTotalPrice() {
    return totalPrice;
  }
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }
  public List<ProductPurchased> getListProducts() {
    return listProducts;
  }
  public void setListProducts(List<ProductPurchased> listProducts) {
    this.listProducts = listProducts;
  }
  public List<Offer> getListOffers() {
    return listOffers;
  }
  public void setListOffers(List<Offer> listOffers) {
    this.listOffers = listOffers;
  }
  
}
