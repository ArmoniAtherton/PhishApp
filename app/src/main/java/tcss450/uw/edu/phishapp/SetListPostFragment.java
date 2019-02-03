package tcss450.uw.edu.phishapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tcss450.uw.edu.phishapp.setlist.SetPost;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetListPostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SetListPostFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private SetPost mySetListPost;

    public SetListPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_set_list_post, container, false);
        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            mySetListPost = (SetPost) savedInstanceState.getSerializable("SetListPost");

            TextView longDate = v.findViewById(R.id.long_date);
            longDate.setText(mySetListPost.getLongDate());

            TextView location = v.findViewById(R.id.location);
            location.setText(mySetListPost.getLocation());

            TextView setListData = v.findViewById(R.id.set_list_data);
            setListData.setText(stripStringOfHtml(mySetListPost.getListData()));

            TextView setListNote = v.findViewById(R.id.set_list_node);
            setListNote.setText(stripStringOfHtml(mySetListPost.getListNotes()));


            Button b = (Button) v.findViewById(R.id.full_teaser);
            b.setOnClickListener(this::viewFullPost);

        }


        return v;
    }

    public String stripStringOfHtml(final String text) {
        Spanned span = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
        return span.toString();
    }

    private void viewFullPost(View view) {
        if (mListener != null) {
            mListener.onSetListPostUrlFragmentInteraction(mySetListPost.getUrl());
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.onUrlFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSetListPostUrlFragmentInteraction(String url);
    }
}
