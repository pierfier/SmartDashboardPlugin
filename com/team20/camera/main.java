package com.team20.camera;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main extends JFrame{
        
        //the name of the URL
        String urlName;

        Display display;

        //try to use this "rtsp://192.168.0.100/axis-media/media.amp"

        public static void main(String[] args){
                for(int i =0; i < args.length;i++){  
                          if(args[i].equals("-u") || args[i].equals("--url")){
                                urlName = args[i + 1];

        //finalizes the url path name
                                urlName += "jview.htm";
                          }
                }
                new main();
        }

        public main(){
                this.setBounds(0, 0, 500, 500);
                this.add(display);
                this.setVisible(true);
                this.setLayout(null);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
                Url url = new Url(urlName);
                
                display = new Display(this.getWidth(), this.getHeight(), url);
                display.setLayout(null);
               
                //main loop that continually gets in camera feed
                while(true){
                        try{
                                display.update();
                                Thread.sleep(100);
                        }catch(Exception e){}
                }
        }
}
