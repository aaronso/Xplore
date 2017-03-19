package xplore.xplore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

/**
 * Created by aaron on 2017-03-18.
 */

public class ImageAdapter extends BaseAdapter {
    //private Context mContext;
    private LayoutInflater inflater;
    ImageAdapter adapter;
    Trail allTrails[] = new Trail[128];

    ViewHolder holder;

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
                            JSONObject trail = data.getJSONObject(i);
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
                            allTrails[i].url = trail.getString("image");

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


    public ImageAdapter(Context c) {
        inflater = LayoutInflater.from(c);
        for (int i=0; i < 128 ;i++)
            allTrails[i] = new Trail();

        try {
            getTrails();
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //mContext = c;
    }


    public int getCount() {
        return 128;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }



    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        JSONObject trail;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();

            holder.thumbTrail = (ImageView) convertView.findViewById(R.id.hikePic);
            holder.nameTrail =  (TextView) convertView.findViewById(R.id.hikeName);
            holder.trailDiff = (TextView) convertView.findViewById(R.id.hikeDiff);
            holder.locTrail = (TextView) convertView.findViewById(R.id.hikeLoc);
            holder.lengthTrail = (TextView) convertView.findViewById(R.id.hikeLen);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        try {
//            getTrails();
//            notifyDataSetChanged();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            //String dataJSON  = ("http://138.197.140.12/api");
//            JSONObject obj;
//            obj = new JSONObject(dataJSON);
//            JSONArray data = obj.getJSONArray("data");
//            trail = data.getJSONObject(position);
//            holder.thumbTrail.setImageResource(mThumbIds[position]);
//            holder.nameTrail.setText(trail.getString("name"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

            holder.thumbTrail.setImageResource(R.drawable.sample_2);

            holder.nameTrail.setText(allTrails[position].name);
            holder.trailDiff.setText(allTrails[position].difficulty);
            holder.locTrail.setText(allTrails[position].name);
            holder.lengthTrail.setText(allTrails[position].length);

            if (allTrails[position].bmp != null){
                holder.thumbTrail.setImageBitmap(allTrails[position].bmp);
            }

        return convertView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2,
            R.drawable.sample_2,
            R.drawable.sample_2,
            R.drawable.sample_2,
            R.drawable.sample_2,
            R.drawable.sample_2,
            R.drawable.sample_2,


    };

    static class Trail {
        String url ="https://www.vancouvertrails.com/images/photos/lindeman-lake-1.jpg";
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

    static class ViewHolder {
        ImageView thumbTrail;
        TextView nameTrail;
        TextView trailDiff;
        TextView locTrail;
        TextView lengthTrail;
    }



}
