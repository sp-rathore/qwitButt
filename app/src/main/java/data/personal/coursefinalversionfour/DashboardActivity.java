package data.personal.coursefinalversionfour;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    Button addEvent,seeStat;
    TextView tvDashboard,tvLifeRegained,tvMoaneySaved;
    DBHandler db = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        UserConfig user = db.getSingleUser();
        System.out.println("User: " + user.getId() +":"+ user.getName());

        if(user.getName()==null)
        {
            Intent intent = new Intent(DashboardActivity.this, ConfigurationActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addEvent=(Button)findViewById(R.id.add_smoking);
        seeStat = (Button)findViewById(R.id.statistics);
        tvLifeRegained = (TextView) findViewById(R.id.days);
        tvMoaneySaved = (TextView) findViewById(R.id.money);

        tvLifeRegained.setTextColor(Color.GREEN);
        tvLifeRegained.setText(getLifeRegainded());
        Log.i("Returned Life saved: ", getLifeRegainded());

        tvMoaneySaved.setTextColor(Color.GREEN);
        tvMoaneySaved.setText(getMoneySaved());
        Log.i("Money saved: ", getLifeRegainded());

        // Font path
        String fontPath = "fonts/Quicksand-Bold.otf";

        // Loading Font Face
        Typeface m_typeFace = Typeface.createFromAsset(getAssets(), fontPath);


        // Applying font
        tvDashboard = (TextView) findViewById(R.id.contact_dashboard);
        tvDashboard.setTypeface(m_typeFace);

        //System.out.println("Number of cig:" + db.getNumOfCigByDate(Utility.getDate()));

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Inside Button");
                Intent intent = new Intent(DashboardActivity.this, AddEventActivity.class);
                startActivity(intent);
                System.out.println("End Button");
            }
        });
        seeStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(DashboardActivity.this, LocationEventActivity.class);
                Intent intent = new Intent(DashboardActivity.this, DataVisualizationActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
                //GetLocation();

                Intent intent = new Intent(DashboardActivity.this, FileUploader.class);
                startService(intent);

            }

            else
                Toast.makeText(DashboardActivity.this, "Check Internet Connection!",Toast.LENGTH_SHORT).show();
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private String getLifeRegainded(){
        int cigarettecount  = 0, avg_cig_daily_earlier=0, num_of_days = 0,cigarettes_potentially_consumed =0,
                cigarettes_consumed_less=0,life_regained=0;

        cigarettecount = db.getNumOfCigConsumed();


        Log.i("CigCOunt: ", String.valueOf(cigarettecount));
        avg_cig_daily_earlier = db.getAvgCigConsumed();
        Log.i("CigDailyAvg: ", String.valueOf(avg_cig_daily_earlier));

        Log.i("DEBUG1: ", String.valueOf(num_of_days));
        num_of_days = db.getNumOfDays();
        Log.i("DEBUG2: ", String.valueOf(num_of_days));

        Log.i("NumOfDays: ", String.valueOf(num_of_days));
        cigarettes_potentially_consumed = num_of_days*avg_cig_daily_earlier;
        cigarettes_consumed_less = cigarettes_potentially_consumed - cigarettecount;
        life_regained= 14 * cigarettes_consumed_less;
        int MINS_PER_YEAR = 24 * 365 * 60;
        int MINS_PER_MONTH = 24 * 30 * 60;
        int MINS_PER_WEEK = 24 * 7 * 60;
        int MINS_PER_DAY = 24 * 60;
        int years = life_regained / MINS_PER_YEAR;
        life_regained = life_regained % MINS_PER_YEAR;
        int months = life_regained / MINS_PER_MONTH;
        life_regained = life_regained % MINS_PER_MONTH;
        int weeks = life_regained / MINS_PER_WEEK;
        life_regained = life_regained % MINS_PER_WEEK;
        int days = life_regained / MINS_PER_DAY;
        life_regained = life_regained % MINS_PER_DAY;
        String returnString = "";

        if(years>0)
            returnString = years + " Year(s) ";
        if(months>0)
            returnString = returnString + months + " Month(s) ";
        if(weeks>0)
            returnString = returnString + weeks + " Week(s) ";
        if(days>0)
            returnString = returnString + days + " Day(s) ";
        if(life_regained>0)
            returnString = returnString + life_regained + " Minute(s)";

        if(returnString.isEmpty())
            returnString = "0 Day(s)";
        return  returnString;
    }

    private String getMoneySaved(){
        int cigarettecount  = 0, avg_cig_daily_earlier=0, num_of_days = 0,cigarettes_potentially_consumed =0,
                cigarettes_consumed_less=0,life_regained=0;
        cigarettecount = db.getNumOfCigConsumed();
        Log.i("CigCOunt: ", String.valueOf(cigarettecount));
        avg_cig_daily_earlier = db.getAvgCigConsumed();
        Log.i("CigDailyAvg: ", String.valueOf(avg_cig_daily_earlier));
        num_of_days = db.getNumOfDays();
        Log.i("NumOfDays: ", String.valueOf(num_of_days));
        cigarettes_potentially_consumed = num_of_days*avg_cig_daily_earlier;
        cigarettes_consumed_less = cigarettes_potentially_consumed - cigarettecount;
        CostPerCig cosPerCig = db.getCostPerCig();
        String returnString = "";
        float cost_per_cig = 0f;
        if(cosPerCig.getNumofCigPkt()>0)
            cost_per_cig = cosPerCig.getCostOfPkt()/cosPerCig.getNumofCigPkt();
        float money_saved = 0f;
        money_saved = cigarettes_consumed_less * cost_per_cig;
        if(money_saved>0)
            returnString = money_saved + " DKK";
        if(returnString.isEmpty())
            returnString = "0 DKK";
        return  returnString;
    }
}
