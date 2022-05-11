package com.vastausf.wesolient.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vastausf.wesolient.model.data.Workspace
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkspaceDao {
    @Query("SELECT * FROM workspace WHERE uid == :uid")
    fun getByUid(uid: String): Flow<Workspace?>

    @Query("SELECT * FROM workspace")
    fun getAll(): Flow<List<Workspace>>

    @Insert
    fun insert(workspace: Workspace): Long

    @Query("DELETE FROM workspace WHERE uid == :uid")
    fun deleteById(uid: String): Int
}