package core.projectmanagementtool;

import core.projectmanagementtool.model.Issue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;

import org.json.JSONObject;


public class ListIssuesActivity extends AppCompatActivity {

    Issue[] issuesArray = {new Issue("Issue1","Sprint1"),new Issue("Issue2","Sprint1")};

    public void loadData(){
        ArrayAdapter adapter = new ArrayAdapter<Issue>(this,
                R.layout.activity_listview, issuesArray);

        ListView listView = (ListView) findViewById(R.id.listOfIssues);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_issues);
        this.loadData();

        ListView listView = (ListView) findViewById(R.id.listOfIssues);

        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                //Object item = adapter.getItemAtPosition(position);
                String jsonIssue = issuesArray[position].toJSON();
                Intent intent = new Intent(ListIssuesActivity.this,UpdateIssueActivity.class)
                        .putExtra("Issue", jsonIssue)
                        .putExtra("Position", position);
                startActivityForResult(intent,1);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String stringIssue = data.getStringExtra("Issue");
                int position = data.getIntExtra("Position",0);
                try {
                    JSONObject jsonIssue = new JSONObject(stringIssue);
                    issuesArray[position] = new Issue(jsonIssue.get("name").toString(),jsonIssue.get("sprint").toString());
                }catch(org.json.JSONException e){}
                this.loadData();
            }
        }
    }
}
