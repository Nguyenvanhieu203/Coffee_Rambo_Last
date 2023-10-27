/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nguye
 */
public class ValidateModel {
    private final String validateEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String validatePass = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d).{6,}$";
    private final String validatePhone = "^\\d{10}$";

    public final Pattern emailPattern;
    public final Pattern passPattern;
    public final Pattern phonePattern;
    
    public ValidateModel() {
        emailPattern = Pattern.compile(validateEmail);
        passPattern = Pattern.compile(validatePass);
        phonePattern = Pattern.compile(validatePhone);
    }
    
    public boolean validateEmailModel(String value) {
        Matcher matcher = emailPattern.matcher(value);
        return matcher.matches();
    }
    
    public boolean validatePassModel(String value) {
        Matcher matcher = passPattern.matcher(value);
        return matcher.matches();
    }
    
    public boolean validatePhoneModel(int value) {
        Matcher matcher = phonePattern.matcher(String.valueOf(value));
        return matcher.matches();
    }
}
