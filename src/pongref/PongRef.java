package pongref;
//copyright 2013 Wintriss Technical Schools
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PongRef extends JComponent implements MouseMotionListener
{

    URL pingSoundAddress = getClass().getResource("fire.wav");
    AudioClip pingSoundFile = JApplet.newAudioClip(pingSoundAddress);
    JFrame jf;
    int ballx = 1000;
    int bally = 1000;
    int ballXspeed = 2;
    int ballYspeed = 2;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    int ballSize = 50;
    int paddleWidth = 25;
    int paddleHeight = 100;
    int paddleX = 25;
    int paddleY = 1000;
    Ellipse2D.Double ball;
    Rectangle2D.Double paddle;
    int score = 0;
    Image sailboatImage;

    public static void main(String[] args)
    {
        new PongRef().getGoing();
    }

    private void getGoing()
    {
        jf = new JFrame("My Pong Game");
        jf.setVisible(true);
        jf.setSize(width, height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(this); // This means this paint method
        jf.addMouseMotionListener(this);
        sailboatImage = new ImageIcon(this.getClass().getResource("sailboat-218697.jpg")).getImage();
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(sailboatImage, 0, 0, null);
        g2.setColor(Color.GREEN);
        ball = new Ellipse2D.Double(ballx, bally, ballSize, ballSize);
        //g2.fillOval((int) ballx, bally, ballSize, ballSize); //fill Oval at start of project, then make object
        g2.fill(ball);
        g2.setColor(Color.blue);
        paddle = new Rectangle2D.Double(paddleX, paddleY, paddleWidth, paddleHeight);
        //g2.fillRect(paddleX, paddleY, paddleWidth, paddleHeight); //fill Rect at start of project, then make object
        g2.fill(paddle);
        g2.setColor(Color.BLACK);
        g2.drawString("SCORE", width - 500, 100);
        g2.setFont(new Font("Bank Gothic", Font.BOLD, 99));
        g2.drawString(score + "", width - 500, 200);
        ballx += ballXspeed;
        bally += ballYspeed;

        if (ballx > (width - ballSize)) // To compensate for ball size
        {
            ballXspeed = -ballXspeed;
        }
        if (bally > (height - ballSize - 100)) // To compensate for bottom margin
        {
            ballYspeed = -ballYspeed;
        }
        if (bally < 0)
        {
            ballYspeed = -ballYspeed;
        }
        if (ball.intersects(paddle))
        {
            if (ballXspeed < 0) // To keep the ball from sticking to the paddle
            {
                ballXspeed = -ballXspeed;
                ballx += 10; // To keep the score from going up by more than one
            }
            score = score + 1;
            pingSoundFile.play();
        }
        if (ballx < 0)
        {
            g2.setColor(Color.red);
            g2.scale(9, 9);
            g2.drawString("Loser", 0, 99);
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
    }

    @Override
    public void mouseMoved(MouseEvent me)
    {
        paddleY = me.getY();
    }
}
