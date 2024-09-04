/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Service.HotelService;
import java.io.EOFException;
import java.util.ArrayList;

/**
 *
 * @author Viet
 */
public class HotelManagement {
    public static void main(String[] args) throws EOFException {
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        HotelService hs = new HotelService();
        int choice;
        ops.add("Adding new Hotel");
        ops.add("Checking exits Hotel");
        ops.add("Updating Hotel information");
        ops.add("Deleting Hotel");
        ops.add("Searching Hotel.");
        ops.add("Displaying a hotel list");
        ops.add("Search by Service");
        ops.add("Orther: Exit");
        do {
            choice = menu.intGetChoiceString(ops, 1, 8);
            switch (choice) {
                case 1:
                    hs.addHotel();
                    break;
                case 2:
                    hs.checkExitsHotel();
                    break;
                case 3:
                    hs.updateHotelInfomation();
                    break;
                case 4:
                    hs.deleteHotel();
                    break;
                case 5:
                    hs.searchHotel();
                    break;
                case 6:
                    hs.displayHotel();
                    break;
                case 7:
                    hs.searchHotelByService();
                default:
                    System.out.println("Quit!");
            }
        } while (!(choice < 1 || choice > 7));
    }
}
