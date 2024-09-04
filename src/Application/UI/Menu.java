/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import Application.Utilities.DataInput;
import java.util.ArrayList;

/**
 *
 * @author Viet
 */
public class Menu {
    private final DataInput ip;

    public Menu() {
        ip = new DataInput();
    }
    /**
     * Using create list menu of in array
     * @param options
     * @param x
     * @param y
     * @return 
     */
    public int intGetChoice(ArrayList options, int x, int y) {
        int respose;
        
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%2d.", i + 1);
            System.out.println(options.get(i));
        }
        respose = ip.inputInteger("Please input integer " + x + " to " + y +"", x, y);
        return respose;
    }
    String Blank(int n){
        String result = "";
        for (int i = 0; i < n; i++) {
            result += " ";
        }
        return result;
    }
    public int intGetChoiceString(ArrayList<String> options, int x, int y) {
        int respose;
        int max = options.get(0).length();
        for (String option : options) {
            if(option.length() > max){
                max = option.length();
            }
        }
        max += 3;
        for (int i = 0; i < max+8; i++) {
            System.out.printf("-");
        }
        System.out.println("");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("|   %2d.", i + 1);
            String result = options.get(i) + Blank(max - options.get(i).length()) + "|";
            System.out.printf("%s\n",result);
        }
        for (int i = 0; i < max+8; i++) {
            System.out.printf("-");
        }
        System.out.println("");      
        respose = ip.inputInteger("Please input integer " + x + " to " + y +"", x, y);
        return respose;
    }
    /**
     * Using return object choice in array
     * @param options
     * @param x
     * @param y
     * @return 
     */
    public Object refGetChoice(ArrayList options, int x, int y){
        int respose;
        do{
            respose = intGetChoice(options, x, y);
        }while(respose < x || respose > y);
        return options.get(respose - 1);
    }
}
