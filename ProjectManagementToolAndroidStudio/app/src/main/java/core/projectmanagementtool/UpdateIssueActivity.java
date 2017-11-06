package core.projectmanagementtool;

import java.io.*;

import core.projectmanagementtool.model.Issue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;


public class UpdateIssueActivity extends AppCompatActivity {

    Issue currentIssue;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_issue);
        String stringIssue = getIntent().getStringExtra("Issue");
        try {
            JSONObject jsonIssue = new JSONObject(stringIssue);
            currentIssue = new Issue(jsonIssue.get("name").toString(),jsonIssue.get("sprint").toString());
            position = getIntent().getIntExtra("Position",0);
            final EditText nameField = (EditText) findViewById(R.id.editTextName);
            final EditText sprintField = (EditText) findViewById(R.id.editTextSprint);
            nameField.setText(currentIssue.getName());
            sprintField.setText(currentIssue.getSprint());
        }catch(org.json.JSONException e){}
    }

    public void updateIssue(View button){
        final EditText nameField = (EditText) findViewById(R.id.editTextName);
        final EditText sprintField = (EditText) findViewById(R.id.editTextSprint);
        String name = nameField.getText().toString();
        String sprint = sprintField.getText().toString();
        this.currentIssue.setName(name);
        this.currentIssue.setSprint(sprint);

        String jsonIssue = currentIssue.toJSON();
        Intent intent = new Intent();
        intent.putExtra("Issue", jsonIssue).putExtra("Position",position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
