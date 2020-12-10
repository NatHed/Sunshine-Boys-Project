package weather.app.simpleweather;

public class ModelActivity {

    String weatherName, weatherVal;


    public ModelActivity(String wtext, String wName) {
        this.weatherName = wtext;
        this.weatherVal = wName;


    }


public String getWeatherName()
{
    return weatherName;
}



public String getWeatherval()
    {
        return weatherVal;
    }


}
