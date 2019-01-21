
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
        import android.widget.TextView;

        import java.util.ArrayList;

        import tcss450.uw.edu.phishapp.model.Credentials;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class SuccessFragment extends Fragment {

//    private OnSuccessFragmentInteractionListener mListener;
    private static final String TAG = "LoginFrag";

    public SuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_success, container, false);

        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            Credentials c = (Credentials) savedInstanceState.getSerializable("Success");
            TextView userEmail = (TextView) v.findViewById(R.id.fragSuccess_email_textView);

            userEmail.setText(c.getEmail());
//            getActivity().getSupportFragmentManager().popBackStack();
//            getActivity().getSupportFragmentManager().popBackStack();

        }
        return v;

    }


//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onLoginSuccess(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnSuccessFragmentInteractionListener) {
//            mListener = (OnSuccessFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnLoginFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnSuccessFragmentInteractionListener {
//        void onSuccessUserFragmentInteraction(Credentials theUser);
//
//    }
}


