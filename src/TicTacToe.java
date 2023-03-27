import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class TicTacToe extends JFrame {

    private JPanel mainPanel;
    private JButton humanButton;
    private JButton botButton;
    private JPanel cell1;
    private JPanel cell2;
    private JPanel cell3;
    private JPanel cell4;
    private JPanel cell5;
    private JPanel cell6;
    private JPanel cell7;
    private JPanel cell8;
    private JPanel cell9;

    JPanel[][] cellsList = {
            {cell1, cell2, cell3},
            {cell4, cell5, cell6},
            {cell7, cell8, cell9}
    };

    URL iconURL = getClass().getResource("img/icon.png");
    ImageIcon icon = new ImageIcon(Objects.requireNonNull(iconURL));

    URL crossURL = getClass().getResource("img/cross.png");
    ImageIcon cross = new ImageIcon(Objects.requireNonNull(crossURL));
    URL circleURL = getClass().getResource("img/circle.png");
    ImageIcon circle = new ImageIcon(Objects.requireNonNull(circleURL));

    ImageIcon humanSymbol;
    ImageIcon botSymbol;

    int[][] gameBoard = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    int indexOfBestResult = 0;

    public TicTacToe() {
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(160, 90, 480, 590);
        this.setIconImage(icon.getImage());
        this.setContentPane(mainPanel);

        humanButton.addActionListener(e -> {
            humanSymbol = circle;
            botSymbol = cross;

            for (int i=0;i<cellsList.length; i++) {
                for (int j=0;j<cellsList[i].length; j++) {
                    int finalJ = j;
                    int finalI = i;
                    gameBoard[finalI][finalJ] = 0;

                    var comps = cellsList[finalI][finalJ].getComponents();
                    for (var comp : comps) {
                        cellsList[finalI][finalJ].remove(comp);
                    }
                    revalidate();
                    repaint();

                    cellsList[i][j].addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            panelClick(cellsList[finalI][finalJ]);
                        }
                    });
                }
            }
            humanButton.setEnabled(false);
            botButton.setEnabled(false);
        });

        botButton.addActionListener(e -> {
            humanSymbol = cross;
            botSymbol = circle;
            for (int i=0;i<cellsList.length; i++) {
                for (int j=0;j<cellsList[i].length; j++) {
                    int finalJ = j;
                    int finalI = i;
                    gameBoard[finalI][finalJ] = 0;

                    var comps = cellsList[finalI][finalJ].getComponents();
                    for (var comp : comps) {
                        cellsList[finalI][finalJ].remove(comp);
                    }
                    revalidate();
                    repaint();

                    cellsList[i][j].addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            panelClick(cellsList[finalI][finalJ]);
                        }
                    });
                }
            }
            humanButton.setEnabled(false);
            botButton.setEnabled(false);

            minimax(false, gameBoard);

            int x = (int)Math.floor(indexOfBestResult/3);
            int y = indexOfBestResult%3;
            gameBoard[x][y]=-1;
            JLabel img2 = new JLabel();
            img2.setIcon(botSymbol);
            img2.setHorizontalAlignment(SwingConstants.CENTER);
            cellsList[x][y].add(img2);
            revalidate();
            repaint();
            var tab2 = cellsList[x][y].getMouseListeners();
            for (MouseListener elem : tab2) {
                cellsList[x][y].removeMouseListener(elem);
            }
        });
    }

    public static void setBorders(TicTacToe frame) {
        var border1 = BorderFactory.createMatteBorder(0,0,3,3, Color.WHITE);
        var border2 = BorderFactory.createMatteBorder(0,3,3,3, Color.WHITE);
        var border3 = BorderFactory.createMatteBorder(0,3,3,0, Color.WHITE);
        var border4 = BorderFactory.createMatteBorder(3,0,3,3, Color.WHITE);
        var border5 = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
        var border6 = BorderFactory.createMatteBorder(3,3,3,0, Color.WHITE);
        var border7 = BorderFactory.createMatteBorder(3,0,0,3, Color.WHITE);
        var border8 = BorderFactory.createMatteBorder(3,3,0,3, Color.WHITE);
        var border9 = BorderFactory.createMatteBorder(3,3,0,0, Color.WHITE);

        MatteBorder[][] tab = {
                {border1, border2, border3},
                {border4, border5, border6},
                {border7, border8, border9}
        };

        var dim = new Dimension(96, 96);

        for (int i=0;i<frame.cellsList.length; i++) {
            for (int j=0;j<frame.cellsList[i].length; j++) {
                frame.cellsList[i][j].setBorder(tab[i][j]);
                frame.cellsList[i][j].setPreferredSize(dim);
            }
        }
    }

    public void panelClick(JPanel panel) {

        for (int i=0;i<cellsList.length; i++) {
            for (int j=0;j<cellsList[i].length; j++) {
                if (cellsList[i][j].equals(panel)) gameBoard[i][j] = 1;
            }
        }

        JLabel img = new JLabel();
        img.setIcon(humanSymbol);
        img.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(img);
        revalidate();
        repaint();
        var tab = panel.getMouseListeners();
        for (MouseListener e : tab) {
            panel.removeMouseListener(e);
        }

        minimax(false, gameBoard);

        int x = (int)Math.floor(indexOfBestResult/3);
        int y = indexOfBestResult%3;
        gameBoard[x][y]=-1;
        JLabel img2 = new JLabel();
        img2.setIcon(botSymbol);
        img2.setHorizontalAlignment(SwingConstants.CENTER);
        cellsList[x][y].add(img2);
        revalidate();
        repaint();
        var tab2 = cellsList[x][y].getMouseListeners();
        for (MouseListener e : tab2) {
            cellsList[x][y].removeMouseListener(e);
        }

        if (gameOver(gameBoard)) {
            humanButton.setEnabled(true);
            botButton.setEnabled(true);

            for (JPanel[] jPanels : cellsList) {
                for (JPanel jPanel : jPanels) {

                    var tab3 = jPanel.getMouseListeners();
                    for (MouseListener e : tab3) {
                        jPanel.removeMouseListener(e);
                    }
                }
            }

            if (winner(gameBoard)==1) JOptionPane.showMessageDialog(this, "You win!", "Game over", JOptionPane.ERROR_MESSAGE);
            else if (winner(gameBoard)==-1)JOptionPane.showMessageDialog(this, "Bot win!", "Game over", JOptionPane.ERROR_MESSAGE);
            else JOptionPane.showMessageDialog(this, "No one win!", "Game over", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean gameOver(int[][] board) {

        if (board[0][0]==board[1][0] && board[1][0]==board[2][0] && board[0][0]!=0) return true;
        if (board[0][1]==board[1][1] && board[1][1]==board[2][1] && board[0][1]!=0) return true;
        if (board[0][2]==board[1][2] && board[1][2]==board[2][2] && board[0][2]!=0) return true;

        if (board[0][0]==board[0][1] && board[0][1]==board[0][2] && board[0][0]!=0) return true;
        if (board[1][0]==board[1][1] && board[1][1]==board[1][2] && board[1][0]!=0) return true;
        if (board[2][0]==board[2][1] && board[2][1]==board[2][2] && board[2][0]!=0) return true;

        if (board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!=0) return true;
        if (board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!=0) return true;

        for (int i=0;i<cellsList.length; i++) {
            for (int j=0;j<cellsList[i].length; j++) {
                if (board[i][j] == 0) return false;
            }
        }

        return true;
    }

    public int minimax(boolean maximizing, int[][] board) {

        if (gameOver(board)) return scores(board);

        var results = new ArrayList<Integer>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {

                    board[i][j] = maximizing ? 1 : -1;
                    results.add(minimax(!maximizing, board));

                    board[i][j] = 0;
                } else {
                    if (maximizing) results.add(Integer.MIN_VALUE);
                    else results.add(Integer.MAX_VALUE);
                }
            }
        }

        int bestResult;

        if (maximizing) bestResult = Integer.MIN_VALUE;
        else bestResult = Integer.MAX_VALUE;

        for (int e : results) {
            if (maximizing) {
                if (e >= bestResult) bestResult = e;
            } else {
                if (e <= bestResult) bestResult = e;
            }
        }

        indexOfBestResult = results.indexOf(bestResult);

        return bestResult;
    }

    public int scores(int[][] board) {

        if (gameOver(board)) {
            if (winner(board)==1) return Integer.MAX_VALUE;
            if (winner(board)==-1) return Integer.MIN_VALUE;
        }

        int score = 0;

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][1]) {
                if (board[i][0] == 1) {
                    score += 50;
                } else if (board[i][0] == -1) {
                    score -= 50;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 1) {
                    score += 50;
                } else if (board[0][i] == -1) {
                    score -= 50;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 1) {
                score += 50;
            } else if (board[0][0] == -1) {
                score -= 50;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 1) {
                score += 50;
            } else if (board[0][2] == -1) {
                score -= 50;
            }
        }

        return score;
    }

    public int winner(int [][] board) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != 0) {
                return board[row][0];
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != 0) {
                return board[0][col];
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            return board[0][2];
        }

        return 0;
    }
}
