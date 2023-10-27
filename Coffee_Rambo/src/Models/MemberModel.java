/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.Drink;
import Models.Data.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nguye
 */
public class MemberModel extends ConnectToSql {

    public List<Member> GetAllMember() {  
        List<Member> listCustomer = new ArrayList<>();
        String query = "select * from memberShip";
        try{
            PreparedStatement prepare = con.prepareStatement(query); 
            ResultSet result = prepare.executeQuery();
            
            while(result.next()){
                listCustomer.add(new Member(result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDouble(5),
                        result.getInt(6)));

                
            }
            return listCustomer;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public List<Member> GetMemberByPhoneNumber(String phoneNumber) {  
       
        List<Member> listCustomer = new ArrayList<>();
        String query = "SELECT * FROM memberShip WHERE PhoneNumber LIKE ?";
        try {
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.setString(1, "%" + phoneNumber + "%");
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                listCustomer.add(new Member(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5),
                    result.getInt(6)
                ));
            }
            return listCustomer;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public boolean PayWithouthScore(int phoneNumber, double totalPayment){
        try {
        // Bước 1: Tìm thông tin người dùng dựa trên số điện thoại
            String selectQuery = "SELECT * FROM memberShip WHERE PhoneNumber = ?";
            PreparedStatement selectStatement = con.prepareStatement(selectQuery);
            selectStatement.setInt(1, phoneNumber);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Bước 2: Lấy điểm hiện tại của người dùng
                int currentScore = resultSet.getInt("Score");

                // Bước 3: Tính toán điểm mới (1% của totalPayment)
                double scoreIncrease = totalPayment * 0.01;
                int newScore = (int) (currentScore + scoreIncrease);

                // Bước 4: Cập nhật điểm mới vào bảng memberShip
                String updateQuery = "UPDATE memberShip SET Score = ? WHERE PhoneNumber = ?";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setInt(1, newScore);
                updateStatement.setInt(2, phoneNumber);

                int rowsUpdated = updateStatement.executeUpdate();

                // Kiểm tra xem có dòng nào bị cập nhật không
                if (rowsUpdated > 0) {
                    return true; // Thanh toán và tích điểm thành công
                }
            }

            // Trong trường hợp không tìm thấy người dùng
            return false; // Không thực hiện thanh toán và tích điểm

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Xảy ra lỗi trong quá trình thực hiện
        }
    }
    public boolean PayWitScore(int phoneNumber, double totalPayment){
        try {
            String selectQuery = "SELECT * FROM memberShip WHERE PhoneNumber = ?";
            PreparedStatement selectStatement = con.prepareStatement(selectQuery);
            selectStatement.setInt(1, phoneNumber);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String updateQuery = "UPDATE memberShip SET Score = 0 WHERE PhoneNumber = ?";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setInt(1, phoneNumber);
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    return true;
                }
           
            }
            return false;   

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Xảy ra lỗi trong quá trình thực hiện
        }
    }
    public boolean addMember(Member member) {
        String query = "insert into memberShip values (?,?,?,?,?);";
        try{
            PreparedStatement prepare = con.prepareStatement(query); 
            prepare.setInt(1,member.getPhoneNumber());
            prepare.setString(2,member.getNameCustomer());
            prepare.setString(3,member.getStartDate());
            prepare.setDouble(4,member.getRevenue());
            prepare.setInt(5,member.getScore());
            
            int rowsAffected = prepare.executeUpdate();
            
            if (rowsAffected > 0) {
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
//    public static void main(String[] args){
//        MemberModel mb = new MemberModel();
//        mb.PayWitScore(866889311, 20000);
//    }
}
