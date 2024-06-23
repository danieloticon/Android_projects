package com.example.todoapp.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todoapp.databinding.FragmentAddTodoBinding
class add_todo : DialogFragment() {
private lateinit var binding: FragmentAddTodoBinding
private lateinit var listener: dialogsavebtnclicklistener

fun setlistener(listener:dialogsavebtnclicklistener){
    this.listener=listener
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentAddTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerevent()
    }

    private fun registerevent() {
        binding.todobtn.setOnClickListener {
            val title:String=binding.todoet.text.toString()
            if (title.isNotEmpty()){
listener.onsavetodo(title,binding.todoet)
            }
            else{
                binding.todoet.error="Error"
                Toast.makeText(context,"Fill",Toast.LENGTH_SHORT).show()
            }
        }

    }
    interface dialogsavebtnclicklistener{
        fun onsavetodo(todo:String,todoet:EditText)
    }
}