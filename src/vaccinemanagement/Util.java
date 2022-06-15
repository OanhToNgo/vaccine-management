/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.util.Scanner;
import java.sql.Date;
import java.util.Calendar;
import java.util.StringTokenizer;
import sun.nio.cs.ext.MS950;

/**
 *
 * @author oanhn
 */
public class Util {
    private static Scanner sc = new Scanner(System.in);
     public static int getInt(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static String getID(String inputMsg, String errorMsg,String format){
        String id;
        boolean match;
        while(true){
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if(id.length() == 0 || id.isEmpty()||match == false)
                System.out.println(errorMsg);
            else
                return id;
        }
    }
    
    public static String getString(String inputMsg,String errorMsg){
        String id;
        while(true){
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if(id.length()== 0 || id.isEmpty())
                System.out.println(errorMsg);
            else
                return id;
        }
    }
    
    //DATE HANDLER
    public static boolean isLeap(int y){
        boolean result = false;
        if((y%400 == 0)||(y%4 == 0)&&(y%100 !=0 ))
            result=true;
        return result;
    }
    
    public static boolean valid(int y, int m, int d){
        if(y<0 || m<0 ||d<0 ||m>12 ||d>31)
            return false;
        int maxD = 31;
        if(m ==4 || m == 6 || m == 9 || m == 11)
            maxD = 30;
        else if(m == 2){
            if(isLeap(y))
                maxD = 29;
            else
                maxD = 28;
        }
        return d <= maxD;  
    }
    
    public static long toDate(String ymd){
        if(ymd.equalsIgnoreCase("NULL"))
            return -1;
        else{
             StringTokenizer stk = new StringTokenizer(ymd, "/-");
            int y = Integer.parseInt(stk.nextToken());
            int m = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            if (!valid(y, m, d)) {
                return -1;
            }
            Calendar cal = Calendar.getInstance();
            cal.set(y, m - 1, d);
            long t = cal.getTime().getTime();
            return t;
            
        }  
    }
    public static Date getDateFromFile(String input){
        long time = toDate(input);
        Date date = new Date(time);
        return date;
        
    }
    public static Date getDateV2(String input){
        Date date;
        if(input.isEmpty()||input.length()==0)
            date = null;
        else{
            long time = toDate(input);
            if (time == -1)
                date = null;
            else
                date = new Date(time);
        }
        
        return date;
       
    }
   
}
