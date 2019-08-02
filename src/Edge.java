import java.util.Random;
public class Edge {
    private Agent agent1;
    private Agent agent2;
    private double pay12;  //agent1 pays agent2
    private double pay21;  //agent2 pays agent1
    private final int stargNumber = 10;
    private final int limit = 5;
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

    public double getPay12() {
        return pay12;
    }

    public double getPay21() {
        return pay21;
    }

    public void setPay12(double pay12) {
        this.pay12 = pay12;
    }

    public void setPay21(double pay21) {
        this.pay21 = pay21;
    }
}
