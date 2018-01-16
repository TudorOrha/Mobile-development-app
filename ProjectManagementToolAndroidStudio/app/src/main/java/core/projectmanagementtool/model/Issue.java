package core.projectmanagementtool.model;

import java.io.Serializable;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by tudor on 04-Nov-17.
 */

public class Issue implements Serializable {
    private String name;
    private String sprint;

    public Issue(){
        this.name = "NoArgsConst";
        this.sprint = "yup";
    }

    public Issue(String name,String sprint){
        this.name = name;
        this.sprint = sprint;
    }

    public String getName(){
        return this.name;
    }

    public String getSprint(){
        return this.sprint;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSprint(String sprint){
        this.sprint = sprint;
    }

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", getName());
            jsonObject.put("sprint", getSprint());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String toString(){
        return this.name;
    }

}
