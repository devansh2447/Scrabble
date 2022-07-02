
/**
 * Write a description of class Bag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bag
{
    Tile[] tiles;
    int numberOfTiles;

    public Bag(){
        //initialize tiles
        this.tiles = new Tile[100];
        int[] distribution = getDistribution();
        int num = 0;
        int count = 0;
        for(int iter = 0; iter < distribution.length; iter++){
            num = distribution[iter];
            for(int iter2 = 0; iter2 < num; iter2++){
                String letter = getLetter(iter);
                if(!letter.equals("_")){
                    this.tiles[count] = new Tile(getLetter(iter), false);
                    count++;
                }
                else{
                    this.tiles[count] = new Tile("", true);
                    count++;
                }
            }
        }
    }

    public static String getLetter(int num){
        String reference = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
        return reference.charAt(num) + "";
    }

    public void crop(){
        Tile[] tiles = new Tile[100 - countNull(this.tiles)];
        int firstNotFilled = 0;
        for(int iter = 0; iter < this.tiles.length; iter++){
            if(this.tiles[iter] != null){
                tiles[firstNotFilled] = this.tiles[iter];
                firstNotFilled++;
            }
        }
        this.tiles = tiles;
    }

    public static int countNull(Tile[] tiles){
        int count = 0;
        for(int iter = 0; iter < tiles.length; iter++){
            if(tiles[iter] == null){
                count++;
            }
        }
        return count;
    }

    public static int count(Tile[] tiles, Tile check){
        int count = 0;
        for(int iter = 0; iter < tiles.length; iter++){
            if(Tile.equals(tiles[iter], check)){
                count++;
            }
        }
        return count;
    }

    public static int[] getDistribution(){
        return new int[] {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1, 2};
    }
}
