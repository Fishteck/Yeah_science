package android.example.yeahscience.utils

import android.example.yeahscience.R
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.fragment_main.*


fun Fragment.showToast ( message : String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


fun ImageView.takeAndSetImage (url: String, transformation: Transformation ?= null) {
    if (transformation == null) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this)
    } else {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .transform(transformation)
            .into(this)
    }
}

