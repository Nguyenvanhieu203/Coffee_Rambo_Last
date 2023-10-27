/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.Drink;
import Models.Data.Menu;
import Models.Data.Staff;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class HRManagerModel extends ConnectToSql {
    public List<Staff> GetAllStaff() {
        List<Staff> listStaff = new ArrayList<>();
        String query = "select * from Staffs";
        try{
            PreparedStatement prepare = con.prepareStatement(query); 
            ResultSet result = prepare.executeQuery();
            
            while(result.next()){
                listStaff.add(new Staff(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDate(5),
                        result.getInt(7),
                        result.getString(12),
                        result.getString(6),
                        result.getString(8),
                        result.getDate(9),
                        result.getBigDecimal(10),
                        result.getString(11),
                        result.getInt(13)
                    )
                );
            }
            return listStaff;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
//    Update Staff
    public boolean UpdateStaff(Staff staff) {
        boolean result = false;
        String storedProcedureCall = "{Call UpdateStaff(?, ?, ?, ?, ?, ? , ? , ? ,?,?,?)}";
        try(CallableStatement callableStatement = con.prepareCall(storedProcedureCall)){
            callableStatement.setInt(1, staff.iD);
            callableStatement.setString(2, staff.firstName);
            callableStatement.setString(3, staff.lastName);
            callableStatement.setDate(4, (Date) staff.dob);
            callableStatement.setInt(5,  staff.phoneNumber);
            callableStatement.setString(6,  staff.Email);
            callableStatement.setString(7,  staff.gender);
            callableStatement.setDate(8, (Date) staff.hireDate);
            callableStatement.setBigDecimal(9,  staff.salary);
            callableStatement.setString(10,  staff.position);
            callableStatement.setInt(11,  staff.State);
            int RowsAffect = callableStatement.executeUpdate();
            if(RowsAffect > 0) result = true;
            else result = false;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
        
    }
    
    public Staff GetStaffById(int Id) {
        Staff result =  null;
        String query = "SELECT * FROM Staffs WHERE Id = ?";
        try {
            PreparedStatement prepare = con.prepareStatement(query); 
            prepare.setInt(1, Id);

            // Thực hiện truy vấn và nhận ResultSet
            ResultSet resultSet = prepare.executeQuery();

            // Kiểm tra xem ResultSet có dữ liệu không
            if (resultSet.next()) {
                // Lấy các giá trị từ ResultSet và tạo đối tượng Menu
                int id = resultSet.getInt("Id");
                String FirstName = resultSet.getString("FirstName");
                String LastName = resultSet.getString("LastName");
                Date Dob = resultSet.getDate("Dob");
                int PhoneNumber = resultSet.getInt("PhoneNumber");
                String Gender = resultSet.getString("Gender");
                Date HireDate = resultSet.getDate("HireDate");
                BigDecimal Salary = resultSet.getBigDecimal("Salary");
                String Position = resultSet.getString("Position");
                String Email = resultSet.getString("Email");
                int status = resultSet.getInt("State");

                // Tạo đối tượng Menu với các giá trị từ ResultSet
                result = new Staff(id, FirstName, LastName, null, Dob, PhoneNumber, Email, null, Gender, HireDate, Salary, Position, status);
            }
            // Đóng ResultSet sau khi sử dụng
            resultSet.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public boolean AddStaff(Staff staff) {
        boolean result = false;
        String storedProcedureCall = "{Call AddStaff(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try(CallableStatement callableStatement = con.prepareCall(storedProcedureCall)){
//            callableStatement.setInt(1, menu.Id);
            callableStatement.setString(1, staff.firstName);
            callableStatement.setString(2, staff.lastName);
            callableStatement.setDate(3, (Date)staff.dob);
            callableStatement.setString(4, staff.passwordHas);
            callableStatement.setInt(5, staff.phoneNumber);
            callableStatement.setString(6, staff.gender);
            callableStatement.setDate(7, (Date)staff.hireDate);
            callableStatement.setBigDecimal(8, staff.salary);
            callableStatement.setString(9, staff.position);
            callableStatement.setString(10, staff.Email);
            callableStatement.setInt(11, staff.State);
            int RowsAffect = callableStatement.executeUpdate();
            if(RowsAffect > 0) result = true;
            else result = false;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    public List<Staff> FindStaffById(String Id) {
    List<Staff> listStaff = new ArrayList<>();
    String query = "SELECT * FROM Staffs WHERE Id LIKE ? OR FullName LIKE ?";
    
    try {
        PreparedStatement prepare = con.prepareStatement(query);
        prepare.setString(1, "%" + Id + "%"); // Set Id parameter with wildcards
        prepare.setString(2, "%" + Id + "%"); // Set FullName parameter with wildcards
        
        ResultSet result = prepare.executeQuery();
        
        while (result.next()) {
            listStaff.add(new Staff(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDate(5),
                    result.getInt(7),
                    result.getString(12),
                    result.getString(6),
                    result.getString(8),
                    result.getDate(9),
                    result.getBigDecimal(10),
                    result.getString(11),
                    result.getInt(13)
                )
            );
        }
        return listStaff;
        
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

}
