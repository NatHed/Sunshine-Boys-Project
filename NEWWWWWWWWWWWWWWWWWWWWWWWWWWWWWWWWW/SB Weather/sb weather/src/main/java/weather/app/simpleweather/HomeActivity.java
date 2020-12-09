package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
   // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelActivity> data;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();

        //Jeremy's Code

        recyclerView =(RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        String temp = getString(R.string.temperature);
        String humid = getString(R.string.humidity);
        String apressure = getString(R.string.windspeed);
        String wspeed = getString(R.string.airpressure);

        adapter = new AdapterActivity(data);
        recyclerView.setAdapter(adapter);
        data = new ArrayList<ModelActivity>();
        data.add(new ModelActivity
                ("" + temp,"0"));
        data.add(new ModelActivity("" + humid,"40%"));
        data.add(new ModelActivity("" + apressure,"100kpa"));
        data.add(new ModelActivity("" + wspeed,"10 Kmh"));

        AdapterActivity winfo = new AdapterActivity(data,HomeActivity.this);
        recyclerView.setAdapter(winfo);

        BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(HomeActivity.this, "@string/homePage", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;

                    case R.id.action_about:
                        //Toast.makeText(HomeActivity.this, "@string/abouUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                        break;
                }
                return true;
            }
        });
    }



    /*
    @Override public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.logoutMenu: {

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                break;
            }

            case R.id.aboutUsMenu: {
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;

            }

            case R.id.refreshMenu:{
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                break;
            }

            case R.id.contactUsMenu: {

                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
                break;
            }

            case R.id.settings: {

                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

 */
}