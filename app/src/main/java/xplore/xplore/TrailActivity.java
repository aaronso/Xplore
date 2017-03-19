package xplore.xplore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    int objNum;
    Typeface quickBold, quickReg, quickMed;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_info);
        try {
            get1Trail();

        } catch (Exception e) {


        }
        while (flag){}
        setButtonsAndTextViews();
        //loadCustomFonts();
        //setCustomFonts();
        setButtonsAndTextViews();
        Intent i = getIntent();
        objNum = i.getIntExtra("trailObject", 0);

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

    private void updateTextViews(){
        placeName.setText(theTrail.name);
        difficultyLevel.setText(theTrail.difficulty);
        travelTime.setText(theTrail.transportation);
        durationDesc.setText(theTrail.time);
        roundTripDesc.setText(theTrail.length);
        elevationGainDesc.setText(theTrail.elevation);
        seasonDesc.setText(theTrail.season);
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

    final OkHttpClient client = new OkHttpClient();

    public void get1Trail() throws Exception {
        Request request = new Request.Builder()
                .url("http://138.197.140.12/api")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                String jsonData = response.body().string();
                try {
                    JSONObject obj = new JSONObject(jsonData);
                    JSONArray data = obj.getJSONArray("data");
                        JSONObject trail = data.getJSONObject(objNum);
                        theTrail.id = trail.getString("id");
                        theTrail.name = trail.getString("name");
                        theTrail.time = trail.getString("time");
                        theTrail.lat = Float.valueOf(trail.getString("lat"));
                        theTrail.lon = Float.valueOf(trail.getString("lon"));
                        theTrail.length = trail.getString("length");
                        theTrail.difficulty = trail.getString("difficulty");
                        theTrail.elevation = trail.getString("elevation");
                        theTrail.season = trail.getString("season");
                        theTrail.transportation = trail.getString("transportation");
                        theTrail.url = trail.getString("thumbnail");

                        setButtonsAndTextViews();





                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

        });
        flag = false;
        setButtonsAndTextViews();
    }
}
