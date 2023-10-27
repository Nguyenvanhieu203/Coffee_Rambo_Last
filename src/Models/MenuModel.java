/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.Drink;
import Models.Data.DrinkType;
import Models.Data.Menu;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import java.sql.*;

/**
 *
 * @author nguye
 */
public class MenuModel extends ConnectToSql {
    public List<Drink> showMenu(){
        
        List<Drink> listDrink = new ArrayList<>();
        String query = "select * from drinks";
        try{
            PreparedStatement prepare = con.prepareStatement(query); 
            ResultSet result = prepare.executeQuery();
            
            while(result.next()){
                listDrink.add(new Drink(result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getDouble(4)));
//                        result.getString(5);
            }
            return listDrink;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Menu> getMenuManager() {
        List<Menu> listMenu = new ArrayList<>();
        String query = "select d.Id, d.NameDrink, d.Price, dt.NameType, d.State from Drinks d join DrinkType dt on d.IdType = dt.Id";
        try{
            PreparedStatement prepare = con.prepareStatement(query); 
            ResultSet result = prepare.executeQuery();
            
            while(result.next()){
                listMenu.add(new Menu(
                                result.getInt(1),
                                result.getString(2),
                                result.getDouble(3),
                                result.getString(4),
                                result.getInt(5)
                        )
                );
            }
            return listMenu;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean AddFood(Menu menu) {
        boolean result = false;
        String storedProcedureCall = "{Call AddFood(?, ?, ?)}";
        try(CallableStatement callableStatement = con.prepareCall(storedProcedureCall)){
//            callableStatement.setInt(1, menu.Id);
            callableStatement.setString(1, menu.NameDrink);
            callableStatement.setDouble(2, menu.Price);
            callableStatement.setString(3, menu.NameType);
            int RowsAffect = callableStatement.executeUpdate();
            if(RowsAffect > 0) result = true;
            else result = false;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
//    get All Name type
    public List<DrinkType> getAlldrinkType() {
        List<DrinkType> listDrinkType = new ArrayList<>();
        String query = "select * from DrinkType";
        try{
            PreparedStatement prepare = con.prepareStatement(query); 
            ResultSet result = prepare.executeQuery();
            
            while(result.next()){
                listDrinkType.add(new DrinkType(
                                result.getInt(1),
                                result.getString(2)
                        )
                );
            }
            return listDrinkType;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Drink> getDrinkByType(String typeName) {
        List<Drink> listDrink = new ArrayList<>();
        try {
            String queryFindId = "";
            if(typeName.equals("Tất cả") ){
                queryFindId = "SELECT * FROM Drinks";
                PreparedStatement pre = con.prepareStatement(queryFindId);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    listDrink.add(new Drink(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4)));
                }
                return listDrink;
            }
            queryFindId = "SELECT id FROM DrinkType WHERE NameType = ?";
            PreparedStatement prepare = con.prepareStatement(queryFindId); 
            prepare.setString(1, typeName);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                int idType = result.getInt(1);
                String queryFindByType = "SELECT * FROM drinks WHERE IdType = ?";
                PreparedStatement pre = con.prepareStatement(queryFindByType);
                pre.setInt(1, idType); // Set the IdType parameter
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    listDrink.add(new Drink(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4)));
                }
                return listDrink;
            }

            result.close();
            prepare.close();

            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public List<Drink> getDrinkByName(String drinkName) {
        List<Drink> listDrink = new ArrayList<>();
        try {

            String queryFindByType = "SELECT * FROM drinks WHERE NameDrink LIKE ?";
            PreparedStatement pre = con.prepareStatement(queryFindByType);
            pre.setString(1, "%" + drinkName + "%"); // Set the NameDrink parameter with % for wildcard matching
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                listDrink.add(new Drink(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getDouble(4)));
            }
            return listDrink;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static void main(String[] args){
        
        MenuModel x = new MenuModel();
        String typeName = "Sinh tố dâu";
        List<Drink> listDrink = x.getDrinkByName(typeName);
        for(Drink item: listDrink){
            System.out.print(item.getNameDrink());
        }
    }
    
//    Get Food By Id
    public Menu GetFoodById(int Id) {
        Menu result =  null;
        String query = "SELECT * FROM Drinks WHERE Id = ?";
        try {
            PreparedStatement prepare = con.prepareStatement(query); 
            prepare.setInt(1, Id);

            // Thực hiện truy vấn và nhận ResultSet
            ResultSet resultSet = prepare.executeQuery();

            // Kiểm tra xem ResultSet có dữ liệu không
            if (resultSet.next()) {
                // Lấy các giá trị từ ResultSet và tạo đối tượng Menu
                int id = resultSet.getInt("Id");
                String nameDrink = resultSet.getString("NameDrink");
                double price = resultSet.getDouble("Price");
//                String nameType = resultSet.getString("NameType");
                int status = resultSet.getInt("State");

                // Tạo đối tượng Menu với các giá trị từ ResultSet
                result = new Menu(id, nameDrink, price, null, status);
            }
            // Đóng ResultSet sau khi sử dụng
            resultSet.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

//Update food
    public boolean UpdateFood(Menu menu) {
        boolean result = false;
        String storedProcedureCall = "{Call UpdateFood(?, ?, ?, ?, ?)}";
        try(CallableStatement callableStatement = con.prepareCall(storedProcedureCall)){
            callableStatement.setInt(1, menu.Id);
            callableStatement.setString(2, menu.NameDrink);
            callableStatement.setDouble(3, menu.Price);
            callableStatement.setString(4, menu.NameType);
            callableStatement.setInt(5, menu.Status);
            int RowsAffect = callableStatement.executeUpdate();
            if(RowsAffect > 0) result = true;
            else result = false;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    //    Find 
    public List<Menu> FindMenuById(String Id) {
        List<Menu> listMenu = new ArrayList<>();
        String query = "SELECT d.Id, d.NameDrink, d.Price, dt.NameType, d.State FROM Drinks d JOIN DrinkType dt ON d.IdType = dt.Id WHERE d.Id LIKE ? OR d.NameDrink LIKE ? OR dt.NameType LIKE ?";

        try {
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.setString(1, "%" + Id + "%"); // Set Id parameter with wildcards
            prepare.setString(2, "%" + Id + "%"); // Set NameDrink parameter with wildcards
            prepare.setString(3, "%" + Id + "%"); // Set NameType parameter with wildcards

            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                listMenu.add(new Menu(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getString(4),
                        result.getInt(5)
                    )
                );
            }
            return listMenu;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

