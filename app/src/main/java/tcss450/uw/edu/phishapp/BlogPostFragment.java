package tcss450.uw.edu.phishapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tcss450.uw.edu.phishapp.blog.BlogPost;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlogPostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BlogPostFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private BlogPost myBlogPost;

    public BlogPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blog_post, container, false);

        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            myBlogPost = (BlogPost) savedInstanceState.getSerializable("BlogPost");

            TextView title = v.findViewById(R.id.fragBlog_blogTitle_textView);
            title.setText(myBlogPost.getTitle());
//            Log.wtf("WRONG", b.getAuthor());

            TextView publishDate = v.findViewById(R.id.fragBlog_publishDate_textView);
            publishDate.setText(myBlogPost.getPubDate());

            TextView fullTeaser = v.findViewById(R.id.small_teaser);
            fullTeaser.setText(Html.fromHtml(myBlogPost.getTeaser()));

            Button b = (Button) v.findViewById(R.id.full_teaser);
            b.setOnClickListener(this::viewFullPost);

        }


        return v;
    }

    private void viewFullPost(View view) {
        if (mListener != null) {
            mListener.onUrlFragmentInteraction(myBlogPost.getUrl());
        }


    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onUrlFragmentInteraction(uri);
//        }
//    }

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
        void onUrlFragmentInteraction(String url);
    }
}
