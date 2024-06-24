package Snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static Snake.HighScoreManager.generateLeaderboard;


public class MainMenu extends JFrame {
    public MainMenu() {
        setVisible(true);
        setTitle("Snake-Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton start = new JButton("Start Game");
        JButton help = new JButton("Help");
        JButton average = new JButton("Average Score");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game();
                game.setVisible(true);
                dispose();
            }
        });
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpMessage = "Snake Game Help\n\n" +
                        "Controls:\n" +
                        "   - Use arrow keys (Up, Down, Left, Right) to control the snake.\n" +
                        "   - Press 'A' and 'D' keys as alternative controls for Left and Right.\n\n" +
                        "Objective:\n" +
                        "   - Eat the apples to grow the snake and increase your score.\n" +
                        "   - Avoid collisions with the snake's own body and the game boundaries.\n" +
                        "   - The game ends when the snake collides with itself or the boundaries.\n\n" +
                        "Have fun playing Snake!";
                JOptionPane.showMessageDialog(MainMenu.this, helpMessage);
            }
        });
        JButton leaderBoard = new JButton("Leader Board");
        leaderBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayLeaderBoard();
            }
        });
        average.addActionListener(e -> {
            double averageScore = calculateAverageScore();
            JOptionPane.showMessageDialog(MainMenu.this, "Average Score: " + averageScore, "Average Score", JOptionPane.INFORMATION_MESSAGE);
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(start);
        panel.add(leaderBoard);
        panel.add(average);
        panel.add(help);
        panel.add(exit);
        add(panel);
        setSize(450, 450);
        setLocationRelativeTo(null);
    }

    public void displayLeaderBoard() {
        int threshold = 0;
        List<HighScore> leaderboard = generateLeaderboard(threshold);

        StringBuilder leaderboardText = new StringBuilder("Leaderboard:\n");

        for (int i = 0; i < leaderboard.size(); i++) {
            HighScore score = leaderboard.get(i);
            leaderboardText.append((i + 1)).append(" ").append("\t").append(score.getName()).append(" ").append(score.getScore()).append("\n");
            //
        }

        JOptionPane.showMessageDialog(null, leaderboardText.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }

    public double calculateAverageScore() {
        List<HighScore> highScores = generateLeaderboard(0);

        return highScores.stream()
                .mapToInt(HighScore::getScore)
                .average()
                .orElse(0.0);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
