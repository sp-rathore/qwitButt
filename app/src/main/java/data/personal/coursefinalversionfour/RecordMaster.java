package data.personal.coursefinalversionfour;

import java.util.Calendar;

/**
 * Created by ShishupalRathore on 19/03/17.
 */

public class RecordMaster {
    private int     record_id;
    private int     user_id;
    private int     num_cig_in_pkt;
    private Double   cost_of_pkt;
    private int     avg_daily_cig_use_curr;
    private int     goal_daily_cig_redn;
    private String target_dt;
    private String  end_dt;

    public RecordMaster() {    }

    public RecordMaster(int user_id, int num_cig_in_pkt, Double cost_of_pkt, int avg_daily_cig_use_curr,
                        int goal_daily_cig_redn, String target_dt)
    {
        System.out.println("RM1 - Cost of Cig: " + cost_of_pkt.toString());
        this.user_id                = user_id;
        this.num_cig_in_pkt         = num_cig_in_pkt;
        this.cost_of_pkt            = cost_of_pkt;
        this.avg_daily_cig_use_curr = avg_daily_cig_use_curr;
        this.goal_daily_cig_redn    = goal_daily_cig_redn;
        this.target_dt = target_dt;
    }
    public RecordMaster(int num_cig_in_pkt, Double cost_of_pkt, int avg_daily_cig_use_curr, int goal_daily_cig_redn, String target_dt, String end_dt)
    {
        System.out.println("RM2 - Cost of Cig: " + cost_of_pkt.toString());
        this.num_cig_in_pkt         = num_cig_in_pkt;
        this.cost_of_pkt            = cost_of_pkt;
        this.avg_daily_cig_use_curr = avg_daily_cig_use_curr;
        this.goal_daily_cig_redn    = goal_daily_cig_redn;
        this.target_dt              = target_dt;
        this.end_dt                 = end_dt;
    }

    public void setRecordId(int record_id)
    {this.record_id = record_id;}

    public void setUserId(int user_id)
    {this.user_id = user_id;}

    public void setNumCigInPkt(int num_cig_in_pkt)
    { this.num_cig_in_pkt = num_cig_in_pkt; }

    public void setCostOfPkt(Double cost_of_pkt)
    { this.cost_of_pkt = cost_of_pkt; }

    public void setAvgDailyCigUseCurr(int avg_daily_cig_use_curr)
    { this.avg_daily_cig_use_curr = avg_daily_cig_use_curr; }

    public void setGoalDailyCigRedn(int goal_daily_cig_redn)
    { this.goal_daily_cig_redn = goal_daily_cig_redn; }

    public void setTargetDt(String target_dt)
    {this.target_dt = target_dt;}

    public void setEndDt(String end_dt)
    {this.end_dt = end_dt;}

    public int getRecordId()
    { return record_id; }

    public int getUserId()
    { return user_id; }

    public int getNumCigInPkt()
    { return num_cig_in_pkt; }

    public Double getCostOfPkt()
    { return cost_of_pkt; }

    public int getAvgDailyCigUseCurr()
    { return avg_daily_cig_use_curr; }

    public int getGoalDailyCigRedn()
    { return goal_daily_cig_redn; }

    public String getTargetDt()
    { return target_dt; }

    public String  getEndDt()
    { return end_dt; }

}
