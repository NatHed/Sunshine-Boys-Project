package nathaniel.hedman.sunshineboyscodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

public class ContactPageActivity extends AppCompatActivity {

    private TextView eTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_page);
    }


    public void Call(View v) {
        // Find the EditText by its unique ID
        TextView e = (TextView) findViewById(R.id.number);

        // show() method display the toast with message
        // "clicked"
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG)
                .show();

        // Use format with "tel:" and phoneNumber created is
        // stored in u.
        Uri u = Uri.parse("tel:" + e.getText().toString());

        // Create the intent and set the data for the
        // intent as the phone number.
        Intent i = new Intent(Intent.ACTION_DIAL, u);


        try {
            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            startActivity(i);
        } catch (SecurityException s) {
            // show() method display the toast with
            // exception message.
            Toast.makeText(this, s.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }

    }


    public void Email(View v) {
        // Find the EditText by its unique ID
        TextView e = (TextView) findViewById(R.id.EmailAddress);

        // show() method display the toast with message
        // "clicked"
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG)
                .show();

        // Use format with "tel:" and phoneNumber created is
        // stored in u.
        Uri u = Uri.parse(e.getText().toString());


        // Create the intent and set the data for the
        // intent as the Email.
        Intent emailIntent = new Intent(Intent.ACTION_SEND, u);
        emailIntent.setType("message/rfc822");



        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactPageActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }
}






