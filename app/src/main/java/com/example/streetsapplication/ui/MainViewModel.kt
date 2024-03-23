package com.example.streetsapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streetsapplication.domain.model.PhotoDomain
import com.example.streetsapplication.domain.use_case.*
import com.example.streetsapplication.ui.model.StreetAndLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addPhotoUseCase: AddPhotoUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val deletePhotosUseCase: DeletePhotosUseCase,
    private val getStreetAndLocationUseCase: GetStreetAndLocationUseCase,
    private val insertStreetAndLocationUseCase: InsertStreetAndLocationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state: StateFlow<UIState> = _state.asStateFlow()

    val selectedPhotos = arrayListOf<PhotoDomain>()

    init {
        getPhotos()
        getStreetAndLocation()
    }

    fun setUserEvent(event: UserEvent) {
        when (event) {
            is UserEvent.AddButtonClicked -> handleOnAddButtonClicked(event.photoDomain)
            is UserEvent.DeleteButtonClicked -> handleOnDeleteButtonClicked(event.photos)
            is UserEvent.LongPhotoClicked -> handleOnLongPhotoClicked(event.photoDomain)
            is UserEvent.ChangeStreetOrLocation -> handleOnChangedStreetOrLocation(event.streetAndLocation)
        }
    }

    private fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosUseCase()
                .onStart { _state.value = UIState.Loading }
                .catch { _state.value = UIState.Error(it.message.orEmpty()) }
                .collect { photos ->
                    _state.value = UIState.Success(photos)
                }
        }
    }

    private fun getStreetAndLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getStreetAndLocationUseCase()
                .onStart { _state.value = UIState.Loading }
                .catch { _state.value = UIState.Error(it.message.orEmpty()) }
                .collect { streetAndLocationDomain ->
                    _state.value =
                        UIState.StreetAndLocationSuccess(streetAndLocationDomain.mapDomainToUi())
                }
        }
    }

    private fun handleOnAddButtonClicked(photoDomain: PhotoDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            addPhotoUseCase(photoDomain)
                .onStart { _state.value = UIState.Loading }
                .catch { _state.value = UIState.Error(it.message.orEmpty()) }
                .collect { getPhotos() }
        }
    }

    private fun handleOnDeleteButtonClicked(photos: List<PhotoDomain>) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePhotosUseCase(photos)
                .onStart { _state.value = UIState.Loading }
                .catch { _state.value = UIState.Error(it.message.orEmpty()) }
                .collect { getPhotos() }
        }
    }

    private fun handleOnLongPhotoClicked(photoDomain: PhotoDomain) {
        updateSelectedPhotos(photoDomain)
    }

    @OptIn(FlowPreview::class)
    private fun handleOnChangedStreetOrLocation(streetAndLocation: StreetAndLocation) {
        viewModelScope.launch(Dispatchers.IO) {
            insertStreetAndLocationUseCase(streetAndLocation.mapToDomain())
                .debounce(DEBOUNCE_TIME)
                .catch { _state.value = UIState.Error(it.message.orEmpty()) }
                .collect {}
        }
    }

    private fun updateSelectedPhotos(photoDomain: PhotoDomain) {
        if (selectedPhotos.contains(photoDomain)) {
            selectedPhotos.remove(photoDomain)
        } else {
            selectedPhotos.add(photoDomain)
        }
    }


    sealed interface UserEvent {

        data class AddButtonClicked(val photoDomain: PhotoDomain) : UserEvent
        data class DeleteButtonClicked(val photos: List<PhotoDomain>) : UserEvent
        data class LongPhotoClicked(val photoDomain: PhotoDomain) : UserEvent
        data class ChangeStreetOrLocation(val streetAndLocation: StreetAndLocation) : UserEvent
    }

    sealed interface UIState {

        object Loading : UIState
        data class Success(val photos: List<PhotoDomain>) : UIState
        data class StreetAndLocationSuccess(val streetAndLocation: StreetAndLocation) : UIState
        data class Error(val errorMessage: String) : UIState
    }

    companion object {

        private const val DEBOUNCE_TIME = 500L
    }
}