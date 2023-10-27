/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Data;

/**
 *
 * @author nguye
 */
public class Menu {
    public int Id;
    public String NameDrink;
    public double Price;
    public String NameType;
    public int Status;
    
    public Menu() {
        
    }
    public Menu(int Id, String NameDrink, double Price, String NameType, int Status ) {
        this.Id = Id;
        this.NameDrink = NameDrink;
        this.Price = Price;
        this.NameType = NameType;
        this.Status = Status;
    }
    
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getNameDrink() {
        return NameDrink;
    }
    public void setNameDrink(String NameDrink) {
        this.NameDrink = NameDrink;
    }
    public double getPrice() {
        return Price;
    }
    public void setPrice(double Price) {
        this.Price = Price;
    }
    public String getNameType() {
        return NameType;
    }
    public void setNameType(String NameType) {
        this.NameType = NameType;
    }
    public int getStatus() {
        return Status;
    }
    public void setStatus(int Status) {
        this.Status = Status;
    }
}
