/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.BillDetailModel;
import Models.Data.BillDetail;
import Models.Data.Drink;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BillDetailController {
    private final BillDetailModel _BillDetailModel;
    public BillDetailController (BillDetailModel billDetailModdel){
        _BillDetailModel = billDetailModdel;
    }
    
    public boolean AddBillDetail (int idBill, List<Drink> drinks, List<Integer> quantity){
        boolean result = _BillDetailModel.addBillDetail(idBill, drinks, quantity);
        if(result) return true;
        return false;
    }
    
    public int getRecenBill (){
        int result = _BillDetailModel.getRecentBill();
        return result;
    }
}
