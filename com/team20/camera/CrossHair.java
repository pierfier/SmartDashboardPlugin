package com.team20.camera;

import java.awt.Graphics;
import java.awt.Color;

public class CrossHair{
        
        //size of the stroke of the line
        //its just the thiness of the line
        int stroke;

        //of the image
        int width, height;

        //this value is the coordinate
        //for the crosshair
        //this could be x or y depending on if it
        //is horizontal or vertical
        int coordinate;

        //this boolean determines if the crosshair is a horizontal or vertical
        private boolean horizontal;
        
        public CrossHair(int width, int height){
                this.width = width;
                this.height = height;       
        }

        //method to see if the crosshair is horizontal
        public boolean isHorizontal(){
                return horizontal;
        }

        //method to see if the crosshair is vertical
        public boolean isVertical(){
                return !horizontal;
        }

        //set the thiness of the crosshair
        public void setThiness(int s){
                this.stroke = s;
        }

        //sets if crosshair is horizontal
        public void setHorizontal(){
                horizontal = true;
        }

        //sets if crosshair is vertical
        public void setVertical(){
                horizontal = false;
        }

        //draws the crosshairs
        public void draw(Graphics g){
                //the horizontal color is green
                //the vertical color is black (might have to change)
                
                //coordinate for y value
                if(horizontal){
                        g.setColor(Color.GREEN);
                        g.fillRect(0, coordinate, width, stroke);
                }else{
                        g.setColor(Color.BLACK);
                        
                        //coordinate is the x value
                        g.fillRect(coordinate, 0, stroke, height);
                }
        }
}
