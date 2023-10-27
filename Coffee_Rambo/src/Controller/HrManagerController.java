/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Data.Staff;
import Models.HRManagerModel;
import java.util.List;

/**
 *
 * @author nguye
 */
public class HrManagerController {

    private final HRManagerModel _hRManagerModel;
    public HrManagerController(HRManagerModel hRManagerModel) {
        _hRManagerModel = hRManagerModel;
    }
    
    public List<Staff> GetAllStaff() {
        List<Staff> resultLisst = _hRManagerModel.GetAllStaff();
        return resultLisst;
    }
    
    public Staff GetStaffById(int Id) {
        Staff result = _hRManagerModel.GetStaffById(Id);
        return result;
    }
    
    public boolean UpdateStaff(Staff staff) {
        boolean result = _hRManagerModel.UpdateStaff(staff);
        return result;
    }
    
    public boolean AddStaff(Staff staff) {
        boolean result = _hRManagerModel.AddStaff(staff);
        return result;
    }
    
    public List<Staff> FindStaffById(String Id) {
        List<Staff> result = _hRManagerModel.FindStaffById(Id);
        return result;
    }
}
