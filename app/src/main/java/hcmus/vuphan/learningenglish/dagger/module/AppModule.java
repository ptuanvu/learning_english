package hcmus.vuphan.learningenglish.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hcmus.vuphan.learningenglish.App;

/**
 * Created by vuphan on 05/11/2016.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(App app) {
        this.mApplication = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
