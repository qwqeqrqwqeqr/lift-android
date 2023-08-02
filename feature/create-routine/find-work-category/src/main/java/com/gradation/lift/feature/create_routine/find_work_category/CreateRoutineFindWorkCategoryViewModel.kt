package com.gradation.lift.feature.create_routine.find_work_category

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
    val selectedWorkPartFilter = MutableStateFlow(FILTER_ALL)

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

    @OptIn(FlowPreview::class)
    val workCategoryList =
        combine(
            selectedWorkPartFilter,
            searchText.debounce(1000).distinctUntilChanged(),
            getWorkCategoryUseCase()
        ) { workPartFilter, searchText, workCategoryList ->
            when (workCategoryList) {
                is DataState.Fail -> emptyList<WorkCategory>()
                is DataState.Success -> workCategoryList.data.also {
                    it
                        .filter { workCategory -> workCategory.name.contains(searchText) }
                        .filter { workCategory -> workPartFilter == FILTER_ALL || workCategory.name == workPartFilter }
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

    fun updateSelectedWorkPartFilter(): (String) -> Unit = {
        selectedWorkPartFilter.value = it
    }

}

data class SelectedWorkPartFilter(
    val workPart: String,
    val selected: Boolean = false
)


const val FILTER_ALL ="전체"
