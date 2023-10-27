/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.Bill;
import Models.Data.Drink;
import Models.Data.Member;
import Models.Data.Staff;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BillModel extends ConnectToSql{
    public int getIdFromTable(String tableName){
        int id = -1;
        try {
            String query = "select Id from tableFoods where TableName = ?";
            
            // Tạo PreparedStatement
            PreparedStatement prepare = con.prepareStatement(query); 
            prepare.setString(1, tableName);

            
            //Thực hiện truy vấn
            ResultSet result = prepare.executeQuery();
            if(result.next()){
                id = result.getInt("Id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
    public boolean addBill (Bill bill){
        String query = "insert into bills (IdTable, IdMemberShip,IdStaff,CreateDate,TotalPrice) values (?,?,?,?,?);";
        try{
            PreparedStatement prepare = con.prepareStatement(query); 
            prepare.setInt(1,bill.getIdTable());
            prepare.setInt(2,bill.getIdMemberShip());
            prepare.setInt(3,bill.getIdStaff());
            prepare.setString(4,bill.getDate());
            prepare.setDouble(5,bill.getTotalPrice());
            
            int rowsAffected = prepare.executeUpdate();
            
            if (rowsAffected > 0) {
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
//    public List<Staff> showStaff(){
//        
//        List<Staff> listStaff = new ArrayList<>();
//        String query = "select * from Staffs";
//        try{
//            PreparedStatement prepare = con.prepareStatement(query); 
//            ResultSet result = prepare.executeQuery();
//            
//            while(result.next()){
//                listStaff.add(new Staff(
//                        result.getInt(1),
//                        result.getString(2),  
//                        result.getString(3),
//                        result.getString(4),  
//                        result.getDate(5),
//                        result.getString(6),
//                        result.getInt(7),
//                        result.getString(8),
//                        result.getDate(9),
//                        result.getDouble(10),
//                        result.getString(11),
//                        result.getString(12),
//                        result.getString(13)));
//                
//            }
//            return listStaff;
//            
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return null;
//    }
//    public static void main(String[] args){
//        String nameTb = "Bàn 12";
//        BillModel bill = new BillModel();
//        int result = bill.getIdFromTable(nameTb);
//        System.out.print(result);
//    }
}
