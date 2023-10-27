/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.BillModel;
import Models.Data.Bill;
import Models.Data.Member;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BillController {
    private final BillModel _BillModel;
    public BillController(BillModel billModel){
        _BillModel = billModel;
    }
//    public List<Bill> getAllBills() {
//        List<Bill> listResult = _BillModel.getAllBills();
//        if(listResult != null ) return listResult;
//        return null;
//    }
    public boolean AddBill (Bill bill){
        boolean result = _BillModel.addBill(bill);
        if(result) return true;
        return false;
    }
    
    public int getIdTable (String nameTable){
        int result = _BillModel.getIdFromTable(nameTable);
        return result;
    }
}
