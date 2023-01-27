package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)


        binding.add.setOnClickListener {
            itemlist.add(binding.editText.text.toString())
            binding.listView.adapter =  adapter
            adapter.notifyDataSetChanged()    // This is because every time when you add the item the input      space or the eidt text space will be cleared
            binding.editText.text.clear()
        }

        binding.delete.setOnClickListener {
            val position: SparseBooleanArray = binding.listView.checkedItemPositions
            val count = binding.listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        binding.clear.setOnClickListener {
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "You Selected the item --> "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()}
    }
}