
/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    Square[] squares; //start from index 1 (NOT 0)
    String wordList;
    
    public Board(){
        this.squares = new Square[226];
        int assign = 1;
        for(int row = 1; row < 16; row++){
            for(int column = 1; column < 16; column++){
                this.squares[assign] = new Square(column, row, 1, 1);
                //add code to check for double and triple words and letters - develop separate methods
                //use AssignValues class
                assign++;
            }
        }
    }
    
    public void getDoubleLetter(){
        
    }

    public boolean add(int x, int y, String name){
        if(this.squares[getPos(x, y)].tile == null){
            this.squares[getPos(x, y)].tile = new Tile(name, !name.equals("_"));
            return true;
        }
        else{
            return false;
        }
    }

    public Board clone(){
        Board forReturn = new Board();
        Square[] squares = new Square[this.squares.length];
        int iter;
        for(iter = 1; iter < squares.length; iter++){
            squares[iter] = this.squares[iter].clone();
        }
        forReturn.squares = squares;
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
        System.out.println("  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15 ");
        int rowNum = 1;
        for(int iter = 1; iter < this.squares.length; iter++){
            if((iter - 1) % 15 == 0){
                System.out.print(rowNum + " ");
                rowNum++;
            }
            if(this.squares[iter] != null){
                this.squares[iter].print();
            }
            else{
                System.out.print("    ");
            }
            if(iter % 15 == 0){
                System.out.println();
                System.out.println();
            }
        }
    }
}
