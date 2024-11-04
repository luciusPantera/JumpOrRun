package JumpOrRun.Utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import JumpOrRun.Main.*;

public class Load {
    public static BufferedImage GetImage(String src){
        BufferedImage img = null;
        File is = new File("JumpOrRun/Resources/" + src);
        try{
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public static int[][][] getLevel(int nr) throws IOException{
        //String file = readFile("../Level/" + "level1.leveldata");
        
        BufferedReader buffer = new BufferedReader(new FileReader("JumpOrRun/Levels/" + "level" + nr + ".leveldata"));
        String zeile1 = buffer.readLine();
        String[] dimensions = zeile1.split(" ");
        int height = Integer.valueOf(dimensions[0]);
        int width = Integer.valueOf(dimensions[1]);
        int[][][] data = new int[3][height][width];
        buffer.readLine();
        for(int j = 0; j < 3; j++){
            for(int i = 0; i < height; i++){
                String row = buffer.readLine();
                String[] rowValues = row.split(" ");
                for (int index = 0; index < rowValues.length; index++) {
                    data[j][i][index] = Integer.valueOf(rowValues[index]);
                }
            }
            buffer.readLine();
        }
        buffer.close();
        return data;
    }

    public static String readFile(String source) throws IOException{
        BufferedReader buffer = new BufferedReader(new FileReader(source));
        String file = new String();
        String zeile = buffer.readLine();
        while (zeile != null) {
            file += zeile;
            zeile = buffer.readLine();
        }
        return file;
    }

    public static int[] getPlayerdata(){
        String data = "";
        try {
            data = readFile("JumpOrRun/Utils/data.playerdata");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] levelData = new int[1];
        String values = data.split(": ")[1];
        levelData[0] = Integer.valueOf(values);
        return levelData;
    }

    public static void saveData(int levelNr){
        String data = "Level: " + levelNr;
        try {
            FileWriter myWriter = new FileWriter("JumpOrRun/Utils/data.playerdata");
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully saved to file.");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}