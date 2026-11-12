
import javax.swing.*;
import java.awt.*;

public class TicTacToe {

    // Store the game window and components
    JFrame frame;
    JButton[] buttons = new JButton[9];

    JLabel statusLabel;
    JLabel scoreLabel;

    // Game variables
    char currentPlayer = 'X';
    boolean gameOver = false;

    int scoreX = 0;
    int scoreO = 0;

    // Constructor
    public TicTacToe() {

        // Create the window
        frame = new JFrame("Tic Tac Toe");

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Player X's turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Score label
        scoreLabel = new JLabel("X: 0     O: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(statusLabel);
        topPanel.add(scoreLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        // Create the game board
        JPanel board = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton("");

            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));

            final int index = i;

            buttons[i].addActionListener(e -> makeMove(index));

            board.add(buttons[i]);
        }

        frame.add(board, BorderLayout.CENTER);

        // Restart button
        JButton restartButton = new JButton("Restart Game");

        restartButton.addActionListener(e -> restartGame());

        frame.add(restartButton, BorderLayout.SOUTH);

        // Display the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Handle player moves
    public void makeMove(int index) {

        if (gameOver || !buttons[index].getText().equals("")) {
            return;
        }

        buttons[index].setText(String.valueOf(currentPlayer));

        // Check for winner
        if (checkWinner()) {

            statusLabel.setText("Player " + currentPlayer + " wins!");

            if (currentPlayer == 'X') {
                scoreX++;
            } else {
                scoreO++;
            }

            updateScore();
            gameOver = true;

        } else if (checkDraw()) {

            statusLabel.setText("It's a draw!");
            gameOver = true;

        } else {

            // Switch players
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    // Check winning combinations
    public boolean checkWinner() {

        int[][] winningCombinations = {

                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},

                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},

                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] combination : winningCombinations) {

            if (buttons[combination[0]].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[combination[1]].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[combination[2]].getText().equals(String.valueOf(currentPlayer))) {

                return true;
            }
        }

        return false;
    }

    // Check if the board is full
    public boolean checkDraw() {

        for (int i = 0; i < 9; i++) {

            if (buttons[i].getText().equals("")) {
                return false;
            }
        }

        return true;
    }

    // Update the score
    public void updateScore() {

        scoreLabel.setText("X: " + scoreX + "     O: " + scoreO);
    }

    // Restart the game
    public void restartGame() {

        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }

        currentPlayer = 'X';
        gameOver = false;

        statusLabel.setText("Player X's turn");
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
