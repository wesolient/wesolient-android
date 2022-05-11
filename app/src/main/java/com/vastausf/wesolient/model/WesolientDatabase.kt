package com.vastausf.wesolient.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vastausf.wesolient.model.data.Workspace

@Database(entities = [Workspace::class], version = 1)
abstract class WesolientDatabase : RoomDatabase() {
    abstract fun workspaceDao(): WorkspaceDao
}
