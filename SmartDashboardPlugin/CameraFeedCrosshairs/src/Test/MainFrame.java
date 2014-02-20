/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javax.swing.JFrame;

/**
 *
 * @author pierre
 */
public class MainFrame extends JFrame{
    
    public static void main(String[] args) {
        new MainFrame();
    }
    
    public MainFrame(){
        this.setBounds(0, 0, 500, 500);
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TestPanel panel = new TestPanel();
        this.add(panel);
        
        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setVisible(true);
    }
}
