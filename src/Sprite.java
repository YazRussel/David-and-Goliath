import javax.swing.*;
import java.awt.*;

public abstract class Sprite implements Drawable{
    protected Room currentRoom;
    protected ImageIcon image;
    public static final int SIZE = 50;

    //Initializes the currentRoom and image to default
    public Sprite() {
        this.currentRoom = null;
        this.image = null;
    }

    //Assign a room
    public void setCurrentRoom(Room r) {
        this.currentRoom = r;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    //Responsible for calculating the center position when printing the picture
    //If the room and image are not null then this is the room
    //Where we have to print the picture
    @Override
    public void draw(Graphics g) {
        if (this.currentRoom != null && this.image != null) {
            Point roomImage = this.currentRoom.getPosition();
            int x = roomImage.x + (SIZE - this.image.getIconWidth()) / 2;
            int y = roomImage.y + (SIZE - this.image.getIconHeight()) / 2;
            this.image.paintIcon(null, g, x, y);
        }
    }

    //To check if the room has an exit according to the room
    public void moveSouth() {
        if (currentRoom.hasSouthExit()) {
            currentRoom = currentRoom.getSouthExit();
        }
    }
    public void moveEast(){
        if(currentRoom.hasEastExit()){
            currentRoom = currentRoom.getEastExit();
        }
    }
    public void moveWest(){
        if(currentRoom.hasWestExit()){
            currentRoom = currentRoom.getWestExit();
        }
    }
    public void moveNorth(){
        if(currentRoom.hasNorthExit()){
            currentRoom = currentRoom.getNorthExit();
        }
    }
}
