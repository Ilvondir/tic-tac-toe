import javax.swing.*;
import java.awt.*;
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

    URL iconURL = getClass().getResource("img/icon.png");
    ImageIcon icon = new ImageIcon(Objects.requireNonNull(iconURL));

    public TicTacToe() {
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(160, 90, 480, 590);
        this.setIconImage(icon.getImage());
        this.setContentPane(mainPanel);

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

        frame.cell1.setBorder(border1);
        frame.cell2.setBorder(border2);
        frame.cell3.setBorder(border3);
        frame.cell4.setBorder(border4);
        frame.cell5.setBorder(border5);
        frame.cell6.setBorder(border6);
        frame.cell7.setBorder(border7);
        frame.cell8.setBorder(border8);
        frame.cell9.setBorder(border9);
    }

    public static void main(String[] args) {
        var frame = new TicTacToe();
        setBorders(frame);
        frame.setVisible(true);
    }
}
