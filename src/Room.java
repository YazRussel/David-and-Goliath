import java.awt.*;
public class Room implements Drawable{
    private Point pos;
    private Room exitEast, exitWest, exitNorth, exitSouth;

    public static final int SIZE = 50;
    //Constructor and Initializes the Room object


    /**
     *
     * @param x
     * @param y
     */
    public Room(int x, int y) {
        //Represent the position of the room
        this.pos = new Point(x, y);
        //Represent the exit of the room
        this.exitEast = null;
        this.exitWest = null;
        this.exitNorth = null;
        this.exitSouth = null;
    }

    //When it exits to the East, the West side will also give an entrance
    public void setEastExit(Room r) {
        this.exitEast = r;
        r.exitWest = this;
    }
    //When it exits to the North, the South side will also give an entrance
    public void setNorthExit(Room r) {
        this.exitNorth = r;
        r.exitSouth = this;
    }
    //When it exits to the West, the East side will give an entrance
    public void setWestExit(Room r) {
        this.exitWest = r;
        r.exitEast = this;
    }
    //When it exits to the South, the North side will give an entrance
    public void setSouthExit(Room r) {
        this.exitSouth = r;
        r.exitNorth = this;
    }


    @Override
    public void draw(Graphics g){
        int wallSize = 50;
        int doorWidth = 20;

        // North wall, when the code did not touch this, the wall will remain the same
        //Or else it will make a passageway
        if (exitNorth == null) {
            g.drawLine(pos.x, pos.y, pos.x + wallSize, pos.y);
        } else {
            g.drawLine(pos.x, pos.y, pos.x + doorWidth, pos.y);
            g.drawLine(pos.x + wallSize - doorWidth, pos.y, pos.x + wallSize, pos.y);

        }

        // East wall,when the code did not touch this, the wall will remain the same
        //Or else it will make a passageway
        if (exitEast == null) {
            g.drawLine(pos.x + wallSize, pos.y, pos.x + wallSize, pos.y + wallSize);
        } else {
            g.drawLine(pos.x + wallSize, pos.y, pos.x + wallSize, pos.y + doorWidth);
            g.drawLine(pos.x + wallSize, pos.y + wallSize - doorWidth, pos.x + wallSize, pos.y + wallSize);
            g.drawLine(pos.x + wallSize, pos.y + doorWidth, pos.x + wallSize + 10, pos.y+ doorWidth);
            g.drawLine(pos.x + wallSize, pos.y + doorWidth +10, pos.x + wallSize + 10, pos.y + doorWidth + 10);
        }
        // South wall,when the code did not touch this, the wall will remain the same
        //Or else it will make a passageway
        if (exitSouth == null) {
            g.drawLine(pos.x, pos.y + wallSize, pos.x + wallSize, pos.y + wallSize);
        } else {
            g.drawLine(pos.x, pos.y + wallSize, pos.x + doorWidth, pos.y + wallSize);
            g.drawLine(pos.x + wallSize - doorWidth, pos.y + wallSize, pos.x + wallSize, pos.y + wallSize);
            g.drawLine(pos.x+doorWidth, pos.y +wallSize, pos.x +doorWidth, pos.y + wallSize+10);
            g.drawLine(pos.x+wallSize - doorWidth, pos.y + wallSize, pos.x + doorWidth +10, pos.y + wallSize + 10);
        }
        // West wall,when the code did not touch this, the wall will remain the same
        //Or else it will make a passageway
        if (exitWest == null) {
            g.drawLine(pos.x, pos.y, pos.x, pos.y + wallSize);
        } else {
            g.drawLine(pos.x, pos.y, pos.x, pos.y + doorWidth);
            g.drawLine(pos.x, pos.y + wallSize - doorWidth, pos.x, pos.y + wallSize);
        }
    }

    //To get the previous x and y coordinates value
    public Point getPosition(){
        return pos;
    }
    //Give a return of a boolean type: either true or false
    // To know if according to the pressed key if it has an exit
    //Or doesn't have an exit. If it has an exit then it will pass to the next room
    public boolean hasNorthExit() {
        if (exitNorth == null) {
            return false;
        }else{
            return true;
        }
    }
    public boolean hasSouthExit(){
        if (exitSouth == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean hasEastExit() {
        if (exitEast == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean hasWestExit(){
        if (exitWest == null){
            return  false;
        }else{
            return true;
        }
    }
    //Suggest to have an exit in any direction, and if there's an
    //Exit, it will be given access to move or pass
    public Room getNorthExit(){
        return exitNorth;
    }
    public Room getSouthExit(){
        return exitSouth;
    }
    public Room getEastExit(){
        return exitEast;
    }
    public Room getWestExit(){
        return exitWest;
    }



}



