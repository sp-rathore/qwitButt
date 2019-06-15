package data.personal.coursefinalversionfour;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


public class SuccessActivity extends AppCompatActivity {
    TextView tvLocation;
    TextView cigAmount;
    DBHandler db = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getActionBar().setHomeButtonEnabled(true);
       // this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvLocation= (TextView) findViewById(R.id.tv_done);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        int locationid=6;
        if(b!=null)
        {
            locationid = b.getInt("location");
            System.out.println("LocationID from Intent:" + locationid);
        }
        cigAmount=(TextView)findViewById(R.id.cig_amount_today);
        cigAmount.setTextColor(Color.RED);
        cigAmount.setText(db.getNumOfCigByDate(Utility.getDate())+" Cigarette(s)");
        if(locationid==R.id.button_home)
            tvLocation.setCompoundDrawablesWithIntrinsicBounds( R.drawable.home, 0, 0, 0);
        else if(locationid==R.id.button_work)
            tvLocation.setCompoundDrawablesWithIntrinsicBounds( R.drawable.work, 0, 0, 0);
        else if(locationid==R.id.button_driving)
            tvLocation.setCompoundDrawablesWithIntrinsicBounds( R.drawable.driving, 0, 0, 0);
        else if(locationid==R.id.button_walking)
            tvLocation.setCompoundDrawablesWithIntrinsicBounds( R.drawable.walk, 0, 0, 0);
        else if(locationid==R.id.button_school)
            tvLocation.setCompoundDrawablesWithIntrinsicBounds( R.drawable.school, 0, 0, 0);
        else
            tvLocation.setCompoundDrawablesWithIntrinsicBounds( R.drawable.other, 0, 0, 0);
    }
  /*  @Override
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
        if (id == R.id.button_home) {
            Intent intent = new Intent(SuccessActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
*/
  @Override
  public void onBackPressed()
  {
      super.onBackPressed();
      System.out.println("Back Button");
      startActivity(new Intent(SuccessActivity.this, DashboardActivity.class));
      finish();
  }
}
