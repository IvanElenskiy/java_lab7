/**
 * Created by Ivan on 26.03.2015.
 */
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class main{

    public static void main(String[] args) {

        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        PingPong pongPanel = new PingPong();
        frame.add(pongPanel, BorderLayout.CENTER);

        frame.setSize(500, 500);
        frame.setVisible(true);

    }
}