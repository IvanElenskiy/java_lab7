/**
 * Created by Student on 09.04.2015.
 */
public class Ball {
    private int ballX;
    private int ballY;
    private int diameter;
    private int ballDeltaX;
    private int ballDeltaY;
    public Ball()
    {
        ballX = 250;
        ballY = 250;
        diameter = 20;
        ballDeltaX = -1;
        ballDeltaY = 3;
    }
    int GetX() {return ballX;}
    int GetY() {return ballY;}
    int GetD() {return diameter;}
    int GetDX() {return ballDeltaX;}
    int GetDY() {return ballDeltaY;}
    void setX(int val) {ballX = val;}
    void setY(int val) {ballY = val;}
    void knockDX() {ballDeltaX *= -1;}
    void knockDY() {ballDeltaY *= -1;}
    void move() {ballX += ballDeltaX; ballY += ballDeltaY;}
    void restore() {ballX = 250; ballY = 250;}
}
