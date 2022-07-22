import java.io.IOException;
import java.util.*;

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

    public Instance(){

    }

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

    public void print(){
        this.board.print();
        System.out.println();
        for(int iter = 0; iter < this.players.length; iter++){
            System.out.println(this.players[iter].name + "'s score: " + this.players[iter].score);
            System.out.print(this.players[iter].name + "'s tiles: ");
            this.players[iter].printTiles();
            System.out.println();
        }
        System.out.println();
    }

    public void newMove(int playerNum, boolean isFirstTime, boolean print){
        //add code to check if tiles are still there in bag
        if(this.bag.isEmpty()){
            this.winner();
        }
        if(print){
            this.print();
        }    
        Instance old = this.clone();
        Scanner getInput = new Scanner(System.in);
        System.out.println(this.players[playerNum].name + "'s move.");
        System.out.println("Please enter your desired moves in the following format: piece name + x position + y position (x and y are 1 and 1 for the top left, separate each move with commas).");
        String input = getInput.nextLine();
        getInput.close();
        boolean isValid = this.interpretInput(input, playerNum);
        if(!isValid){
            this.board = old.board;
            this.bag = old.bag;
            this.players = old.players;
            this.numberOfPlayers = old.numberOfPlayers;
            this.log = old.log;
            this.reference = old.reference;
            System.out.println("Invalid move, please try again.");
            System.out.println();
            this.newMove(playerNum, isFirstTime, false);
        }
        else if(isFirstTime && input.contains("88") == false){
            this.board = old.board;
            this.bag = old.bag;
            this.players = old.players;
            this.numberOfPlayers = old.numberOfPlayers;
            this.log = old.log;
            this.reference = old.reference;
            System.out.println("First move has to have a piece on the middle square, please try again.");
            System.out.println();
            this.newMove(playerNum, isFirstTime, false);
        }
        else{
            System.out.println("Press x to exit, and any other key to continue");
            String continuePlay = getInput.nextLine();
            if(continuePlay.equalsIgnoreCase("x")){
                System.out.println("Exiting program...");
                Utils.exit();
            }
            if(playerNum == this.numberOfPlayers - 1){
                this.newMove(0, false, true);
            }
            else{
                this.newMove(playerNum++, false, true);
            }
        }
    }

    public void winner(){
        int[] winners = this.getWinner();
        int numberOfWinners = count(winners);
        if(!(numberOfWinners == 1)){
            System.out.println("Looks like there has been a draw between " + numberOfWinners + " players!");
            System.out.println("Winners: ");
        }
        else{
            System.out.println("Winner: ");
        }
        for(int iter = 0; iter < winners.length; iter++){
            if(winners[iter] < winners.length){
                System.out.println("Player " + this.players[winners[iter]].name + " got " + this.players[winners[iter]].score + " points.");
            }
        }
    }

    public void continuePlay() throws IOException{
        Scanner check = new Scanner(System.in);
        System.out.println("Press e to exit and any other key to continue.");
        String input = check.nextLine();
        check.close();
        if(input.equalsIgnoreCase("e")){
            Utils.exit();
        }
        else{
            init();
        }
    }

    public int[] getWinner(){
        int[] scores = fill(new int[this.players.length]);
        int[] results = new int[scores.length];
        int fill = 0;
        int highest = 0;
        for(int iter = 0; iter < scores.length; iter++){
            if(this.players[iter] != null && this.players[iter].score >= highest){
                results[fill] = this.players[iter].score;
                fill++;
                highest = this.players[iter].score;
            }
        }
        return results;
    }

    public static int count(int[] reference){
        int result = 0;
        for(int iter = 0; iter < reference.length; iter++){
            if(!(reference[iter] > reference.length)){
                result++;
            }
        }
        return result;
    }

    public static int[] fill(int[] toFill){
        int add = toFill.length + 1;
        for(int iter = 0; iter < toFill.length; iter++){
            if(toFill[iter] == 0){
                toFill[iter] = add;
            }
        }
        return toFill;
    }

    public Instance clone(){
        Instance forReturn = new Instance();
        Player[] players = new Player[this.players.length];
        for(int iter = 0; iter < players.length; iter++){
            players[iter] =  this.players[iter].clone();
        }
        forReturn.players = players;
        forReturn.bag = this.bag.clone();
        forReturn.board = this.board.clone();
        forReturn.numberOfPlayers = this.numberOfPlayers;
        forReturn.log = this.log;
        forReturn.reference = this.reference;
        return forReturn;
    }

    public boolean interpretInput(String input, int playerNum) throws java.lang.StringIndexOutOfBoundsException{
        Board board = this.board;
        Player player = this.players[playerNum];
        String letter;
        int xPos;
        int yPos;
        for(int iter = 0; iter < input.length(); iter = iter + 3){
            letter = input.charAt(iter) + "";
            xPos = Integer.parseInt(input.charAt(iter + 1) + "");
            yPos = Integer.parseInt(input.charAt(iter + 2) + "");
            boolean isValid = board.add(xPos, yPos, letter) && player.hasTileBlank(letter);
            if(!isValid){
                return false;
            }
            else{
                player.removeTile(letter);
            }
        }
        player.fillTiles();
        return true;
    }

    public static void init() throws IOException{
        Utils.init();
        //add code to get players and log options
    }

    public static void test() throws IOException{
        Instance instance = new Instance(2, new String[] {"A", "B"}, false);
        instance.newMove(0, true, true);
    }

    

    public static void main(String args[]){
        try {
            test();
        } catch (IOException e) {
        }
    }
}
