package com.davydikes.aboutminsk.screen.screen_languages

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.davydikes.aboutminsk.R
import com.davydikes.aboutminsk.databinding.FragmentLanguagesBinding
import com.davydikes.aboutminsk.support.NavigationFragment
import com.davydikes.aboutminsk.support.setVerticalMargin
import by.kirich1409.viewbindingdelegate.viewBinding
import com.davydikes.aboutminsk.support.navigateSafe
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLanguages :
    NavigationFragment<FragmentLanguagesBinding>(R.layout.fragment_languages) {

    override val viewBinding: FragmentLanguagesBinding by viewBinding()
    private val viewModel: ViewModelLanguages by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.importLanguages()

        viewModel.selected.observe(this.viewLifecycleOwner) {
            if (it) {
                findNavController().navigateSafe(FragmentLanguagesDirections.toFragmentMain())
            } else {
                viewBinding.root.alpha = 1f
            }
        }
        viewBinding.btnLangBe.setOnClickListener {
            viewModel.selectedLanguage("be")
        }
        viewBinding.btnLangEn.setOnClickListener {
            viewModel.selectedLanguage("en")
        }
        viewBinding.btnLangRu.setOnClickListener {
            viewModel.selectedLanguage("ru")
        }
        viewBinding.btnLangCs.setOnClickListener {
            viewModel.selectedLanguage("cs")
        }
        viewBinding.btnLangZh.setOnClickListener {
            viewModel.selectedLanguage("zh")
        }
    }

    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
        viewBinding.toolbarLanguages.setVerticalMargin(marginTop = top)
    }

}
