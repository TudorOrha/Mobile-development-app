package core.projectmanagementtool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import core.projectmanagementtool.model.Issue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAddPage(View view) {
        Intent intent = new Intent(MainActivity.this,AddIssuesActivity.class);
        startActivity(intent);
    }

    public void goToListPage(View view){
        Intent intent = new Intent(MainActivity.this,ListIssuesActivity.class);
        startActivity(intent);
    }
}
