package com.surajdalvi.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    var currentImageUrl : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }

    private fun loadMeme(){
        // Instantiate the RequestQueue.
        // ProgressBar Visible
        val bar: ProgressBar = findViewById(R.id.progressBar)
        bar.visibility = View.VISIBLE

       // val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                currentImageUrl = response.getString("url")
                val meme: ImageView = findViewById(R.id.memeImageView)
                // bar.visibility = View.GONE
                // Use request listener in glide
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        bar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        bar.visibility = View.GONE
                        return false
                    }
                }).into(meme)
            },
            // Error Toast
            {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            })

        // Note: Log is used to print something in logcat
        // ex - Log.d("error", it.localizedMessage)

        // Add the request to the RequestQueue.
        //  queue.add(jsonObjectRequest)
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }




    // Function for next meme
    fun nextMeme(view: View) {
        loadMeme()
    }

    // Function for sharing memes
    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hey this is Suraj Dalvi, Checkout this cool Memes I got from Reddit $currentImageUrl")
        val chooser = Intent.createChooser(intent, "Share this Meme with your friends!")
        startActivity(chooser)
    }
}