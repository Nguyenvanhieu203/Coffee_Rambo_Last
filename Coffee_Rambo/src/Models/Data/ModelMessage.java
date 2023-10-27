/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Data;

/**
 *
 * @author nguye
 */
public class ModelMessage {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModelMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ModelMessage() {
    }
    
    boolean success; //Trạng thái Thàng công hay Thất bại
    String message;  //Lời nhắn
}
