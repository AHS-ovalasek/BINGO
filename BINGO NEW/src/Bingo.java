
/**
 ******************************************************************************
 *                        BINGO
 ******************************************************************************
 *
 * The standard bingo game.
 *
 *
 *
 *
 * Name: Owen Valasek
 *
 * Date:6/4/19
 *
 *
 ***************************************************************************** */
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Bingo {

    private Random rand = new Random();
    private int[][] card;       //Bingo card configuration
    private int[] stream;       //list of 75 integers
    private boolean[][] marks;  //simulates placing chips on a Bingo card

    public Bingo() {
        card = new int[5][5];
        stream = new int[75];
        marks = new boolean[5][5];
    }

    /**
     * This method writes a random Bingo card configuration and a stream of
     * random number between 1 and 75 to the output file.
     *
     * The first column in the table contains only integers between 1 and 15,
     * the second column numbers are all between 16 and 30, the third are 31 to
     * 45, the fourth 46-60, and the fifth 61-75.
     *
     * There are no duplicate numbers on a Bingo card.
     */
    public void write(String outputFile) throws IOException {
        //creates a writer file
        FileWriter fwriter = new FileWriter(outputFile);
        PrintWriter out = new PrintWriter(fwriter);
        //creates an array list
        ArrayList<Integer> list = new ArrayList<>();
        //goes through for loops that add to an arraylist
        //goes through if loops that add random numbers from num - num 
        for (int y = 0; y < card.length; y++) {
            for (int x = 0; x < card[0].length; x++) {
                if (x < 1) {
                    card[y][x] = rand.nextInt(15) + 1;
                } else if (x < 2) {
                    card[y][x] = rand.nextInt(30 - 16) + 16;
                } else if (x < 3) {
                    card[y][x] = rand.nextInt(45 - 31) + 31;  
                } else if (x < 4) {
                    card[y][x] = rand.nextInt(60 - 46) + 46;
                } else if (x < 5) {
                    card[y][x] = rand.nextInt(75 - 61) + 61;
                }
                card[2][2] = 0;

                
                }
            }
        //shuffles a list of numbers
        this.shuffle(list);
        System.out.println("");
        //adds card to the file - input.txt
        for (int x = 0; x < card.length; x++) {
            for (int y = 0; y < card[0].length; y++) {
                System.out.print(card[x][y] + "\t");
                out.print(card[x][y] + "\t");
            }
            out.println();
            System.out.println("");
        }

        for (int x = 0; x < stream.length; x++) {
            out.print(list.get(x) + " ");
        }
        out.close();
}
    

    /**
     * Shuffles the list of numbers
     */
    public void shuffle(ArrayList<Integer> list) {
        //creates a method that shuffles a list of numbers using a for loop
        for (int x = 0; x < stream.length; x++) {
            int w = rand.nextInt(74) + 1;
            list.add(w);
            // System.out.print(w + " ");
        }
    }

    /**
     * This method reads a given inputFile that contains a Bingo card
     * configuration and a stream of numbers between 1 and 75. . A Bingo card
     * configuration is stored in the card array. A list of 75 integers is
     * stored in the stream array.
     */
    public void read(String inputFile) throws IOException {
        ArrayList<String> position = new ArrayList<String>();
        //creates a reader file
        FileReader file = new FileReader(inputFile);
        Scanner read;
        read = new Scanner(new File(inputFile));  
        //adds card objects to an array list
        while (read.hasNext()) {
            String spot1 = read.next();
            position.add(spot1);
        }
        file.close();
        
        String[] newpos = position.toArray(new String[0]);
        
        int pos = 0; 
        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 5; y++){
                card[y][x] = Integer.parseInt(newpos[pos]);
                pos++;
            }
        }
        
        for(int x = 0; x < stream.length; x++){
            stream[x] = Integer.parseInt(newpos[pos]);
            pos++;
        }
        
    }


    /**
     * This method returns the first integer from the stream array that gives
     * you the earliest winning condition.
     *
     * - all the spots in a column are marked - all the spots in a row are
     * marked - all the spots in either of the two diagonals are marked - all
     * four corner squares are marked
     */
    public int playGame() {
        int t = 0;
            //loops through a for loop which increments the stream array
        for (t = 0; t < stream.length; t++) {
            //loops through to make all numbers called equal to 0
            for (int x = 0; x < card.length; x++) {
                for (int y = 0; y < card[0].length; y++) {
                    if (card[x][y] == stream[t]) {
                        card[x][y] = 0;

                    }

                }
               
            }
              //checks if any wins in 4 corners
             if(card[0][0] == 0 && card[0][4] == 0 && card[4][0] == 0 && 
                    card[4][4] == 0){
                return stream[t];
                //check for any wins diagonally
            }else if(card[0][0] == 0 && card[1][1]== 0 && card[2][2]==0 && 
                    card[3][3]==0 && card[4][4] ==0){
                return stream[t];
            }else if(card[4][0]==0 && card[3][1]==0 && card[2][2]==0 && 
                    card[1][3]==0 && card[0][4]==0){
                return stream[t];
                //checks for any wins across rows or down columns
            }else if(card[0][0]==0 && card[1][0]==0 && card[2][0]==0 && 
                    card[3][0]==0 && card[4][0] ==0){
                return stream[t];
            }else if(card[0][0]==0 && card[0][1]==0 && card[0][2]==0 && 
                    card[0][3]==0 && card[0][4]==0){
                return stream[t];
            }else if(card[0][1]==0 && card[1][1]==0 && card[2][1]==0 && 
                    card[3][1]==0 && card[4][1]==0){
                return stream[t];
            }else if(card[0][2]==0 && card[1][2]==0 && card[2][2]==0 && 
                    card[3][2]==0 && card[4][2]==0){
                return stream[t];
            }else if(card[0][3]==0 && card[1][3]==0 && card[2][3]==0 && 
                    card[3][3]==0 && card[4][3]==0){
                return stream[t];
            }else if(card[0][4]==0 && card[1][4]==0 && card[2][4]==0 && 
                    card[3][4]==0 && card[4][4]==0){
                return stream[t];
            }else if(card[1][0]==0 && card[1][1]==0 && card[1][2]==0 && 
                    card[1][3]==0 && card[1][4]==0){
                return stream[t];
            }else if(card[2][0]==0 && card[2][1]==0 && card[2][2]==0 && 
                    card[2][3]==0 && card[2][4]==0){
                return stream[t];
            }else if(card[3][0]==0 && card[3][1]==0 && card[3][2]==0 && 
                    card[3][3]==0 && card[3][4]==0){
                return stream[t];
            }else if(card[4][0]==0 && card[4][1]==0 && card[4][2]==0 && 
                    card[4][3]==0 && card[4][4]==0){
                return stream[t];
            }
        }   
             
         
     
     
        
            
            return stream[t];
    }
}  

