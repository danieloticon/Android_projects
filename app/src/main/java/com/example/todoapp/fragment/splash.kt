package com.example.todoapp.fragment
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.google.firebase.auth.FirebaseAuth

class splash : Fragment() {
    private lateinit var  navccontrol: NavController
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=FirebaseAuth.getInstance()
        navccontrol=Navigation.findNavController(view)
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if (auth.currentUser!=null){

                navccontrol.navigate(R.id.action_splash_to_home2)
                                                          }
            else{
                navccontrol.navigate(R.id.action_splash_to_sign_in)
                                                          }

        },1500)
    }


}