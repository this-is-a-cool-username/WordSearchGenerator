import java.util.Arrays;
import java.util.Random;

public class Object{

    private static char [][] wordGrid;
    
    public static void Object(){//constructor class initializes word puzzle array
        
        wordGrid = new char[10][10];

    }

    public void getPrint(){

        for(int i = 0; i < 10; i++){
            for(int j=0; j < 10; j++){
            Random text = new Random();
            char c = (char)(text.nextInt(26) + 'A');
                wordGrid[i][j] = c;
            }  
        }
        for(char[] row : wordGrid)
        System.out.println(Arrays.toString(row)); 
                
                 
    } 

    public void getCreate(int height, int width){

        wordGrid = new char[height][width];
        for(int i = 0; i < height; i++){
            for(int j=0; j < height; j++){
            Random text = new Random();
            char c = (char)(text.nextInt(26) + 'A');
                wordGrid[i][j] = c;
            }
        }
        for(char[] row : wordGrid)
           System.out.println(Arrays.toString(row));
    }

    public void setGenerate(String word1, String word2, String word3, String word4, String word5){


        
    }
}