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
public class Coordinate {
    // A coordinate system to allow returning of both X and Y with simple methods
    private int x;
    private int y;
    
    public Coordinate(int inputX, int inputY){
        x = inputX;
        y = inputY;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int newX){
        x = newX;
    }
    public void setY(int newY){
        y = newY;
    }
    public void incrementX(){
        x++;
    }
    public void incrementY(){
        y++;
    }
    public void decrementX(){
        x--;
    }
    public void decrementY(){
        y--;
    }
}
