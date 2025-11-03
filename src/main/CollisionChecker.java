package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottonWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLetfCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottonRow = entityBottonWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up": entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLetfCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "down": entityBottonRow = (entityBottonWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLetfCol][entityBottonRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottonRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "letf": entityLetfCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLetfCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLetfCol][entityBottonRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "right": entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottonRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
        }
    }

}
