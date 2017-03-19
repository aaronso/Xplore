package xplore.xplore;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button splashButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        loadCustomFont();

        splashButton.setOnClickListener(this);

    }

    private void loadCustomFont(){
        splashButton = (Button)findViewById(R.id.splash_button);
        Typeface FontTypo = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.ttf");
        splashButton.setTypeface(FontTypo);

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Xplore.class);
        startActivity(i);
        // need to add code to get to trail info layout
    }
}
