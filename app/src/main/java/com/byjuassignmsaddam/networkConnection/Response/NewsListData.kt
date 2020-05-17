package com.byjuassignmentbysaddam.networkConnection.Response

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// User.kt

data class NewsListData(

    var status: String,
    var totalResults: Int,
    var articles:ArrayList<ArticlesResponse>




)

data class ArticlesResponse(
    val source: SourceData?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?

)



data class SourceData(
    var id:String?,
    var name: String?
)

@Entity
data class NewsDetailModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "author")
    var author: String?="",
    @ColumnInfo(name = "title")
    var title: String?="",
    @ColumnInfo(name = "description")
    var description: String?="",
    @ColumnInfo(name = "url")
    var url: String?="",
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String?="",
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String?="",
    @ColumnInfo(name = "content")
    var content: String?="",
    @ColumnInfo(name = "name")
    var name: String?=""

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsDetailModel> {
        override fun createFromParcel(parcel: Parcel): NewsDetailModel {
            return NewsDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}