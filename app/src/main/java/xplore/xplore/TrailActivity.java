package xplore.xplore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        int objNum =  i.getParcelableExtra("trailObject");
        

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
