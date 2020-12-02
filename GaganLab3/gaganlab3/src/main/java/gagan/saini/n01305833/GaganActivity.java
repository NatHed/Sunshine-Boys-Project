package gagan.saini.n01305833;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class GaganActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.gagantoolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.gagandrawerid);
        navigationView = findViewById(R.id.gagannavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        // THIS WILL SAVE A FRAGMENT AS A MAIN SCREEN

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.gaganContainer_fragment, new GaHome());
        fragmentTransaction.commit();



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {


            case R.id.gaganNDopn1:
                Toast.makeText(this, R.string.opn1msgjava, Toast.LENGTH_SHORT).show();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.gaganContainer_fragment, new GaHome());
               // int color1 = getResources().getColor(R.color.purpleish);
               // getWindow().getDecorView().setBackgroundColor(color1);

                fragmentTransaction.commit();
                break;

            case R.id.gaganNDopn2:
                Toast.makeText(this, R.string.opn2msgjava, Toast.LENGTH_SHORT).show();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.gaganContainer_fragment, new GagSet());
                //int color2 = getResources().getColor(R.color.orange);
                //getWindow().getDecorView().setBackgroundColor(color2);
                fragmentTransaction.commit();
                break;
        }


        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.overflow_menu, menu);

        menu.findItem(R.id.gaganhelpopnid).setIntent(new Intent(this, SainiActivityHelp.class));
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(item.getIntent());
        super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onBackPressed() {

        String exit= getString(R.string.exit);
        String yes= getString(R.string.yes);
        String no=getString(R.string.no);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("" + exit)
                .setCancelable(false)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                    }
                })

                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }








}