package com.example.krasimiryankov.libraryapp.data.network

import com.example.krasimiryankov.libraryapp.data.BookConst
import com.example.krasimiryankov.libraryapp.model.BookEntry

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun getBooksFromApi(
        bookApi: BookApi,
        query: String,
        page: Int,
        onSuccess: (books: List<BookEntry>) -> Unit,
        onError: (error: String) -> Unit
) {
    bookApi.getBooks(query, page).enqueue(
            object : Callback<BooksResponse> {
                override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                    if (response.isSuccessful) {
                        val books = response.body()?.books ?: emptyList<BookEntry>()
                        onSuccess(books)
                    }
                }

                override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                    onError(t.message ?: "Error")
                }
            }
    )
}

interface BookApi {
    @GET("search")
    fun getBooks(@Query("query") query: String,
                 @Query("page") page: Int): Call<BooksResponse>

    companion object {
        fun create(): BookApi {
            return Retrofit.Builder()
                    .baseUrl(BookConst.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(BookApi::class.java)
        }
    }

}