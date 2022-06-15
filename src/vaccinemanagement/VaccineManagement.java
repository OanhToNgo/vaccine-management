/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.util.Scanner;
import java.sql.Date;
import java.util.ArrayList;



/**
 *
 * @author oanhn
 */
public class VaccineManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);   
       int mainChoice;
       StVacList list = new StVacList();
       list.AddFromFile("Injection.txt");
       list.addVaccineList();
       list.addToNotVaccinatedList();
       ArrayList<String> mainMenuList = new ArrayList();
       mainMenuList.add("Show information all students have been injected.");
       mainMenuList.add("Add student's vaccine injection information");
       mainMenuList.add("Updating information of students' vaccine injection");
       mainMenuList.add("Delete student vaccine injection information");
       mainMenuList.add("Search for injection by studentID");
       mainMenuList.add("Save to file");
       mainMenuList.add("Quit");
       System.out.println("Welcome to Vaccine Management - @ 2021 by Ngo To Oanh - SE160787");
        do {   
            System.out.println();
            Menu mainMenu = new Menu();
            mainChoice = mainMenu.getIntChoice(mainMenuList);
            switch(mainChoice){
                case 1:
                    list.print();
                    break;
                case 2:
                    list.addStudentList();
                    list.addToNotVaccinatedList();
                    int subChoice;
                    do { 
                       list.AddNewInjection();
                       ArrayList<String> addSubLst = new ArrayList();
                       System.out.println("Do you want to add more. Press 1 to add.");
                       addSubLst.add("Yes,add more.");
                       addSubLst.add("No,finish.");
                       Menu subAddMenu = new Menu();
                       subChoice = subAddMenu.getIntChoice(addSubLst);
                       
                    } while (subChoice-1 != 1);
                    break;
                case 3:
                    list.UpdateAnInjection();
                    break;
                case 4:
                    list.removeInjection();
                    break;
                case 5:
                    list.searchAStudent();
                    break;
                case 6:
                    list.SaveToFile();
                    break;
            }
            
        } while (mainChoice != 7);
       
       
      
 
   
    }
    
   
   
}
