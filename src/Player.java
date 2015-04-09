/**
 * Created by Student on 09.04.2015.
 */
public class Player{
    private int playerX;
    private int playerY;
    private int playerWidth;
    private int playerHeight;
    private int paddleSpeed = 5;
    public Player(PlayerType val)
    {
        playerWidth = 10;
        playerHeight = 50;
        if (val.name() == PlayerType.PlayerOne.name())
        {
            playerX = 25;
            playerY = 250;
        }
        if (val.name() == PlayerType.PlayerTwo.name())
        {
            playerX = 465;
            playerY = 250;
        }
        paddleSpeed = 5;
    }
    public int GetX() {return playerX;}
    public int GetY() {return playerY;}
    public int GetH() {return playerHeight;}
    public int GetW() {return playerWidth;}
    public int GetS() {return paddleSpeed;}
    public void SetY(int val) {playerY = val;}
}
