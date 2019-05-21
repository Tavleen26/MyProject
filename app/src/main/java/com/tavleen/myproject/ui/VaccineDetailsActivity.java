package com.tavleen.myproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tavleen.myproject.R;

public class VaccineDetailsActivity extends AppCompatActivity {


    TextView txt;

    int index=0;

    void initViews(){
        txt=findViewById(R.id.textViewVaccineDetails);
        index=getIntent().getIntExtra("keyIndex",0);

        switch (index){
            case 0:
                txt.setText(getText(R.string.BCG));
                break;
            case 1:
                txt.setText(getText(R.string.DTP));
                break;
            case 2:
                txt.setText(getText(R.string.HepA));
                break;
            case 3:
                txt.setText(getText(R.string.HepB));
                break;
            case 4:
                txt.setText(getText(R.string.Hib));
                break;
            case 5:
                txt.setText(getText(R.string.IPV));
                break;
            case 6:
                txt.setText(getText(R.string.Influenza));
                break;
            case 7:
                txt.setText(getText(R.string.MMR));
                break;
            case 8:
                txt.setText(getText(R.string.Measles));
                break;
            case 9:
                txt.setText(getText(R.string.OPV));
                break;
            case 10:
                txt.setText(getText(R.string.PCV));
                break;

            case 11:
                txt.setText(getText(R.string.Rota));
                break;

            case 12:
                txt.setText(getText(R.string.Typhoid));
                break;

            case 13:
                txt.setText(getText(R.string.Varicella));
                break;

            case 14:
                txt.setText(getText(R.string.TDAP));
                break;



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);
        initViews();
    }
}
