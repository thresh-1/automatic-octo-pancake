package com.example.appy.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.appy.R
import com.example.appy.databinding.ActivityMainBinding
import com.example.appy.main.model.Text
import com.example.appy.main.viewmodel.TextViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TextViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOnClick()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect(::renderState)
            }
        }
    }

    private fun renderState(text: Text?) {
        val status = getText(R.string.active)
        "$status $text".also { binding.status.text = it }
    }

    private fun setupOnClick() {
        binding.loadButton.setOnClickListener {
            val id = binding.idInputLayout.editText?.text.toString().toInt()
            viewModel.onEvent(TextViewModel.Event.OnLoad(id = id))
            clear()

            val selected = getString(R.string.select_id)
            "$selected $id".also { binding.selected.text = it }
        }

        binding.saveButton.setOnClickListener {
            val id = binding.idInputLayout.editText?.text.toString().toInt()
            val string = binding.textInputLayout.editText?.text.toString()

            val text = Text(id = id, text = string)
            viewModel.onEvent(TextViewModel.Event.OnInsert(text))
            clear()
        }

        binding.saveButton.setOnLongClickListener {
            viewModel.onEvent(event = TextViewModel.Event.OnClear)
            clear()
            true
        }
    }

    private fun clear() {
        binding.idInputLayout.editText?.text?.clear()
        binding.textInputLayout.editText?.text?.clear()
    }
}