/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Data.ModelMessage;
import Models.LoginModel;
import Models.Data.Staff;
import Models.ServiceMail;

/**
 *
 * @author nguye
 */
public class LoginController extends Staff {
    private final LoginModel _loginModel;
    public LoginController(LoginModel loginModel) {
        _loginModel = loginModel;
    }
    //    Login Controller
    public boolean Login(Staff staff) {
        Boolean result = _loginModel.Login(staff);
        if(result) return true;
        return false;
    }
//    SignIn Controller
    public boolean SignUp(Staff staff) {
        Boolean result = _loginModel.SignUp(staff);
        if(result) return true;
        return false;
    }
//    Get FullName Staff
    public String GetFullNameStaff(String Email, String Password) {
        String Result = _loginModel.getStaffNameByAccount(Email, Password);
        if(Result != null || !"".equals(Result)) return Result;
        return null;
    }
    
//    Get Position User
    public String getRoleUser(String Email, String Password) {
        String result = _loginModel.getRoleUser(Email, Password);
        if(result != null || !"".equals(result)) return result;
        return null;
    }
    
    public ModelMessage SendMail(String Email, String code){
        ServiceMail svMail = new ServiceMail();
        ModelMessage result =  svMail.sendMain(Email, code);
        return result;
    }
}
