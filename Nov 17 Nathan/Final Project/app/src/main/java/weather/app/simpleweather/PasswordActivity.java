package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText EmailResetPassword;
    private Button btnreset;
    private static int DELAY = 4000;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


        SeekBar seekBar = findViewById(R.id.seekbar);

        Context current = getApplicationContext();
        seekBar.setMax(255);
        boolean settingsCanWrite = Settings.System.canWrite(current);
        if (!settingsCanWrite) { // won't be able to change brightness if we can't write system settings
            requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS}, 101); // ...so ask for permission to do that
        }

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



        firebaseAuth = FirebaseAuth.getInstance();

        EmailResetPassword = (EditText) findViewById(R.id.resetEmailPassword);
        btnreset = (Button) findViewById(R.id.btnResetPassword);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = EmailResetPassword.getText().toString().trim();
                String email = getString(R.string.email);
                final String passreset = getString(R.string.passreset);
                final String linkerror = getString(R.string.linkerror);

                if(useremail.equals("")){

                    Toast.makeText(PasswordActivity.this,email, Toast.LENGTH_SHORT).show();

                }else{

                   firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {

                           if(task.isSuccessful()){

                               Toast.makeText(PasswordActivity.this, passreset, Toast.LENGTH_SHORT).show();
                               finish();
                               startActivity(new Intent(PasswordActivity.this, MainActivity.class));
                           }else {

                               Toast.makeText(PasswordActivity.this, linkerror, Toast.LENGTH_SHORT).show();

                           }
                       }
                   });
                }
            }
        });

    }



    public void askPermission(final Context c){

        final String permission = getString(R.string.permission);
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


                Toast.makeText(PasswordActivity.this, permission, Toast.LENGTH_LONG).show();

            }




        }

    }

}