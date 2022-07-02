
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile
{
    String name;
    int value;
    boolean isBlank;
    
    public Tile(String name, boolean isBlank){
        this.name = name;
        this.isBlank = isBlank;
        if(isBlank == false){
            this.assignScore();
        }
        else{
            this.value = 0;
        }
    }
    
    public void assignScore(){
        String[] alphabets = getAlphabets();
        int[] scores = getScores();
        for(int iter = 0; iter < alphabets.length; iter++){
            if(this.name.equals(alphabets[iter])){
                this.value = scores[iter];
            }
        }
    }
    
    public String getString(){
        if(this == null){
            return "   ";
        }
        else{
            return this.name + this.value;
        }
    }
    
    public static int[] getScores(){        
        int[] forReturn = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        return forReturn;
    }
    
    public static String[] getAlphabets(){
        String[] forReturn = new String[] {"A", "B" , "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return forReturn;
    }
    
    public static boolean equals(Tile tile1, Tile tile2)throws java.lang.NullPointerException{
        if(tile1 == null){
            if(tile2 == null){
                return true;
            }
            else{
                return false;
            }
        }else{
            return tile1.name.equals(tile2.name) && tile1.value == tile2.value && tile1.isBlank == tile2.isBlank;
        }
        
    }

    public Tile cloneTile(){
        return new Tile(this.name, this.isBlank);
    }
}
