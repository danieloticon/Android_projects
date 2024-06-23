package com.example.todoapp.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
class sign_up : Fragment() {
private lateinit var binding: FragmentSignUpBinding
private lateinit var  navccontrol:NavController
private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerevent()
    }

    private fun registerevent() {
        binding.signintxt.setOnClickListener {
            navccontrol.navigate(R.id.action_sign_up_to_sign_in)
        }
        binding.signup.setOnClickListener {
            var email:String=binding.email.text.toString()
            var password:String=binding.password.text.toString()
            var confirm:String=binding.confirm.text.toString()
            if (email.isNotEmpty()&&password.isNotEmpty()&&confirm.isNotEmpty()&&password.equals(confirm)){
                binding.bar.visibility=View.VISIBLE
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context,"congratulation",Toast.LENGTH_SHORT).show()
                        navccontrol.navigate(R.id.action_sign_up_to_home2)
                    }
                    else{
                        Toast.makeText(context,"Sorry",Toast.LENGTH_SHORT).show()
                    }
                    binding.bar.visibility=View.INVISIBLE
                }
            }
            else{
                binding.email.error="Error"
                binding.password.error="Error"
                binding.confirm.error="Error"
            }
        }
    }

    private fun init(view:View)
    {
        navccontrol=Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
    }


    }
