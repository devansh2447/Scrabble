
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
                this.squares[assign] = new Square(column, row, getLetterMultiply(column, row), getWordMultiply(column, row));
                assign++;
            }
        }
    }
    
    public static int getLetterMultiply(int column, int row){
        String doubleSquares = getDoubleLetter();
        String tripleSquares = getTripleLetter();
        String check = column + "," + row;
        if(doubleSquares.contains(check)){
            return 2;
        }
        if(tripleSquares.contains(check)){
            return 3;
        }
        return 1;
    }

    public static int getWordMultiply(int column, int row){
        String tripleSquares = getTripleWord();
        String check = column + "," + row;
        if(getDoubleWord(column, row)){
            return 2;
        }
        if(tripleSquares.contains(check)){
            return 3;
        }
        return 1;
    }

    public static String getDoubleLetter(){
        return "4,1,,12,1,,7,3,,9,3,,1,4,,8,4,,15,4,,3,7,,7,7,,9,7,,13,7,,4,8,,12,8,,3,9,,7,9,,9,9,,13,9,,1,12,,8,12,,15,12,,7,13,,9,13,,4,15,,12,15";
    }

    public static String getTripleLetter(){
        return "6,2,,10,2,,2,6,,6,6,,10,6,,14,6,,2,10,,6,10,,10,10,,14,10,,6,14,,10,14";
    }

    public static boolean getDoubleWord(int column, int row){
        if(column == row || 15 - column + 1 == row){
            if(column == 8){
                return true;
            }
            if(column > 1 && column < 6){
                return true;
            }
            else if(column > 11 && column < 15){
                return true;
            }
            return false;
        }
        return false;
    }

    public static String getTripleWord(){
        return "1,1,,8,1,,15,1,,1,8,,15,8,,1,15,,8,15,,15,15";
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
