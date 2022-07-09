
/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Square
{
    Tile tile;
    int letterMultiply;
    int wordMultiply;
    int x;
    int y;
    //coordinates start from the top left

    public Square(int x, int y, int letterMultiply, int wordMultiply){
        this.x = x;
        this.y = y;
        this.letterMultiply = letterMultiply;
        this.wordMultiply = wordMultiply;
    }

    public Square clone(){
        Square forReturn = new Square(this.x, this.y, this.letterMultiply, this.wordMultiply);
        if(this.tile != null){
            forReturn.tile = this.tile.clone();
        }        
        return forReturn;
    }

    public void print(){
        if(this != null && this.tile != null){
            if(this.letterMultiply == this.wordMultiply){
                System.out.print(this.tile.getString() + "  ");
            }
            else if(this.letterMultiply == 1){
                System.out.print(this.tile.getString() + "w" + this.wordMultiply);
            }
            else{
                System.out.print(this.tile.getString() + "l" + this.letterMultiply);
            }
        }
        else{
            System.out.print("    ");
        }
    }
    
    public boolean hasTile(){
        return this.tile != null;
    }
}
