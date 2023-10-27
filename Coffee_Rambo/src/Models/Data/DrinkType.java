/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Data;

/**
 *
 * @author nguye
 */
public class DrinkType {
    public int Id;
    public String NameType;
    public DrinkType() {
        
    }
    
    public DrinkType(int Id, String NameDrink) {
        this.Id = Id;
        this.NameType = NameDrink;
    }
    
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }
    public String getNameType() {
        return NameType;
    }
    
    public void setNameType(String NameType) {
        this.NameType = NameType;
    }
}
