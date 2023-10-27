/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nguye
 */
public class ConnectToSql {
    Connection con;
    public ConnectToSql (){
        try{
            String user = "sa";
            String pass = "020303";
            String url = "jdbc:sqlserver://MinhLe\\SQL2022:1433;databaseName=CoffeShop;trustServerCertificate=true";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, pass);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//        ConnectToSql connect = new ConnectToSql();
//        
//        if (connect.con != null) {
//            try {
//                String query = "select * from Staffs where PhoneNumber = '869819316' and PasswordHas = 'MinhLe0203'";
//
//                // Tạo PreparedStatement
//                PreparedStatement prepare = connect.con.prepareStatement(query); 
//
//                // Thực hiện truy vấn
//                ResultSet result = prepare.executeQuery();
//
//                if (result.next()) {
//                    System.out.println("okela");
//                } else {
//                    System.out.println("Không có dữ liệu.");
//                }
//
//                result.close();
//                prepare.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
//        }
//    }
}
