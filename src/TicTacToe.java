import javax.swing.*;

public class TicTacToe extends JFrame {
    public TicTacToe() {
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(160, 90, 480, 450);
    }

    public static void main(String[] args) {
        var frame = new TicTacToe();
        frame.setVisible(true);
    }
}
