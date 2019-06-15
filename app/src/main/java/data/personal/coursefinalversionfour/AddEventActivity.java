package data.personal.coursefinalversionfour;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddEventActivity extends AppCompatActivity {
    int selectedlocationId, locationId;
    TextView cigAmount;
    private LocationManager locationManager;
    private LocationListener listener;
    private Location location;
    UserConfig user;
    Double lat = 0.0;
    Double lon = 0.0;
    Button btnSave;
    DBHandler db = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupLocation);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        selectedlocationId = radioButton.getId();
        btnSave = (Button) findViewById(R.id.button_save);
        cigAmount=(TextView)findViewById(R.id.cig_amount_today);
        cigAmount.setTextColor(Color.RED);
        cigAmount.setText(db.getNumOfCigByDate(Utility.getDate())+" Cigarette(s)");
        //System.out.println("Number of cig:" + db.getNumOfCigByDate(Utility.getDate()));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedlocationId = checkedId;
                if(selectedlocationId==R.id.button_home)
                    locationId=1;
                else if(selectedlocationId==R.id.button_work)
                    locationId=2;
                else if(selectedlocationId==R.id.button_driving)
                    locationId=3;
                else if(selectedlocationId==R.id.button_walking)
                    locationId=4;
                else if(selectedlocationId==R.id.button_school)
                    locationId=5;
                else
                    locationId=6;
                System.out.println("Location ID: " + locationId);
            }
        });

        System.out.println("Day Period: " + Utility.getDayPeriod());
        user = db.getSingleUser();
        System.out.println("Date Time: " + Utility.getDateTime());
        System.out.println("UserID: " + user.getId());
        System.out.println("Longitude: " + String.valueOf(lon));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Save Button Clicked: " + String.valueOf(lon));
                db.addEventMaster(new EventMaster(Utility.getDayPeriod(),user.getId(),locationId,lat,lon,Utility.getDateTime()));
                Intent intent = new Intent(AddEventActivity.this, SuccessActivity.class);
                intent.putExtra("location",selectedlocationId);
                startActivity(intent);
            }
        });
    }
 }
