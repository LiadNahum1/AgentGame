import java.util.Random;

public class Game {
    private Random r = new Random();
     private final int numOfAg = 100;
     private Agent [] agents = new Agent[numOfAg];
     private int [][] matrixNegibors = new int [numOfAg][numOfAg];
    private int  turnsplayed;
    private int gameEarns;

     public Game(int numExp) {
            buildMarixNegibors(matrixNegibors);
            // todo initailizestuf
         startExp();
     }

    private void buildMarixNegibors(int[][] matrixNegibors) {
        int isNegibor;
        Edge edge;
        for(int i=0; i<matrixNegibors.length; i++){
            for(int j=i+1; j<matrixNegibors.length; j++){
                //todo build randomize matrix
                //todo build edge and add to each agent
                // make sure nobady has more than 10 nigbors
            }
        }
    }
    public void startExp(){
            int countStable = 0, j =0;
            boolean res = true;
            while(countStable < numOfAg && turnsplayed <= 100000000) {
                res = agents[j].palyTurn();
                if (!res) //agent didn't want to change strategy = he is stable
                    countStable = countStable + 1;
                else
                    countStable = 0;

                if( j == numOfAg -1)
                    j = 0;
                else
                    j = j + 1;
                turnsplayed = turnsplayed + 1;
            }
            if(j == 100000000){
                System.out.println("error game doesnt end");
            }
            gameEarns = totalWelfare();
        }
        public int totalWelfare(){
         int welfare = 0 ;
         for(int i=0; i< numOfAg; i++){
             welfare = welfare + agents[i].calcCurrentEarn() + agents[i].calcPayment() - agents[i].calcPays();
         }
         return welfare;
        }
}
