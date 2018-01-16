package core.projectmanagementtool;

import core.projectmanagementtool.model.Issue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.Map;
import java.util.HashMap;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


public class AddIssuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_issues);
    }

    public void addIssue(View button){
        final EditText nameField = (EditText) findViewById(R.id.editTextName);
        String name = nameField.getText().toString();
        final EditText sprintField = (EditText) findViewById(R.id.editTextPassword);
        String sprint = sprintField.getText().toString();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //mDatabase.child("issues").child("3").child("name").setValue(name);
        //mDatabase.child("issues").child("3").child("sprint").setValue(sprint);

        Issue issue = new Issue(name,sprint);
        mDatabase.child("issues").push().setValue(issue);
        //mDatabase.child("issues").push().child("name").setValue(name);
        //mDatabase.child("issues").push().child("sprint").setValue(sprint);




        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        String[] emails = {"tudor_orha@yahoo.com"};
        String emailTitle = "Collected data";
        String emailContent = "Name: " + name + "\nSprint: " + sprint;
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailTitle);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent);
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }
}
