package com.gradation.lift.feature.create_routine.find_work_category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.work.GetWorkCategoryUseCase
import com.gradation.lift.domain.usecase.work.GetWorkPartUseCase
import com.gradation.lift.model.work.WorkCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CreateRoutineFindWorkCategoryViewModel @Inject constructor(
    private val getWorkPartUseCase: GetWorkPartUseCase,
    private val getWorkCategoryUseCase: GetWorkCategoryUseCase,
) : ViewModel() {


    val searchText = MutableStateFlow("")
    private val selectedWorkPartFilter = MutableStateFlow(FILTER_ALL)

    val workCategoryList =
        combine(
            selectedWorkPartFilter,
            searchText,
            getWorkCategoryUseCase()
        ) { selectedWorkPartFilter, searchText, workCategoryList ->
            when (workCategoryList) {
                is DataState.Fail -> emptyList<WorkCategory>()
                is DataState.Success -> workCategoryList.data
                    .filter { workCategory -> searchText.isBlank()  || workCategory.name.contains(searchText) }
                    .filter { workCategory -> selectedWorkPartFilter == FILTER_ALL || workCategory.workPart.name == selectedWorkPartFilter }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<WorkCategory>()
        )


    val workPartFilterList =
        combine(getWorkPartUseCase(), selectedWorkPartFilter) { workPartList, workPartFilter ->
            when (workPartList) {
                is DataState.Fail -> emptyList<SelectedWorkPartFilter>()
                is DataState.Success -> {
                    listOf<SelectedWorkPartFilter>(
                        SelectedWorkPartFilter(
                            workPart = FILTER_ALL,
                            selected = workPartFilter == FILTER_ALL
                        )
                    ).plus(
                        workPartList.data.map { workPart ->
                            SelectedWorkPartFilter(
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
            initialValue = emptyList<SelectedWorkPartFilter>()
        )


    val filteredWorkCategoryCount = workCategoryList.map { it.count() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )


    fun updateSearchText(): (String) -> Unit = {
        searchText.value = it
    }

    fun updateSelectedWorkPartFilter(): (String) -> Unit = {
        selectedWorkPartFilter.value = it
        Log.d("selected", selectedWorkPartFilter.value)
    }

}

data class SelectedWorkPartFilter(
    val workPart: String,
    val selected: Boolean = false
)


const val FILTER_ALL = "전체"
