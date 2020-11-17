package weather.app.simpleweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ContactUsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        TextView githublk = (TextView) findViewById(R.id.Git);
        githublk.setMovementMethod(LinkMovementMethod.getInstance());
    }
}