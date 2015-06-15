package cz.united121.android.testpupose.Objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cz.united121.android.testpupose.Objects.HelperObject.MyString;

/**
 * Created by United121 on 9.6.2015.
 */
public class CustomSmall implements Parcelable {
    private static final String TAG = CustomSmall.class.getName();


    int mId;
    String mName;
    int mDummy;
    List<MyString> mStringList;

    public CustomSmall(){
        Log.d(TAG,"CustomSmall()");
        mStringList = new ArrayList<>();
    }

    public CustomSmall(Parcel in){
        Log.d(TAG,"CustomSmall(Parcel in)");
        mId = in.readInt();
        mName = in.readString();
        mDummy = in.readInt();
        mStringList = new ArrayList<>();
        in.readTypedList(mStringList,MyString.CREATOR);
    }

    public CustomSmall(int mId, String mName, int mDummy, List<MyString> mStringList){
        Log.d(TAG, "CustomSmall(int mId, String mName, int mDummy, List<MyString> mStringList)");
        this.mId = mId;
        this.mName = mName;
        this.mDummy = mDummy;
        this.mStringList = mStringList;
    }

    @Override
    public int describeContents() {
        Log.d(TAG,"describeContents()");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(TAG,"writeToParcel(Parcel dest, int flags)");
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeInt(mDummy);
        dest.writeTypedList(mStringList);
    }

    public static final Parcelable.Creator<CustomSmall> CREATOR
            = new Parcelable.Creator<CustomSmall>() {
        public CustomSmall createFromParcel(Parcel in) {
            Log.d(TAG,"createFromParcel(Parcel in)");
            return new CustomSmall(in);
        }

        public CustomSmall[] newArray(int size) {
            Log.d(TAG,"newArray(int size)");
            return new CustomSmall[size];
        }
    };
}
