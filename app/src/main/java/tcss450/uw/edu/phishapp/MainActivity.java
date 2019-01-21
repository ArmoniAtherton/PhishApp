package tcss450.uw.edu.phishapp;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import tcss450.uw.edu.phishapp.model.Credentials;

public class MainActivity extends AppCompatActivity implements
        LoginFragment.OnLoginFragmentInteractionListener,
        RegisterFragment.OnRegisterFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //We make activity_main.xml to be a frame layout to put the fragment inside.
        if (savedInstanceState == null) {
            if (findViewById(R.id.frame_main_container) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame_main_container, new LoginFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onRegisterClick() {
        Bundle args = new Bundle();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, new RegisterFragment())
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    //This method could be used for communicating between fragments
    @Override
    public void onLoginSuccess(Credentials theUser, String jwt) {
            Bundle args = new Bundle();

            int count = getSupportFragmentManager().getBackStackEntryCount();
            for(int i = 0; i < count; ++i) {
                getSupportFragmentManager().popBackStack();
            }
            args.putSerializable("Success", theUser);
            SuccessFragment successFragment = new SuccessFragment();
            successFragment.setArguments(args);
//            args.putSerializable("Object", theUser);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_main_container, successFragment);
//                    .addToBackStack(null);
            // Commit the transaction
            transaction.commit();

        }

    @Override
    public void onRegisterSuccess(Credentials theUser) {

        Bundle args = new Bundle();

        int count = getSupportFragmentManager().getBackStackEntryCount();
        for(int i = 0; i < count; ++i) {
            getSupportFragmentManager().popBackStack();
        }

        args.putSerializable("Login", theUser);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(args);
        Log.wtf("Test", "In on register success" + theUser.getEmail());
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, loginFragment);
//                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();

    }

    //Don't need get ride of this method signature.
//    @Override
//    public void onSuccessUserFragmentInteraction(Credentials theUser) {
//
//    }
}
