package hcmus.vuphan.learningenglish.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import hcmus.vuphan.learningenglish.dagger.module.AppModule;
import hcmus.vuphan.learningenglish.dagger.module.DataModule;
import hcmus.vuphan.learningenglish.data.remote.AppRemoteDataStore;
import hcmus.vuphan.learningenglish.mainscreen.MainActivity;

/**
 * Created by vuphan on 05/11/2016.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(AppRemoteDataStore appRemoteDataStore);
}
