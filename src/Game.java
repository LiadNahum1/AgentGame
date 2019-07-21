import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Game {
    private Random r = new Random();
     private int numOfAg;
     private Agent [] agents ;
     private int [][] matrixNegibors;
    private int  turnsplayed;

    public int getNumOfAg() {
        return numOfAg;
    }

    public int[][] getMatrixNegibors() {
        return matrixNegibors;
    }

    public int getTurnsplayed() {
        return turnsplayed;
    }

    public int getGameEarns() {
        return gameEarns;
    }

    public int getStrategyOfPaymant() {
        return strategyOfPaymant;
    }

    private int gameEarns;
    private int strategyOfPaymant;

     public Game(int numOfAg , int strategyOfPaymant) {
         this.strategyOfPaymant = strategyOfPaymant;
         this.numOfAg = numOfAg;
         this.agents = new Agent[numOfAg];
         this.matrixNegibors = new int [numOfAg][numOfAg];
         for (int i =0; i< numOfAg ;i++){
             agents[i] = new Agent(i,strategyOfPaymant);
         }
            buildMarixNegibors(matrixNegibors);
         startExp();
     }

    private void buildMarixNegibors(int[][] matrixNegibors) {
        int isNegibor;
        Edge edge;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<numOfAg; i++) {
            list.add(i);
        }


        for(int i=0; i<numOfAg-1; i++){
            Collections.shuffle(list);
            int neigborsNum = agents[i].getEdges().size();
            for(int j = 0; j < list.size() && j < 10 - neigborsNum; j++){
                if(this.agents[list.get(j)].getEdges().size()<10) {
                    Edge e = new Edge(this.agents[i], this.agents[list.get(j)]);
                    agents[i].getEdges().add(e);
                    agents[list.get(j)].getEdges().add(e);
                }
                else{
                    list.remove(j);
                    }

                }
            if(list.indexOf(i+1) != -1)
                list.remove(list.indexOf(i+1));
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

        public void printAgentStrat() {
            for (int i = 0; i < numOfAg; i++) {
                System.out.println("Agent " + agents[i].getId() + " Strategy " + agents[i].getCurrStrat() + "total earn " + agents[i].calcCurrentEarn()
                + "payment " + agents[i].calcPays());
            }
        }
}
