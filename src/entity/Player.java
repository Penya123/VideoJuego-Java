package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeigth/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16, 24, 24);

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "front";
    }
    public void getPlayerImage(){
        try{
            front = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_front.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_up.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_front1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_front2.png"));
            letf1 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_left1.png"));
            letf2 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_letf2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/andy_right2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rigthPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "letf";
            }
            else if(keyH.rigthPressed){
                direction = "right";

            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            if(!collisionOn){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "letf": worldX -= speed; break;
                    case "right": worldX += speed; break;

                }
            }


            spriteCounter++;
            if (spriteCounter > 11){
                if (spriteNum == 1) spriteNum = 2;
                else spriteNum = 1;
                spriteCounter = 0;
            }
        }
        if (!keyH.downPressed && (!keyH.upPressed || !keyH.leftPressed || !keyH.rigthPressed)){
            if(direction=="down") direction = "front";
        }
        if (!keyH.upPressed && (!keyH.downPressed || !keyH.leftPressed || !keyH.rigthPressed)){
            if (direction=="up") direction = "upp";
        }
        
    }
    public void draw(Graphics2D g2){
        BufferedImage image = front;

        switch (direction){
            case "upp": image = up0; break;
            case "front": image = front; break;
            case "up":
                if(spriteNum == 1) image = up1;
                else image = up2; break;
            case "letf":
                if (spriteNum == 1) image = letf1;
                else image = letf2; break;
            case "down":
                if (spriteNum == 1) image = down1;
                else image = down2; break;
            case "right":
                if (spriteNum == 1) image = right1;
                else image = right2; break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
