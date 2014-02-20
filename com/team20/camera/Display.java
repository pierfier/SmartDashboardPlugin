package com.team20.camera;

import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Graphics;
import com.sun.image.codec.jpeg*;

//this class has the camera feed image and the crosshairs
public class Display extends JPanel{
        
        int width, height;

        //the url from the camera feed
        URL url;
 
        //image from the camera feed
        Image cameraFeed;

        //the crosshairs are just very thin rectangles
        CrossHair horizontalCrossHair, verticalCrossHair;
        

        //constructor
        public Display(int w, int h, URL url){
                this.width = w;
                this.height = h;
                this.url = url;
        }
        
        //the meat of the Display
        //uses an httpURlConnection, grabs the image       
        public void grabImage(){                
                HttpURLConnection huc;
                BufferedInputStream in;
                DataInputStream dis;
                try{
                       //the url for the image
                       url = new URL("http://192.168.0.100/axis-cgi/jpg/image.cgi?resolution=352x240");
                        
                       huc = (HttpURLConnection) url.openConnection();

                       in = new BufferedInputStream(new InputStream(huc.getInputStream()));
                       dis = new DataInputStream(in);

                       JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(dis);
                       cameraFeed = decoder.decodeAsBufferedImage();
                }catch(Exception w){
                        
                }
        }
 
        public void paint(Graphics g){
                //draws the camera feed image
                g.drawImage(cameraFeed, 0 , 0, this);


                //draws the crosshairs
                horizontalCrossHair.draw(g);
                verticalCrossHair.draw(g);
        }

        //this is the method that is going to be called
        public void update(){
                grabImage();
                repaint();

        }
}
