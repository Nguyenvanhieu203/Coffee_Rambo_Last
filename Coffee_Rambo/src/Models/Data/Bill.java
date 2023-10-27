/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Data;

/**
 *
 * @author ASUS
 */
public class Bill {
    private int Id;
    private int idTable;
    private int idStaff;
    private int idMemberShip;
    private String date;
    private double totalPrice;
    
    public Bill(){
        
    }
    
    public Bill(int Id, int idTable, int idStaff, int idMemberShip, String date, double totalPrice) {
        this.Id = Id;
        this.idTable = idTable;
        this.idStaff = idStaff;
        this.idMemberShip = idMemberShip;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public int getIdMemberShip() {
        return idMemberShip;
    }

    public void setIdMemberShip(int idMemberShip) {
        this.idMemberShip = idMemberShip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
