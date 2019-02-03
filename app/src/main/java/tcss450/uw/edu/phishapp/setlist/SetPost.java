package tcss450.uw.edu.phishapp.setlist;


import java.io.Serializable;


/** Class to encapsulate a Phish.net Blog Post. Building an Object requires a publish date and title.
 * Optional fields include URL, teaser, and Author.
 *
 *
 * @author Charles Bryan
 * @version 14 September 2018
 */
public class SetPost implements Serializable {

    private final String mLongDate;
    private final String mLocation;
    private final String mVenue;
    private final String mListData;
    private final String mListNotes;
    private final String mUrl;

    /**
     * Helper class for building Credentials.
     *
     * @author Charles Bryan
     */
    public static class Builder {
//        private final String mPubDate;
//        private final String mTitle;
//        private  String mUrl = "";
//        private  String mTeaser = "";
//        private  String mAuthor = "";
        private final String mLongDate;
        private final String mLocation;
        private String mVenue = "";
        private String mListData = "";
        private String mListNotes = "";
        private String mUrl =  "";



        /**
         * Constructs a new Builder.
         *
         * @param pubDate the published date of the blog post
         * @param title the title of the blog post
         */
        public Builder(String longDate, String location) {
            this.mLongDate = longDate;
            this.mLocation = location;
        }
        /**
         * Add an optional url for the full blog post.
         * @param val an optional url for the full blog post
         * @return the Builder of this BlogPost
         */
        public Builder addVenue(final String val) {
            mVenue = val;
            return this;
        }

        /**
         * Add an optional url for the full blog post.
         * @param val an optional url for the full blog post
         * @return the Builder of this BlogPost
         */
        public Builder addListData(final String val) {
            mListData = val;
            return this;
        }

        /**
         * Add an optional teaser for the full blog post.
         * @param val an optional url teaser for the full blog post.
         * @return the Builder of this BlogPost
         */
        public Builder addListNotes(final String val) {
            mListNotes = val;
            return this;
        }

        /**
         * Add an optional author of the blog post.
         * @param val an optional author of the blog post.
         * @return the Builder of this BlogPost
         */
        public Builder addUrl(final String val) {
            mUrl = val;
            return this;
        }

        public SetPost build() {
            return new SetPost(this);
        }

    }

    private SetPost(final Builder builder) {
        this.mLongDate = builder.mLongDate;
        this.mLocation = builder.mLocation;
        this.mVenue = builder.mVenue;
        this.mListData = builder.mListData;
        this.mListNotes = builder.mListNotes;
        this.mUrl = builder.mUrl;
    }

    public String getLongDate() {
        return mLongDate;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getVenue() {
        return mVenue;
    }

    public String getListData() {
        return mListData;
    }

    public String getListNotes() {
        return mListNotes;
    }

    public String getUrl() {
        return mUrl;
    }



}
