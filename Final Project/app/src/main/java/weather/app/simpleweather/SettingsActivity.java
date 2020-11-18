package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    private static int DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        checkupdate();

        BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(ContactUsActivity.this, "@string/homePage", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_contact:
                        //Toast.makeText(ContactUsActivity.this, "@string/contactUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SettingsActivity.this, ContactUsActivity.class));
                        break;
                    case R.id.action_about:
                        //Toast.makeText(ContactUsActivity.this, "@string/abouUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
                        break;
                }
                return true;
            }
        });


        SeekBar seekBar = findViewById(R.id.seekbar);


        seekBar.setMax(255);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




                android.provider.Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        askPermission(this);


    }

    public void askPermission(final Context c){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(Settings.System.canWrite(c)){

                //you have permission

            }
            else {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        c.startActivity(i);
                    }
                }, DELAY);


                Toast.makeText(SettingsActivity.this, "Please allow the app permission", Toast.LENGTH_LONG).show();

            }




        }

    }

    public void checkupdate(){


        final Button chkbtn = findViewById(R.id.checkupdatebtn);
        final String ckup = getString(R.string.ckupd);





        
        chkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SettingsActivity.this, ckup, Toast.LENGTH_SHORT).show();
                chkbtn.setTextColor(getResources().getColor(R.color.green));
            //Color(getResources().getColor(R.color.green));

            }
        });
        


    }




}