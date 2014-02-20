package source;


import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pierre
 */
public class CrossHair implements Serializable{
    
    //number of pixels that the selection detection tolerates
    static final int selectionTolerance = 3;
    
    Color color;
    
    int x, y;
    
    //the widgets height and width variables
   public static int panelHeight, panelWidth;
    
    //the size of the crosshairs
    static int stroke = 5;
    
    //default constructor (deprecated) replaces null intialization
    public CrossHair(){
        
    }
    
    public CrossHair(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setColor(Color c){
        this.color = c;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    //this method returns if this crosshair is close to the specified x, y coordinates
    //the arguments should be given by the MouseListener implemented methods
    public boolean checkIfClicked(int x, int y){
        boolean selected = false;
        
        if(this.getX() + selectionTolerance > x && this.getX() - selectionTolerance < x){
            selected = true;
        }else if(this.getY() + selectionTolerance > y && this.getY() - selectionTolerance < y){
            selected = true;
        }
        
        return selected;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
                
        //the y crosshair
        g.fillRect(0, y, panelWidth, stroke);
        
        //the x crosshair
        g.fillRect(x, 0, stroke, 500);
    }
}
