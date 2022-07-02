
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    Tile[] tiles;
    String name;
    int pos;
    int score;
    Instance instance;

    public Player(String name, int pos, Instance instance){
        this.name = name;
        this.pos = pos;
        this.instance = instance;
        int[] random = getRandomArray(0, this.instance.bag.tiles.length - 1, 7);
        this.tiles = new Tile[7];
        for(int iter = 0; iter < random.length; iter++){
            this.tiles[iter] = this.instance.bag.tiles[random[iter]].cloneTile();
            instance.bag.tiles[random[iter]] = null;
        }
        instance.bag.crop();
    }

    public void fillTiles(){
        for(int iter = 0; iter < this.tiles.length; iter++){
            if(this.tiles[iter] == null){
                int random = random(0, this.instance.bag.tiles.length - 1);
                this.tiles[iter] = this.instance.bag.tiles[random].cloneTile();
                this.instance.bag.tiles[random] = null;
                this.instance.bag.crop();
            }
        }
    }

    public static int[] getRandomArray(int start, int end, int size){
        int[] forReturn = new int[size];
        int random;
        for(int iter = 0; iter < size; iter++){
            random = random(start, end);
            for(int count = 0; count < 100; count++){
                if(contains(forReturn, random)){
                    random = random(start, end);
                }
                else{
                    break;
                }
            }
            forReturn[iter] = random;
        }
        return forReturn;
    }
    
    public static boolean contains(int[] reference, int check){
        for(int iter = 0; iter < reference.length; iter++){
            if(reference[iter] == check){
                return true;
            }
        }
        return false;
    }

    public static int random(int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }
}
