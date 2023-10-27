/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Data.Drink;
import Models.Data.DrinkType;
import Models.Data.Menu;
import Models.MenuModel;
import java.util.List;

/**
 *
 * @author nguye
 */
public class MenuController {

    private final MenuModel _menuModel;
    public MenuController(MenuModel menuModel) {
        _menuModel = menuModel;
    }
    public List<Drink> getAllMenuDrink() {
        List<Drink> resultList = _menuModel.showMenu();
        if(resultList != null) return resultList;
        return null;
    }
    public List<Menu> getAllMenuManager() {
        List<Menu> resultList = _menuModel.getMenuManager();
        if(resultList != null) return resultList;
        return null;
    }
    
//    Insert Food
    public boolean AddFood(Menu menu) {
        boolean result = _menuModel.AddFood(menu);
        return result;
    }
//    Get all Drink type
    public List<DrinkType> getAllDrinkType() {
        List<DrinkType> resultList = _menuModel.getAlldrinkType();
        return resultList;
    }
    public List<Drink> getDrinkByType(String typeName) {
        List<Drink> resultList = _menuModel.getDrinkByType(typeName);
        return resultList;
    }
    public List<Drink> getDrinkByName (String drinkName){
        List<Drink> resultList = _menuModel.getDrinkByName(drinkName);
        return resultList;
    }
//    Get Food By Id
    public Menu getFoodById(int Id) {
        Menu resultList = _menuModel.GetFoodById(Id);
        return resultList;
    }
//    Update Food By Id
    public boolean UpdateFood(Menu menu) {
        boolean result = _menuModel.UpdateFood(menu);
        return result;
    }
    
    public List<Menu> FindMenuById(String Id) {
        List<Menu> resultList = _menuModel.FindMenuById(Id);
        return resultList;
    }
    
}
