/**
 * Created by Ivan on 26.03.2015.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class PingPong extends JPanel implements KeyListener, ActionListener {

    private boolean showTitleScreen = true;
    private boolean playing = false;
    private boolean gameOver = false;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private final int endGameScore = 3;

    private Player playerOne = new Player(PlayerType.PlayerOne);
    private Player playerTwo = new Player(PlayerType.PlayerTwo);
    private Ball ball = new Ball();
    private Timer game = new Timer(1000/60, this);

    private final long time = 1000 / 60;
    private long evalTime = System.currentTimeMillis();

    public PingPong() {
        setBackground(Color.BLACK);

        setFocusable(true);
        addKeyListener(this);

        game.start();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.WHITE);

        if (showTitleScreen) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("PingPong", 170, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

            g.drawString("Press 'P' to play.", 175, 400);
        } else if (playing) {

            int playerOneRight = playerOne.GetX() + playerOne.GetW();
            int playerTwoLeft = playerTwo.GetX();

            for (int lineY = 0; lineY < getHeight(); lineY += 50) {
                g.drawLine(250, lineY, 250, lineY + 25);
            }

            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 400, 100);

            g.fillOval(ball.GetX(), ball.GetY(), ball.GetD(),ball.GetD());

            g.fillRect(playerOne.GetX(), playerOne.GetY(), playerOne.GetW(), playerOne.GetH());
            g.fillRect(playerTwo.GetX(), playerTwo.GetY(), playerTwo.GetW(), playerTwo.GetH());
        } else if (gameOver) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 400, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            if (playerOneScore > playerTwoScore) {
                g.drawString("Player 1 Wins!", 130, 200);
            } else {
                g.drawString("Player 2 Wins!", 130, 200);
            }

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press space to restart.", 150, 400);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (showTitleScreen) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                showTitleScreen = false;
                playing = true;
            }
        } else if (playing) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = true;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = true;
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = true;
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                gameOver = false;
                playing = false;
                showTitleScreen = true;
                playerOne.SetY(250);
                playerTwo.SetY(250);
                ball.setX(250);
                ball.setY(250);
                playerOneScore = 0;
                playerTwoScore = 0;
            }
        } else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameOver = false;
                showTitleScreen = true;
                playerOne.SetY(250);
                playerTwo.SetY(250);
                ball.setX(250);
                ball.setY(250);
                playerOneScore = 0;
                playerTwoScore = 0;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (playing) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = false;
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = false;
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = false;
            }
        }
    }

    public void CheckPressed() {
        if (upPressed) {
            if (playerOne.GetY() - playerOne.GetS() > 0) {
                playerOne.SetY(playerOne.GetY() - playerOne.GetS());
            }
        }
        if (downPressed) {
            if (playerOne.GetY() + playerOne.GetS() + playerOne.GetH() < getHeight()) {
                playerOne.SetY(playerOne.GetY() + playerOne.GetS());
            }
        }
        if (wPressed) {
            if (playerTwo.GetY() - playerTwo.GetS() > 0) {
                playerTwo.SetY(playerTwo.GetY() - playerTwo.GetS());
            }
        }
        if (sPressed) {
            if (playerTwo.GetY() + playerTwo.GetS() + playerTwo.GetH() < getHeight()) {
                playerTwo.SetY(playerTwo.GetY() + playerTwo.GetS());
            }
        }
    }

    public void Movement() {
        int nextBallLeft = ball.GetX() + ball.GetDX();
        int nextBallRight = ball.GetX() + ball.GetD() + ball.GetDX();
        int nextBallTop = ball.GetY() + ball.GetDY();
        int nextBallBottom = ball.GetY() + ball.GetD() + ball.GetDY();
        int playerOneRight = playerOne.GetX() + playerOne.GetW();
        int playerOneTop = playerOne.GetY();
        int playerOneBottom = playerOne.GetY() + playerOne.GetH();
        int playerTwoLeft = playerTwo.GetX();
        int playerTwoTop = playerTwo.GetY();
        int playerTwoBottom = playerTwo.GetY() + playerTwo.GetH();
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ball.knockDY();
        }
        if (nextBallLeft < playerOneRight) {
            if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
                playerTwoScore++;
                if (playerTwoScore == endGameScore) {
                    playing = false;
                    gameOver = true;
                }
                ball.restore();
            } else {
                ball.knockDX();
            }
        }
        if (nextBallRight > playerTwoLeft) {
            if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
                playerOneScore++;
                if (playerOneScore == endGameScore) {
                    playing = false;
                    gameOver = true;
                }
                ball.restore();
            } else {
                ball.knockDX();
            }
        }
        ball.move();
    }

    // game.start
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()==game){
            if (System.currentTimeMillis() - evalTime < time) {
                try {
                    Thread.sleep(time - (System.currentTimeMillis() - evalTime));
                } catch (Exception e) {
                }
            }
            if (playing) {
                CheckPressed();
                Movement();
            }
            repaint();
            evalTime = System.currentTimeMillis();
        }
    }
}
