package source;


import edu.wpi.first.smartdashboard.camera.WPICameraExtension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class JavaCrossHairWidget extends WPICameraExtension implements MouseListener, MouseMotionListener {
    
    //current crosshair
    CrossHair currentCrossHair;
    int index;
    
    //globals
    FileSaver fileSaver;
    String file = "config//crosshair_config.txt";
    ArrayList<CrossHair> crossHair;
    
    //this is to continuously draw the selected crosshair
    boolean isDragging;
    
    //Buttons for saving and loading
    JButton add, save;
    
    public JavaCrossHairWidget(){
        super();
        
        fileSaver = new FileSaver(file);
        crossHair = fileSaver.readInCrossHairArray();
        isDragging = false;
        
        //panel height and width values
        CrossHair.panelHeight = this.getHeight();
        CrossHair.panelWidth = this.getWidth();
        
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
        
        save.setBounds(100, 100, 100, 50);
        
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                fileSaver.writeCrossHairArray(crossHair);
            }
        });
        repaint();
    }
    
    //saves the crosshairs to a file before disconnecting
    @Override
    public void disconnect(){
        fileSaver.writeCrossHairArray(crossHair);
        super.disconnect();
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
        CrossHair.panelHeight = this.getHeight();
        CrossHair.panelWidth = this.getWidth();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getButton() == me.BUTTON3){
            for (int i = 0; i < crossHair.size(); i++) {
                if(crossHair.get(i).checkIfClicked(me.getX(), me.getY())){
                    
                    //removes the selected cross hair
                    crossHair.remove(i);
                }
            }
            System.out.println("Attempted to Right Click");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton() == me.BUTTON1){
            for (int i = 0; i < crossHair.size(); i++) {
                if(crossHair.get(i).checkIfClicked(me.getX(), me.getY())){
                    currentCrossHair = crossHair.remove(i);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        isDragging = false;
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
        isDragging = true;
        repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(JavaCrossHairWidget.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}