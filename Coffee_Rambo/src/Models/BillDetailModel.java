/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.BillDetail;
import Models.Data.Drink;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BillDetailModel extends ConnectToSql{
    public boolean addBillDetail(int idBill, List<Drink> drinks, List<Integer> quantity){
        try{
           for(int i = 0; i< drinks.size();i++){
               String query = "insert into BillDetail (Idbill, IdDrink,Quantity) values (?,?,?);";
               try{
                    PreparedStatement prepare = con.prepareStatement(query); 
                    prepare.setInt(1,idBill);
                    prepare.setInt(2,drinks.get(i).getiD());
                    prepare.setInt(3,quantity.get(i));
                    
                    int rowsAffected = prepare.executeUpdate();

                    if (rowsAffected < 0) {
                        return false;
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
           }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
    public int getRecentBill(){
        int id = 1;
        try {
            String query = "SELECT TOP 1 Id, IdTable, IdStaff, IdMemberShip, CreateDate, TotalPrice FROM Bills ORDER BY Id DESC;";
            
            // Tạo PreparedStatement
            PreparedStatement prepare = con.prepareStatement(query); 
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
    
    public double revenueReport(int idDrink, double price, String format, String date) {
        int total = 0;
        double totalRevenue;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(date);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng trong Java bắt đầu từ 0
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            String query = "";
            if (format.equals("Ngày")) {
                query = "SELECT bd.Quantity FROM billdetail bd JOIN Bills b ON bd.idBill = b.id WHERE bd.idDrink = ? AND DAY(b.createDate) = ? AND MONTH(b.createDate) = ? AND YEAR(b.createDate) = ?";
            } else if (format.equals("Tháng")) {
                query = "SELECT bd.Quantity FROM billdetail bd JOIN Bills b ON bd.idBill = b.id WHERE bd.idDrink = ? AND MONTH(b.createDate) = ? AND YEAR(b.createDate) = ?";
            } else if (format.equals("Năm")) {
                query = "SELECT bd.Quantity FROM billdetail bd JOIN Bills b ON bd.idBill = b.id WHERE bd.idDrink = ? AND YEAR(b.createDate) = ?";
            } else {
                throw new IllegalArgumentException("Định dạng không hợp lệ");
            }

            PreparedStatement pre = con.prepareStatement(query);
            pre.setInt(1, idDrink);

            if (format.equals("Ngày")) {
                pre.setInt(2, day);
                pre.setInt(3, month);
                pre.setInt(4, year);
            } else if (format.equals("Tháng")) {
                pre.setInt(2, month);
                pre.setInt(3, year);
            } else if (format.equals("Năm")) {
                pre.setInt(2, year);
            }

            ResultSet result = pre.executeQuery();

            while (result.next()) {
                int quantity = result.getInt("Quantity");
                total += quantity;
            }

            totalRevenue = total * price;
            return totalRevenue;
        } catch (Exception ex) {
            ex.printStackTrace();
            return total;
        }
    }


//    public static void main(String[] args ){
////        int idBill = 5;
////        List<Drink> drinkList = new ArrayList<>();
////        
////        Drink drink = new Drink();
////        drink.setiD(1);
////        drinkList.add(drink);
////        
////        Drink drink1 = new Drink();
////        drink1.setiD(2);
////        drinkList.add(drink1);
////        
////        Drink drink2 = new Drink();
////        drink2.setiD(3);
////        drinkList.add(drink2);
////        System.out.print(drinkList.size());
////        List<Integer> quantity = new ArrayList<>(Arrays.asList(2, 2, 3));
////        BillDetailModel billDTai = new BillDetailModel();
////        billDTai.addBillDetail(idBill,drinkList,quantity);
//
//    }
}
