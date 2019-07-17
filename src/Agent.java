import java.util.Vector;

public class Agent {
    private final int stargNumber = 10;
    private int id;
    private int currStrat;
    private int currEarn;
    private int T;
    private Vector<Edge> edges;
    private Vector<Agent> neigbors;
    public Agent(int id ) {

    }
    public boolean palyTurn(){
    int imp =0;
    for (int i =0 ; i<stargNumber; i++){
                imp = calcGain(i);
                if(imp > currEarn + T){
                  T = T + announschange(i); // announse about to chane
                }
                if(imp > currEarn + T){ //check if to actoualy change
                    changeState();
                    return true;
                }
                else{
                    announsUnchange(i);
                }
        }
    return false;
    }

    private void changeState() {

    }

    private int announschange(int replace) {
        int curGain;
        int sumGain = 0;
        for (Edge e: edges) {
            curGain = calcPaymant(replace ,e); //how much they want to give as in order to stay
            if(curGain > 0)// neigbor loses utility with the new strategy and decides to pay
                sumGain = sumGain + curGain;
        }
        return sumGain;
    }

    private int calcPaymant(int replace, Edge e) {
        int pay =0;
        if(e.getAgent1().id == this.id)
            pay = e.getUtility()[currStrat][e.getAgent2().currStrat].getNum2() - e.getUtility()[replace][e.getAgent2().currStrat].getNum2() ;
        else
            pay = e.getUtility()[e.getAgent1().currStrat][currStrat].getNum1() - e.getUtility()[e.getAgent1().currStrat][replace].getNum1() ;
        return pay;
    }

    private int calcGain(int strategy){
        int gain =0;
        for (Edge e: edges) {
            if(e.getAgent1().id == this.id)
            gain = gain + e.getUtility()[strategy][e.getAgent2().currStrat].getNum1();
            else
                gain = gain + e.getUtility()[e.getAgent1().currStrat][strategy].getNum2();
        }
        return gain;
    }

}
