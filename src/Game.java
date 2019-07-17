import java.util.Random;

public class Game {
    private Random r = new Random();
     private final int numOfAg = 100;
     private Agent [] agents = new Agent[numOfAg];
     private int [][] matrixNegibors = new int [numOfAg][numOfAg];


     public Game() {
            buildMarixNegibors(matrixNegibors);
     }

    private void buildMarixNegibors(int[][] matrixNegibors) {
        int isNegibor;
        Edge edge;
        for(int i=0; i<matrixNegibors.length; i++){
            for(int j=i+1; j<matrixNegibors.length; j++){
                //todo build randomize matrix
                //todo build edge and add to each agent
            }
        }
    }
    public  void startexp(){
       // for(int i = 0){
         //   agents[i].playturn();
        //}
    }
}

}
