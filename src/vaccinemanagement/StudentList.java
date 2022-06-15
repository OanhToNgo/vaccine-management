/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 *
 * @author oanhn
 */
public class StudentList /*extends ArrayList<Student>*/ {
    ArrayList<Student>stList = new ArrayList<>();
//    public StudentList(){
//        super();
//    }

    public ArrayList<Student> getStList() {
        return stList;
    }

    public StudentList() {
    }
    
    public void AddFromFile(String fName){
        try {
            File f = new File(fName);
            if(!f.exists())
                return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            Student s ;
            while((details= bf.readLine())!=null){
                StringTokenizer stk = new StringTokenizer(details,",");
                String id = stk.nextToken().toUpperCase();
                String name = stk.nextToken();
                if((!searchIDFromFile(id))){
                   s = new Student(id, name);
                   stList.add(s); 
                }
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
        }
    }
    public Student searchStudentObj(String id){
        if(stList.isEmpty())
            return null;
        for (int i = 0; i < stList.size(); i++) 
            if(stList.get(i).getsID().equalsIgnoreCase(id))
                return stList.get(i);
        return null;
    }
    public int searchStudent(String id){
        int pos;
        if(stList.isEmpty())
            return -1;
        for (int i = 0; i < stList.size(); i++) {
            if(stList.get(i).getsID().equalsIgnoreCase(id))
                return i;
            
        }
        return -1;   
    }
    public boolean searchIDFromFile(String id){
        if(stList.isEmpty())
            return false;
        for(int i = 0; i< stList.size();i++)
            if(stList.get(i).getsID().equalsIgnoreCase(id))
                return true;
        return false;
    }

    @Override
    public String toString() {
        return "StudentList{" + "stList=" + stList + '}';
    }
    public void print(int i){
        System.out.print(stList.get(i).getsID()+", "+stList.get(i).getsName()+"\n");
    }
    public void printList(){
        for (int i = 0; i < stList.size(); i++) {
            System.out.println((i+1)+" - "+stList.get(i).getsID()+", "+stList.get(i).getsName());
        }
    }
}
