
/**
 * Write a description of class Word here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Word
{
    // wordlist from here: https://drive.google.com/file/d/1oGDf1wjWp5RF_X9C7HoedhIWMh5uJs8s/view
    String word;
    int score;
    //add player
    
    public Word(){

    }

    public Word(Square start, Square end, Board board){
        String word = "";
        int score = 0;
        Square square;
        int letterMultiply;
        int highestWordMultiply = 1;
        if(start.x == end.x){
            for(int iter = start.y; iter > end.y - 1; iter--){
                square = board.getSquare(start.x, iter);
                word = word + square.tile.name;
                letterMultiply = square.letterMultiply;
                score = score + (square.tile.value * letterMultiply);
                int wordMultiply = square.wordMultiply;
                if(wordMultiply > highestWordMultiply){
                    highestWordMultiply = wordMultiply;
                }
            }
        }
        else if(start.y == end.y){
            for(int iter = start.x; iter < end.x + 1; iter++){
                square = board.getSquare(iter, start.y);
                word = word + square.tile.name;
                letterMultiply = square.letterMultiply;
                score = score + (square.tile.value * letterMultiply);
                int wordMultiply = square.wordMultiply;
                if(wordMultiply > highestWordMultiply){
                    highestWordMultiply = wordMultiply;
                }
            }
        }
        this.word = word;
        this.score = score * highestWordMultiply;
    }

    public Word clone(){
        Word forReturn = new Word();
        forReturn.word = this.word;
        forReturn.score = this.score;
        return forReturn;        
    }

    public static int getLarger(int int1, int int2){
        if(int1 > int2){
            return int1;
        }
        else{
            return int2;
        }
    }
    
    public static Word getWordHorizontal(Square square, Board board){
        Square[] horizontal = board.getSquares(1, square.y, 1, 0);
        int hPos = square.x - 1;
        String word = getWordFromArray(horizontal, hPos);
        if(isWord(word, board.wordList)){
            return new Word(square, horizontal[hPos + word.length() - 1], board);
        }
        return null;
    }

    public static Word getWordVertical(Square square, Board board){
        Square[] vertical = board.getSquares(square.x, 1, 0, 1);
        int vPos = square.y - 1;
        String word = getWordFromArray(vertical, vPos);
        if(isWord(word, board.wordList)){
            return new Word(square, vertical[vPos + word.length() - 1], board);
        }
        return null;
    }

    public static boolean isWord(String check, String reference){
        return reference.contains("\n" + check + "\r");
    }

    public static String getWordFromArray(Square[] squares, int include){
        String forReturn = "";
        for(int iter = 0; iter < squares.length; iter++){
            if(squares[iter] != null && squares[iter].tile != null){
                forReturn = forReturn + squares[iter].tile.name;
            }
            else{
                if(iter >= include){
                    break;
                }
                else{
                    forReturn = "";
                }
            }
        }
        return forReturn;
    }
    
}
