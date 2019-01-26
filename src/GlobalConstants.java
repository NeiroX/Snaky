package Snaky;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GlobalConstants {

    public static final int SCREEN_W = 800;
    public static final int SCREEN_H = 800;
    public static int recordNow = 0;
    public static final int SNAKE_W = 30;
    public static final int SNAKE_SPACE = 5;
    public static int SNAKE_SPEED = 5;
    public static int SNAKE_SPEED_INCREASE = 1;
    public static int recordMax = 100; // getRecord();
    public static final int APPLE_SIZE = 40;


    /*private getRecord(){
        File file = new File("Objects/Records.txt");
        if (file.length() > 0){
            FileReader reader = new FileReader(file);
            return Integer(reader.readline());
        }
        return 0;
    }*/

}
