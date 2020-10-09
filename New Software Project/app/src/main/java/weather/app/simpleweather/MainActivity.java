package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
               checkInfo(uEmail.getText().toString(), uPassword.getText().toString());

            }
        });

        //this takes them to register page
        userLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
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
    }



}




    