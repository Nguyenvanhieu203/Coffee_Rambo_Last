/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.Staff;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nguye
 */
public class LoginModel extends ConnectToSql {
//    Lofin Database
    public boolean Login(Staff staff){
        try {
            String query = "select * from Staffs where Email = ? and PasswordHas = ?";
            
            // Tạo PreparedStatement
            PreparedStatement prepare = con.prepareStatement(query); 
            prepare.setString(1, staff.Email);
            prepare.setString (2,staff.passwordHas);
            
            //Thực hiện truy vấn
            ResultSet result = prepare.executeQuery();
            if(result.next()){
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
//    SignIn DataBase
    public boolean SignUp(Staff staff) {
        try {
             String query = "INSERT INTO Staffs (FirstName, LastName, FullName, Dob, Email, PassWordHas, Gender, HireDate,PhoneNumber) "
                     + "VALUES (?, ?, ?, ?, ? , ?, ?, ?,?)";
            
            // Tạo PreparedStatement
            
            PreparedStatement prepare = con.prepareStatement(query); 
            prepare.setString(1, staff.firstName);
            prepare.setString(2, staff.lastName);
            prepare.setString(3, staff.firstName + " " + staff.lastName); // Tạo FullName bằng cách kết hợp FirstName và LastName
            prepare.setDate(4, (Date)staff.dob);
            prepare.setString(5, staff.Email);
            prepare.setString(6, staff.passwordHas);
            prepare.setString(7, staff.gender);
            prepare.setDate(8, (Date) staff.hireDate);
            prepare.setInt(9, staff.phoneNumber);
            //Thực hiện truy vấn
            int rowsAffected = prepare.executeUpdate();
            if(rowsAffected > 0){
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public String getStaffNameByAccount (String Email , String pass){
        String staffName = "";
        String query = "select FullName from Staffs where Email = ? and PasswordHas = ?;";
        try{
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.setString(1, Email); // Đặt tham số thay thế cho dấu ? trong câu lệnh SQL
            prepare.setString(2, pass); // Đặt tham số thay thế cho dấu ? trong câu lệnh SQL

            // Thực hiện truy vấn SELECT và lưu kết quả vào ResultSet
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                // Lấy giá trị từ cột 'Id'
                staffName = resultSet.getString("FullName");

            }
          
        }catch(Exception ex){
            ex.printStackTrace();
  
        }
        
        return staffName;
    }
    
    public String getRoleUser(String Email , String pass) {
        String Position = "";
        String query = "select Position from Staffs where Email = ? and PasswordHas = ?;";
        try{
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.setString(1, Email); // Đặt tham số thay thế cho dấu ? trong câu lệnh SQL
            prepare.setString(2, pass); // Đặt tham số thay thế cho dấu ? trong câu lệnh SQL

            // Thực hiện truy vấn SELECT và lưu kết quả vào ResultSet
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                // Lấy giá trị từ cột 'Id'
                Position = resultSet.getString("Position");

            }
          
        }catch(Exception ex){
            ex.printStackTrace();
  
        }
        
        return Position;
    }
    public static void main(String[] args){
        LoginModel loginModel = new LoginModel();
        Staff a = new Staff();
        a.Email = "AnhMinh0203@gmail.com";
        a.passwordHas = "MinhLe0203";
        System.out.print(a.Email);
        System.out.print(a.passwordHas);
        if(loginModel.Login(a)){
            System.out.print("Ok");
        }
        else{
            System.out.print("Fail");
        }
    }
}
