package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {
    private EditText uPassword, uEmail;
    private Button btnsgn;
    private TextView userLogin;

    private FirebaseAuth firebaseAuth;
    //private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private TextView btnforgetPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();


        btnsgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkEmptySpace()) {

                    checkInfo(uEmail.getText().toString(), uPassword.getText().toString());
                }
            }
        });

        //this takes them to register page
        userLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


        btnforgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });
    }


    private void checkInfo(String uEmail, String uPassword){

        progressDialog.setMessage("Please wait we are checking your credentials");
        progressDialog.show();

        

        firebaseAuth.signInWithEmailAndPassword(uEmail, uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }else{

                    Toast.makeText(MainActivity.this,"Login Failed, try again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    }
            }
        });
    }




    private void setupUI(){
        uPassword = (EditText) findViewById(R.id.Password);
        uEmail = (EditText) findViewById(R.id.Email);
        userLogin = (TextView) findViewById(R.id.nohaveAcc);
        btnsgn = (Button) findViewById(R.id.btnSignIn);
        btnforgetPassword = (TextView) findViewById(R.id.FrgtPassword);
    }


    private boolean checkEmptySpace(){

        boolean result = false;


        String checkpassword = uEmail.getText().toString();
        String checkemail = uPassword.getText().toString();

        if(checkemail.isEmpty() || checkpassword.isEmpty()) {

            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }

        return result;
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit this App?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.super.onBackPressed();

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}






    