/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycanvas;

/**
 *
 * @author Doye
 */

// Import stuff
import java.awt.*;
import java.io.*;
import javax.swing.JFrame; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MyCanvas extends Canvas{
    // Prepare to load the file
    static File file = new File("D:\\NetBeansProjects\\MyCanvas\\perfect2k.png");
    // Setup globals
    static BufferedImage image;
    static BufferedImage copy;
    
    static Color colourRed = new Color(255,0,0,255);
    static Color colourBlack = new Color(0,0,0,255);
    static Color colourWhite = new Color(255,255,255,255);
    
    // Create entrance and exit coordinates
    static Coordinate entrance = new Coordinate(0,0);
    static Coordinate exit = new Coordinate(0,0);
    
    public void paint(Graphics g) {  
 
       // g.drawImage(image, 1,1,this);

       //System.out.println(colourRed.getRGB());
      // image.setRGB(40,40,colourRed.getRGB());
       g.drawImage(image, 0,0,this);
      // copy.setRGB(0,0,colourRed.getRGB());
       g.drawImage(copy, image.getWidth() + 50,0,this);
       


// Not turn left, sticck to left wall



          
    }  
        public static void main(String[] args) throws IOException {  
        // Setup GUI
        MyCanvas m = new MyCanvas();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.add(m);  
        f.setSize(400,400);  
        f.setVisible(true);  
        
        // Read the prepared file into image
        image = ImageIO.read(file);
        // Make a new buffered image that doesnt only contain black and white
        copy = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        // Copy the loaded image to copy and create an array based on image data
        int[][] colourArray = copyToCopy();
        // Find the entrance
        getEntrance();
        System.out.println(exit.getX() + " " + exit.getY());
        // Find the exit
        getExit();
        // Create the 'player' that will traverse the maze and place it at the start
        Player player = placePlayer();
        // Get the time before solve
        long startTime = System.currentTimeMillis();

        // Solve the maze
        solveMaze(player, exit, colourArray);
        
        // Get the time after solve
        long endTime = System.currentTimeMillis();
        // convert to seconds
        long seconds = (endTime - startTime) / 1000;

        copy.setRGB(entrance.getX(),entrance.getY(),colourRed.getRGB());
        copy.setRGB(exit.getX(),exit.getY(),colourRed.getRGB());
        
        System.out.println("Time elapsed: " +seconds+ "seconds");
        
       

        //copy = new BufferedImage;
        
    }  
        
        private static int[][] copyToCopy(){
            // For each pixel in the image, copy to new image and add to array
            int[][] colourArray = new int[image.getWidth()][image.getHeight()];
            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    copy.setRGB(x,y,image.getRGB(x,y));
                    colourArray[x][y] = image.getRGB(x, y);
                }
            }
            return colourArray;
        }
        
        private static void getEntrance(){
            // Across the top row of pixels, find the single white pixel
            int current = 0;
            boolean found = false;
            while(!found && current < image.getWidth()){
               if(image.getRGB(current,0) == colourWhite.getRGB()){
                   entrance.setX(current);
                   found = true;
                   

               }
               current++;
           }
        }        
        private static void getExit(){
            // Across the bottom row of pixels, find the single white pixel
            int current = 0;
            boolean found = false;
            while(!found && current < image.getWidth()){
               if(image.getRGB(current,image.getHeight()-1) == colourWhite.getRGB()){
                   exit.setX(current);
                   exit.setY(image.getHeight()-1);
                   found = true;
               }
               current++;
           }
        }
        private static Player placePlayer(){
            // Create the 'player' and place it at the entrance coordinates
            Player player = new Player(entrance);
            return player;
        }
        private static void solveMaze(Player player, Coordinate exitLocation, int[][] colourArray) throws IndexOutOfBoundsException {
            // While the player is not at the exit
            System.out.println("Starting solve");
            System.out.println(player.getAhead().getX() + " " + player.getAhead().getY());
            
            while(player.getLocation().getY() != exitLocation.getY()){
                System.out.println(player.getAhead().getX() + " " + player.getAhead().getY());

                if(colourArray[player.getLeft().getX()][player.getLeft().getY()] != colourBlack.getRGB()){
                    player.turnLeft();
                    player.moveForward();
                    
                }else{
                    if(colourArray[player.getAhead().getX()][player.getAhead().getY()] != colourBlack.getRGB()){
                        player.moveForward();
                    }else{
                        if(colourArray[player.getAhead().getX()][player.getAhead().getY()] == colourBlack.getRGB() && 
                                colourArray[player.getLeft().getX()][player.getLeft().getY()] == colourBlack.getRGB()){
                            player.turnLeft();
                            player.turnLeft();
                            player.turnLeft();
                        }else{
                            player.turnLeft();

                        }
                    }
                }
                copy.setRGB(player.getLocation().getX(),player.getLocation().getY(),colourRed.getRGB());
            }
            System.out.println("Solve complete");
            
            File outputfile = new File ("solved.png");
            try{
            ImageIO.write(copy, "png", outputfile);
            }catch (IOException e){
            }
            
        }
  
} 
