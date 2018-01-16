package core.projectmanagementtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    static boolean isInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            if (!isInitialized) {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                isInitialized = true;
            } else {
                Log.d("RegisterActivity", "Already Initialized");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        mAuth = FirebaseAuth.getInstance();
    }

    public void loginButton(View view) {
        final EditText emailField = (EditText) findViewById(R.id.editTextEmail);
        String email = emailField.getText().toString();
        final EditText passwordField = (EditText) findViewById(R.id.editTextPassword);
        String password = passwordField.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            loginWithPassword(email, password);
        } else {
            Toast.makeText(this, "Email or password cannot be empty!", Toast.LENGTH_LONG).show();
        }
    }

     private void loginWithPassword(String email, String password) {
         mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {

                 if (task.isSuccessful()) {
                     Toast.makeText(getApplicationContext(), "Login " + mAuth.getCurrentUser().getUid(), Toast.LENGTH_LONG).show();
                     Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                     //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                     finish();
                 } else {
                     Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                 }

             }
         });
     }
}
