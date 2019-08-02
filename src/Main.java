import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("How many experiments would you like to do?");
        int numOfExp = s.nextInt();
        System.out.println("How many agents would you like to have?");
        int numOfAg = s.nextInt();
        System.out.println("Which strategy of convincing not to move do you prefer? ");
        int chosenStrat = s.nextInt();
        int totalGameTurns = 0;
        double totalGameEarn =0;
        for(int i = 0; i<numOfExp; i++){
            Game g = new Game(numOfAg ,chosenStrat);
            totalGameEarn += g.getGameEarns();
            totalGameTurns += g.getTurnsplayed();
            g.printAgentStrat();
        }
        System.out.println("Average iterations " + totalGameTurns/numOfExp);
        System.out.println("Average welfare " + totalGameEarn/numOfExp);


    }
}
