package com.dontsu.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//tableName을 사용하지 않으면 현재 클래스 이름 그대로 룸 데이터베이스가 생성됨
@Entity(tableName = "orm_memo")
class RoomMemo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null ,

    @ColumnInfo
    var content: String = "",

    @ColumnInfo(name = "date")
    var datetime: Long = 0,

    @Ignore
    var temp: String = "ignore 사용하면 테이블의 요소로 사용하지 않음!"
){


}
