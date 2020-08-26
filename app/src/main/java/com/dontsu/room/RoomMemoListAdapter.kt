package com.dontsu.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*
import timber.log.Timber
import java.text.SimpleDateFormat

class RoomMemoListAdapter(var listData: MutableList<RoomMemo>, var helper: RoomHelper?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(newList : MutableList<RoomMemo>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recycler, parent, false)
        return RoomMemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when (holder) {
           is RoomMemoViewHolder -> {
               Timber.d("---onBindViewHolder : $position")
               val RoomMemo = listData[position]
               holder.setRoomMemo(RoomMemo)
           }
       }
    }

    inner class RoomMemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mRoomMemo: RoomMemo? = null

        init {
            itemView.deleteBtn.setOnClickListener {
                helper?.roomMemoDao()?.delete(mRoomMemo!!)
                listData.remove(mRoomMemo)
                notifyDataSetChanged()
            }

            itemView.updateBtn.setOnClickListener {
                val memo = RoomMemo(mRoomMemo!!.no, "수정됨", System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)
                val list = helper?.roomMemoDao()?.getAll()!!
                updateList(list)
            }
        }

        fun setRoomMemo(RoomMemo: RoomMemo) {
            itemView.textNo.text = "${RoomMemo.no}"
            itemView.textContent.text = "${RoomMemo.content}"
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            itemView.textDatetime.text = sdf.format(RoomMemo.datetime)

            this.mRoomMemo = RoomMemo
        }

    }

}
