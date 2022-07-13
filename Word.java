
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
    
    //redevelop- do not use Word object

    public static int getLarger(int int1, int int2){
        if(int1 > int2){
            return int1;
        }
        else{
            return int2;
        }
    }
    
    public static boolean checkHorizontal(Square square, Board board){
        Square[] horizontal = board.getSquares(1, square.y, 1, 0);
        int hPos = square.x - 1;
        String word = getWordFromArray(horizontal, hPos);
        if(isWord(word, board.wordList)){
            return true;
        }
        return false;
    }

    public static boolean checkVertical(Square square, Board board){
        Square[] vertical = board.getSquares(square.x, 1, 0, 1);
        int vPos = square.y - 1;
        String word = getWordFromArray(vertical, vPos);
        if(isWord(word, board.wordList)){
            return true;
        }
        return false;
    }

    public static boolean isValid(Square square, Board board){
        return checkHorizontal(square, board) || checkVertical(square, board);
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

    public static boolean checkIfContains(String check, String reference){
        int pos = 0;
        String word = "";
        boolean isStringComplete = false;
        for(pos = 0; pos < reference.length(); pos++){
            if(reference.charAt(pos) != '\n' && reference.charAt(pos) != '\r'){
                word = word + reference.charAt(pos);
            }
            else{
                if(isStringComplete){
                    //add code to check if word can be inserted
                }
            }
        }
        return false;
    }
    
}
