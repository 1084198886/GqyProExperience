package android.gqy.experience.activities.parcelable

import android.content.Intent
import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.parcelable.bean.Book
import android.gqy.experience.activities.parcelable.bean.ParcelableBean
import android.os.Bundle
import android.view.View

/**
 * Parcelable用法
 */
class ParcelableActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)
    }

    fun transmitParcelable(view: View) {
        val intent = Intent(this, ParcelableSecActivity::class.java)
        val bean = ParcelableBean()
        bean.id = 110
        bean.name = "parcelable_obj"
        bean.tags = arrayListOf("tag1", "tag2")
        val book = Book()
        book.bookid = 10
        book.bookname = "bookname"
        bean.book = book

        val booklist = ArrayList<Book>()
        (0..2).forEach {
            val bookBean = Book()
            bookBean.bookid = it
            bookBean.bookname = "bookname$it"
            booklist.add(bookBean)
        }
        bean.books = booklist
        intent.putExtra("parcelableData", bean)
        startActivity(intent)
    }
}
