package android.gqy.experience.activities.parcelable.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @author gqy
 * @date 2020/3/31 0031.
 * @since 1.0.0
 * @see
 * @desc  TODO
 */
class Book() : Parcelable {
     var bookid: Int = 0
     var bookname: String? = null

    constructor(parcel: Parcel) : this() {
        bookid = parcel.readInt()
        bookname = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bookid)
        parcel.writeString(bookname)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}