 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Viet
 */
public class FileManager  {
    public <T> boolean saveDataToFile(List<T> list, String FName, String msg) {
        try {

            File f = new File(FName);
            ObjectOutputStream o;
            try (FileOutputStream fos = new FileOutputStream(f)) {
                o = new ObjectOutputStream(fos);
                for (T item : list) {
                    o.writeObject(item);
                }
            }
            o.close();
            System.out.println(msg);
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
//     public <T> boolean saveDataToFile(List<T> list, String fileName, String msg) {
//        try {
//            File file = new File(fileName);
//
//            FileOutputStream fos = new FileOutputStream(file);
//            BufferedOutputStream bos = new BufferedOutputStream(fos);
//            ObjectOutputStream oos = new ObjectOutputStream(bos);
//
//            for (T item : list) {
//                oos.writeObject(item);
//            }
//
//            oos.close();
//
//            System.out.println(msg);
//            return true;
//        } catch (Exception e) {
//            System.out.println(e);
//            return false;
//        }
   
    
    public <T> boolean loadDataFromFile(List<T> list, String FName){
        try {
            File f = new File(FName);
            if (!f.exists()) {
                return false;
            }
            ObjectInputStream oi;
            try (FileInputStream fis = new FileInputStream(f)) {
                oi = new ObjectInputStream(fis);
                if (f.length() == 0) {
                    System.err.println("File is empty");
                }   boolean check = true;
                while (check) {
                    try {
                        T c = (T) oi.readObject();
                        list.add(c);
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
            oi.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FName);
            return false;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + FName + e);
            return false;
        } catch (NumberFormatException e) {
            System.err.println("Error parsing double value from input: " + e.getMessage());
            return false;
        }
        return true;
    }
    
//        BufferedInputStream ip = null;
//        BufferedOutputStream op = null;
//        try {
//            InputStream ips = new FileInputStream(FName);
//            OutputStream ops = new FileOutputStream(FName);
//            ip = new BufferedInputStream(ips);
//            op = new BufferedOutputStream(ops);
//            int c;
//            while((c = ip.read())!= -1){
//                op.write(c);
//            }
//        } finally  {
//            if(ip != null){
//                ip.close();
//            }
//            if(op != null){
//                op.close();
//            }
//        }return true;
//        }
}


