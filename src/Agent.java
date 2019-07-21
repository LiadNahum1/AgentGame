import java.util.Vector;

public class Agent {
    private final int stargNumber = 10;
    private int id;
    private int currStrat;
    private Vector<Edge> edges;
    private Vector<Agent> neigbors;
    public Agent(int id ) {
    }
    public int calcPayment(){ //how much money neighbors pay for me (T)
        int payment = 0;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                payment = payment + e.getPay21();
            else
                payment = payment + e.getPay12();
        }
        return payment;
    }
    public int calcPays(){ //how much money i pay for neighbors
        int payment = 0;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                payment = payment + e.getPay12();
            else
                payment = payment + e.getPay21();
        }
        return payment;
    }
    public int calcCurrentEarn(){
        int earn = 0;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                earn = earn + e.getUtility()[this.currStrat][e.getAgent2().currStrat].getNum1();
            else
                earn = earn + e.getUtility()[e.getAgent1().currStrat][this.currStrat].getNum2();
        }
        return earn;
    }
    public boolean palyTurn(){
    int imp =0;
    int Ttag =0;

    for (int i =0 ; i<stargNumber; i++){
                imp = calcGain(i); //the gain from switching to the i'th strategy
                if(imp > calcCurrentEarn() + calcPayment()) {
                    Ttag = announschange(i); // announse about to chane
                    if (imp > calcCurrentEarn() + Ttag) { //check if to actoualy change
                        changeStrat(i);
                        return true;
                    }
                }
        }
    return false;
    }

    private void changeStrat(int i) {
        this.currStrat = i;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                e.setPay21(0);
            else
                e.setPay12(0);
        }
    }

    private int announschange(int replace) {
        int sumGain = 0;
        for (Edge e: edges) {
            sumGain = sumGain + calcPaymant(replace ,e); //how much they want to give as in order to stay
        }
        return sumGain;
    }

    private int calcPaymant(int replace, Edge e) { //(T')
        int pay =0;
        if(e.getAgent1().id == this.id) {
            //previous overall earn - after change earn
            pay = (e.getUtility()[this.currStrat][e.getAgent2().currStrat].getNum2() - e.getPay21()) - (e.getUtility()[replace][e.getAgent2().currStrat].getNum2());
            if(pay > 0)
                e.setPay21(pay); //if not change the payment update is right, if change will update to 0
        }
        else{
            pay = (e.getUtility()[e.getAgent1().currStrat][this.currStrat].getNum1() - e.getPay12()) - (e.getUtility()[e.getAgent1().currStrat][replace].getNum1());
            if(pay > 0)
                e.setPay12(pay);
        }
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
