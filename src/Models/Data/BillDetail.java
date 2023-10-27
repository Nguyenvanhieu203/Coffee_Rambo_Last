/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Data;

/**
 *
 * @author ASUS
 */
public class BillDetail {
    private int idBill;
    private int idDrink;
    private int quantity;

    public BillDetail(){
        
    }
    
    public BillDetail(int idBill, int idDrink, int quantity) {
        this.idBill = idBill;
        this.idDrink = idDrink;
        this.quantity = quantity;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
