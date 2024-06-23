package com.example.todoapp.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.util.Todo
import com.example.todoapp.util.todoadapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class home : Fragment(), add_todo.dialogsavebtnclicklistener, todoadapter.todoInterface {
private lateinit var binding:FragmentHomeBinding
private lateinit var auth:FirebaseAuth
private lateinit var database:DatabaseReference
private lateinit var popup:add_todo
private lateinit var adapter:todoadapter
private lateinit var list: MutableList<Todo>
private lateinit var navcontroller:NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        fetchdata()
        registerevents()
    }

    private fun fetchdata() {
database.addValueEventListener(object :ValueEventListener{
    override fun onDataChange(snapshot: DataSnapshot) {
        list.clear()
        for (tasksnapshot in snapshot.children)
        {
            val todo=tasksnapshot.key?.let {
                Todo(it,tasksnapshot.value.toString())
            }
            if (todo!=null)
            {
                list.add(todo)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onCancelled(error: DatabaseError) {
        Toast.makeText(context,"Sorry",Toast.LENGTH_SHORT).show()
    }

})
    }

    private fun registerevents() {
        binding.add.setOnClickListener {
            popup=add_todo()
            popup.setlistener(this)
            popup.show(
                childFragmentManager,"add_todo"
            )
        }
    }

    private fun init(view: View) {
navcontroller=Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance().reference.child("Tasks").child(auth.currentUser?.uid.toString())
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager=LinearLayoutManager(context)
        list= mutableListOf()
        adapter=todoadapter(list)
        adapter.setListener(this)
        binding.recycler.adapter=adapter
    }

    override fun onsavetodo(todo: String, todoet: EditText) {
        database.push().setValue(todo).addOnCompleteListener {
            if (it.isSuccessful)

            {
                Toast.makeText(context,"Todo Added",Toast.LENGTH_SHORT).show()
                todoet.text=null
            }
            else
            {
                Toast.makeText(context,"Sorry",Toast.LENGTH_SHORT).show()
            }
        }
    }
    

    override fun onDelte(todo: Todo) {
        database.child(todo.id).removeValue().addOnCompleteListener {
            if (it.isSuccessful)
            {
                Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Sorry",Toast.LENGTH_SHORT).show()
            }
        }
    }


}