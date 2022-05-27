package com.vastausf.wesolient.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Workspace(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String,
    var link: String
)
