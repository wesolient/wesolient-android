package com.vastausf.wesolient.model

import androidx.room.*
import com.vastausf.wesolient.model.data.Workspace
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkspaceDao {
    @Query("SELECT * FROM workspace WHERE id == :id")
    fun getById(id: String): Flow<Workspace?>

    @Query("SELECT * FROM workspace")
    fun getAll(): Flow<List<Workspace>>

    @Insert
    fun insert(workspace: Workspace): Long

    @Update
    fun update(workspace: Workspace): Int

    @Delete
    fun delete(workspace: Workspace): Int
    @Query("DELETE FROM workspace WHERE id == :id")
    fun deleteById(id: String): Int
}