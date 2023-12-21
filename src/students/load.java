/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package students;

public class load {
    
    
    public static void main(String[] args){
        Screen s = new Screen();
        s.setVisible(true);
        Login l = new Login();
        l.setVisible(false);
        Home h = new Home();
        h.setVisible(false);
        
        try{
            for(int x=0;x<=100;x++){
                Thread.sleep(20);
                s.jLabel1.setText(Integer.toString(x)+"%");
                s.jProgressBar1.setValue(x);
                
                if(x==100){
                    s.setVisible(false);
                    l.setVisible(true);
                }
            }
        } catch (Exception e){
            
        }
    }
    
}
