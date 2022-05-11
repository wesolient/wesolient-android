package com.vastausf.wesolient.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Workspace(
    @PrimaryKey var uid: String = UUID.randomUUID().toString(),
    var title: String,
    var link: String
)
