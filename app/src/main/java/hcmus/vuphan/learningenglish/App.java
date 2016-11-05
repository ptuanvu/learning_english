package hcmus.vuphan.learningenglish;

import android.app.Application;

import hcmus.vuphan.learningenglish.dagger.component.AppComponent;
import hcmus.vuphan.learningenglish.dagger.component.DaggerAppComponent;
import hcmus.vuphan.learningenglish.dagger.module.AppModule;
import hcmus.vuphan.learningenglish.dagger.module.DataModule;

/**
 * Created by vuphan on 05/11/2016.
 */

public class App extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule("someurl_here"))
                .build();
    }


    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
