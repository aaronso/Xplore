package xplore.xplore;

import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jessicango on 2017-03-19.
 */

public class TrailActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_info);
    }

    @Override
    public void onClick(View v) {

    }
}
