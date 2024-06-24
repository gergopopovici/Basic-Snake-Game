package Snake;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Game extends JFrame {
    private Clip clip;
    public Game() {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        // Set preferred size on the View component
        view.setPreferredSize(new Dimension(model.getScreenWidth(), model.getScreenHeight()));

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().add(view);

        // Pack after setting the preferred size
        this.pack();

        // Center the frame on the screen
        this.setLocationRelativeTo(null);

        //Timer timer = new Timer(100, controller);  // Increased timer delay to 150 milliseconds
        //timer.start();
        this.setVisible(true);
        model.startGame();
        try {
            AudioInputStream audioInputStream  = AudioSystem.getAudioInputStream(new File("sound/music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
        try {
            AudioInputStream audioInputStream  = AudioSystem.getAudioInputStream(new File("sound/music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }

   /* public static void main(String[] args) {
        new Game();
    }*/
}

