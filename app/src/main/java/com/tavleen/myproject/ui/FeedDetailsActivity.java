package com.tavleen.myproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.tavleen.myproject.R;

public class FeedDetailsActivity extends AppCompatActivity {

    TextView txt;

    int month = 0;

    void initViews(){
        txt=findViewById(R.id.textViewFeedDetails);

        month = getIntent().getIntExtra("keyMonths",0);

        switch (month){
            case 1:
                txt.setText(getText(R.string.zerotofour));
                break;

            case 2:
                txt.setText(getText(R.string.fourtosix));
                break;

            case 3:
                txt.setText(getText(R.string.sixtonine));
                break;

            case 4:
                txt.setText(getText(R.string.ninetotwelve));
                break;

            case 5:
                txt.setText(getText(R.string.twelvetoeighteen));
                break;

            case 6:
                txt.setText(getText(R.string.eighteentotwentyfour));
                break;

            case 7:
                txt.setText(getText(R.string.twentyfour));
                break;

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);
        initViews();
    }

}
