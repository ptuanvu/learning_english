package hcmus.vuphan.learningenglish.data.remote;

import java.util.List;

import hcmus.vuphan.learningenglish.data.AppDataStore;
import hcmus.vuphan.learningenglish.data.local.models.Lesson;
import rx.Observable;

/**
 * Created by vuphan on 05/11/2016.
 */

public class AppRemoteDataStore implements AppDataStore{
    @Override
    public Observable<List<Lesson>> getAllLesson() {
        return null;
    }
}
