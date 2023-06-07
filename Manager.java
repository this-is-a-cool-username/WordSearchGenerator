import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager{// Manager class 
    private char [][] wordGrid; //array for puzzle
    private char [][] wordSolution;//array for solution
    private List<String> userWords;
    private int sizeX = 10;
    private int sizeY = 10;
    
    public void manager(){//constructor method initializes variables and objects
        this.wordSolution = new char[10][10];
        for(int i = 0; i < 10; i++){
            for(int j=0; j < 10; j++){
                wordSolution[i][j]='#';
            }
        } 
        this.wordGrid = new char[10][10];
        for(int i = 0; i < 10; i++){
            for(int j=0; j < 10; j++){
                wordGrid[i][j]='#';
            }
        } 
        userWords = new ArrayList<String>();
        printIntro();
    }

    public void printIntro(){// this method essentially has the menu for the program
        String select;

        do{
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to the word search generator!");
            System.out.println("This program allows you to create your own word puzzle");
            System.out.println("Please select one of the following options:");
            System.out.println("Press 'g' to generate a new word puzzle");
            System.out.println("Press 'c' to create a custom size word puzzle");
            System.out.println("Press 'p' to print out your word search");
            System.out.println("Press 's' to show the solution to your puzzle");
            System.out.println("Press 'q' to quit the program");
            select = input.next();
            select.toLowerCase();
            switch(select){
                case("c"):
                    createGrid(input);
                break;
                case("p"):
                    print();
                break;
                case("s"):
                    showSolution();
                break;
                case("g"):
                    generate(input);
                break;
                case("q"):
                    System.out.println("Goodbye");
                break;
                default:
                    System.out.println("Please enter a valid command");
                break;
            }
        }while(!select.equals("q"));
    }

    public void print(){//prints the puzzle out once it is created
        
        for(int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++)
                System.out.print(wordGrid[i][j] + " ");
            System.out.println();
        }
    }

    public void createGrid(Scanner input){//this method creates a custom size puzzle to the users request
        System.out.println("Type a height and width for your new puzzle");
        System.out.println("Default size is 10x10");
        System.out.println("Height: ");
        sizeY = input.nextInt();
        System.out.println("Width: ");
        sizeX = input.nextInt();
        wordGrid = new char[sizeY][sizeX];
        for(int i = 0; i < sizeY; i++){
            for(int j=0; j < sizeX; j++){
                wordGrid[i][j]='#';
            }
        } 
        wordSolution = new char[sizeY][sizeX];
        for(int i = 0; i < sizeY; i++){
            for(int j=0; j < sizeX; j++){
                wordSolution[i][j]='#';
            }
        } 
        
    }

    public void generate(Scanner input){//this method creates the puzzle. 
        Random r = new Random();
        getWords(input);
        try{// try catch statement to notify the user if a word does not fit
        for(String x : userWords){
            int maxLimit = 0;
            int counter = 0;
            while(maxLimit < 100 && counter != x.length()){ // limit set for 100 tries to try to get a word to fit in the puzzle
                maxLimit ++;
                int numY = r.nextInt(sizeY);
                int numX = r.nextInt(sizeX);
                int assign = r.nextInt(6);
                counter = 0;
                if(assign == 0){//horizontal placement
                    for(int i = 0; i < x.length() && numX + i < sizeX; i++){//checks if word will fit
                        if(wordGrid[numY][numX + i] == '#')
                            counter++; 
                    }
                    if(counter == x.length()){
                        for(int i = 0; i < x.length(); i++)//adds word to puzzle
                            wordGrid[numY][numX + i] = x.charAt(i);
                    }
               

                }else if(assign == 1){//vertical placement
                    for(int i = 0; i < x.length() && numY + i < sizeY; i++){//checks if word will fit vertically
                        if(wordGrid[numY + i][numX] == '#')
                            counter++;
                    }
                    if(counter == x.length()){
                        for(int i = 0; i < x.length(); i++)//adds word to puzzle
                            wordGrid[numY + i][numX] = x.charAt(i);
                    }                    
                }else if(assign == 2){//diagonal placement
                    for(int i = 0; i < x.length() && (numY + i < sizeY && numX + i <sizeX); i++){//checks if word will fit diagonally
                        if(wordGrid[numY + i][numX + i] == '#')
                            counter++;
                    }
                    if(counter == x.length()){
                        for(int i = 0; i < x.length(); i++)//adds word to puzzle
                            wordGrid[numY + i][numX+i] = x.charAt(i);
                    }                   
                }else if(assign == 3){//reverse horizontal placement
                    for(int i = 0; i < x.length() && numX - i < sizeX; i++){//checks if word will fit
                        if(wordGrid[numY][numX - i] == '#')
                            counter++; 
                    }
                    if(counter == x.length()){
                        for(int i = 0; i < x.length(); i++)//adds word to puzzle
                            wordGrid[numY][numX - i] = x.charAt(i);
                    }
                }else if(assign == 4){//reverse vertical placement
                    for(int i = 0; i < x.length() && numY - i < sizeY; i++){//checks if word will fit vertically
                        if(wordGrid[numY - i][numX] == '#')
                            counter++;
                    }
                    if(counter == x.length()){
                        for(int i = 0; i < x.length(); i++)//adds word to puzzle
                            wordGrid[numY - i][numX] = x.charAt(i);
                    }                    
                }else{
                    for(int i = 0; i < x.length() && (numY - i < sizeY && numX - i <sizeX); i++){
                        if(wordGrid[numY - i][numX - i] == '#')
                            counter++;
                    }
                    if(counter == x.length()){
                        for(int i = 0; i < x.length(); i++)//adds word to puzzle
                            wordGrid[numY - i][numX - i] = x.charAt(i);
                    }
                }
                
            }//end of while loop
        }
        }catch(Exception e){
            System.out.println();
            System.out.println("words could not be added, try resizing the puzzle by pressing 'c'");
            System.out.println();
        }
        for (int i = 0; i < wordGrid.length; ++i) {//saves solution puzzle before adding random letters
            wordSolution[i] = new char[wordSolution[i].length];
            for (int j = 0; j < wordGrid[i].length; ++j) {
               wordSolution[i][j] = wordGrid[i][j];
            }
        }
      
        Random text = new Random();
        
        for(int i = 0; i < sizeY; i++){// for loop adds random letters to puzzle
            for(int j=0; j < sizeX; j++){
                if(wordGrid[i][j] == '#'){
                char c = (char)(text.nextInt(26) + 'A');
                wordGrid[i][j]= c;
                }
            }
        }         
      
    }//end of generate method
    private void getWords(Scanner input){//adds user input words to array
        userWords.clear();
        for(int i = 0; i < sizeY; i++){
            for(int j=0; j < sizeX; j++){
                wordGrid[i][j]='#';
            }
        } 
        System.out.println("How many words would you like to add to the puzzle?");
        int wordCount = input.nextInt();

        for(int i = 0; i < wordCount; i++){
            if(i == 0)
                System.out.println("Type a word: ");
            else
                System.out.println("Type another word");
            
            String word = input.next().toUpperCase();
            userWords.add(word);
        }      

    }
    public void showSolution(){//prints wordSolution array
        for(int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++)
                System.out.print(wordSolution[i][j] + " ");
            System.out.println();
        }
    }
}// end of class