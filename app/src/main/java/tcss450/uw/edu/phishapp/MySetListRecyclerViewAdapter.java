package tcss450.uw.edu.phishapp;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcss450.uw.edu.phishapp.SetListFragment.OnListFragmentInteractionListener;
import tcss450.uw.edu.phishapp.setlist.SetPost;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySetListRecyclerViewAdapter extends RecyclerView.Adapter<MySetListRecyclerViewAdapter.ViewHolder> {

    private final List<SetPost> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MySetListRecyclerViewAdapter(List<SetPost> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        Log.wtf("WRONG", "SIze; " + mValues.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.wtf("WRONG", "SIze; " + mValues.size());
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_setlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mLongDateView.setText(mValues.get(position).getLongDate());
        holder.mLocationView.setText(mValues.get(position).getLocation());
        holder.mVenueView.setText(stripStringOfHtml(mValues.get(position).getVenue()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onSetListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public String stripStringOfHtml(final String text) {
        Spanned span = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
        return span.toString();
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLongDateView;
        public final TextView mLocationView;
        public final TextView mVenueView;
        public SetPost mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLongDateView = (TextView) view.findViewById(R.id.set_list_long_date);
            mLocationView = (TextView) view.findViewById(R.id.set_list_location);
            mVenueView = (TextView) view.findViewById(R.id.set_list_venue);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mLocationView.getText() + "'";
        }
    }
}
