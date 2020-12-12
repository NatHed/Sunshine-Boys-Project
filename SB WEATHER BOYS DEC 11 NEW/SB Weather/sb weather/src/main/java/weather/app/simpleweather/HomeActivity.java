package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import weather.app.simpleweather.Fragments.AboutFragment;
import weather.app.simpleweather.Fragments.HomeFragment;
import weather.app.simpleweather.Fragments.Room2Fragment;
import weather.app.simpleweather.Fragments.Room3Fragment;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
   // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelActivity> data;
    static View.OnClickListener myOnClickListener;
    GoogleSignInClient mGoogleSignInClient;

    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Toronto&appid=23f04464b7119837cf1dc4fa8b39caa3&units=metric";

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

        new ReadJSONFeedTask().execute(URL);


        BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;

                    case R.id.action_Room2:
                        Room2Fragment room2Fragment = new Room2Fragment();
                        FragmentTransaction transaction_room2 = getSupportFragmentManager().beginTransaction();
                        transaction_room2.replace(R.id.container_room2,room2Fragment);
                        transaction_room2.commit();
                        break;

                    case R.id.action_Room3:
                        Room3Fragment room3Fragment = new Room3Fragment();
                        FragmentTransaction transaction_room3 = getSupportFragmentManager().beginTransaction();
                        transaction_room3.replace(R.id.container_room3,room3Fragment);
                        transaction_room3.commit();
                        break;


                }
                return true;
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(new Intent(HomeActivity.this,HomeActivity.class));
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
       return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Fragment selectedFragment = null;
        switch(item.getItemId()){

            case R.id.logoutMenu: {
                firebaseAuth.signOut();
               /* mGoogleSignInClient.signOut();*/
                finish();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;
            }


            case R.id.refreshMenu:{
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                showsnackbar();
                break;
            }



            case R.id.settings: {

                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            }


            case R.id.aboutUsMenu: {
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;

            }

            case R.id.contactUsMenu: {
                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
                break;
            }


        }

        return super.onOptionsItemSelected(item);
    }


    public void showsnackbar()
    {
        String refresh = getString(R.string.refresh);
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),refresh,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }


    public String readJSONFeed(String address) {
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream content = new BufferedInputStream(
                    urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return stringBuilder.toString();
    }


    private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                //JSONArray jsonArray = new JSONArray(result);
                //Uncomment the two rows below to parse weather data from OpenWeatherMap
                JSONObject weatherJson = new JSONObject(result);

                //

                JSONObject dataObject = weatherJson.getJSONObject("main");
                JSONObject windsp_Object = weatherJson.getJSONObject("wind");


               /* temperature.setText(dataObject.getString("temp"));
                humidity.setText(dataObject.getString("humidity"));
                wind_speed.setText(windsp_Object.getString("speed"));
                air_pressure.setText(dataObject.getString("pressure"));*/

                String temperature = (dataObject.getString("temp"));
                String humidity = (dataObject.getString("humidity"));
                String wind_speed = (windsp_Object.getString("speed"));
                String pressure = (dataObject.getString("pressure"));

                String temp = getString(R.string.temperature);
                String humid = getString(R.string.humidity);
                String apressure = getString(R.string.windspeed);
                String wspeed = getString(R.string.airpressure);


                adapter = new AdapterActivity(data);
                recyclerView.setAdapter(adapter);
                data = new ArrayList<ModelActivity>();
                data.add(new ModelActivity
                        (temp, temperature + " °C"));
                data.add(new ModelActivity(humid, humidity + " %"));
                data.add(new ModelActivity(apressure, pressure + " pa"));
                data.add(new ModelActivity(wspeed, wind_speed + " km/h"));

                AdapterActivity winfo = new AdapterActivity(data,HomeActivity.this);
                recyclerView.setAdapter(winfo);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



}