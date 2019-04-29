package com.sangxiang.mvvm.sqlite

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey
    var id: Int=0,
    var imageUrl: String = "",
    var videoUrl:String = ""
)