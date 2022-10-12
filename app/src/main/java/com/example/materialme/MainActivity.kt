package com.example.materialme

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mSportsData: ArrayList<Sport>? = null
    private var mAdapter: SportsAdapter? = null
    private lateinit var resetButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById<View>(R.id.recycleView) as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mSportsData = ArrayList()

        mAdapter = SportsAdapter(this, mSportsData!!)
        mRecyclerView!!.adapter = mAdapter

        initializeData()

        val helper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition

                    mSportsData?.let { Collections.swap(it, from, to) }
                    mAdapter!!.notifyItemMoved(from, to)
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    mSportsData!!.removeAt(viewHolder.adapterPosition)
                    mAdapter!!.notifyItemRemoved(viewHolder.adapterPosition)
                }
            })
        helper.attachToRecyclerView(mRecyclerView)

        resetButton = findViewById(R.id.fabReset)
        resetButton.setOnClickListener {
            resetSports()
        }
    }

    private fun initializeData() {
        val sportsList = resources.getStringArray(R.array.sports_titles)
        val sportsInfo = resources.getStringArray(R.array.sports_info)
        val sportsImage = resources.obtainTypedArray(R.array.sports_image)

        mSportsData!!.clear()

        for (i in sportsList.indices) {
            mSportsData!!.add(Sport(sportsList[i], sportsInfo[i], sportsImage.getResourceId(i, 0)))
        }

        mAdapter!!.notifyDataSetChanged()
    }

    private fun resetSports() {
        initializeData()
    }
}