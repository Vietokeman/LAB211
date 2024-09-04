/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Entity;

import java.io.Serializable;

/**
 *
 * @author Viet
 */
public class HotelInfomation implements Serializable, Comparable<HotelInfomation> {

    private String hotelId;
    private String hotelName;
    private int hotelRoomAvailable;
    private String hotelAddress;
    private String hotelPhone;
    private int hotelRating;
    private int hotelPrice;
    private String hotelClassify;
    private String hotelService;

    public HotelInfomation(String hotelId, String hotelName, int hotelRoomAvailable, String hotelAddress, String hotelPhone, int hotelRating, int hotelPrice, String hotelClassify,String hotelService) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelRoomAvailable = hotelRoomAvailable;
        this.hotelAddress = hotelAddress;
        this.hotelPhone = hotelPhone;
        this.hotelRating = hotelRating;
        this.hotelPrice = hotelPrice;
        this.hotelClassify = hotelClassify;
        this.hotelService = hotelService;
    }

    public String getHotelService() {
        return hotelService;
    }

    public void setHotelService(String hotelService) {
        this.hotelService = hotelService;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getHotelClassify() {
        return hotelClassify;
    }

    public void setHotelClassify(String hotelClassify) {
         if(getHotelPrice()>800){
            this.hotelClassify="Luxury";
        }else{
            this.hotelClassify="Normal";
        }
    }



    public String getHotelId() {
        return hotelId;
    }

    public void setHotel_Id(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHote_Name(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRoomAvailable() {
        return hotelRoomAvailable;
    }

    public void setHotelRoom_Available(int hotelRoomAvailable) {
        this.hotelRoomAvailable = hotelRoomAvailable;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    @Override
    public String toString() {
        return String.format("|%9s|%20s|%5d|%20s|%11s|%4d star|%8d 000|%12s|%10s\n", hotelId, hotelName, hotelRoomAvailable, hotelAddress, hotelPhone, hotelRating,hotelPrice,hotelClassify,hotelService);
    }

    @Override
    public int compareTo(HotelInfomation o) {
        if (this.getHotelId().compareTo(o.getHotelId()) > 0){
            return 1;
        } else if (this.getHotelId().compareTo(o.getHotelId()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
