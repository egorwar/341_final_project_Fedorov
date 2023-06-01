package com.example.lecturekeep.dep_inj

import android.app.Application
import androidx.room.Room
import com.example.lecturekeep.feature_lecturewrite.BLL.repo.LectureRepo
import com.example.lecturekeep.feature_lecturewrite.BLL.usecase.usecase_AddLecture
import com.example.lecturekeep.feature_lecturewrite.BLL.usecase.usecase_DelLectures
import com.example.lecturekeep.feature_lecturewrite.BLL.usecase.usecase_GetLecture
import com.example.lecturekeep.feature_lecturewrite.BLL.usecase.usecase_GetLectures
import com.example.lecturekeep.feature_lecturewrite.BLL.usecase.usecases_Lectures
import com.example.lecturekeep.feature_lecturewrite.DAL.datasrc.LectureDB
import com.example.lecturekeep.feature_lecturewrite.DAL.repo.ImplLectureRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesLectureDB(app : Application) : LectureDB {
        return Room.databaseBuilder(
            app,
            LectureDB::class.java,
            LectureDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLecturesRepo(db: LectureDB): LectureRepo {
        return ImplLectureRepo(db.lectureDao)
    }

    @Provides
    @Singleton
    fun provideLectureUsecases(repo: LectureRepo) : usecases_Lectures {
        return usecases_Lectures(
            getLectures = usecase_GetLectures(repo),
            delLectures = usecase_DelLectures(repo),
            addLecture = usecase_AddLecture(repo),
            getLecture = usecase_GetLecture(repo)
        )
    }
}