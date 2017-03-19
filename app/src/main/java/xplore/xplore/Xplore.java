package xplore.xplore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Xplore extends AppCompatActivity {

    Trail allTrails[] = new Trail[128];
    JSONObject trail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for (int i=0; i < 128 ;i++)
            allTrails[i] = new Trail();

        try {
            getTrails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore);



        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(Xplore.this, TrailActivity.class);



                i.putExtra("trailObject", (Parcelable) trail);
                startActivity(i);
            }
        });

    }


    final OkHttpClient client = new OkHttpClient();

    public void getTrails() throws Exception {
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
                    int n = data.length();
                    for (int i = 0; i < 128; i++){
                        trail = data.getJSONObject(i);
                        allTrails[i].id = trail.getString("id");
                        allTrails[i].name = trail.getString("name");
                        allTrails[i].time = trail.getString("time");
                        allTrails[i].lat = Float.valueOf(trail.getString("lat"));
                        allTrails[i].lon = Float.valueOf(trail.getString("lon"));
                        allTrails[i].length = trail.getString("length");
                        allTrails[i].difficulty = trail.getString("difficulty");
                        allTrails[i].elevation = trail.getString("elevation");
                        allTrails[i].season = trail.getString("season");
                        allTrails[i].transportation = trail.getString("transportation");
                        allTrails[i].url = trail.getString("thumbnail");

                        URL url = null;
                        try {
                            url = new URL(allTrails[i].url);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        Bitmap bmp = null;
                        try {
                            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //notifyDataSetChanged();


            }
        });
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




