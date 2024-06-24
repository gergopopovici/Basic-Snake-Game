package Snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;

import static Snake.HighScoreManager.generateLeaderboard;

public class Controller implements ActionListener, KeyListener {

    private final Model model;
    private final View view;
    private final Timer timer;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setFocusable(true);
        view.addKeyListener(this);

        timer = new Timer(75, this);
        timer.start();

        model.setController(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getGameRunning()) {
            model.move();
            model.Collision();
            if(!model.getGameRunning()){
                saveHighScore();
            }
            model.appleEaten();
            view.repaint();
        } else {
            Timer timer = (Timer) e.getSource();
            timer.stop();
            saveHighScore();
        }
    }

    private void saveHighScore() {
        String playerName = JOptionPane.showInputDialog(view, "Game Over! Enter your name:", "Game Over", JOptionPane.PLAIN_MESSAGE);
        HighScoreManager.saveHighScore(playerName, model.getScore());
        displayLeaderboard();
        System.exit(0);
    }

    private void displayLeaderboard() {
        int threshold = 0;
        List<HighScore> leaderboard = generateLeaderboard(threshold);

        StringBuilder leaderboardText = new StringBuilder("Leaderboard:\n");

        for (int i = 0; i < leaderboard.size(); i++) {
            HighScore score = leaderboard.get(i);
            leaderboardText.append((i + 1)).append(" ").append("\t").append(score.getName()).append(" ").append(score.getScore()).append("\n");
        }

        JOptionPane.showMessageDialog(null, leaderboardText.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }

    public void stopTimer() {
        timer.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (model.getDirection() != 'R') {
                    model.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (model.getDirection() != 'L') {
                    model.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if (model.getDirection() != 'D') {
                    model.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if (model.getDirection() != 'U') {
                    model.setDirection('D');
                }
                break;
            case KeyEvent.VK_A:
                if (model.getDirection() != 'R') {
                    model.setDirection('L');
                }
                break;
            case KeyEvent.VK_D:
                if (model.getDirection() != 'L') {
                    model.setDirection('R');
                }
                break;
            case KeyEvent.VK_W:
                if (model.getDirection() != 'D') {
                    model.setDirection('U');
                }
                break;
            case KeyEvent.VK_S:
                if (model.getDirection() != 'U') {
                    model.setDirection('D');
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
