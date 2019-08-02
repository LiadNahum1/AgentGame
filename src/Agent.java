import java.util.Vector;

public class Agent {
    private final int stargNumber = 10;
    private int id;
    private int currStrat;
    private int strategychosen;
    private Vector<Edge> edges;
    public Agent(int id , int strategychosen) {
        this.id= id;
        this.strategychosen = strategychosen;
        this.currStrat = 0;
        this.edges = new Vector<>();
    }
    public double calcNeighborsPayment(){ //how much money neighbors pay for me (T)
        double payment = 0;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                payment = payment + e.getPay21();
            else
                payment = payment + e.getPay12();
        }
        return payment;
    }
    public double calcIPay(){ //how much money i pay for neighbors
        double payment = 0;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                payment = payment + e.getPay12();
            else
                payment = payment + e.getPay21();
        }
        return payment;
    }
    public double calcCurrentEarn(){
        double earn = 0;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                earn = earn + e.getUtility()[this.currStrat][e.getAgent2().currStrat].getNum1();
            else
                earn = earn + e.getUtility()[e.getAgent1().currStrat][this.currStrat].getNum2();
        }
        return earn;
    }
    public boolean playTurn(){
    double imp =0;
    double Ttag =0;

    for (int i =0 ; i<stargNumber; i++){
                imp = calcGain(i); //the gain from switching to the i'th strategy
                if(imp > calcCurrentEarn() + calcNeighborsPayment()) {
                    Ttag = announceChange(i); // announce on agent's intention to change
                    if (imp > calcCurrentEarn() + Ttag) { //check if to actually change
                        changeStrat(i);
                        return true;
                    }
                }
        }
    return false;
    }

    //if agent decide to change strategy anyway, all payments of the neighbors to this agent are off
    private void changeStrat(int i) {
        this.currStrat = i;
        for(Edge e: edges){
            if(e.getAgent1().id == this.id)
                e.setPay21(0);
            else
                e.setPay12(0);
        }
    }
    private double announceChange(int replace) {
        double profit = calcGain(replace) - calcCurrentEarn(); //how much money agent profits from this switch
        double sumPay = 0;
        for (Edge e : edges) {
            sumPay = sumPay + calcPayment(replace, e); //how much neighbors lose from the switch and willing to pay
        }

        if (strategychosen == 1) { //each neighbor pays the money he loses from switching
            setPayAst1(replace);
            return sumPay;
        }
        else if (strategychosen == 2){ //neighbors split the profit respectively according to the amount of money they lose
            if (sumPay < profit) //the total lost is less than the profit so the neighbors can't convince him
                return 0;
            else {
                double proportion = sumPay / profit;
                setPayAst2(replace, proportion);
                return profit;
            }
        }
        else
            return 0;
    }

    private void setPayAst1(int replace) {
        double pay = 0;
        for(Edge e: edges) {
            pay = calcPayment(replace, e);
            if (pay > 0){
                if (e.getAgent1().id == this.id)
                    e.setPay21(pay);
                else
                    e.setPay12(pay);
            }
        }
    }

    private void setPayAst2(int replace, double proportion) {
        double pay;
        for(Edge e: edges){
            pay = calcPayment(replace, e);
            if (pay > 0 ){
                if (e.getAgent1().id == this.id)
                    e.setPay21(pay / proportion);
                else
                    e.setPay12(pay / proportion);
            }
        }
    }

    private double calcPayment(int replace, Edge e) { //(T')
            double pay = 0;
            if (e.getAgent1().id == this.id) {
                //previous overall earn - after change earn
                pay = (e.getUtility()[this.currStrat][e.getAgent2().currStrat].getNum2() - e.getPay21()) - (e.getUtility()[replace][e.getAgent2().currStrat].getNum2());
            } else {
                pay = (e.getUtility()[e.getAgent1().currStrat][this.currStrat].getNum1() - e.getPay12()) - (e.getUtility()[e.getAgent1().currStrat][replace].getNum1());
            }
            return pay;
    }

    private double calcGain(int strategy){
        double gain =0;
        for (Edge e: edges) {
            if(e.getAgent1().id == this.id)
            gain = gain + e.getUtility()[strategy][e.getAgent2().currStrat].getNum1();
            else
                gain = gain + e.getUtility()[e.getAgent1().currStrat][strategy].getNum2();
        }
        return gain;
    }

    public double getStargNumber() {
        return stargNumber;
    }

    public int getId() {
        return id;
    }

    public int getCurrStrat() {
        return currStrat;
    }

    public Vector<Edge> getEdges() {
        return edges;
    }

}
