
/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    Square[] squares; //start from index 1 (NOT 0)
    Word[] words;
    int wordPos;
    String wordList;
    
    public Board(){
        this.wordPos = 0;
        this.squares = new Square[226];
        this.words = new Word[1000000];
        int assign = 1;
        for(int row = 1; row < 22; row++){
            for(int column = 1; column < 22; column++){
                this.squares[assign] = new Square(column, row, 1, 1);
                //add code to check for double and triple words and letters - develop separate mthods
            }
        }
    }

    public Board clone(){
        Board forReturn = new Board();
        Square[] squares = new Square[this.squares.length];
        Word[] words = new Word[this.words.length];
        int iter;
        for(iter = 0; iter < squares.length; iter++){
            squares[iter] = this.squares[iter].clone();
        }
        forReturn.squares = squares;
        for(iter = 0; iter < words.length; iter++){
            words[iter] = this.words[iter].clone();
        }
        forReturn.words = words;
        forReturn.wordPos = this.wordPos;
        forReturn.wordList = this.wordList;
        return forReturn;
    }
    
    public Square getSquare(int x, int y){
        return this.squares[15 * (y - 1) + x];
    }
    
    public static int getPos(int x, int y){
        return 15 * (y - 1) + x;
    }
    
    public Square[] getSquares(int startX, int startY, int xMod, int yMod){
        Square[] forReturn = new Square[15];
        for(int iter = 0; iter < 15; iter++){
            forReturn[iter] = this.getSquare(startX + iter * xMod, startY + iter * yMod);
        }
        return forReturn;
    }

    public void print(){
        for(int iter = 1; iter < this.squares.length; iter++){
            if(this.squares[iter]!= null){
                this.squares[iter].print();
            }
            else{
                System.out.print("    ");
            }
            if(iter % 15 == 0){
                System.out.println();
            }
        }
    }
}
