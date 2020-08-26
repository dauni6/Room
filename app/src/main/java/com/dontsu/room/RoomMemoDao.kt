package com.dontsu.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

//Room은 데이터베이스에 읽고 쓰는 메서드를 인터페이스 형태로 설계하고 사용함.
//코드 없이 이름만 명시하는 형태로 인터페이스를 만들면 Room이 나머지 코드를 자동생성함.
@Dao
interface RoomMemoDao {
    @Query("select * from orm_memo")
    fun getAll(): MutableList<RoomMemo>

    @Insert(onConflict = REPLACE)
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)
}
