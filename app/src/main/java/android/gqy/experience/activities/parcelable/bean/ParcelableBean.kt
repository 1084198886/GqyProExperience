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
class ParcelableBean() : Parcelable {
    var id: Int = 0
    var name: String? = null
    var books = ArrayList<Book>()
    var book: Book? = null
    var tags = ArrayList<String>()

    constructor(parcel: Parcel) : this() {
        this.id = parcel.readInt()
        this.name = parcel.readString()
        this.tags = parcel.createStringArrayList() as ArrayList<String>
        // 读取对象需要提供一个类加载器,因为写入的时候写入了类的相关信息
        this.book = parcel.readParcelable(Book::class.java.classLoader)
        //这一类需要用相应的类加载器去获取,对应writeList
        parcel.readList(books, Book::class.java.classLoader)
    }

    /**
     * 序列化
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeStringList(tags)
        // 这里的flag几乎都是0,除非标识当前对象需要作为返回值返回,不能立即释放资源
        parcel.writeParcelable(book, 0)
        //这些方法把类的信息和数据都写入Parcel，以使将来能使用合适的类装载器重新构造类的实例.所以效率不高
        parcel.writeList(books)
    }

    /**
     * 描述
     * 返回的是内容的描述信息
     * 只针对一些特殊的需要描述信息的对象,需要返回1,其他情况返回0
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * 反序列化(CREATOR不可改名)
     */
    companion object CREATOR : Parcelable.Creator<ParcelableBean> {
        /**
         * 从序列化对象中，获取原始的对象
         */
        override fun createFromParcel(parcel: Parcel): ParcelableBean {
            return ParcelableBean(parcel)
        }

        /**
         * 创建指定长度的原始对象数组
         */
        override fun newArray(size: Int): Array<ParcelableBean?> {
            return arrayOfNulls(size)
        }
    }
}