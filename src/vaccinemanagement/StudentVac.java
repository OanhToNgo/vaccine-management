/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;
import java.sql.Date;
/**
 *
 * @author oanhn
 */
public class StudentVac {
    String sID;
    String name;
    String vID;
    String place1;
    Date d1;
    String place2;
    Date d2;

    public StudentVac(String sID, String name, String vID, String place1, Date d1, String place2, Date d2) {
        this.sID = sID;
        this.name = name;
        this.vID = vID;
        this.place1 = place1;
        this.d1 = d1;
        this.place2 = place2;
        this.d2 = d2;
    }

    public StudentVac(String sID, String name, String vID, String place1, Date d1) {
        this.sID = sID;
        this.name = name;
        this.vID = vID;
        this.place1 = place1;
        this.d1 = d1;
    }
    
    public StudentVac(Student st){
        sID = st.getsID();
        name = st.getsName();
    }
    public StudentVac(){
        
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getvID() {
        return vID;
    }

    public void setvID(String vID) {
        this.vID = vID;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }

    @Override
    public String toString() {
        return "StudentVac{" + "sID=" + sID + ", name=" + name + ", vID=" + vID + ", place1=" + place1 + ", d1=" + d1 + ", place2=" + place2 + ", d2=" + d2 + '}';
    }
   
   
    
}
