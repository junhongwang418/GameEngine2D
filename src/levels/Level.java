package levels;

import gameItems.Pipe;
import gameItems.Tile;
import org.joml.Vector2f;
import sprites.Sprite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by one on 9/17/16.
 */
public class Level {

    private char[][] levelData;

    public Level(String fileName) {

        List<String> data = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader("res/"+fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }


            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        levelData = new char[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            char[] charArray = data.get(i).toCharArray();
            levelData[i] = new char[charArray.length];
            levelData[i] = charArray;
        }

    }

    public void init(List<Sprite> sprites) throws Exception {
        for (int y = 0; y < levelData.length; y++) {
            for (int x = 0; x < levelData[y].length; x++) {
                switch (levelData[y][x]) {
                    case '#':
                        System.out.print('#');
                        Tile tile = new Tile(new Vector2f(x*Tile.SIZE, (levelData.length - 1 - y)*Tile.SIZE), 24);
                        tile.init();
                        sprites.add(tile);
                        break;
                    case '.':
                        System.out.print('.');
//                        Tile tile2 = new Tile(new Vector2f(x*Tile.SIZE, y*Tile.SIZE), 0);
//                        tile2.init();
//                        sprites.add(tile2);
                        break;
                    case 'W':
                        System.out.print('W');
                        Tile tile3 = new Tile(new Vector2f(x*Tile.SIZE, (levelData.length - 1 - y)*Tile.SIZE), 2);
                        tile3.init();
                        sprites.add(tile3);
                        break;
                    case 'G':
                        System.out.print('G');
                        Tile tile4 = new Tile(new Vector2f(x*Tile.SIZE, (levelData.length - 1 - y)*Tile.SIZE),5);
                        tile4.init();
                        sprites.add(tile4);
                        break;
                    case 'B':
                        System.out.print('B');
                        Tile tile5 = new Tile(new Vector2f(x*Tile.SIZE, (levelData.length - 1 - y)*Tile.SIZE), 31);
                        tile5.init();
                        sprites.add(tile5);
                        break;
                    case 'A':
                        System.out.print('A');
                        Tile tile6 = new Tile(new Vector2f(x*Tile.SIZE, (levelData.length - 1 - y)*Tile.SIZE), 34);
                        tile6.init();
                        sprites.add(tile6);
                        break;
                    case '-':
                        System.out.print('-');
                        Tile tile7 = new Tile(new Vector2f(x*Tile.SIZE, (levelData.length - 1 - y)*Tile.SIZE), 33);
                        tile7.init();
                        sprites.add(tile7);
                        break;
                    case 'P':
                        System.out.print('P');
                        Pipe pipe = new Pipe(new Vector2f(x*Pipe.SIZE, (levelData.length - 1 - y)*Pipe.SIZE), 0);
                        pipe.init();
                        sprites.add(pipe);
                        break;
                    case '|':
                        System.out.print('|');
                        Pipe pipe1 = new Pipe(new Vector2f(x*Pipe.SIZE, (levelData.length - 1 - y)*Pipe.SIZE), 11);
                        pipe1.init();
                        sprites.add(pipe1);
                        break;
                    case 'C':

                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }

    public char[][] getLevelData() {
        return levelData;
    }

}
