/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.ProductDao;

import BussinessLayer.Entity.HotelInfomation;
import DataLayer.FileManager;
import java.util.List;

/**
 *
 * @author Viet
 */
public class HotelDao {
    private final FileManager fm;

    public HotelDao() {
        fm = new FileManager();
    }
    
    public boolean loadDataFromFile(List<HotelInfomation> hotel ,String fName){
        return fm.loadDataFromFile(hotel, fName);
    }
   
    public boolean saveDataFromFile(List<HotelInfomation> hotel,String fName){
        return fm.saveDataToFile(hotel, fName, "Product save file successfull!");
    }
}
