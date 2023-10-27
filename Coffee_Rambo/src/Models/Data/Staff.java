/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class Staff implements Serializable {
    public int iD ;
    public String firstName;
    public String lastName;
    public String fullName;
    public Date dob;
    public int phoneNumber ;
    public String Email ;
    public String passwordHas;
    public String gender;
    public Date hireDate;
    public BigDecimal salary;
    public String position;
    public int State;
    
    public static String FullName;

    public Staff(){
        
    }
    public Staff(int Id,String firstName, String lastName, String fullName, Date dob, int phoneNumber, String Email, String passwordHas, String gender, Date hireDate, BigDecimal salary, String position, int State) {
        this.iD = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.dob = dob;
        this.passwordHas = passwordHas;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.hireDate = hireDate;
        this.salary = salary;
        this.position = position;
        this.Email = Email;
        this.State = State;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPasswordHas() {
        return passwordHas;
    }

    public void setPasswordHas(String passwordHas) {
        this.passwordHas = passwordHas;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public int getState() {
        return State;
    }
    
    public void setState(int State) {
        this.State = State;
    }
}
