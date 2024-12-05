
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
//Receives the input of the user by the keystrokes
public class Main extends JPanel implements KeyListener{
    //Enter an expandable array
    private ArrayList<Room> rooms = new ArrayList<>();
    private David david;
    private Clip clip;
    private Clip chip;
    private Goliath goliath;
    private ArrayList<Stone> stones = new ArrayList<>();

    private ArrayList<Drawable> drawables = new ArrayList<>();

    public Main() {
        addKeyListener(this);
        setFocusable(true);
        david =  new David();
        goliath = new Goliath();
        try{
            File win = new File("C:\\Users\\Yaz Russel\\IdeaProjects\\Project\\src\\winnig.wav");
            File pick = new File("C:\\Users\\Yaz Russel\\IdeaProjects\\Project\\src\\pick.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(win);
            AudioInputStream ace = AudioSystem.getAudioInputStream(pick);
            clip = AudioSystem.getClip();
            chip = AudioSystem.getClip();
            clip.open(ais);
            chip.open(ace);
        }catch (Exception e){
            System.out.println("Unable to open audio file");
            e.printStackTrace();
        }


        //To have 5x5 boxes
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Spacing rooms 60 pixels apart
                rooms.add(new Room(10 + j * 60, 10 + i * 60));

            }
        }
        //Make a passageway
        rooms.get(0).setEastExit(rooms.get(1));
        rooms.get(0).setSouthExit(rooms.get(5));
        rooms.get(1).setEastExit(rooms.get(2));
        rooms.get(2).setEastExit(rooms.get(3));
        rooms.get(3).setSouthExit(rooms.get(8));
        rooms.get(4).setSouthExit(rooms.get(9));
        rooms.get(5).setEastExit(rooms.get(6));
        rooms.get(6).setSouthExit(rooms.get(11));
        rooms.get(7).setSouthExit(rooms.get(12));
        rooms.get(8).setEastExit(rooms.get(9));
        rooms.get(8).setSouthExit(rooms.get(13));
        rooms.get(10).setEastExit(rooms.get(11));
        rooms.get(10).setSouthExit(rooms.get(15));
        rooms.get(12).setSouthExit(rooms.get(17));
        rooms.get(14).setWestExit(rooms.get(13));
        rooms.get(14).setSouthExit(rooms.get(19));
        rooms.get(15).setSouthExit(rooms.get(20));
        rooms.get(16).setSouthExit(rooms.get(21));
        rooms.get(16).setEastExit(rooms.get(17));
        rooms.get(17).setEastExit(rooms.get(18));
        rooms.get(17).setSouthExit(rooms.get(22));
        rooms.get(18).setEastExit(rooms.get(19));
        rooms.get(22).setEastExit(rooms.get(23));
        rooms.get(23).setEastExit(rooms.get(24));

        {
//            drawables.add(new Room(0, 0));
//            drawables.add(new David());
        }


        //Responsible for assigning David, Goliath, and Stone a room
        david.setCurrentRoom((rooms.get(20)));
        goliath.setCurrentRoom(rooms.get(4));
        int[] locateStone = {0, 7, 8, 21, 24};
        for (int j : locateStone) {
            Stone stone = new Stone();
            stone.setCurrentRoom(rooms.get(j));
            stones.add(stone);

        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        //Draw the rooms
        super.paintComponent(g);
        for (Room room : rooms) {
            room.draw(g);
        }
        for (Drawable drawable:drawables){
            drawable.draw(g);
        }
        //Draw David, Goliath, and Stones
        if (david != null) {
            david.draw(g);
        }
        if (goliath != null) {
            goliath.draw(g);
        }
        for (Stone stone : stones){
            if (stone!=null){
                stone.draw(g);
            }
        }


    }
    public void keyTyped(KeyEvent e){
    }
    public void keyPressed(KeyEvent e){
        //Get the value of the pressed key that will later on
        //Be compared in the switch
        int pressedKey = e.getKeyCode();
        switch (pressedKey){
            case KeyEvent.VK_UP:
                david.moveNorth();
                break;
            case KeyEvent.VK_DOWN:
                david.moveSouth();
                break;
            case KeyEvent.VK_RIGHT:
                david.moveEast();
                break;
            case KeyEvent.VK_LEFT:
                david.moveWest();
                break;
        }
        for (Stone stone: stones){
            try {
                // Checks if david went into the room and collect the stone
                // When the david collect the stone there will a sound and
                // Also add the stone to his pocket in order to win
                if (stone.getCurrentRoom().equals(david.getCurrentRoom())) {
                    picking();
                    david.pickUpStone();
                    stone.setCurrentRoom(null);
//                    stones.remove(stone);
                }
            }catch(Exception f){
                continue;

            }
        }
        // Show some message when David win or lose
        // Reset the game
        if(david.getCurrentRoom().equals(goliath.getCurrentRoom())){
            if(david.isArmed()){
                winningSound();
                JOptionPane.showMessageDialog(null, "Congratulations David! You slew Goliath!");
            }else{
                JOptionPane.showMessageDialog(null, "Oh no David! Goliath got you! Try again.");
            }
            reset();
        }
        //Repaint to erase the old photo and paint it to the new
        //Location of the room when necessary.
        repaint();
        switch (pressedKey){
            case KeyEvent.VK_W:
                goliath.moveNorth();
                break;
            case KeyEvent.VK_S:
                goliath.moveSouth();
                break;
            case KeyEvent.VK_D:
                goliath.moveEast();
                break;
            case KeyEvent.VK_A:
                goliath.moveWest();
                break;
        }
        repaint();

    }
    public void keyReleased(KeyEvent e){
    }

    // Responsible for starting the audio when David slew Goliath
    private  void winningSound(){
        if(!clip.isRunning()){
            clip.setFramePosition(0);
            clip.start();
        }
    }
    // Responsible to start the audio
    private void picking(){
        if(!chip.isRunning()){
            chip.setFramePosition(0);
            chip.start();
        }
    }

    // Reset the format of the game
    private void reset(){
        david.reset();
        david.setCurrentRoom((rooms.get(20)));
        goliath.setCurrentRoom(rooms.get(4));
        stones.clear();
        ArrayList<Integer> locateStone = new ArrayList<>();
        for(int j = 0; j <= 5; j++){
            int x = (int) (Math.random() * 24 + 1) ;
            if(x == 4){
                x++;
            }else if (x == 1){
                x++;
            }
            locateStone.add(x);
        }
//        int[] locateStone = {0, 7, 8, 21, 24};
        // Going to put a stone in a room
        for (int j : locateStone) {
            Stone stone = new Stone();
            stone.setCurrentRoom(rooms.get(j));
            stones.add(stone);

        }

        }




    //Pop up a window to show the Maze
    public static void main(String[] args) {
        JFrame window = new JFrame("Maze Runner");
        Main main = new Main();
        window.add(main);
        window.setSize(400,400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setBackground(Color.LIGHT_GRAY);
    }
}
