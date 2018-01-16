package core.projectmanagementtool;

import core.projectmanagementtool.model.Issue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class ListIssuesActivity extends AppCompatActivity {


    List<Issue> issuesArray = new ArrayList<Issue>(5);
    //issuesArray =
    //Issue[] issuesArray = {new Issue("Issue1","Sprint1"),new Issue("Issue2","Sprint1")};

    public void loadData(){

        //issuesArray.add(new Issue("name1","sprint1"));

        final ArrayAdapter adapter = new ArrayAdapter<Issue>(this,
                R.layout.activity_listview, issuesArray);

        final ListView listView = (ListView) findViewById(R.id.listOfIssues);
        listView.setAdapter(adapter);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("issues");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                issuesArray.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Issue post = postSnapshot.getValue(Issue.class);
                    Log.d("post",post.toString());
                    issuesArray.add(post);
                    listView.setAdapter(adapter);
                    //Log.e("Get Data", post.<YourMethod> ());
                }

                /*
                Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                //ArrayList<Object> td = (ArrayList<Object>) dataSnapshot.getValue();
                //Log.d("ArrayDataSet",td.toString());

                //Object[] issuesArr = new Object[td.size()];
                //issuesArr = td.toArray(issuesArr);

                Collection<Object> values = td.values();
                Log.d("UpdatedValues", values.toString());

                for (Object e: values){
                    Log.d("UpdatedValues", e.toString());
                    Log.d("Class",e.getClass().toString());
                    //issuesArray[0] = new Issue(e.get(sprint));
                }

                //notifyDataSetChanged();*/
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: " ,firebaseError.getMessage());
            }
        });



        // This doesn't include databases

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
                String jsonIssue = issuesArray.get(position).toJSON();
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
                    issuesArray.set(position, new Issue(jsonIssue.get("name").toString(),jsonIssue.get("sprint").toString()));
                }catch(org.json.JSONException e){}
                this.loadData();
            }
        }
    }
}
