package weather.app.simpleweather.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

import weather.app.simpleweather.AdapterActivity;
import weather.app.simpleweather.HomeActivity;
import weather.app.simpleweather.ModelActivity;
import weather.app.simpleweather.R;


public class Room3Fragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelActivity> data;
    static View.OnClickListener myOnClickListener;

    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Yuma&appid=23f04464b7119837cf1dc4fa8b39caa3&units=metric";



    @Override
    public void onCreate(Bundle savedInstanceState) {


        String temp = getString(R.string.temperature);
        String humid = getString(R.string.humidity);
        String apressure = getString(R.string.windspeed);
        String wspeed = getString(R.string.airpressure);

        super.onCreate(savedInstanceState);
        data = new ArrayList<ModelActivity>();
        data.add(new ModelActivity
                ("" + temp,"500"));
        data.add(new ModelActivity("" + humid,"400%"));
        data.add(new ModelActivity("" + apressure,"1000kpa"));
        data.add(new ModelActivity("" + wspeed,"1000 Kmh"));


        setHasOptionsMenu(true);


        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room3, container, false);

        recyclerView =(RecyclerView) view.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new ReadJSONFeedTask().execute(URL);


        BottomNavigationView bottomNavigationView2 = view.findViewById(R.id.bottom_navi_room3);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        /*Intent intent = new Intent(new Intent(getActivity(),HomeActivity.class));
                        startActivity(intent);*/


                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction transaction_home = getFragmentManager().beginTransaction();
                        transaction_home.replace(R.id.container_room3,homeFragment);
                        transaction_home.commit();

                        break;

                    case R.id.action_Room2:

                        Room2Fragment room2Fragment = new Room2Fragment();
                        FragmentTransaction transaction_room2 = getFragmentManager().beginTransaction();
                        transaction_room2.replace(R.id.container_room3,room2Fragment);
                        transaction_room2.commit();
                        break;


                    case R.id.action_Room3:
                        Room3Fragment room3Fragment = new Room3Fragment();
                        FragmentTransaction transaction_room3 = getFragmentManager().beginTransaction();
                        transaction_room3.replace(R.id.container_room3,room3Fragment);
                        transaction_room3.commit();
                        break;
                }
                return true;
            }
        });


        return view;
    }




    public String readJSONFeed(String address) {
        java.net.URL url = null;
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
                String apressure = getString(R.string.airpressure);
                String wspeed = getString(R.string.windspeed);


                adapter = new AdapterActivity(data);
                recyclerView.setAdapter(adapter);
                data = new ArrayList<ModelActivity>();
                data.add(new ModelActivity
                        (temp, temperature + " °C"));
                data.add(new ModelActivity(humid, humidity + " %"));
                data.add(new ModelActivity(apressure, pressure + " pa"));
                data.add(new ModelActivity(wspeed, wind_speed + " km/h"));

                AdapterActivity winfo = new AdapterActivity(data, getActivity());
                recyclerView.setAdapter(winfo);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}