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

        splashButton = (Button)findViewById(R.id.splash_button);
        splashButton.setOnClickListener(this);

        loadCustomFont();
    }

    private void loadCustomFont(){
        Typeface FontTypo = Typeface.createFromAsset(getAssets(), "fonts/Typo_Round_Thin_Demo.ttf");
        splashButton.setTypeface(FontTypo);

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Xplore.class);
        startActivity(i);
        // need to add code to get to trail info layout
    }
}
