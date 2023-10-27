/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Data;

import java.util.Date;

/**
 *
 * @author nguye
 */
public class Member {
    private int iD;
    private int phoneNumber;
    private String nameCustomer;
    private String startDate;
    private double revenue;
    private int Score;

    public Member(){
        
    }
    public Member(int iD, int phoneNumber, String nameCustomer, String startDate, double revenue, int Score) {
        this.iD = iD;
        this.phoneNumber = phoneNumber;
        this.nameCustomer = nameCustomer;
        this.startDate = startDate;
        this.revenue = revenue;
        this.Score = Score;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
}
