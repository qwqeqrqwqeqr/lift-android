package com.gradation.lift.feature.create_routine.find_work_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.work.GetWorkCategoryUseCase
import com.gradation.lift.domain.usecase.work.GetWorkPartUseCase
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CreateRoutineFindWorkCategoryViewModel @Inject constructor(
    private val getWorkPartUseCase: GetWorkPartUseCase,
    private val getWorkCategoryUseCase: GetWorkCategoryUseCase,
) : ViewModel() {


    val searchText = MutableStateFlow("")
    val selectedWorkPartFilter = MutableStateFlow("")

    val workPartFilterList =
        combine(getWorkPartUseCase(), selectedWorkPartFilter) { workPartList, workPartFilter ->
            when (workPartList) {
                is DataState.Fail -> emptyList<SelectedWorkPart>()
                is DataState.Success -> {
                    listOf<SelectedWorkPart>(
                        SelectedWorkPart(
                            workPart = "",
                            selected = workPartFilter.isBlank()
                        )
                    ).plus(
                        workPartList.data.map { workPart ->
                            SelectedWorkPart(
                                workPart = workPart.name,
                                selected = workPart.name == workPartFilter
                            )
                        }
                    )
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<SelectedWorkPart>()
        )

    val workCategoryList =
        combine(
            selectedWorkPartFilter,
            searchText,
            getWorkCategoryUseCase()
        ) { workPartFilter, searchText, workCategoryList ->
            when (workCategoryList) {
                is DataState.Fail -> emptyList<WorkCategory>()
                is DataState.Success -> workCategoryList.data.also {
                    it
                        .filter { workCategory -> workCategory.name.contains(searchText) }
                        .filter { workCategory -> workPartFilter.isBlank() || workCategory.name == workPartFilter }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<WorkCategory>()
        )


    val filteredWorkCategoryCount = workCategoryList.map { it.count() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )


    fun updateSearchText(): (String) -> Unit = {
        searchText.value = it
    }

}

data class SelectedWorkPart(
    val workPart: String,
    val selected: Boolean = false
)