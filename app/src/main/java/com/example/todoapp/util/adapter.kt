package com.example.todoapp.util
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
class todoadapter( private var list:MutableList<Todo>):RecyclerView.Adapter<todoadapter.viewholder>() {
  private var listener:todoInterface?=null
    fun setListener(listener:todoInterface)
    {
        this.listener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return viewholder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       with(holder)
       {
           with(list[position])
           {
               holder.title.text=this.title
               holder.delete.setOnClickListener {
                   listener?.onDelte(this)
               }
           }
       }
    }
    inner class viewholder(item: View):RecyclerView.ViewHolder(item){
val title=item.findViewById<TextView>(R.id.titletxt)
        val delete=item.findViewById<ImageView>(R.id.del)
    }
    interface  todoInterface {
        fun onDelte(todo: Todo)
    }
}