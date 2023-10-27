/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Data.TableFood;
import Models.TableFoodModel;
import java.util.List;

/**
 *
 * @author nguye
 */
public class TableFoodController {

    private final TableFoodModel _tableFoodModel;
    public TableFoodController(TableFoodModel tableFoodModel) {
        _tableFoodModel = tableFoodModel;
    }
    
    public List<TableFood> GetTableFood(String floor) {
        List<TableFood> result = _tableFoodModel.getTableByFloor(floor);
        if(result == null ) return null;
        return result;
    }
    
    public boolean addTable(TableFood tableFood){
        boolean result = _tableFoodModel.addTableFood(tableFood);
        if(result) return true;
        return false;
    }
    
    public boolean deleteTable(TableFood tableFood){
        boolean result = _tableFoodModel.dropTable(tableFood);
        if(result) return true;
        return false;
    }
}
