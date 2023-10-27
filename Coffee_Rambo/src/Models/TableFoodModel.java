/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.TableFood;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class TableFoodModel extends ConnectToSql {
    public List<TableFood> getTableByFloor (String floor){
        List<TableFood> listTable = new ArrayList<>();
        String query = "";
        if(floor == "Tất cả")   {
            query = "select * from tableFoods";
            try{
            PreparedStatement prepare = con.prepareStatement(query);
            ResultSet result = prepare.executeQuery();
            
            while(result.next()){
                String nameTable = result.getString(2); 
                String Floor = result.getString(3);
                
                TableFood table = new TableFood (nameTable,Floor);
                listTable.add(table);
            }
            return listTable;
                    
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return null;
            }
        else  {
            query = "select * from tableFoods where Floors = ?";
            try{
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.setString (1,floor);
            ResultSet result = prepare.executeQuery();
            
            while(result.next()){

                String tableName = result.getString(2);
                String Floor = result.getString(3);
                TableFood table = new TableFood (tableName,Floor);
                listTable.add(table);
            }
            return listTable;
                    
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return null;
        }
    }

//    public String getTableName() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    public boolean addTableFood(TableFood tableFood){
        try{

            String checkQuery = "select * from tablefoods where TableName = ?";
            PreparedStatement pre = con.prepareStatement(checkQuery);
            pre.setString(1,tableFood.getTableName());
            
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                if(count > 0){
                    return false;
                }
            }

            String query = "Insert into TableFoods (TableName,Floors) values (?,?)";
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.setString(1, tableFood.getTableName());
            prepare.setString(2, tableFood.getFloor());
            
            int result = prepare.executeUpdate();
            if(result > 0){
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    } 
    
    public boolean dropTable(TableFood tableFood) {
    try {
        String checkQuery = "SELECT * FROM TableFoods WHERE TableName = ?";
        PreparedStatement pre = con.prepareStatement(checkQuery);
        pre.setString(1, tableFood.getTableName());

        ResultSet result = pre.executeQuery();
        if (result.next()) { // Kiểm tra xem có dòng dữ liệu nào được trả về không
            int id = result.getInt("Id");
            
            String deleteTableFromBill = "DELETE FROM bills WHERE IdTable = ?";
            PreparedStatement prep = con.prepareStatement(deleteTableFromBill);
            prep.setInt(1, id);
            
            int rs = prep.executeUpdate();
            if (rs > 0) {
                String query = "DELETE FROM TableFoods WHERE TableName = ?";
                PreparedStatement prepare = con.prepareStatement(query);
                prepare.setString(1, tableFood.getTableName());

                int rowsDeleted = prepare.executeUpdate();
                if (rowsDeleted > 0) {
                    return true;
                }
            }
        }
        String query = "DELETE FROM TableFoods WHERE TableName = ?";
        PreparedStatement prepare = con.prepareStatement(query);
        prepare.setString(1, tableFood.getTableName());

        int rowsDeleted = prepare.executeUpdate();
        if (rowsDeleted > 0) {
            return true;
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args){
        String tableName = "Bàn 2";
//        String floor = "Tầng 3";
        
        TableFood tableFood = new TableFood();
        tableFood.setTableName(tableName);
//        tableFood.setFloor(floor);
        
        TableFoodModel controller = new TableFoodModel();
        
        // test add table
//        if(controller.addTableFood(tableFood)){
//            System.out.print("Ok");
//        }else{
//            System.out.print("Fail");
//        }

        // test delete table
        if(controller.dropTable(tableFood)){
            System.out.print("Ok");
        }else{
            System.out.print("Fail");
        }
    }
}
