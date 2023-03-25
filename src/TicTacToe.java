import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    public JPanel[] cellsList = {cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9};

    URL iconURL = getClass().getResource("img/icon.png");
    ImageIcon icon = new ImageIcon(Objects.requireNonNull(iconURL));

    URL crossURL = getClass().getResource("img/cross.png");
    ImageIcon cross = new ImageIcon(Objects.requireNonNull(crossURL));
    URL circleURL = getClass().getResource("img/circle.png");
    ImageIcon circle = new ImageIcon(Objects.requireNonNull(circleURL));

    public TicTacToe() {
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(160, 90, 480, 590);
        this.setIconImage(icon.getImage());
        this.setContentPane(mainPanel);

        for (int i=0;i<cellsList.length; i++) {
            int finalI = i;
            cellsList[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    panelClick(cellsList[finalI]);
                }
        });
        }
    }

    public void panelClick(JPanel panel) {
        JLabel img = new JLabel();
        img.setIcon(circle);
        img.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(img);
        revalidate();
        repaint();
        var tab = panel.getMouseListeners();
        for (MouseListener e : tab) {
            panel.removeMouseListener(e);
        }
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

        MatteBorder[] tab = {border1, border2, border3, border4, border5, border6, border7, border8, border9};

        var dim = new Dimension(96, 96);

        for (int i=0; i<frame.cellsList.length; i++) {
            frame.cellsList[i].setBorder(tab[i]);
            frame.cellsList[i].setPreferredSize(dim);
        }
    }

    public static void main(String[] args) {
        var frame = new TicTacToe();
        setBorders(frame);
        frame.setVisible(true);
    }
}
