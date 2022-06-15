/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author oanhn
 */
public class VaccineList{
    ArrayList<Vaccine>vacList = new ArrayList<>();
    public VaccineList(){
        
    }
    
    public void AddFromFile(String fName){
        try {
            File f = new File(fName);
            if(!f.exists())
                return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details = bf.readLine())!=null){
                StringTokenizer stk = new StringTokenizer(details,",");
                String id = stk.nextToken();
                String name = stk.nextToken();
                if((!searchIDFromFile(id))){
                    Vaccine v = new Vaccine(id,name);
                    vacList.add(v);
                }
                
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     public boolean searchIDFromFile(String id){
        if(vacList.isEmpty())
            return false;
        for(int i = 0; i< vacList.size();i++)
            if(vacList.get(i).getvID().equalsIgnoreCase(id))
                return true;
        return false;
    }

    @Override
    public String toString() {
        return "VaccineList{" + "vac=" + vacList + '}';
    }
    
    public void print(int i){
        System.out.print(vacList.get(i).getvID()+", "+vacList.get(i).getvName()+"\n");
    }
    public void printList(){
        for (int i = 0; i < vacList.size(); i++) {
            System.out.println((i+1)+" - "+vacList.get(i).getvID()+", "+vacList.get(i).getvName());
        }
    }
   
}
