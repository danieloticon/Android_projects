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
import com.example.todoapp.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class sign_in : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var  navccontrol: NavController
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerevent()
    }

    private fun registerevent() {
        binding.signuptxt.setOnClickListener {
            navccontrol.navigate(R.id.action_sign_in_to_sign_up)
        }
        binding.signinbtn.setOnClickListener {
            var email:String=binding.emailsignin.text.toString()
            var password:String=binding.passwordsignin.text.toString()

            if (email.isNotEmpty()&&password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context,"congratulation", Toast.LENGTH_SHORT).show()
                        navccontrol.navigate(R.id.action_sign_in_to_home2)
                    }
                    else{
                        Toast.makeText(context,"Sorry", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                binding.passwordsignin.error="Error"
                binding.emailsignin.error="Error"

            }
        }
    }

    private fun init(view:View)
    {
        navccontrol= Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
    }


}