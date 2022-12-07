package Game;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.plaf.DimensionUIResource;

public class GamePanel extends JPanel implements Runnable {
    // Size for characters
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    // Size for window or game
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyH = new KeyHandler(); // second video
    Thread gameThread;

    // set palyer's default position (second video)
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // Constructor
    public GamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        ; // second video
        this.setFocusable(true); // second video

    }

    // method to start the game
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            System.out.println("The game loop is running");

            // 1 update = update information such as character positions
            update();
            // 2 draw = draw the screen with the updated information
            repaint();
        }

    }

    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;

        } else if (keyH.downPressed == true) {
            playerX += playerSpeed;

        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();

    }
}
