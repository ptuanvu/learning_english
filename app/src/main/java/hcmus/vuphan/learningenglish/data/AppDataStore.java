package hcmus.vuphan.learningenglish.data;

import java.util.List;

import hcmus.vuphan.learningenglish.data.local.models.Lesson;
import rx.Observable;

/**
 * Created by vuphan on 05/11/2016.
 */

public interface AppDataStore {
    Observable<List<Lesson>> getAllLesson();
}

