package com.example.onording;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {
   TextView textlogin ;
   EditText username;
   EditText email;
   EditText pass;
   EditText confirmpass;
   Button button;
   float v ;
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
   {
       ViewGroup root = (ViewGroup)  inflater.inflate(R.layout.signup_tab_fragment, container , false);

   textlogin = root.findViewById(R.id.textlogin);
   username = root.findViewById(R.id.username);
   email = root.findViewById(R.id.email);
   pass = root.findViewById(R.id.pass);
   confirmpass = root.findViewById(R.id.confirmPassword);
   button = root.findViewById(R.id.button);
       email.setTranslationX(800);
       pass.setTranslationX(800);
       username.setTranslationX(800);
       textlogin.setTranslationX(800);
        confirmpass.setTranslationX(800);
       button.setTranslationX(800);

       email.setAlpha(v);
       pass.setAlpha(v);
       username.setAlpha(v);
       textlogin.setAlpha(v);
       confirmpass.setAlpha(v);
       button.setAlpha(v);

       email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
       username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
       pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
       textlogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
       confirmpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
       button.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();



       return root;

   }













}
