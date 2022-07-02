import java.io.IOException;

/**
 * Write a description of class Instance here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Instance
{
    Board board;
    Bag bag;
    Player[] players;
    int numberOfPlayers;
    boolean log;
    String reference;

    public Instance(int numberOfPlayers, String[] playerNames, boolean log) throws java.io.IOException {
        Utils.init();
        this.reference = Utils.processFile(Utils.getPathForReference());
        this.board = new Board();
        this.board.wordList = reference;
        this.numberOfPlayers = numberOfPlayers;
        this.log = log;
        this.bag = new Bag();
        this.players = new Player[numberOfPlayers];
        for(int iter = 0; iter < numberOfPlayers; iter++){
            this.players[iter] = new Player(playerNames[iter], iter + 1, this);
        }
        //get players
    }

    public static void test() throws IOException{
        Instance instance = new Instance(2, new String[] {"A", "B"}, false);
        instance.board.print();
    }

    public static void main(String args[]){
        try {
            test();
        } catch (IOException e) {
        }
    }
}
