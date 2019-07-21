import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("how Much experiments would you like to do?");
        int numOfExp = s.nextInt();
        System.out.println("how Much agents would you like to have?");
        int numOfAg = s.nextInt();
        System.out.println("Which strategy to play with?");
        int chosenStrat = s.nextInt();
        int totalGameTurns = 0, totalGameEarn =0;
        for(int i = 0; i<numOfExp; i++){
            Game g = new Game(numOfAg ,chosenStrat);
            totalGameEarn += g.getGameEarns();
            totalGameTurns += g.getTurnsplayed();
            g.printAgentStrat();
        }
        System.out.println(" took to play in avrage " + totalGameTurns/numOfExp);
        System.out.println(" avrage welfare " + totalGameEarn/numOfExp);


    }
}
