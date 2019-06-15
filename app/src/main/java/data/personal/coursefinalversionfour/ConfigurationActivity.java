package data.personal.coursefinalversionfour;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ConfigurationActivity extends AppCompatActivity {
    Button btnSave;
    EditText txtName,txtEmail,txtAge,txtCost,txtNumCig,txtCigPerDay,txtTargetAmount;
    DatePicker dpTargetDate;
    TextView tvTargetDate;
    Date dtTarget;
    DBHandler db = new DBHandler(this);
    int day,month,years;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    private long fileSize = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSave = (Button) findViewById(R.id.btnSave);
        txtName=(EditText)findViewById(R.id.txtName);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtAge=(EditText)findViewById(R.id.txtAge);
        txtCost=(EditText)findViewById(R.id.txtCostPrPkt);
        txtNumCig=(EditText)findViewById(R.id.txtCigPerPkt);
        txtCigPerDay=(EditText)findViewById(R.id.txtCigPerDay);
        txtTargetAmount=(EditText)findViewById(R.id.txtTargetAmount);
        dpTargetDate=(DatePicker)findViewById(R.id.dpTargetDate);
        tvTargetDate =(TextView)findViewById(R.id.tvTargetDate);
        Calendar today = Calendar.getInstance();
        day = dpTargetDate.getDayOfMonth();
        month = dpTargetDate.getMonth();
        years = dpTargetDate.getYear();
        dtTarget = new Date(years - 1900, month, day);
        tvTargetDate.setText("Achieve Goal By: " + Utility.getDateOnlyFormatted(dtTarget).toString());
        dpTargetDate.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener(){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                        day = dayOfMonth;
                        month = monthOfYear;
                        years = year;
                        dtTarget = new Date(years - 1900, month, day);
                        tvTargetDate.setText("Achieve Goal By: " + Utility.getDateOnlyFormatted(dtTarget).toString());
                        //Toast.makeText(getApplicationContext(),"onDateChanged", Toast.LENGTH_SHORT).show();
                    }});
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* db.addConfiguration(new UserConfig(txtName.getText().toString(),Integer.parseInt(txtAge.getText().toString()), txtEmail.getText().toString()));
                UserConfig user = db.getSingleUser();
                System.out.println("UID: " + user.getId());
                System.out.println("Number of Cig: " + txtNumCig.getText().toString());
                System.out.println("Cost of Cig: " + new Double(txtCost.getText().toString()));
                System.out.println("Cig per day: " + txtCigPerDay.getText().toString());
                System.out.println("Target: " + txtTargetAmount.getText().toString());
                System.out.println("Target Date: " + Utility.getDateFormatted(dtTarget));
                db.addRecordMaster(new RecordMaster(user.getId(),Integer.parseInt(txtNumCig.getText().toString()),new Double(txtCost.getText().toString()),Integer.parseInt(txtCigPerDay.getText().toString()),Integer.parseInt(txtTargetAmount.getText().toString()),Utility.getDateFormatted(dtTarget)));
                Toast.makeText(getApplicationContext(),"Configuration Done!", Toast.LENGTH_SHORT).show();

                try { Thread.sleep(1200); }
                catch (InterruptedException e) { e.printStackTrace(); }

                Intent intent = new Intent(ConfigurationActivity.this, DashboardActivity.class);
                startActivity(intent);*/
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Data is saving ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                progressBarStatus = 0;

                fileSize = 0;
                new Thread(new Runnable() {
                    public void run() {
                        while (progressBarStatus < 100) {
                            db.addConfiguration(new UserConfig(txtName.getText().toString(),Integer.parseInt(txtAge.getText().toString()), txtEmail.getText().toString()));
                            progressBarStatus = 50;
                            UserConfig user = db.getSingleUser();
                            System.out.println("UID: " + user.getId());
                            System.out.println("Number of Cig: " + txtNumCig.getText().toString());
                            System.out.println("Cost of Cig: " + new Double(txtCost.getText().toString()));
                            System.out.println("Cig per day: " + txtCigPerDay.getText().toString());
                            System.out.println("Target: " + txtTargetAmount.getText().toString());
                            System.out.println("Target Date: " + Utility.getDateFormatted(dtTarget));
                            db.addRecordMaster(new RecordMaster(user.getId(),Integer.parseInt(txtNumCig.getText().toString()),new Double(txtCost.getText().toString()),Integer.parseInt(txtCigPerDay.getText().toString()),Integer.parseInt(txtTargetAmount.getText().toString()),Utility.getDateFormatted(dtTarget)));
                            progressBarStatus=100;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            progressBarbHandler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }

                        if (progressBarStatus >= 100) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressBar.dismiss();
                            Intent intent = new Intent(ConfigurationActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        }
                    }
                }).start();
           }
        });
    }

}
