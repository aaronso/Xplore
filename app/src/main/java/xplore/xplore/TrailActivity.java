package xplore.xplore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jessicango on 2017-03-19.
 */

public class TrailActivity extends AppCompatActivity implements View.OnClickListener{

    Trail theTrail = new Trail();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_info);
        Intent i = getIntent();
        JSONObject trailObj = (JSONObject) i.getParcelableExtra("trailObject");


        try {
            theTrail.id = trailObj.getString("id");
            theTrail.name = trailObj.getString("name");
            theTrail.time = trailObj.getString("time");
            theTrail.lat = Float.valueOf(trailObj.getString("lat"));
            theTrail.lon = Float.valueOf(trailObj.getString("lon"));
            theTrail.length = trailObj.getString("length");
            theTrail.difficulty = trailObj.getString("difficulty");
            theTrail.elevation = trailObj.getString("elevation");
            theTrail.season = trailObj.getString("season");
            theTrail.transportation = trailObj.getString("transportation");
            theTrail.url = trailObj.getString("thumbnail");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onClick(View v) {

    }

    static class Trail {
        String url ="https://www.vancouvertrails.com/images/photos-thumbnail/lindeman-lake-1.jpg";
        int pic = R.drawable.sample_2;
        Bitmap bmp = null;
        String id = "";
        String name = "";
        String time = "";
        float lat = 0;
        float lon = 0;
        String length = "";
        String difficulty = "";
        String elevation = "";
        String season = "";
        String transportation = "";

    }
}
