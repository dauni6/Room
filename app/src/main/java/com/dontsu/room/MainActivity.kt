package com.dontsu.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var helper: RoomHelper? = null
    private lateinit var memoListAdapter: RoomMemoListAdapter

    init {
        //Timber initialize
        Timber.plant(Timber.DebugTree())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helper = Room.databaseBuilder(this@MainActivity, RoomHelper::class.java, "room_memo")
            .allowMainThreadQueries()
            .build()

        recycler.apply {
            val list = helper?.roomMemoDao()?.getAll()!!
            memoListAdapter = RoomMemoListAdapter(list, helper)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = memoListAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        saveBtn.setOnClickListener {
            if (editMemo.text.isNotEmpty()) {
                val memo = RoomMemo(null, editMemo.text.toString(), System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)
                val list = helper?.roomMemoDao()?.getAll()!!
                memoListAdapter.updateList(list)
                editMemo.setText("")
            }
        }

    }

}
