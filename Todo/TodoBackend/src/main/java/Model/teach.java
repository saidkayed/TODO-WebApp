/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author sidad
 */
public class teach {

    int milk = 4;
    int egg = 4;
    int inc = 0;
    boolean skift = true;
    String[] handleliste = {"milk","egg","bread"};

    public boolean ifelse() {

        if (milk == 5 && (egg == 4 || egg == 3)) {
            System.out.println("true");
            return true;
        } else {
System.out.println("false");
            return false;
        }

    }
    
    public void forloop(){
        for (int i = 0; i < handleliste.length; i++) {
            System.out.println(handleliste[i]);    
        }
    }

    public void whileloop(){
        while (skift) {            
            System.out.println(inc);
            inc++;
            if(inc == 10){
                System.out.println("nu er jeg stoppet");
                skift = false;
            }
        }
    }
    public static void main(String[] args) {

        teach t = new teach();
        /*
        t.ifelse();
        */
     /*   
        t.forloop();
*/
     
     t.whileloop();
    }

}
