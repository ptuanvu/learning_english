package hcmus.vuphan.learningenglish.mainscreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hcmus.vuphan.learningenglish.App;
import hcmus.vuphan.learningenglish.R;
import hcmus.vuphan.learningenglish.data.AppRepository;
import hcmus.vuphan.learningenglish.data.local.InitialDefaultData;

public class MainActivity extends AppCompatActivity {

    @Inject
    AppRepository repository;
    @Inject
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getAppComponent().inject(this);

        if (preferences.getBoolean("first_run", true)) {
            InitialDefaultData initialDefaultData = new InitialDefaultData(repository, getApplicationContext());
            initialDefaultData.initData();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("first_run", false);
            editor.apply();
        }
    }


}
