package weather.app.simpleweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private static int DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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


}