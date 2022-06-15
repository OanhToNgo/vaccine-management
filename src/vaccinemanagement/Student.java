/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

/**
 *
 * @author oanhn
 */
public class Student {
    String sID;
    String sName;

    public Student() {
    }
    

    public Student(String sID, String sName) {
        this.sID = sID;
        this.sName = sName;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return "Student{" + "sID=" + sID + ", sName=" + sName + '}';
    }
    
}
