package tcss450.uw.edu.phishapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tcss450.uw.edu.phishapp.model.Credentials;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnRegisterFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegisterFragment extends Fragment {

    private OnRegisterFragmentInteractionListener mListener;
    private static final String TAG = "LoginFrag";

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        Button b = (Button) v.findViewById(R.id.fragRegister_register_button);
        //Use a method reference to add the OnClickListener
        b.setOnClickListener(this::onRegisterClicked);

        return v;

    }
    //This will call the the register fragment to be called.
    public void onRegisterClicked(View view) {
        if (mListener != null) {

            EditText userEmail = (EditText) getActivity().findViewById(R.id.fragRegister_email_editText);
            EditText userPassword = (EditText) getActivity().findViewById(R.id.fragRegister_password_editText);
            EditText userRetypePassword = (EditText) getActivity().findViewById(R.id.fragRegister_retypePassword_editText);

            String email = userEmail.getText().toString();

            //Check if email isn't empty.
            if (email.length() > 0 ) {
                //Make sure password text box is not empty.
                if (userPassword.length() != 0 ) {
                    //check retype password
                    if (userRetypePassword.length() != 0 ) {
                        if (isEmailValid(email)) { //This will check if email is in right format
                            if (userPassword.getText().length() >= 6) { //Check if at least 6 chars
//                                if (userRetypePassword.getText().length() >= 6) { //Check if at least 6 chars
                                    //This will add the user name and password
                                    if (userPassword.getText().toString().equals(
                                            userRetypePassword.getText().toString())) { // Check if pass are ==
                                        Credentials temp =  new Credentials.Builder(
                                                             userEmail.getText().toString(),
                                                          userPassword.getText().toString())
                                                        .build();
                                        mListener.onRegisterSuccess(temp);
                                        LoginFragment.validUsers.add(temp);
                                    } else { //Pass are not equal
                                        userPassword.setError("Password Dont Match!");
                                        userRetypePassword.setError("Password Dont Match!");
                                    }
//                                } else { // pass 2 need 6 char.
//                                    userRetypePassword.setError("Password must have at least 6 character!");
//                                }

                            } else { // pass 1 need 6 char.
                                userPassword.setError("Password must have at least 6 character!");
                            }
                        } else { // fix email format
                            userEmail.setError("Email Not Correct Format!");
                        }
                    }  else {
                        userRetypePassword.setError("Empty, must enter characters for Retype password text box!");
                    }
                }  else {
                    userPassword.setError("Empty, must enter characters for password text box!");
                }

//                Log.wtf(TAG, "Register!!");
            } else {
                userEmail.setError("Empty, must enter email!");

            }
        }
    }

    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentInteractionListener) {
            mListener = (OnRegisterFragmentInteractionListener) context;
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
    public interface OnRegisterFragmentInteractionListener {
        void onRegisterSuccess(Credentials theUser);

    }
}

