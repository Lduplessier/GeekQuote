package fr.myschool.geekquote.model;

import android.os.Parcel;
import android.os.Parcelable;


import java.io.Serializable;
import java.util.Date;

import fr.myschool.geekquote.activity.QuoteListActivity;

public class Quote implements Serializable, Parcelable {
    private String strQuote;
    private Float rating;
    private Date creationDate;

    public Quote(Object response) {
    }

    public Quote(String strQuote) {
        super();
        this.strQuote = strQuote;
        this.rating = 0F;
        this.creationDate = new Date();
    }

    public Quote(String strQuote, Float rating, Date creationDate) {
        this.strQuote = strQuote;
        this.rating = rating;
        this.creationDate = creationDate;
    }


    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStrQuote() {
        return strQuote;
    }

    public Float getRating() {
        return rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "strQuote='" + strQuote + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }

    protected Quote(Parcel in) {
        strQuote = in.readString();
        rating = in.readByte() == 0x00 ? null : in.readFloat();
        long tmpCreationDate = in.readLong();
        creationDate = tmpCreationDate != -1 ? new Date(tmpCreationDate) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(strQuote);
        if (rating == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(rating);
        }
        dest.writeLong(creationDate != null ? creationDate.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Quote> CREATOR = new Parcelable.Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };
}