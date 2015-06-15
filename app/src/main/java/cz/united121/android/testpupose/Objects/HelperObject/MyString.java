package cz.united121.android.testpupose.Objects.HelperObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by United121 on 9.6.2015.
 */
public class MyString implements Parcelable {
    private static final String TAG = MyString.class.getName();


    String mString;

    public MyString(){
        Log.d(TAG, "MyString()");
    }

    public MyString(String mString){
        Log.d(TAG,"MyString(String mString)");
        this.mString = mString;
    }

    public MyString(Parcel in){
        Log.d(TAG,"MyString(Parcel in)");
        mString = in.readString();
    }

    @Override
    public int describeContents() {
        Log.d(TAG,"describeContents()");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(TAG,"writeToParcel(Parcel dest, int flags)");
        dest.writeString(mString);
    }

    public static final Parcelable.Creator<MyString> CREATOR
            = new Parcelable.Creator<MyString>() {
        public MyString createFromParcel(Parcel in) {
            Log.d(TAG,"createFromParcel(Parcel in)");
            return new MyString(in);
        }

        public MyString[] newArray(int size) {
            Log.d(TAG,"newArray(int size)");
            return new MyString[size];
        }
    };

}
