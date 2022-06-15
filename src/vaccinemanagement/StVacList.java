/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.Date;
import java.util.Scanner;
/**
 *
 * @author oanhn
 */
public class StVacList {
    ArrayList<StudentVac> sVList= new ArrayList<>();
    StudentList sList = new StudentList();
    ArrayList<Student> sTmp = new ArrayList<>();
    VaccineList vList = new VaccineList();
    Scanner sc = new Scanner(System.in);
    public StVacList(){
        
    }
    public StVacList(Student s){
       
    }
    public void addStudentList(){
        sList.AddFromFile("Student.txt");
        
    }
    public void addVaccineList(){
        vList.AddFromFile("Vaccines.txt");
    }
    public void printStudentNotVaccinated(){
        for (int i = 0; i <sVList.size(); i++) {
            if(sVList.get(i).vID == null)
                System.out.println(sVList.get(i).getsID()+", "+sVList.get(i).getName());
        }
    }
    public void addToNotVaccinatedList(){
        for (int i = 0; i <sList.stList.size(); i++) {
            int check =0;
            for (int j = 0; j <sVList.size(); j++) {
                if(sList.stList.get(i).getsID().equalsIgnoreCase(sVList.get(j).getsID())){
                    check =1;
                    break;
                }
                
            }
            if(check == 0){
                sTmp.add(sList.stList.get(i));
            }
            
        }
        for (int i = 0; i < sTmp.size(); i++) {
            StudentVac tmp = new StudentVac(sTmp.get(i));
            sVList.add(tmp);
        }
        
    }
    public void printVaccineList(){
        vList.printList();
    }
    public void AddNewInjection(){
        if (sVList.isEmpty())
            System.out.println("The list is empty.");
        else {
            printStudentNotVaccinated();
            String tmp = Util.getString("Input an ID", "try again");
            int result = searchId(tmp);
            if (result == -1) {
                System.out.println("Cannot find this ID");
            } else {
                if (sVList.get(result).getvID() != null) {
                    System.out.println("This student has at least one shot.");
                    return;
                } else {
                    printVaccineList();
                    int c2 = Util.getInt("Please choose one", "Please try again");
                    Vaccine v = vList.vacList.get(c2 - 1);
                    sVList.get(result).setvID(v.getvID());
                    String p1 = Util.getString("Input a place", "Please try again");
                    sVList.get(result).setPlace1(p1);
                    Date d1;
                    Date now = new Date(System.currentTimeMillis());
                    boolean flag = true;
                    do {
                        System.out.println("Please input a date");
                        String dInput = sc.nextLine();
                        d1 = Util.getDateV2(dInput);
                        if (d1.compareTo(now) > 0) {
                            System.out.println("Please enter another day which is smaller than today.");
                        } else {
                            flag = false;
                        }
                    } while (flag);

                    sVList.get(result).setD1(d1);
                    long time = (now.getTime() - d1.getTime()) / 86400000;
                    Menu d2SubMenu = new Menu();
                    ArrayList<String> d2Menu = new ArrayList<>();
                    d2Menu.add("Press 1 to insert 2nd shot.");
                    d2Menu.add("Press 2 to quit.");
                    int d2Choice;
                    if (time >= 28) {
                        do {
                            System.out.println("Do you want to upgdate 2nd shot?");
                            d2Choice = d2SubMenu.getIntChoice(d2Menu);
                            if (d2Choice == 1) {
                                String p2 = Util.getString("Input a place", "Try again");
                                sVList.get(result).setPlace2(p2);
                                Date d2;
                                boolean flag1 = true;
                                do {
                                    System.out.println("Please input a 2nd date");
                                    String d2Input = sc.nextLine();
                                    d2 = Util.getDateV2(d2Input);
                                    if (d2.compareTo(d1) < 0) {
                                        System.out.println("Please enter another day which is greater than 1st date.");
                                    } else {
                                        long t = (d2.getTime() - d1.getTime()) / 86400000;
                                        if (t >= 28 && t <= 84) {
                                            sVList.get(result).setD2(d2);
                                            flag1 = false;
                                        } else {
                                            System.out.println("The 2nd shot must be from 4 to 12 weeks.");
                                        }
                                    }
                                } while (flag1);
                            }

                        } while ((d2Choice - 1 != 1) && sVList.get(result).getD2() == null);
                    }
                    System.out.println("The student is added successfully.");

                }

            }
            
        }
    }
    
    public void UpdateAnInjection(){
        if(sVList.isEmpty())
            System.out.println("The list is empty.");
        else{
            //print the list with 1 or 2 shots
            System.out.println("The list of student that can be updated: ");
            for (int i = 0; i < sVList.size(); i++) {
                if (sVList.get(i).getvID() != null) {
                    System.out.println(sVList.get(i).getsID() + ", " + sVList.get(i).getName() + ", " + sVList.get(i).getPlace1()
                            + ", " + sVList.get(i).getD1() + ", " + sVList.get(i).getPlace2() + ", " + sVList.get(i).getD2());
                }   
            }
            String tmp = Util.getString("Please enter an ID to update","Try again");
            int result = searchId(tmp);
            if(result ==-1)
                System.out.println("Cannot find this ID");
            else{
                if(sVList.get(result).getvID()== null){
                    System.out.println("This student doesn't have at least one shot. Please choose Add function.");
                    return;
                }
                else{
                    System.out.println("Please input a new place for 1st shot: ");
                    String p1New = sc.nextLine();
                    if (p1New.equalsIgnoreCase(sVList.get(result).getPlace1()) || p1New.isEmpty() || p1New.length() == 0) {
                        System.out.println("The place didn't change.");
                    } else {
                        sVList.get(result).setPlace1(p1New);
                    }
                    Date d1New;
                    Date now = new Date(System.currentTimeMillis());
                    boolean flag = true;
                    do {
                        System.out.println("Please input a new date for 1st shot: ");
                        String d1NewInput = sc.nextLine();
                        d1New = Util.getDateV2(d1NewInput);
                        if (d1New == null) {
                            System.out.println("The date cannot be null.");

                        } else {
                            if (d1New.compareTo(now) > 0) {
                                System.out.println("Please enter another day which is smaller than today.");
                            } else {
                                flag = false;
                            }

                        }

                    } while (flag);
                    if (d1New.compareTo(sVList.get(result).getD1()) == 0) {
                        System.out.println("The date didn't change");
                    } else {
                        sVList.get(result).setD1(d1New);
                    }
                    //after changed the 1st place and shot
                    long time1 = (now.getTime() - d1New.getTime()) / 86400000;
                    Menu d3SubMenu = new Menu();
                    ArrayList<String> d3Menu = new ArrayList<>();
                    d3Menu.add("Press 1 to insert 2nd shot.");
                    d3Menu.add("Press 2 to quit.");
                    int d3Choice =3;
                    if (time1 >= 28 || d3Choice ==3) {
                        do {
                            System.out.println("Do you want to upgdate 2nd shot?");
                            d3Choice = d3SubMenu.getIntChoice(d3Menu);
                            if (d3Choice == 1) {
                                System.out.println("Please input a new place for 2nd shot");
                                String p2New = sc.nextLine();
                                if (p2New.equalsIgnoreCase(sVList.get(result).getPlace2()) || p2New.isEmpty() || p2New.length() == 0) {
                                    System.out.println("The place didn't change");
                                } else {
                                    sVList.get(result).setPlace2(p2New);
                                }
                                Date d2New;
                                boolean flag1 = true;
                                do {
                                    System.out.println("Please input a 2nd date");
                                    String d2NewInput = sc.nextLine();
                                    d2New = Util.getDateV2(d2NewInput);
                                    if (d2New.compareTo(d1New) < 0) {
                                        System.out.println("Please enter another day which is greater than 1st date.");
                                    } else {
                                        long t = (d2New.getTime() - d1New.getTime()) / 86400000;
                                        if (t >= 28 && t <= 84) {
                                            sVList.get(result).setD2(d2New);
                                            flag1 = false;
                                        } else {
                                            System.out.println("The 2nd shot must be from 4 to 12 weeks.");
                                        }
                                    }
                                } while (flag1);
                            }

                        } while ((d3Choice - 1 != 1) && sVList.get(result).getD2() == null);
                    }
                }
            }
            System.out.println(sVList.get(result).getsID()+","+sVList.get(result).getName()
            +","+sVList.get(result).getvID()+","+sVList.get(result).getPlace1()+","
            +sVList.get(result).getD1()+","+sVList.get(result).getPlace2()+","+sVList.get(result).getD2());
        }
    }
    public void removeInjection(){
        String id;
        int position;
        id = Util.getString("Input Injection's ID to remove","ID is required");
        position = searchId(id);
        if(position ==-1)
            System.out.println("This Injection's ID cannot find!");
        else{
            ArrayList subList = new ArrayList();
            subList.add("1.Yes, remove");
            subList.add("2.No, don't remove");
            Menu subMenu = new Menu();
            System.out.println("Are you sure that you want to remove this Injection? Press 1 to delete.");
            int subChoice = subMenu.getIntChoice(subList);
            System.out.println("You chose: "+subChoice);
            if(subChoice ==1){
                sVList.remove(position);
                System.out.println("The Injection is removed successfully!");
            }else
                System.out.println("The Injection is still in the list.");
        }
    }
    public int searchId(String id) {
        int pos;
        if (sVList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < sVList.size(); i++) {
            if (sVList.get(i).getsID().equalsIgnoreCase(id)) {
                return i;
            }

        }
        return -1;

    }

    public void print() {
        for (int i = 0; i < sVList.size(); i++) {
            System.out.println(sVList.get(i).getsID() + ", " + sVList.get(i).getName() + ", "
                    + sVList.get(i).getvID() + ", " + sVList.get(i).getPlace1() + ", " + sVList.get(i).getD1() + ", "
                    + sVList.get(i).getPlace2() + ", " + sVList.get(i).getD2());

        }
    }

    public void searchAStudent() {
        String id;
        id = Util.getString("Please enter a Student's ID", "Try again");
        int position = searchId(id);
        System.out.println("The student information:");
        System.out.println(sVList.get(position).getsID()
                + ", " + sVList.get(position).getvID() + ", " + sVList.get(position).getPlace1()
                + ", " + sVList.get(position).getD1() + ", " + sVList.get(position).getPlace2()
                + ", " + sVList.get(position).getD2());

    }
    
 //=======================FROM FILE========================
    public boolean searchIDFromFile(String id){
        if(sVList.isEmpty())
            return false;
        for(int i = 0; i< sVList.size();i++)
            if(sVList.get(i).getsID().equalsIgnoreCase(id))
                return true;
        return false;
    }
    public void AddFromFile(String fName){
        try{
            File f = new File(fName);
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String sId = stk.nextToken().toUpperCase();
                if ((!searchIDFromFile(sId))) {
                    String name = stk.nextToken();
                    String vId = stk.nextToken();
                    String place1 = stk.nextToken();
                    Date d1 = Util.getDateFromFile(stk.nextToken());
                    String place2 = stk.nextToken();
                    if (place2.equalsIgnoreCase("null")) {
                        StudentVac n = new StudentVac(sId, name, vId, place1, d1);
                        sVList.add(n);
                    } else {
                        Date d2 = Util.getDateFromFile(stk.nextToken());
                        StudentVac n1 = new StudentVac(sId, name, vId, place1, d1, place2, d2);
                        sVList.add(n1);
                    }

                }
            }
            bf.close();
            fr.close();

        } catch (Exception e) {
            
        }
    }
    
    public void SaveToFile(){
        if(sVList.size()==0){
            System.out.println("Empty list.");
            return;
        }
        try{
            
            FileWriter fw = new FileWriter("Injection.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i <sVList.size(); i++) {
                if(sVList.get(i).getvID()!=null)
                    pw.println(sVList.get(i).getsID()+","+sVList.get(i).getName()+","+sVList.get(i).getvID()
                    +","+sVList.get(i).getPlace1()+","+sVList.get(i).getD1()+","+sVList.get(i).getPlace2()
                            +","+sVList.get(i).getD2());
            }
            pw.close();
            fw.close();
        }catch(Exception e){
            System.out.println(e);
            
        }
    }
}
