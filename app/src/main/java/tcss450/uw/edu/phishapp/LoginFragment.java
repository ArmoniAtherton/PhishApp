package tcss450.uw.edu.phishapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import tcss450.uw.edu.phishapp.model.Credentials;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLoginFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment {

    private OnLoginFragmentInteractionListener mListener;
    private static final String TAG = "LoginFrag";
    public static ArrayList<Credentials> validUsers = new ArrayList<>();
    private int myUser;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        Credentials temp =  new Credentials.Builder(
                "athera@u.com",
                "aaaaaa")
                .build();
        validUsers.add(temp);

        // Inflate the layout for this fragment.
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        Button b = (Button) v.findViewById(R.id.fragLogin_register_button);
        //Use a method reference to add the OnClickListener
        b.setOnClickListener(this::onRegisterButtonClicked);

        b = (Button) v.findViewById(R.id.fragLogin_signIn_button);
        //Use a method reference to add the OnClickListener
        b.setOnClickListener(this::onLoginButtonClicked);

        //Here since not connecting to database we will have to populate some users.
//        validUsers = new ArrayList<>();
        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            Credentials c = (Credentials) savedInstanceState.getSerializable("Login");
            EditText userEmail = (EditText) v.findViewById(R.id.fragLogin_email_editText);
            EditText userPassword = (EditText) v.findViewById(R.id.fragLogin_password_editText);
            userEmail.setText(c.getEmail());
            userPassword.setText(c.getPassword());

        }

        return v;

    }

    //This will call the the register fragment to be called.
    public void onRegisterButtonClicked(View view) {
        if (mListener != null) {
            //check if valid email was sent.

            mListener.onRegisterClick();
            Log.wtf(TAG, "Register!!");
        }
    }

    //This will call the the Login/Success fragment to be called.
    public void onLoginButtonClicked(View view) {
        if (mListener != null) {

            EditText userEmail = (EditText) getActivity().findViewById(R.id.fragLogin_email_editText);
            EditText userPassword = (EditText) getActivity().findViewById(R.id.fragLogin_password_editText);
            String email = userEmail.getText().toString();
            String password = userPassword.getText().toString();
            userEmail.setError(null);
            userPassword.setError(null);

            //Make sure email text box is not empty.
            if (email.length() != 0 ) {
                //Make sure password text box is not empty.
                if (password.length() != 0 ) {
                    //Check if email is in correct format.
                    if (isEmailValid(email)) {
                        //If the user exists
                        if (verifiedUser(email, password)) {
                            //Now check if password is correct
                            if (verifiedUserPassword(email, password, userPassword)) {
                                mListener.onLoginSuccess(validUsers.get(myUser),null);
                            } else {
                                userPassword.setError("Password Incorrect, Try Again!");
                            }
                        } else { //User does not exist
//                        userEmail.setError("Account not found, Register to make a account!");
                            Toast.makeText(this.getContext(), "Please Register!",
                                            Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        userEmail.setError("Invalid Email, Please Try Again!");
                    }
                } else {
                    userPassword.setError("Empty, must enter password!");
                }

            } else {
                userEmail.setError("Empty, must enter email!");
            }
        }
    }


    private boolean verifiedUserPassword(String theEmail, String thePassword, EditText theUserPassword) {
        boolean validUserPass = false;
        for (int i = 0; i < validUsers.size(); i++) {
            //Find UserName
            if (validUsers.get(i).getEmail().equals(theEmail)) {
                if (validUsers.get(i).getPassword().equals(thePassword)) {
                    validUserPass = true;
                }
            }

        }
        return validUserPass;
    }

    private boolean verifiedUser(String theEmail, String thePassword) {
        myUser = 0;
        boolean validUser = false;
        for (int i = 0; i < validUsers.size(); i++) {
            //Check if username already exists
            if (validUsers.get(i).getEmail().equals(theEmail)) {
                validUser = true;
                myUser = i;
            }
        }
        return validUser;
    }

    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            mListener = (OnLoginFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnLoginFragmentInteractionListener {
        void onLoginSuccess(Credentials theUser, String jwt);
        void onRegisterClick();
    }
}
