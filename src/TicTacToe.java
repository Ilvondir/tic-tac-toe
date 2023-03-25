import javax.swing.*;
import java.net.URL;
import java.util.Objects;

public class TicTacToe extends JFrame {

    private JPanel mainPanel;
    private JButton humanButton;
    private JButton botButton;

    URL iconURL = getClass().getResource("img/icon.png");
    ImageIcon icon = new ImageIcon(Objects.requireNonNull(iconURL));

    public TicTacToe() {
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(160, 90, 480, 450);
        this.setIconImage(icon.getImage());
        this.setContentPane(mainPanel);

    }

    public static void main(String[] args) {
        var frame = new TicTacToe();
        frame.setVisible(true);
    }
}
