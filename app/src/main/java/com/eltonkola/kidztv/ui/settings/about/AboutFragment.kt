package com.eltonkola.kidztv.ui.settings.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eltonkola.kidztv.R
import kotlinx.android.synthetic.main.fragment_settings_video_manager.view.*

class AboutFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings_about, container, false)

        rootView.item_detail.text = "About Manager"

        return rootView
    }

}