package data.personal.coursefinalversionfour;

/**
 * Created by ShishupalRathore on 21/04/17.
 */

public class CostPerCig {
    private int cost_of_pkt = 0;
    private int num_of_cig_in_pkt =0;

    public CostPerCig() {    }

    public CostPerCig(int cost_of_pkt, int num_of_cig_in_pkt)
    {
        System.out.println("RM1 - Cost Per Cig: ");
        this.cost_of_pkt            = cost_of_pkt;
        this.num_of_cig_in_pkt            = num_of_cig_in_pkt;
    }

    public void setCostOfPkt(int cost_of_pkt)
    {
        this.cost_of_pkt = cost_of_pkt;
    }

    public void setNumOfCigPkt(int num_of_cig_in_pkt)
    {
        this.num_of_cig_in_pkt = num_of_cig_in_pkt;
    }

    public int getCostOfPkt()
    {
        return cost_of_pkt;
    }

    public int getNumofCigPkt()
    {
        return num_of_cig_in_pkt;
    }


}
