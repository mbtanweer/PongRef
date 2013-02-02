package pongref;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PongRef extends JComponent
{

    public JFrame jf;
    int ballx = 100;
    int bally = 100;
    int ballXspeed = 10;
    int ballYspeed = 10;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    int ballSize = 50;
    int paddleWidth = 25;
    int paddleHeight = 100;
    int paddleX = 25;
    int paddleY = 1000;

    public static void main(String[] args) throws InterruptedException
    {
        new PongRef().getGoing();
    }

    private void getGoing()
    {
        jf = new JFrame("My Pong Game");
        jf.setVisible(true);
        jf.setSize(width, height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(this);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.fillOval((int) ballx, bally, ballSize, ballSize);
        g2.setColor(Color.blue);
        g2.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);
        ballx += ballXspeed;
        bally += ballYspeed;

        if (ballx > (width - ballSize))
        {
            ballXspeed = -ballXspeed;
        }
        if (bally > (height - ballSize - 100))
        {
            ballYspeed = -ballYspeed;
        }
        if (ballx < 0)
        {
            ballXspeed = -ballXspeed;
        }
        if (bally < 0)
        {
            ballYspeed = -ballYspeed;
        }
        repaint();
    }
}
