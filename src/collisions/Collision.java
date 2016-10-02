package collisions;


import gameItems.Player;
import gameItems.Tile;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import particles.ParticleBatch;
import particles.ParticleEngine;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by one on 9/18/16.
 */
public class Collision {

    public static void collideWithLevel(Player player, char[][] levelData, ParticleBatch particleBatch) {

        List<Vector2f> collideTilePosition = new ArrayList<Vector2f>(); // center of the tile

        // check four corners
        boolean bottomLeft = checkTilePosition(player.getPosition().x, player.getPosition().y, levelData, collideTilePosition);
        boolean bottomRight = checkTilePosition(player.getPosition().x + Player.SIZE, player.getPosition().y, levelData, collideTilePosition);


        if (bottomLeft || bottomRight) {
            player.setInAir(false);
            player.getVelocity().y = 0;
        }

        boolean upperLeft = checkTilePosition(player.getPosition().x, player.getPosition().y + Player.SIZE, levelData, collideTilePosition);
        boolean upperRight = checkTilePosition(player.getPosition().x + Player.SIZE, player.getPosition().y + Player.SIZE, levelData, collideTilePosition);

        if (upperLeft || upperRight) {
            player.getVelocity().y = -0.8f*player.getVelocity().y;
            particleBatch.addParticle(new Vector2f(player.getPosition().x + Player.SIZE/2 - particleBatch.SIZE/2, player.getPosition().y + Player.SIZE/2), new Vector2f(0, 1), new Vector3f(0, 0, 0));
        }

        for (int i = 0; i < collideTilePosition.size(); i++) {
            collideWithTile(player, collideTilePosition.get(i));
        }

    }

    private static boolean checkTilePosition(float x, float y, char[][] levelData, List<Vector2f> collideTilePosition) {

        Vector2i cornerPos = new Vector2i((int)(x / Tile.SIZE), (int)(y / Tile.SIZE));



        if (levelData[cornerPos.y][cornerPos.x] != '.') {

            collideTilePosition.add(new Vector2f(cornerPos.x * Tile.SIZE + Tile.SIZE/2, cornerPos.y * Tile.SIZE + Tile.SIZE/2));
            return true;
        }

        return false;

    }

    // AABB collision
    private static void collideWithTile(Player player, Vector2f tilePosition) {

        final float PLAYER_RADIUS = Player.SIZE/2;
        final float TILE_RADIUS = Tile.SIZE/2;
        final float MIN_DISTANCE = PLAYER_RADIUS + TILE_RADIUS;

        Vector2f centerPlayerPosition = new Vector2f(player.getPosition().x+PLAYER_RADIUS, player.getPosition().y+PLAYER_RADIUS);
        Vector2f distance = new Vector2f(tilePosition.x - centerPlayerPosition.x, tilePosition.y - centerPlayerPosition.y);

        float xDepth = MIN_DISTANCE - Math.abs(distance.x);
        float yDepth = MIN_DISTANCE - Math.abs(distance.y);


        if (xDepth > 0 && yDepth > 0) {

            if (xDepth < yDepth) {
                if (distance.x > 0) {
                    player.increasePosition(-xDepth, 0);
                } else {
                    player.increasePosition(xDepth, 0);
                }
            } else {
                if (distance.y > 0) {
                    player.increasePosition(0, -yDepth);
                } else {
                    player.increasePosition(0, yDepth);
                }

            }



        }


    }

}
