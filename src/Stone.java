import javax.swing.*;

public class Stone extends Sprite{
    //A constructor to find the location of the picture and get the image
    public Stone(){
        super();
        this.image = new ImageIcon("C:\\Users\\Yaz Russel\\IdeaProjects\\Project\\src\\bola.png");
        if (this.image.getIconWidth() <= 0) {
            System.out.println("Image not loaded properly");
        }
    }
}
