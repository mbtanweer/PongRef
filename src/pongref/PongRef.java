package pongref;
//copyright 2013 Wintriss Technical Schools
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class PongRef extends JComponent implements MouseMotionListener, ActionListener
{
    URL pingSoundAddress = getClass().getResource("fire.wav");
    AudioClip pingSoundFile = JApplet.newAudioClip(pingSoundAddress);
    JFrame jf;
    public int ballx = 1000;
    public int bally = 1000;
    public int ballXspeed = 20;
    int ballYspeed = 20;
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
    Timer ticker;
    
    public static void main(String[] args) throws InterruptedException
    {
        new PongRef().getGoing();
    }
    private void getGoing() throws InterruptedException
    {
        ticker = new Timer(20, this);
        ticker.start();
        jf = new JFrame("My Pong Game");
        jf.setSize(width, height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(this); // This means this paint method
        jf.addMouseMotionListener(this);
        sailboatImage = new ImageIcon(this.getClass().getResource("sailboat-218697.jpg")).getImage();
        ball = new Ellipse2D.Double(ballx, bally, ballSize, ballSize);
        paddle = new Rectangle2D.Double(paddleX, paddleY, paddleWidth, paddleHeight);
        jf.setVisible(true);
    }
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(1.4, 1.1);
        g2.drawImage(sailboatImage, 0, 0, null);
        g2.scale(1, 1);
        g2.setColor(Color.GREEN);
        //g2.fillOval((int) ballx, bally, ballSize, ballSize); //fill Oval at start of project, then make object
        g2.fill(ball);
        g2.setColor(Color.blue);
        //g2.fillRect(paddleX, paddleY, paddleWidth, paddleHeight); //fill Rect at start of project, then make object
        g2.fill(paddle);
        g2.setColor(Color.BLACK);
        g2.drawString("SCORE", width - 500, 100);
        g2.setFont(new Font("Bank Gothic", Font.BOLD, 99));
        g2.drawString(score + "", width - 500, 200);
        ball.x += ballXspeed;
        ball.y += ballYspeed;

        if (ball.x > (width - (ballSize + 800))) // To compensate for ball size
        {
            ballXspeed = -ballXspeed;
        }
        if (ball.y > (height - (ballSize + 250))) // To compensate for bottom margin
        {
            ballYspeed = -ballYspeed;
        }
        if (ball.y < 0)
        {
            ballYspeed = -ballYspeed;
        }
        if (ball.intersects(paddle))
        {
            if (ballXspeed < 0) // To keep the ball from sticking to the paddle
            {
                ballXspeed = -ballXspeed;
            }
            score = score + 1;
            pingSoundFile.play();
        }
        if (ball.x < 0)
        {
            g2.setColor(Color.red);
            g2.scale(6, 6);
            g2.drawString("Loser", 0, 99);
            g2.scale(1, 1);
        }
//        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent me)
    {
    }
    @Override
    public void mouseMoved(MouseEvent me)
    {
        paddle.y = me.getY();
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        repaint();
    }
}
