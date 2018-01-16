package core.projectmanagementtool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    static boolean isInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try{
            if(!isInitialized){
                FirebaseDatabase.getInstance().set
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        mAuth = FirebaseAuth.getInstance();
    }
}
