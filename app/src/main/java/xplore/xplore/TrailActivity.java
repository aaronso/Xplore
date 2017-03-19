package xplore.xplore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jessicango on 2017-03-19.
 */

public class TrailActivity extends AppCompatActivity implements View.OnClickListener{
    Trail theTrail = new Trail();
    TextView placeName;
    TextView difficulty;
    TextView difficultyLevel;
    TextView travel;
    TextView travelTime;
    TextView duration;
    TextView durationDesc;
    TextView roundTrip;
    TextView roundTripDesc;
    TextView elevationGain;
    TextView elevationGainDesc;
    TextView season;
    TextView seasonDesc;
    Button navButton;
    Typeface quickBold, quickReg, quickMed;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_info);
        setButtonsAndTextViews();
        loadCustomFonts();
        setCustomFonts();
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

    private void setButtonsAndTextViews(){
        placeName = (TextView)findViewById(R.id.place_name);
        difficulty = (TextView)findViewById(R.id.diff);
        difficultyLevel = (TextView)findViewById(R.id.diff_desc);
        travel = (TextView)findViewById(R.id.travel);
        travelTime = (TextView)findViewById(R.id.travel_time);
        duration = (TextView)findViewById(R.id.duration);
        durationDesc = (TextView)findViewById(R.id.duration_desc);
        roundTrip = (TextView)findViewById(R.id.roundtrip);
        roundTripDesc = (TextView)findViewById(R.id.roundtrip_desc);
        elevationGain = (TextView)findViewById(R.id.elevation_gain);
        elevationGainDesc = (TextView)findViewById(R.id.elevation_gain_desc);
        season = (TextView)findViewById(R.id.season);
        seasonDesc = (TextView)findViewById(R.id.season_desc);
        navButton = (Button)findViewById(R.id.navigate_button);
    }

    private void loadCustomFonts(){
        quickBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.ttf");
        quickReg = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.ttf");
        quickMed = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Medium.ttf");
        //splashButton.setTypeface(FontTypo);

    }

    private void setCustomFonts(){
        placeName.setTypeface(quickReg);

        //header fonts
        difficulty.setTypeface(quickBold);
        travel.setTypeface(quickBold);
        duration.setTypeface(quickBold);
        roundTrip.setTypeface(quickBold);
        elevationGain.setTypeface(quickBold);
        season.setTypeface(quickBold);

        //body fonts
        difficultyLevel.setTypeface(quickMed);
        travelTime.setTypeface(quickMed);
        durationDesc.setTypeface(quickMed);
        roundTripDesc.setTypeface(quickMed);
        elevationGainDesc.setTypeface(quickMed);
        seasonDesc.setTypeface(quickMed);

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
