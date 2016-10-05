package collisions;


import gameItems.Enemy;
import gameItems.Player;
import gameItems.Tile;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import particles.ParticleBatch;
import particles.ParticleEngine;
import sprites.Sprite;
import utils.Gravity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by one on 9/18/16.
 */
public class Collision {

    public static void enemiesCollideWithEnemies(List<Enemy> enemies) {

        for (int i = 0; i < enemies.size(); i++) {

            Enemy subject = enemies.get(i);

            final float SUBJECT_RADIUS_X = subject.getModel().getWidth()/2;
            final float SUBJECT_RADIUS_Y = subject.getModel().getHeight()/2;

            Vector2f centerSubjectPosition = new Vector2f(subject.getPosition().x + subject.getModel().getWidth()/2, subject.getPosition().y + subject.getModel().getHeight()/2);


            for (int j = i+1; j < enemies.size(); j++) {

                Enemy object = enemies.get(j);

                final float OBJECT_RADIUS_X = object.getModel().getWidth()/2;
                final float OBJECT_RADIUS_Y = object.getModel().getHeight()/2;
                final float MIN_DISTANCE_X = SUBJECT_RADIUS_X + OBJECT_RADIUS_X;
                final float MIN_DISTANCE_Y = SUBJECT_RADIUS_Y + OBJECT_RADIUS_Y;

                Vector2f centerObjectPosition = new Vector2f(object.getPosition().x + object.getModel().getWidth()/2, object.getPosition().y + object.getModel().getHeight()/2);

                Vector2f distance = new Vector2f(centerObjectPosition.x - centerSubjectPosition.x, centerObjectPosition.y - centerSubjectPosition.y);

                float xDepth = MIN_DISTANCE_X - Math.abs(distance.x);
                float yDepth = MIN_DISTANCE_Y - Math.abs(distance.y);

                if (xDepth > 0 && yDepth > 0) {

                    if (xDepth < yDepth) {
                        if (distance.x > 0) {
                            subject.increasePosition(-xDepth, 0);
                        } else {
                            subject.increasePosition(xDepth, 0);
                        }
                    } else {
                        if (distance.y > 0) {
                            subject.increasePosition(0, -yDepth);
                        } else {
                            if (distance.x < subject.getModel().getWidth()/2) {
                                subject.setInAir(false);
                                subject.getVelocity().y = 0;
                            }
                            subject.increasePosition(0, yDepth);
                        }

                    }



                }

            }
        }

    }


    public static void collideWithEnemies(Player subject, List<Enemy> sprites) {

        final float SUBJECT_RADIUS_X = subject.getModel().getWidth()/2;
        final float SUBJECT_RADIUS_Y = subject.getModel().getHeight()/2;

        Vector2f centerSubjectPosition = new Vector2f(subject.getPosition().x + subject.getModel().getWidth()/2, subject.getPosition().y + subject.getModel().getHeight()/2);

        for (Iterator<Enemy> iterator = sprites.listIterator(); iterator.hasNext();) {

            Enemy enemy = iterator.next();

            final float SPRITE_RADIUS_X = enemy.getModel().getWidth()/2;
            final float SPRITE_RADIUS_Y = enemy.getModel().getHeight()/2;
            final float MIN_DISTANCE_X = SUBJECT_RADIUS_X + SPRITE_RADIUS_X;
            final float MIN_DISTANCE_Y = SUBJECT_RADIUS_Y + SPRITE_RADIUS_Y;

            Vector2f centerSpritePosition = new Vector2f(enemy.getPosition().x + enemy.getModel().getWidth()/2, enemy.getPosition().y + enemy.getModel().getHeight()/2);

            Vector2f distance = new Vector2f(centerSpritePosition.x - centerSubjectPosition.x, centerSpritePosition.y - centerSubjectPosition.y);

            float xDepth = MIN_DISTANCE_X - Math.abs(distance.x);
            float yDepth = MIN_DISTANCE_Y - Math.abs(distance.y);





            if (xDepth > 0 && yDepth > 0) {

                if (distance.y < 0 && distance.x < enemy.getModel().getWidth()/2 && distance.x > -enemy.getModel().getWidth()/2) {

                    iterator.remove();

                    if (subject.getVelocity().y < 0) {
                        subject.getVelocity().y = -0.4f*subject.getVelocity().y;

                    } else {
                        subject.getVelocity().y += 50;
                    }

                }

                if (xDepth < yDepth) {
                    if (distance.x > 0) {
                        enemy.increasePosition(xDepth, 0);
                    } else {
                        enemy.increasePosition(-xDepth, 0);
                    }
                } else {
                    if (distance.y > 0) {
                        enemy.increasePosition(0, yDepth);
                    } else {
                        if (distance.x < subject.getModel().getWidth()/2) {
                            subject.setInAir(false);
                        }
                        enemy.increasePosition(0, -yDepth);
                    }

                }



            }


        }

    }

    public static void collideWithLevel2(Enemy enemy, char[][] levelData) {

        List<Vector2f> collideTilePosition = new ArrayList<Vector2f>(); // center of the tile

        // check four corners
        boolean bottomLeft = checkTilePosition(enemy.getPosition().x, enemy.getPosition().y + enemy.getModel().getWidth()/2, levelData, collideTilePosition);
        boolean bottomRight = checkTilePosition(enemy.getPosition().x + enemy.getModel().getWidth(), enemy.getPosition().y + enemy.getModel().getWidth()/2, levelData, collideTilePosition);


        if (bottomLeft || bottomRight) {

        }

//        boolean upperLeft = checkTilePosition(player.getPosition().x, player.getPosition().y + player.getModel().getHeight() - player.getModel().getWidth()/2, levelData, collideTilePosition);
//        boolean upperRight = checkTilePosition(player.getPosition().x + player.getModel().getWidth(), player.getPosition().y + player.getModel().getHeight() - player.getModel().getWidth()/2, levelData, collideTilePosition);


        // check top-bottom circle
        boolean bottomCenter = checkTilePosition(enemy.getPosition().x + enemy.getModel().getWidth()/2, enemy.getPosition().y, levelData, collideTilePosition);
        boolean topCenter = checkTilePosition(enemy.getPosition().x + enemy.getModel().getWidth()/2, enemy.getPosition().y + enemy.getModel().getHeight(), levelData, collideTilePosition);

        if (bottomCenter) {
            enemy.setInAir(false);
            enemy.getVelocity().y = 0;
        } else {
            enemy.setInAir(true);
        }

        if (topCenter) {
            enemy.getVelocity().y = -0.8f * enemy.getVelocity().y;
        }

        // check top-top-right, top-top-left
        boolean topTopLeft = checkTilePosition(enemy.getPosition().x, enemy.getPosition().y + enemy.getModel().getHeight(), levelData, collideTilePosition);
        boolean topTopRight = checkTilePosition(enemy.getPosition().x + enemy.getModel().getWidth(), enemy.getPosition().y + enemy.getModel().getHeight(), levelData, collideTilePosition);


        for (int i = 0; i < collideTilePosition.size(); i++) {
            collideWithTile(enemy, collideTilePosition.get(i));
        }


    }

    // AABB collision
    public static void collideWithLevel(Player player, char[][] levelData, ParticleBatch particleBatch) {

        List<Vector2f> collideTilePosition = new ArrayList<Vector2f>(); // center of the tile

        // check four corners
        boolean bottomLeft = checkTilePosition(player.getPosition().x, player.getPosition().y + player.getModel().getWidth()/2, levelData, collideTilePosition);
        boolean bottomRight = checkTilePosition(player.getPosition().x + player.getModel().getWidth(), player.getPosition().y + player.getModel().getWidth()/2, levelData, collideTilePosition);


        if (bottomLeft || bottomRight) {

        }

//        boolean upperLeft = checkTilePosition(player.getPosition().x, player.getPosition().y + player.getModel().getHeight() - player.getModel().getWidth()/2, levelData, collideTilePosition);
//        boolean upperRight = checkTilePosition(player.getPosition().x + player.getModel().getWidth(), player.getPosition().y + player.getModel().getHeight() - player.getModel().getWidth()/2, levelData, collideTilePosition);


        // check top-bottom circles
        boolean bottomCenter = checkTilePosition(player.getPosition().x + player.getModel().getWidth()/2, player.getPosition().y, levelData, collideTilePosition);
        boolean topCenter = checkTilePosition(player.getPosition().x + player.getModel().getWidth()/2, player.getPosition().y + player.getModel().getHeight(), levelData, collideTilePosition);

        if (bottomCenter) {
            player.setInAir(false);
            player.getVelocity().y = 0;
        } else {
            player.setInAir(true);
        }

        if (topCenter) {
            if (player.getVelocity().y > 0) {
                player.getVelocity().y = -0.8f*player.getVelocity().y;
            }
//            particleBatch.addParticle(new Vector2f(player.getPosition().x + Player.SIZE/2 - particleBatch.SIZE/2, player.getPosition().y + Player.SIZE/2), new Vector2f(0, 1), new Vector3f(0, 0, 0));
        }

        // check top-top-right, top-top-left
        boolean topTopLeft = checkTilePosition(player.getPosition().x, player.getPosition().y + player.getModel().getHeight(), levelData, collideTilePosition);
        boolean topTopRight = checkTilePosition(player.getPosition().x + player.getModel().getWidth(), player.getPosition().y + player.getModel().getHeight(), levelData, collideTilePosition);


        for (int i = 0; i < collideTilePosition.size(); i++) {
            collideWithTile(player, collideTilePosition.get(i));
        }



    }

    private static boolean checkTilePosition(float x, float y, char[][] levelData, List<Vector2f> collideTilePosition) {

        Vector2i cornerPos = new Vector2i((int)(x / Tile.SIZE), (int)(y / Tile.SIZE));


        char c = levelData[(levelData.length - 1 - cornerPos.y)][cornerPos.x];


        if (c != '.') {

            collideTilePosition.add(new Vector2f(cornerPos.x * Tile.SIZE + Tile.SIZE/2, cornerPos.y * Tile.SIZE + Tile.SIZE/2));
            return true;
        }



        return false;

    }


    private static void collideWithTile(Sprite sprite, Vector2f tilePosition) {

        final float PLAYER_RADIUS_X = sprite.getModel().getWidth()/2;
        final float PLAYER_RADIUS_Y = sprite.getModel().getHeight()/2;
        final float TILE_RADIUS_X = Tile.SIZE/2;
        final float TILE_RADIUS_Y = Tile.SIZE/2;
        final float MIN_DISTANCE_X = PLAYER_RADIUS_X + TILE_RADIUS_X;
        final float MIN_DISTANCE_Y = PLAYER_RADIUS_Y + TILE_RADIUS_Y;

        Vector2f centerPlayerPosition = new Vector2f(sprite.getPosition().x+PLAYER_RADIUS_X, sprite.getPosition().y+PLAYER_RADIUS_Y);
        Vector2f distance = new Vector2f(tilePosition.x - centerPlayerPosition.x, tilePosition.y - centerPlayerPosition.y);

        float xDepth = MIN_DISTANCE_X - Math.abs(distance.x);
        float yDepth = MIN_DISTANCE_Y - Math.abs(distance.y);


        if (xDepth > 0 && yDepth > 0) {

            if (xDepth < yDepth) {
                if (distance.x > 0) {
                    sprite.increasePosition(-xDepth, 0);
                } else {
                    sprite.increasePosition(xDepth, 0);
                }
            } else {
                if (distance.y > 0) {
                    sprite.increasePosition(0, -yDepth);
                } else {
                    sprite.increasePosition(0, yDepth);
                }

            }



        }


    }




}
