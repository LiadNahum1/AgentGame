import java.util.Random;
public class Edge {
    private Agent agent1;
    private Agent agent2;
    private final int stargNumber = 10;
    private final int limit = 5; //ask
    private Pair [][] utility;

    public Edge(Agent agent1, Agent agent2){
        this.agent1 = agent1;
        this.agent2 = agent2;
        this.utility = new Pair [stargNumber][stargNumber];
        fillUtil();
    }

    private void fillUtil() {
        Random r = new Random();
        for (int i=0; i<stargNumber; i++){
            for(int j=0; j<stargNumber; j++){
                //first num is the utility of agent1, second is of agent2
                this.utility[i][j] = new Pair(r.nextInt(limit), r.nextInt(limit));
            }
        }
    }

    public Agent getAgent1() {
        return agent1;
    }

    public Agent getAgent2() {
        return agent2;
    }

    public int getStargNumber() {
        return stargNumber;
    }

    public int getLimit() {
        return limit;
    }

    public Pair[][] getUtility() {
        return utility;
    }
}
