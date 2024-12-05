import javax.swing.*;

public class David extends Sprite {
    private int numStones = 0;

    //A constructor to find the location of the picture and get the image
    public David() {
        super();
        this.image = new ImageIcon("C:\\Users\\Yaz Russel\\IdeaProjects\\Project\\src\\liit.png");
        if (this.image.getIconWidth() <= 0) {
            System.out.println("Image not loaded properly");
        }
        this.numStones = 0;
    }

    public void pickUpStone(){

        numStones++;
    }
    public boolean isArmed(){
        return numStones >= 5;
    }
    public void reset(){

        numStones = 0;
    }
}
