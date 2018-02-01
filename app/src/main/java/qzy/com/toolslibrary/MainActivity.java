package qzy.com.toolslibrary;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import qzy.com.utilslib.Person;
import qzy.com.utilslib.svgText.VectorCompatTextView;

public class MainActivity extends AppCompatActivity {

    VectorCompatTextView homePageIcon;
    boolean isChecked = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homePageIcon = (VectorCompatTextView) findViewById(R.id.homePageIcon);
        //setSelectorColor(homePageIcon);

        homePageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = !isChecked;
                if(isChecked){
                    homePageIcon.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.colorf84848));
                }else{
                    homePageIcon.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.color333333));
                }
            }
        });
    }
}
