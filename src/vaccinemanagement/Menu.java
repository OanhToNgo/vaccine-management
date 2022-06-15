/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author oanhn
 */
public class Menu<T> {
    Scanner sc = new Scanner(System.in);
     public int getIntChoice(ArrayList lst) {
        int number;
        while (true) {
            try {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + "-" + lst.get(i));
                }
                System.out.print("Please choose " + "1.." + lst.size() + ":");
                number = Integer.parseInt(sc.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println("Please try again!");
            }
        }
    }
    
    
}
