package com.example.streetsapplication.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.streetsapplication.databinding.ActivityMainBinding
import com.example.streetsapplication.domain.model.PhotoDomain
import com.example.streetsapplication.ui.adapter.PhotosAdapter
import com.example.streetsapplication.ui.model.StreetAndLocation
import com.example.streetsapplication.ui.util.createUniqueId
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                handleAddingPhoto(result.data?.data)
            }
        }

    private val viewModel by viewModels<MainViewModel>()

    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter(::handleOnLongClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpCLickListeners()
        setUpViews()
        initObservers()
    }

    private fun setUpCLickListeners() = with(binding) {
        plusButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = IMAGE_INTENT_TYPE
            activityResultLauncher.launch(intent)
        }
        deleteButton.setOnClickListener {
            viewModel.setUserEvent(MainViewModel.UserEvent.DeleteButtonClicked(viewModel.selectedPhotos))
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    handleState(state)
                }
            }
        }
    }

    private fun setUpViews() = with(binding) {
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.adapter = adapter

        streetsTextView.doAfterTextChanged {
            if (it?.isNotBlank() == true && it.isNotEmpty()) {
                viewModel.setUserEvent(
                    MainViewModel.UserEvent.ChangeStreetOrLocation(
                        StreetAndLocation(
                            nameOfStreet = it.toString(),
                            nameOfLocation = nameOfLocationTextView.text.toString()
                        )
                    )
                )
            }
        }
        nameOfLocationTextView.doAfterTextChanged {
            if (it?.isNotBlank() == true && it.isNotEmpty()) {
                viewModel.setUserEvent(
                    MainViewModel.UserEvent.ChangeStreetOrLocation(
                        StreetAndLocation(
                            nameOfStreet = streetsTextView.text.toString(),
                            nameOfLocation = it.toString()
                        )
                    )
                )
            }
        }
    }

    private fun handleState(state: MainViewModel.UIState) {
        when (state) {
            is MainViewModel.UIState.Error -> handleErrorState(state.errorMessage)
            is MainViewModel.UIState.Loading -> handleLoadingState()
            is MainViewModel.UIState.Success -> handleResult(state.photos)
            is MainViewModel.UIState.StreetAndLocationSuccess -> handleStreetOrLocationAdded(state.streetAndLocation)
        }
    }

    private fun handleErrorState(errorMessage: String) = with(binding) {
        progressBar.isVisible = false
        centerBackLayout.isVisible = true
        // TODO: make Dialog with Error message
    }

    private fun handleLoadingState() = with(binding) {
        progressBar.isVisible = true
        centerBackLayout.isVisible = false
    }

    private fun handleResult(photos: List<PhotoDomain>) = with(binding) {
        progressBar.isVisible = false
        centerBackLayout.isVisible = true
        adapter.submitList(photos)
    }

    private fun handleStreetOrLocationAdded(streetAndLocation: StreetAndLocation) = with(binding) {
        progressBar.isVisible = false
        centerBackLayout.isVisible = true
        if (streetAndLocation.nameOfStreet.isNotEmpty()) {
            streetsTextView.setText(streetAndLocation.nameOfStreet)
        }
        if (streetAndLocation.nameOfLocation.isNotEmpty()) {
            nameOfLocationTextView.setText(streetAndLocation.nameOfLocation)
        }
    }

    private fun handleOnLongClick(photo: PhotoDomain) {
        viewModel.setUserEvent(MainViewModel.UserEvent.LongPhotoClicked(photo))
        adapter.setSelectedState()
        adapter.setUnselectedState {
            binding.deleteButton.isVisible = it
        }
    }

    private fun handleAddingPhoto(photoUri: Uri?) = with(binding) {
        val photoDomain = PhotoDomain(
            id = createUniqueId(),
            photoUri = photoUri
        )
        viewModel.setUserEvent(MainViewModel.UserEvent.AddButtonClicked(photoDomain))
    }


    companion object {

        const val IMAGE_INTENT_TYPE = "image/*"
    }
}