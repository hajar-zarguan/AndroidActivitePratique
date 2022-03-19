package com.example.onording;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment  {

    TextView forgetpass;
    EditText email;
    EditText pass ;
    Button login;
    float v=0 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
    {
        ViewGroup root = (ViewGroup)  inflater.inflate(R.layout.login_tab_fragment, container , false);
       email = root.findViewById(R.id.email);
       pass = root.findViewById(R.id.pass);
       forgetpass = root.findViewById(R.id.forgetpassword);
       login = root.findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( root.getContext(), Activity2.class);
                Bundle b = new Bundle();
                b.putString("email", String.valueOf(email.getText()));
                b.putString("password", String.valueOf(pass.getText()));
                intent.putExtras(b);
                startActivity(intent);

            }
        });


       email.setTranslationX(800);
       pass.setTranslationX(800);
        forgetpass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetpass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
       login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();




        return root;
    }
}
