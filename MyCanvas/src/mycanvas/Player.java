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
public class Player {
    // Creates a dimension enum with the cardinal directions
    public enum Direction{
        UP, LEFT, DOWN, RIGHT
    }
    
    private Direction direction;
    private Coordinate location;
    private Coordinate ahead;
    private Coordinate left;
    
    public Player(Coordinate xy){
        // Construct a player with the input location, facing down
        location = xy;
        direction  = Direction.DOWN;
        // ahead is the pixel the 'player' is facing
        ahead = new Coordinate(xy.getX(),xy.getY());
        left = new Coordinate(xy.getX(),xy.getY());
        System.out.println(ahead.getX() + " " + ahead.getY());
        // ahead is currently equal to the palyers location, move it down one
        ahead.incrementY();
        left.incrementX();
        System.out.println(ahead.getX() + " " + ahead.getY());

    }
    
    public void turnLeft(){
        // Turns the player anti-clockwise and corrects 'ahead'
        switch(direction){
            case UP: 
                direction = Direction.LEFT;
                ahead.incrementY();
                ahead.decrementX();
                left.incrementX();
                left.incrementY();
                break;
            case 
                LEFT: direction = Direction.DOWN;
                ahead.incrementX();
                ahead.incrementY();
                left.incrementX();
                left.decrementY();
                break;
            case 
                DOWN: direction = Direction.RIGHT;
                ahead.incrementX();
                ahead.decrementY();
                left.decrementX();
                left.decrementY();                
                break;
            case 
                RIGHT: direction = Direction.UP;
                ahead.decrementX();
                ahead.decrementY();
                left.decrementX();
                left.incrementY();
                break;
        }
        
    }
    public void moveForward(){
        // Move the player forward based on the direction
        switch(direction){
            case 
                    UP: location.decrementY(); 
                    left.decrementY();
                    ahead.decrementY();
                    break;
            case 
                    LEFT: location.decrementX();
                    ahead.decrementX();
                    left.decrementX();
                    break;
            case 
                    DOWN: location.incrementY(); 
                    ahead.incrementY();
                    left.incrementY();
                    break;
            case 
                    RIGHT: location.incrementX(); 
                    ahead.incrementX();
                    left.incrementX();
                    break;
        }
    }
    // Get methods
    public Coordinate getAhead(){
        return ahead;
    }
    public Coordinate getLocation(){
        return location;
    }
    public Coordinate getLeft(){
        return left;
    }

}
