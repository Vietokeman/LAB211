/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Components;

import Application.Utilities.DataInput;
import BussinessLayer.Entity.HotelInfomation;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Viet
 */
public class DataValidation {
    private final Scanner sc;
    private final SearchData sd;
    private final DataInput di;

    public DataValidation() {
        sc = new Scanner(System.in);
        sd = new SearchData();
        di = new DataInput();
    }
    
    public String inputHotelID(ArrayList<HotelInfomation> arr) {
        String id = "";
        do {
            System.out.println("Enter id of hotel: ");
            id = sc.nextLine().toUpperCase();
            if (sd.searchHotelByID(arr, id) != null) {
                System.err.println("Duplicated code.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.err.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
    public String inputNameUD(String msg, HotelInfomation hotel){
        String name = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return hotel.getHotelName();
            }else if(!pattern.matcher(name).matches()){
                System.out.println("Please enter the correct format of the name");
            }else{
                return name;
            }
        } while (true);
    }
    public int inputHotelAvailableUD(String msg, int x, int y, HotelInfomation hotel) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = hotel.getHotelRoomAvailable();
                    return input;
                }
                input = Integer.parseInt(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("This must be number");
            check = true;
        }
        return 0;
    }
    public String inputAddressUD(String msg,HotelInfomation hotel){
        String name = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return hotel.getHotelName();
            }else if(!pattern.matcher(name).matches()){
                System.out.println("Please enter the correct format of the address");
            }else{
                return name;
            }
        } while (true);
    }
    public String inputPhoneUD(String msg,HotelInfomation hotel){
        String phone = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("0\\d{9}");
        do {            
            phone = sc.nextLine();
            if(phone.trim().isEmpty()){
                return hotel.getHotelPhone();
            }else if(!pattern.matcher(phone).matches()){
                System.out.println("Please enter the correct format of the phone");
            }else{
                return phone;
            }
        } while (true);
    }
    public int inputHotelRatingUD(String msg, int x, int y, HotelInfomation hotel) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = hotel.getHotelRating();
                    return input;
                }
                input = Integer.parseInt(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("This must be number");
            check = true;
        }
        return 0;
    }
}
