package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactUsActivity extends AppCompatActivity {
    private final static int Request_code_permissions = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        if (ContextCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            permissions_dialog();


        }









        /*BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(ContactUsActivity.this, "@string/homePage", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ContactUsActivity.this, HomeActivity.class));
                        break;

                    case R.id.action_about:
                        //Toast.makeText(ContactUsActivity.this, "@string/abouUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ContactUsActivity.this, AboutActivity.class));
                        break;
                }
                return true;
            }
        });*/


    }


    public void permissions_dialog() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("For making phone calls from app")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(ContactUsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Request_code_permissions);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();

        } else {

            ActivityCompat.requestPermissions(ContactUsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Request_code_permissions);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case Request_code_permissions:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        finish();
                        return;
                    }
                }
        }
    }
}


