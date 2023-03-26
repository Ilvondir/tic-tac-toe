import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
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
    private JPanel Board;
    private JPanel Buttons;
    private JPanel Label;

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

    int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

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

        System.out.println();
        for (int i=0;i<cellsList.length; i++) {
            for (int j=0;j<cellsList[i].length; j++) {
                if (cellsList[i][j].equals(panel)) board[i][j] = 1;
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(gameOver(board));
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
}
