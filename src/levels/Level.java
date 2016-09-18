package levels;

import org.joml.Vector2f;
import sprites.Model;
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

    private List<String> levelData = new ArrayList<String>();

    public Level(String fileName) {
        try {
            FileReader fileReader = new FileReader("res/"+fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                levelData.add(line);
            }


            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(List<Sprite> sprites, Model model) {
        for (int y = 0; y < levelData.size(); y++) {
            char[] charData = levelData.get(y).toCharArray();
            for (int x = 0; x < charData.length; x++) {
                switch (charData[x]) {
                    case '#':
                        System.out.print('#');
                        Sprite sprite0 = new Sprite(new Vector2f(x*50, y*50), 0);
                        sprite0.init(model);
                        sprites.add(sprite0);
                        break;
                    case '.':
                        System.out.print('.');
                        Sprite sprite1 = new Sprite(new Vector2f(x*50, y*50), 1);
                        sprite1.init(model);
                        sprites.add(sprite1);
                        break;
                    case 'W':
                        System.out.print('W');
                        Sprite sprite3 = new Sprite(new Vector2f(x*50, y*50), 3);
                        sprite3.init(model);
                        sprites.add(sprite3);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }

}
