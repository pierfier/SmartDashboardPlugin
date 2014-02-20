package Test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import source.CrossHair;
import source.FileSaver;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import source.JavaCrossHairWidget;

public class TestPanel extends JPanel implements MouseListener, MouseMotionListener {
    
    //current crosshair
    CrossHair currentCrossHair;
    
    //globals
    FileSaver fileSaver;
    String file = "config//crosshair_config.txt";
    ArrayList<CrossHair> crossHair;
    
    //this is to continuously draw the selected crosshair
    boolean isDragging;
    
    //button code to add, load, save new crosshairs
    JButton add, save;
    
    public TestPanel(){
        fileSaver = new FileSaver(file);
        isDragging = false;
        
        //Swing housekeeping
        this.setLayout(null);
        
        //crosshair ArrayList initialization
        crossHair = fileSaver.readInCrossHairArray();
        printCHStatus();
        
        //button code
        add = new JButton();
        add.setText("Add");
        this.add(add);
        
        add.setBounds(0, 0, 100, 50);
        
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                crossHair.add(new CrossHair(50, 50, Color.BLUE));
            }
        });
        save = new JButton();
        save.setText("Save");
        this.add(save);
        
        save.setBounds(100, 0, 100, 50);
        
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                fileSaver.writeCrossHairArray(crossHair);
            }
        });
        repaint();
    }
    
    public void printCHStatus(){
        for (int i = 0; i < crossHair.size(); i++) {
            System.out.println("x: " + crossHair.get(i).getX());
            System.out.println("y: " + crossHair.get(i).getY());
        }
    }
    
    @Override
    public void paint(Graphics g) {
        
        //draws the saved crosshairs
        for (int i = 0; i < crossHair.size(); i++) {
            crossHair.get(i).draw(g);
        }

        if(isDragging){
          //draws the current crosshair
          currentCrossHair.draw(g);
        }
        
                CrossHair.panelHeight = getHeight();
                CrossHair.panelWidth = getWidth();
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("X: " + me.getX());
        System.out.println("Y: " + me.getY());
        if(me.getButton() == me.BUTTON3){
            for (int i = 0; i < crossHair.size(); i++) {
                if(crossHair.get(i).checkIfClicked(me.getX(), me.getY())){
                    
                    //removes the selected cross hair
                    crossHair.remove(i);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton() == me.BUTTON1){
            for (int i = 0; i < crossHair.size(); i++) {
                if(crossHair.get(i).checkIfClicked(me.getX(), me.getY())){
                    currentCrossHair = crossHair.remove(i);
                    currentCrossHair.setColor(Color.GREEN);
                    isDragging = true;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        isDragging = false;
        currentCrossHair.setColor(Color.BLUE);
        crossHair.add(currentCrossHair);
        currentCrossHair = null;
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        if(isDragging){
        repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(JavaCrossHairWidget.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
        

    @Override
    public void mouseMoved(MouseEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}