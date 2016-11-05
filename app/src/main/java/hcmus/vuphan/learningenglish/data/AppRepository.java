package hcmus.vuphan.learningenglish.data;

import java.util.List;

import javax.inject.Inject;

import hcmus.vuphan.learningenglish.data.local.AppLocalDataStore;
import hcmus.vuphan.learningenglish.data.local.models.Lesson;
import hcmus.vuphan.learningenglish.data.remote.AppRemoteDataStore;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by vuphan on 05/11/2016.
 */

public class AppRepository implements AppDataStore {
    private AppLocalDataStore mAppLocalDataStore;
    private AppRemoteDataStore mAppRemoteDataStore;

    @Inject
    public AppRepository(AppLocalDataStore localDataStore, AppRemoteDataStore remoteDataStore) {
        this.mAppLocalDataStore = localDataStore;
        this.mAppRemoteDataStore = remoteDataStore;
    }

    @Override
    public Observable<List<Lesson>> getAllLesson() {
        return Observable.concat(mAppLocalDataStore.getAllLesson(), mAppRemoteDataStore.getAllLesson())
                .first(new Func1<List<Lesson>, Boolean>() {
                    @Override
                    public Boolean call(List<Lesson> lessons) {
                        return lessons != null;
                    }
                });
    }
}
