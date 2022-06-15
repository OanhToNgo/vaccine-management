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
public class Vaccine {
    String vID;
    String vName;

    public Vaccine(String vID, String vName) {
        this.vID = vID;
        this.vName = vName;
    }

    public Vaccine() {
    }
    
    public String getvID() {
        return vID;
    }

    public void setvID(String vID) {
        this.vID = vID;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    @Override
    public String toString() {
        return ("vId="+vID+","+"vName="+vName);
    }
    
}
