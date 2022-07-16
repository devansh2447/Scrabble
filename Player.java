
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

    public Player(){
        
    }

    public Player(String name, int pos, Instance instance){
        this.name = name;
        this.pos = pos;
        this.instance = instance;
        int[] random = getRandomArray(0, this.instance.bag.tiles.length - 1, 7);
        this.tiles = new Tile[7];
        for(int iter = 0; iter < random.length; iter++){
            this.tiles[iter] = this.instance.bag.tiles[random[iter]].clone();
            instance.bag.tiles[random[iter]] = null;
        }
        instance.bag.crop();
    }

    public boolean hasTile(String name){
        for(int iter = 0; iter < this.tiles.length; iter++){
            if(this.tiles[iter].name.equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasTileBlank(String name){
        for(int iter = 0; iter < this.tiles.length; iter++){
            if(this.tiles[iter].name.equals(name)){
                return true;
            }
            else if(this.tiles[iter].isBlank == true){
                this.tiles[iter].name = name;
                return true;
            }
        }
        return false;
    }

    public void removeTile(String name){
        for(int iter = 0; iter < this.tiles.length; iter++){
            if(this.tiles[iter].name.equals(name)){
                int score = this.score + this.tiles[iter].value;
                this.score = score;
                this.tiles[iter] = null;
            }
        }
    }

    public void fillTiles(){
        for(int iter = 0; iter < this.tiles.length; iter++){
            if(this.tiles[iter] == null && this.instance.bag.tiles.length > 0){
                int random = random(0, this.instance.bag.tiles.length - 1);
                this.tiles[iter] = this.instance.bag.tiles[random].clone();
                this.instance.bag.tiles[random] = null;
                this.instance.bag.crop();
            }
        }
    }

    public Player clone(){
        Player forReturn = new Player();
        Tile[] tiles = new Tile[this.tiles.length];
        for(int iter = 0; iter < tiles.length; iter++){
            tiles[iter] = this.tiles[iter].clone();
        }
        forReturn.tiles = tiles;
        forReturn.name = this.name;
        forReturn.pos = this.pos;
        forReturn.score = this.score;
        forReturn.instance = this.instance;
        return forReturn;
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
    
    public void printTiles(){
        for(int iter = 0; iter < this.tiles.length; iter++){
            System.out.print(this.tiles[iter].name);
        }
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
