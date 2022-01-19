package com.davydikes.aboutminsk.screen.pointer_details

import android.content.ContentValues
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.davydikes.aboutminsk.R
import com.davydikes.aboutminsk.databinding.FragmentPlaceDetailsBinding
import com.davydikes.aboutminsk.support.NavigationFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.davydikes.aboutminsk.support.setVerticalMargin
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import android.text.method.ScrollingMovementMethod


class FragmentPlaceDetails :
    NavigationFragment<FragmentPlaceDetailsBinding>(R.layout.fragment_place_details) {

    override val viewBinding: FragmentPlaceDetailsBinding by viewBinding()

    private val args: FragmentPlaceDetailsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tvDetails.movementMethod = ScrollingMovementMethod()
        args.place?.let { item ->
            Picasso.get()
                .load(item.photo)
                .into(viewBinding.tvImage, object : Callback {
                    override fun onSuccess() {
                        Log.d(ContentValues.TAG, "success1")
                    }

                    override fun onError(e: Exception?) {
                        Log.d(ContentValues.TAG, "error")
                    }
                })
            //viewBinding.webView.loadUrl(item.sound)
            viewBinding.titleToolbar.setText(item.name)
            viewBinding.titleToolbar.ellipsize = TextUtils.TruncateAt.MARQUEE
            viewBinding.titleToolbar.isSingleLine = true
            viewBinding.titleToolbar.marqueeRepeatLimit = -1
            viewBinding.titleToolbar.isSelected = true
            viewBinding.tvDetails.text = textReplace(item.text)
        }
    }

    private fun textReplace(text: String): String {
        val map =
            mapOf(
                "<p>" to "",
                "</p>" to "",
                "&mdash;" to "-",
                "&ndash;" to "-",
                "&laquo;" to "«",
                "&raquo;" to "»",
                "<p style=\"margin-left:0cm; margin-right:0cm\">" to "",
                "&ldquo;" to "“",
                "&rdquo;" to "”",
                "&nbsp;" to " ",
                "&#39;" to "'",
                "&scaron;" to "š",
                "&quot;" to "\"",
                "<strong>" to "",
                "</strong>" to "",
                "&rsquo;" to "’",

                )
        var result = text
        map.forEach { (t, u) -> result = result.replace(t, u) }
        return result
    }

    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
        viewBinding.toolbarPlaceDetail.setVerticalMargin(marginTop = top)
        viewBinding.tvDetails.setPadding(0, 0, 0, bottom)
    }

    override val backPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
}