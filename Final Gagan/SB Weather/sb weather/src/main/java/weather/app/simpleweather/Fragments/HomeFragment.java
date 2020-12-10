package weather.app.simpleweather.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import weather.app.simpleweather.AboutActivity;
import weather.app.simpleweather.AdapterActivity;
import weather.app.simpleweather.ContactUsActivity;
import weather.app.simpleweather.LoginActivity;
import weather.app.simpleweather.ModelActivity;
import weather.app.simpleweather.R;
import weather.app.simpleweather.SettingsActivity;

public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelActivity> data;
    static View.OnClickListener myOnClickListener;

    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Toronto&appid=23f04464b7119837cf1dc4fa8b39caa3&units=metric";


    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        String temp = getString(R.string.temperature);
        String humid = getString(R.string.humidity);
        String apressure = getString(R.string.windspeed);
        String wspeed = getString(R.string.airpressure);

        super.onCreate(savedInstanceState);
        data = new ArrayList<ModelActivity>();
        data.add(new ModelActivity
                ("" + temp,"0"));
        data.add(new ModelActivity("" + humid,"40%"));
        data.add(new ModelActivity("" + apressure,"100kpa"));
        data.add(new ModelActivity("" + wspeed,"10 Kmh"));


        setHasOptionsMenu(true);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Fragment selectedFragment = null;
        switch(item.getItemId()){

            case R.id.logoutMenu: {

                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            }

            case R.id.settingsMenu: {

                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            }

            case R.id.aboutMenu: {

                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            }

            case R.id.contactMenu: {

                startActivity(new Intent(getActivity(), ContactUsActivity.class));
                break;
            }


            case R.id.refreshMenu:{
                selectedFragment = new HomeFragment();
                showsnackbar();
                break;
            }


        }

        return super.onOptionsItemSelected(item);
    }

    public void showsnackbar()
    {
        String refresh = getString(R.string.refresh);
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),refresh,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView =(RecyclerView) view.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*String temp = getString(R.string.temperature);
        String humid = getString(R.string.humidity);
        String apressure = getString(R.string.windspeed);
        String wspeed = getString(R.string.airpressure);

        adapter = new Adapter(data);
        recyclerView.setAdapter(adapter);
        data = new ArrayList<Model>();
        data.add(new Model
                ("" + temp,"0"));
        data.add(new Model("" + humid,"40%"));
        data.add(new Model("" + apressure,"100kpa"));
        data.add(new Model("" + wspeed,"10 Kmh"));

        Adapter winfo = new Adapter(data, getActivity());
        recyclerView.setAdapter(winfo);*/
        new ReadJSONFeedTask().execute(URL);

        return view;
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
