/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Service;

import Application.UI.Menu;
import Application.Utilities.DataInput;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Components.SearchData;
import BussinessLayer.Entity.HotelInfomation;
import DataLayer.ProductDao.HotelDao;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Viet
 */
public class HotelService implements IHotelService {

    private ArrayList<HotelInfomation> arrHotel;
    private DataInput di;
    private DataValidation dv;
    private HotelDao hd;
    private SearchData sd;
    private Menu mn;
    private static final Scanner sc = new Scanner(System.in);
    private List<HotelInfomation> deletedHotel = new ArrayList<>();;

    public HotelService() throws EOFException {
        arrHotel = new ArrayList<>();
        di = new DataInput();
        dv = new DataValidation();
        hd = new HotelDao();
        sd = new SearchData();
        mn = new Menu();
        try {
            hd.loadDataFromFile(arrHotel, "Hotel.dat");
        } catch (Exception e) {
            System.out.println("List empty");
        }

        if (arrHotel.isEmpty()) {
            System.out.println("Empty list, add new one");
            addHotel();
        }
    }

    String blank(int n) {
        String result = "";
        for (int i = 0; i < n+5; i++) {
            result += " ";
        }
        return result;
    }

    public void graphic(HotelInfomation hotel) {
    String address = hotel.getHotelAddress();
    Pattern pattern = Pattern.compile("(.{0,30})\\b"); 
    Matcher matcher = pattern.matcher(address);
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    // In nhóm đầu tiên 
    if(matcher.find()) {
        System.out.printf("|%9s|%20s|%25s|%30s|%11s|%7s star|%8s 000|%12s|%10s\n",
                hotel.getHotelId(), 
                hotel.getHotelName(),
                hotel.getHotelRoomAvailable(),
                matcher.group(1), hotel.getHotelPhone(), hotel.getHotelRating(),
                hotel.getHotelPrice(),
                hotel.getHotelClassify(),
                hotel.getHotelService());
    }
    
    // In các nhóm còn lại
    while(matcher.find()) {
        // Kiểm tra xem có phải nhóm rỗng không
        if(!matcher.group(1).equals("")) {  
            System.out.printf("|%9s|%20s|%25s|%30s|%11s|%12s|%12s|%12s|%10s\n",
                    "", "", "", 
                    matcher.group(1), "", "",hotel.getHotelPrice(),hotel.getHotelClassify(),hotel.getHotelService());
        }
    }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

}

    @Override
    public void addHotel() {
        String hotelId;
        String hotelName;
        int hotelRoomAvailable;
        int hotelPrice;
        String hotelClassify;
        String hotelAddress;
        String hotelPhone;
        int hotelRating;
        String hotelService;
        boolean choice = true;
        while (choice) {
            hotelId = autoID();
            hotelName = di.inputStringPattern("Enter name of hotel: ", "^[a-zA-Z\\s]+$");
            hotelRoomAvailable = di.inputInteger("Enter the number of available rooms", 0, 10000);
            hotelAddress = di.inputStringCanBlank("Enter hotel address: ");
            hotelPhone = di.inputStringPattern("Enter hotel phone number(like 0xxxxxxxx)", "0\\d{9}");
            hotelRating = di.inputInteger("Enter hotel rating(0-10)", 0, 10);
            System.out.println("Enter hotel Price");
            hotelPrice = sc.nextInt();
            if(hotelPrice>800){
                hotelClassify = "Luxury";
             
            }else{
                hotelClassify="Normal";
            }
            System.out.println("Enter hotel service: laundry/gym/pool");
            hotelService = di.inputStringCanBlank("Enter service");
            arrHotel.add(new HotelInfomation(hotelId, hotelName, hotelRoomAvailable, hotelAddress, hotelPhone, hotelRating, hotelPrice,hotelClassify,hotelService));
            hd.saveDataFromFile(arrHotel, "Hotel.dat");
            System.out.println("Add hotel succesfully");
            choice = di.inputYN("Do you want to continue(Y/N): ");
        }
    }

    @Override
    public void checkExitsHotel() {
        String id = di.inputStringCanBlank("Enter id of hotel you want to check: ");
        ArrayList<HotelInfomation> arrTemp = new ArrayList<>();
        hd.loadDataFromFile(arrTemp, "Hotel.dat");
        HotelInfomation hi = sd.searchHotelByID(arrTemp, id);
        if (hi != null) {
            System.out.println("Exist Hotel");
            graphic(hi);
        } else {
            System.out.println("“Not Hotel Found!");
        }
    }

    @Override
    public void updateHotelInfomation() {
        System.out.println("Enter ID of product you want to update: ");
        String id = sc.nextLine();
        HotelInfomation hotel = sd.searchHotelByID(arrHotel, id);
        String hotelName;
        int hotelRoomAvailable;
        int hotelPrice;
        String hotelClassify;
        String hotelAddress;
        String hotelPhone;
        String hotelService;
        int hotelRating;
        if (hotel != null) {
            System.out.println("Found! Here is product: ");
            graphic(hotel);
            hotelName = dv.inputNameUD("Enter name you want to update: ", hotel);
            hotelRoomAvailable = dv.inputHotelAvailableUD("Enter available of hotel update: ", 0, 10000, hotel);
            hotelAddress = dv.inputAddressUD("Enter hotel address you want to update: ", hotel);
            hotelPhone = dv.inputPhoneUD("Enter phone number you want to update: ", hotel);
            hotelRating = dv.inputHotelRatingUD("Enter hotel rating you want to update: ", 0, 5, hotel);
            hotelPrice = sc.nextInt();
             if(hotelPrice>800){
                hotelClassify = "Luxury";
             
            }else{
                hotelClassify="Normal";
            }System.out.println("Enter service you want to change");
            hotelService = sc.nextLine();
            arrHotel.set(arrHotel.indexOf(hotel), new HotelInfomation(id, hotelName, hotelRoomAvailable, hotelAddress, hotelPhone, hotelRating,hotelPrice,hotelClassify,hotelService));
            System.out.println("Here is hotel after update: ");
            System.out.println(hotel);
            hd.saveDataFromFile(arrHotel, "Hotel.dat");
        } else {
            System.out.println("Not found!");
        }
    }

    @Override
    public void deleteHotel() {
        System.out.println("Enter id of product you want to delete: ");
        String id = sc.nextLine();
        HotelInfomation hotel = sd.searchHotelByID(arrHotel, id);
        boolean choice = true;
        if (hotel != null) {
            System.out.println("Found! Here is product: ");
            graphic(hotel);
            choice = di.inputYN("Do you ready want to delete(Y/N): ");
            if (choice) {
                arrHotel.remove(hotel);
                deletedHotel.add(hotel);
                hd.saveDataFromFile(deletedHotel, "countDelete.txt");
                System.out.println("Delete successfully!");
            }
        } else {
            System.out.println("Not found!");
        }
        hd.saveDataFromFile(arrHotel, "Hotel.dat");
    }


    @Override
    public void searchHotel() {
        ArrayList<String> ops = new ArrayList<>();
        int choice;
        ArrayList<HotelInfomation> arrTemp = arrHotel;
        System.out.println("Select the information you want to search for flights: ");
        ops.add("Search by Hotel_ID");
        ops.add("Search by Hotel_Name");
        ops.add("Exit");
        do {
            boolean check = false;
            choice = mn.intGetChoiceString(ops, 1, 3);
            switch (choice) {
                case 1:
                    String id = sc.nextLine();
                    for (HotelInfomation hotelInfomation : arrTemp) {
                        if (hotelInfomation.getHotelId().contains(id)) {
                            graphic(hotelInfomation);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found id" + id);
                    }
                    break;
                case 2:
                    String name = sc.nextLine();

                    for (HotelInfomation hotelInfomation : arrTemp) {
                        if (hotelInfomation.getHotelName().contains(name)) {
                            graphic(hotelInfomation);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found name" + name);
                    }
                    break;

                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 2));
    }

    @Override
    public void displayHotel() {
        Collections.sort(arrHotel);
        System.out.printf("|%9s|%20s|%25s|%30s|%11s|%10s|%12s|%12s|%10s\n", "HotelID", "HotelName", "HotelRoomAvailable", "HotelAddress", "HotelPhone", "HotelRating","HotelPrice","HotelClassify","HotelService");
        for (HotelInfomation hotelInfomation : arrHotel) {
            graphic(hotelInfomation);
        }
    }
//    public void findHotelByRating() {
//      int rating;
//      rating = sc.nextLine();
//    ArrayList<HotelInfomation> results = new ArrayList<>();
//    for (HotelInfomation hotel : arrHotel) {
//        if (hotel.getHotelRating() > rating) {
//            results.add(hotel);
//        }
//    }
//
//    if (results.isEmpty()) {
//        System.out.println("Không tìm thấy khách sạn phù hợp với tiêu chí.");
//    } else {
//        System.out.println("Đã tìm thấy các khách sạn sau:");
//    }
//        for (HotelInfomation t : results) {
//            graphic(t);
//        }
//}
    public void finding(){
        String four;
        System.out.println("Enter 4 number last of phone");
        four = sc.nextLine();
        int r;
        System.out.println("Enter rating");
        r = sc.nextInt();
        for (HotelInfomation hotel : arrHotel) {
            if(hotel.getHotelPhone().contains(four) && hotel.getHotelRating()> r)
                graphic(hotel);
        }
        }
    public String autoID(){
        int endID = 0;
        for (HotelInfomation hotelInfomation : arrHotel) {
            String hotelID = hotelInfomation.getHotelId();
            if(hotelID.matches("H\\d{2}")){
                int autoN = Integer.parseInt(hotelID.substring(1));
                endID = Math.max(endID, autoN);
            }
        }endID ++;
        for (HotelInfomation i : deletedHotel) {
            if(i.getHotelId().contains("H"+ String.format("%02d", endID))){
                endID++;
            }
        }
        return String.format("H%02d", endID);
    }

    public void searchHotelByService() {
        String service;
        System.out.println("Enter the service you want to find");
        service = sc.nextLine();
        ArrayList<HotelInfomation> arr = new ArrayList<>();
        for (HotelInfomation hotelInfomation : arrHotel) {
            if(hotelInfomation.getHotelService().equals(service))
                arr.add(hotelInfomation);
        }
        for (HotelInfomation hotelInfomation : arr) {
            graphic(hotelInfomation);
        }
    }

 }
