/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.ValidateModel;

/**
 *
 * @author nguye
 */
public class ValidateController {
    private final ValidateModel _validateModel;
    public ValidateController( ValidateModel validateModel){
        _validateModel = validateModel;
    }
    
    public boolean validateEmail (String value) {
        Boolean result = _validateModel.validateEmailModel(value);
        if(result) return true;
        return false;
    }
    
    public boolean validatePass (String value) {
        Boolean result = _validateModel.validatePassModel(value);
        if(result) return true;
        return false;
    }
    
    public boolean validatePhone (int value) {
        Boolean result = _validateModel.validatePhoneModel(value);
        if(result) return true;
        return false;
    }
}
