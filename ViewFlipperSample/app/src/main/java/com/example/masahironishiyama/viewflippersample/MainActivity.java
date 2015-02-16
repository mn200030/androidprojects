package com.example.masahironishiyama.viewflippersample;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button nextButton;
    Button backButton;

    Animation inFromRightAnimation;
    Animation inFromLeftAnimation;
    Animation outToRightAnimation;
    Animation outToLeftAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = (Button)findViewById(R.id.nextButton);
        backButton = (Button)findViewById(R.id.backButton);
        viewFlipper = (ViewFlipper)findViewById(R.id.flipper);
        inFromRightAnimation = AnimationUtils.loadAnimation(this, R.anim.right_in);
        inFromLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.left_in);
        outToRightAnimation = AnimationUtils.loadAnimation(this, R.anim.right_out);
        outToLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.left_out);

        nextButton.setOnClickListener(this);
        backButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextButton:
                viewFlipper.setInAnimation(inFromRightAnimation);
                viewFlipper.setOutAnimation(outToLeftAnimation);
                viewFlipper.showNext();
                break;
            case R.id.backButton:
                viewFlipper.setInAnimation(inFromLeftAnimation);
                viewFlipper.setOutAnimation(outToRightAnimation);
                viewFlipper.showPrevious();
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
