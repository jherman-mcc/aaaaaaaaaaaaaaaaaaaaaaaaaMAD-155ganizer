package com.example.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.organizer.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.widget.*
import java.util.*


open class MyAdapter2 : AppCompatActivity() {
    var listItems = ArrayList<String>()
//    var adapter: ArrayAdapter<String>?= null
    // Define the variable of CalendarView type
    // and TextView type;
    var calendar: CalendarView? = null
    var date_view: TextView? = null
    var addButton: Button? = null
    var getValue: TextView? = null
    var ListElements = arrayOf<String>()
    lateinit var listview: ListView
    lateinit var fab1: FloatingActionButton
    lateinit var fab2: FloatingActionButton
    lateinit var undoOnClickListener1: View.OnClickListener
    lateinit var undoOnClickListener2: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_adapter2)
        listview = findViewById(R.id.listView1)
        addButton = findViewById(R.id.button1)
        getValue = findViewById(R.id.textView1)
        // By ID we can use each component
        // which id is assign in xml file
        // use findViewById() to get the
        // CalendarView and TextView
        calendar = findViewById<View>(R.id.calendar) as CalendarView
        date_view = findViewById<View>(R.id.date_view) as TextView
        var getValue2 = getValue
        val ListElementsArrayList: MutableList<String> = ArrayList(Arrays.asList(*ListElements))
        var adapter = ArrayAdapter(
            this@MyAdapter2,
            android.R.layout.simple_list_item_1,
            ListElementsArrayList
        )

// Add a calendar date using Button1 to add it.

        with(listview) {
            this?.setAdapter(adapter)
        }
        with(addButton) {
            this?.setOnClickListener(View.OnClickListener {
            adapter.notifyDataSetChanged()
            })
        }
// Remove a line item from listView1 when selecting the item and pressing the bottom Fab1 button:

    fab1 = findViewById(R.id.FAB1)
        with(listview) {
            this?.setAdapter(adapter)
        }
        with(fab1) {
            this?.setOnClickListener(View.OnClickListener {
              with(ListElementsArrayList) { remove(getValue2?.getText().toString()) }
//            Disable the following line because the program shuts down when encountering it:
//            listItems.removeAt(listItems.size - 1)
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "Item removed", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            })
        }


    undoOnClickListener1 = View.OnClickListener {
        listItems.removeAt(listItems.size - 1)
        adapter?.notifyDataSetChanged()
        Snackbar.make(it, "Item removed", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    undoOnClickListener2 = View.OnClickListener {
        listItems.add("Item 1")
        adapter?.notifyDataSetChanged()
        Snackbar.make(it, "Item re-added", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    // Add Listener in calendar
    calendar!!
        .setOnDateChangeListener(
        CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
        // In this Listener have one method
        // and in this method we will
        // get the value of DAYS, MONTH, YEARS
        // Store the value of date with
        // format in String type Variable
        // Add 1 in month because month
        // index is start with 0

        var Date1: String? = ""

        Date1 = Date1 + (month + 1) + "-" + (dayOfMonth.toString())+ "-"+ year

        // set this date in TextView for Display
        date_view!!.text = Date1
        with(ListElementsArrayList) { add(date_view!!.text.toString()) }
                })
}

private fun addListItem() {
//    listItems.add("Item 1")
//    adapter?.notifyDataSetChanged()
}

private fun deleteListItem() {
//    listItems.remove("Item 1")
//    adapter?.notifyDataSetChanged()
 }

}

