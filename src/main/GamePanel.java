package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen setings
    final int originalTileSize = 16; //Pixel size: 16x16// px
    final int scale = 3; //scale times 3

    public final int tileSize = originalTileSize * scale; //48x48 px
    public final int maxScreenCol = 16; //Horizonal size
    public final int maxScreenRow = 12; //Vertical size
    public final int screenWidth = tileSize * maxScreenCol; //768
    public final int screenHeigth = tileSize * maxScreenRow; //576

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWolrdRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWolrdRow;

    //FSP
    int fps = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);

    //Colision
    public CollisionChecker cChecker = new CollisionChecker(this);

    //Pocision por defecto del jugador
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//        double drawInterval = 1_000_000_000/fps; //0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread != null){
//            update();
//            repaint();
//            try{
//                double remainingTime = nextDrawTime - System.nanoTime();
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long)(remainingTime/1000000));
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }
    public void run(){
        double drawInterval = 1_000_000_000/fps;
        double delta = 0;
        long lastTime =System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }

    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();

    }

}
