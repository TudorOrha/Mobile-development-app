package core.projectmanagementtool.controller;

import core.projectmanagementtool.model.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tudor on 12/6/2017.
 */

public class Controller {

    //Issue[] issuesArray = {new Issue("Issue1","Sprint1"),new Issue("Issue2","Sprint1")};
    List<Issue> issuesList;

    public Controller(){
        issuesList = new ArrayList<>();
        issuesList.add(new Issue("Issue1","Sprint1"));
        issuesList.add(new Issue("Issue2","Sprint1"));
    }

    public void add(Issue issue){
        issuesList.add(issue);
    }

    public List<Issue> getIssues(){
        return issuesList;
    }

}
