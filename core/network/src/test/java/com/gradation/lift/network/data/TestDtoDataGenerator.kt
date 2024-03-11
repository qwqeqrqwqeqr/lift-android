package com.gradation.lift.network.data

import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_AUTHENTICATION_METHOD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_DATE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_GENDER_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_HEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LABEL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PASSWORD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PROGRESS_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_REPETITION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_SCORE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_STAMP_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEEKDAY_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_CATEGORY_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_PART_NAME_DATA
import com.gradation.lift.network.dto.auth.CheckExistUserResponseDto
import com.gradation.lift.network.dto.auth.CreateEmailAuthenticationCodeRequestDto
import com.gradation.lift.network.dto.auth.CreateEmailAuthenticationCodeResponseDto
import com.gradation.lift.network.dto.auth.RefreshResponseDto
import com.gradation.lift.network.dto.auth.SignInDefaultRequestDto
import com.gradation.lift.network.dto.auth.SignInDefaultResponseDto
import com.gradation.lift.network.dto.auth.SignInGoogleRequestDto
import com.gradation.lift.network.dto.auth.SignInGoogleResponseDto
import com.gradation.lift.network.dto.auth.SignInKakaoRequestDto
import com.gradation.lift.network.dto.auth.SignInKakaoResponseDto
import com.gradation.lift.network.dto.auth.SignInNaverRequestDto
import com.gradation.lift.network.dto.auth.SignInNaverResponseDto
import com.gradation.lift.network.dto.auth.SignUpDefaultRequestDto
import com.gradation.lift.network.dto.auth.SignUpDefaultResponseDto
import com.gradation.lift.network.dto.auth.SignUpGoogleRequestDto
import com.gradation.lift.network.dto.auth.SignUpGoogleResponseDto
import com.gradation.lift.network.dto.auth.SignUpKakaoRequestDto
import com.gradation.lift.network.dto.auth.SignUpKakaoResponseDto
import com.gradation.lift.network.dto.auth.SignUpNaverRequestDto
import com.gradation.lift.network.dto.auth.SignUpNaverResponseDto
import com.gradation.lift.network.dto.auth.UpdatePasswordRequestDto
import com.gradation.lift.network.dto.auth.UpdatePasswordResponseDto
import com.gradation.lift.network.dto.auth.ValidateEmailAuthenticationRequestDto
import com.gradation.lift.network.dto.auth.ValidateEmailAuthenticationResponseDto
import com.gradation.lift.network.dto.badge.BadgeDto
import com.gradation.lift.network.dto.badge.CreateUserBadgeDto
import com.gradation.lift.network.dto.badge.CreateUserBadgeRequestDto
import com.gradation.lift.network.dto.badge.CreateUserBadgeResponseDto
import com.gradation.lift.network.dto.badge.GetBadgeResponseDto
import com.gradation.lift.network.dto.badge.GetUserBadgeByMainFlagResponseDto
import com.gradation.lift.network.dto.badge.GetUserBadgeConditionResponseDto
import com.gradation.lift.network.dto.badge.GetUserBadgeResponseDto
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagDto
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagRequestDto
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagResponseDto
import com.gradation.lift.network.dto.badge.UserBadgeDto
import com.gradation.lift.network.dto.checker.CheckDuplicateEmailResponseDto
import com.gradation.lift.network.dto.checker.CheckDuplicateNameResponseDto
import com.gradation.lift.network.dto.favorite.GetWorkCategoryFavoriteResponseDto
import com.gradation.lift.network.dto.favorite.UpdateWorkCategoryFavoriteRequestDto
import com.gradation.lift.network.dto.favorite.UpdateWorkCategoryFavoriteResponseDto
import com.gradation.lift.network.dto.history.CreateHistoryRequestDto
import com.gradation.lift.network.dto.history.CreateHistoryResponseDto
import com.gradation.lift.network.dto.history.CreateHistoryRoutineDto
import com.gradation.lift.network.dto.history.DeleteHistoryResponseDto
import com.gradation.lift.network.dto.history.GetHistoryByHistoryIdResponseDto
import com.gradation.lift.network.dto.history.GetHistoryResponseDto
import com.gradation.lift.network.dto.history.HistoryDto
import com.gradation.lift.network.dto.history.HistoryRoutineDto
import com.gradation.lift.network.dto.history.UpdateHistoryInfoRequestDto
import com.gradation.lift.network.dto.history.UpdateHistoryInfoResponseDto
import com.gradation.lift.network.dto.inquiry.CreateInquiryRequestDto
import com.gradation.lift.network.dto.inquiry.CreateInquiryResponseDto
import com.gradation.lift.network.dto.notice.GetNoticeByIdResponseDto
import com.gradation.lift.network.dto.notice.GetNoticeResponseDto
import com.gradation.lift.network.dto.notice.NoticeDto
import com.gradation.lift.network.dto.picture.GetRoutineSetPictureResponseDto
import com.gradation.lift.network.dto.picture.GetUserProfilePictureResponseDto
import com.gradation.lift.network.dto.picture.RoutineSetPictureDto
import com.gradation.lift.network.dto.picture.UserProfilePictureDto
import com.gradation.lift.network.dto.routine.CreateRoutineDto
import com.gradation.lift.network.dto.routine.CreateRoutineSetRoutineRequestDto
import com.gradation.lift.network.dto.routine.CreateRoutineSetRoutineResponseDto
import com.gradation.lift.network.dto.routine.DeleteRoutineSetRoutineResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetRoutineByLabelResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetRoutineByRecentResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetRoutineByRoutineSetIdResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetRoutineByWeekdayResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetRoutineResponseDto
import com.gradation.lift.network.dto.routine.RoutineDto
import com.gradation.lift.network.dto.routine.RoutineSetDto
import com.gradation.lift.network.dto.routine.RoutineSetRoutineDto
import com.gradation.lift.network.dto.routine.UpdateRoutineDto
import com.gradation.lift.network.dto.routine.UpdateRoutineSetRoutineRequestDto
import com.gradation.lift.network.dto.routine.UpdateRoutineSetRoutineResponseDto
import com.gradation.lift.network.dto.routine.UpdateUsedRoutineSetRequestDto
import com.gradation.lift.network.dto.routine.UpdateUsedRoutineSetResponseDto
import com.gradation.lift.network.dto.terms.CreateUserTermsConsentRequestDto
import com.gradation.lift.network.dto.terms.CreateUserTermsConsentResponseDto
import com.gradation.lift.network.dto.terms.GetUserMarketingTermsConsentResponseDto
import com.gradation.lift.network.dto.terms.UpdateUserMarketingTermsConsentRequestDto
import com.gradation.lift.network.dto.terms.UpdateUserMarketingTermsConsentResponseDto
import com.gradation.lift.network.dto.user.CreateUserDetailRequestDto
import com.gradation.lift.network.dto.user.CreateUserDetailResponseDto
import com.gradation.lift.network.dto.user.DeleteUserRequestDto
import com.gradation.lift.network.dto.user.DeleteUserResponseDto
import com.gradation.lift.network.dto.user.ExistUserDetailResponseDto
import com.gradation.lift.network.dto.user.GetUserAuthenticationMethodResponseDto
import com.gradation.lift.network.dto.user.GetUserDetailResponseDto
import com.gradation.lift.network.dto.user.UpdateUserDetailInfoRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailInfoResponseDto
import com.gradation.lift.network.dto.user.UpdateUserDetailNameRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailNameResponseDto
import com.gradation.lift.network.dto.user.UpdateUserDetailProfilePictureRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailProfilePictureResponseDto
import com.gradation.lift.network.dto.user.UpdateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailResponseDto
import com.gradation.lift.network.dto.user.UserDetailDto
import com.gradation.lift.network.dto.work.EffectContentDto
import com.gradation.lift.network.dto.work.GetPopularWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetRecommendWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryByIdResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import com.gradation.lift.network.dto.work.SequenceContentDto
import com.gradation.lift.network.dto.work.WorkCategoryDto
import com.gradation.lift.network.dto.work.WorkPartDto

object TestDtoDataGenerator {


    object Auth {
        object SignUpDefault {
            internal val SIGN_UP_DEFAULT_REQUEST_DTO = SignUpDefaultRequestDto(
                email = FAKE_EMAIL_DATA,
                password = FAKE_PASSWORD_DATA,
                name = FAKE_STRING_DATA,
                phoneNumber = FAKE_STRING_DATA
            )
            internal val SIGN_UP_DEFAULT_RESPONSE_DTO = SignUpDefaultResponseDto(
                result = FAKE_BOOLEAN_DATA

            )
        }

        object SignInDefault {

            internal val SIGN_IN_DEFAULT_REQUEST_DTO = SignInDefaultRequestDto(
                id = FAKE_EMAIL_DATA,
                password = FAKE_PASSWORD_DATA
            )
            internal val SIGN_IN_DEFAULT_RESPONSE_DTO = SignInDefaultResponseDto(
                accessToken = FAKE_ACCESS_TOKEN,
                refreshToken = FAKE_REFRESH_TOKEN
            )


        }

        object SignUpGoogle {
            internal val SIGN_UP_GOOGLE_REQUEST_DTO = SignUpGoogleRequestDto(
                id = FAKE_STRING_DATA,
                email = FAKE_EMAIL_DATA
            )
            internal val SIGN_UP_GOOGLE_RESPONSE_DTO = SignUpGoogleResponseDto(
                result = FAKE_BOOLEAN_DATA
            )

        }

        object SignInGoogle {
            internal val SIGN_IN_GOOGLE_REQUEST_DTO = SignInGoogleRequestDto(
                id = FAKE_STRING_DATA,
                email = FAKE_EMAIL_DATA
            )
            internal val SIGN_IN_GOOGLE_RESPONSE_DTO = SignInGoogleResponseDto(
                accessToken = FAKE_ACCESS_TOKEN,
                refreshToken = FAKE_REFRESH_TOKEN
            )
        }

        object SignUpKakao {
            internal val SIGN_UP_KAKAO_REQUEST_DTO = SignUpKakaoRequestDto(
                id = FAKE_STRING_DATA,
                email = FAKE_EMAIL_DATA
            )
            internal val SIGN_UP_KAKAO_RESPONSE_DTO = SignUpKakaoResponseDto(
                result = FAKE_BOOLEAN_DATA
            )
        }


        object SignInKakao {
            internal val SIGN_IN_KAKAO_REQUEST_DTO = SignInKakaoRequestDto(
                id = FAKE_STRING_DATA,
                email = FAKE_EMAIL_DATA
            )
            internal val SIGN_IN_KAKAO_RESPONSE_DTO = SignInKakaoResponseDto(
                accessToken = FAKE_ACCESS_TOKEN,
                refreshToken = FAKE_REFRESH_TOKEN
            )
        }

        object SignUpNaver {
            internal val SIGN_UP_NAVER_REQUEST_DTO = SignUpNaverRequestDto(
                id = FAKE_STRING_DATA,
                email = FAKE_EMAIL_DATA
            )
            internal val SIGN_UP_NAVER_RESPONSE_DTO = SignUpNaverResponseDto(
                result = FAKE_BOOLEAN_DATA
            )
        }


        object SignInNaver {
            internal val SIGN_IN_NAVER_REQUEST_DTO = SignInNaverRequestDto(
                id = FAKE_STRING_DATA,
                email = FAKE_EMAIL_DATA
            )
            internal val SIGN_IN_NAVER_RESPONSE_DTO = SignInNaverResponseDto(
                accessToken = FAKE_ACCESS_TOKEN,
                refreshToken = FAKE_REFRESH_TOKEN
            )
        }


        object CheckExistUser {
            internal val CHECK_EXIST_USER_RESPONSE_DTO = CheckExistUserResponseDto(
                result = FAKE_BOOLEAN_DATA
            )
        }


        object CreateEmailAuthenticationCode {
            internal val CREATE_EMAIL_AUTHENTICATION_CODE_REQUEST_DTO =
                CreateEmailAuthenticationCodeRequestDto(
                    email = FAKE_EMAIL_DATA,
                    isSigned = FAKE_BOOLEAN_DATA
                )
            internal val CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_DTO =
                CreateEmailAuthenticationCodeResponseDto(
                    result = FAKE_BOOLEAN_DATA
                )
        }

        object ValidateEmailAuthentication {
            internal val VALIDATE_EMAIL_AUTHENTICATION_REQUEST_DTO =
                ValidateEmailAuthenticationRequestDto(
                    email = FAKE_EMAIL_DATA,
                    code = FAKE_INT_DATA,
                    isSigned = FAKE_BOOLEAN_DATA
                )
            internal val VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_DTO =
                ValidateEmailAuthenticationResponseDto(
                    result = FAKE_BOOLEAN_DATA
                )
        }

        object UpdateUserPassword {
            internal val UPDATE_PASSWORD_REQUEST_DTO = UpdatePasswordRequestDto(
                email = FAKE_EMAIL_DATA,
                password = FAKE_PASSWORD_DATA
            )
            internal val UPDATE_PASSWORD_RESPONSE_DTO = UpdatePasswordResponseDto(
                result = FAKE_BOOLEAN_DATA
            )
        }


    }

    object Badge {

        private val BADGE_DTO = BadgeDto(
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            hint = FAKE_STRING_DATA,
            url = FAKE_URL_DATA,
            color = FAKE_COLOR_DATA,
            backgroundColor = FAKE_COLOR_DATA
        )
        private val USER_BADGE_DTO = UserBadgeDto(
            badge = BADGE_DTO,
            badgeTimeStamp = FAKE_TIME_STAMP_DATA,
            mainFlag = FAKE_BOOLEAN_DATA
        )
        private val CREATE_USER_BADGE_DTO = CreateUserBadgeDto(
            id = FAKE_INT_DATA,
            badgeTimeStamp = FAKE_TIME_STAMP_DATA,
        )
        private val UPDATE_USER_BADGE_MAIN_FLAG_DTO =
            UpdateUserBadgeMainFlagDto(FAKE_INT_DATA, FAKE_BOOLEAN_DATA)

        object GetBadge {
            internal val GET_BADGE_RESPONSE_DTO = GetBadgeResponseDto(listOf(BADGE_DTO))
        }

        object GetUserBadge {
            internal val GET_USER_BADGE_RESPONSE_DTO =
                GetUserBadgeResponseDto(listOf(USER_BADGE_DTO))
        }

        object CreateUserBadge {
            internal val CREATE_USER_BADGE_REQUEST_DTO = CreateUserBadgeRequestDto(
                CREATE_USER_BADGE_DTO
            )
            internal val CREATE_USER_BADGE_RESPONSE_DTO =
                CreateUserBadgeResponseDto(FAKE_BOOLEAN_DATA)
        }

        object GetUserBadgeCondition {
            internal val GET_USER_BADGE_CONDITION_RESPONSE_DTO =
                GetUserBadgeConditionResponseDto(BADGE_DTO)
        }

        object GetUserBadgeByMainFlag {
            internal val GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_DTO =
                GetUserBadgeByMainFlagResponseDto(
                    listOf(USER_BADGE_DTO)
                )
        }

        object UpdateUserBadgeByMainFlag {
            internal val UPDATE_USER_BADGE_BY_MAIN_FLAG_REQUEST_DTO =
                UpdateUserBadgeMainFlagRequestDto(
                    listOf(UPDATE_USER_BADGE_MAIN_FLAG_DTO)
                )
            internal val UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_DTO =
                UpdateUserBadgeMainFlagResponseDto(
                    FAKE_BOOLEAN_DATA
                )
        }
    }

    object Routine {
        internal val ROUTINE_DTO = RoutineDto(
            routineId = FAKE_INT_DATA,
            routineSetId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workWeightList = listOf(
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA
            ),
            workRepetitionList = listOf(
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA
            )
        )
        internal val CREATE_ROUTINE_DTO = CreateRoutineDto(
            workCategory = FAKE_WORK_CATEGORY_NAME_DATA,
            workWeightList = listOf(
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA
            ),
            workRepetitionList = listOf(
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA
            )
        )
        internal val UPDATE_ROUTINE_DTO = UpdateRoutineDto(
            id = FAKE_INT_DATA,
            routineSetId = FAKE_INT_DATA,
            workCategory = FAKE_WORK_CATEGORY_NAME_DATA,
            workWeightList = listOf(
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA
            ),
            workRepetitionList = listOf(
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA
            )
        )
        internal val ROUTINE_SET_DTO = RoutineSetDto(
            routineSetId = FAKE_INT_DATA,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = FAKE_WEEKDAY_DATA,
            picture = FAKE_URL_DATA,
            label = FAKE_LABEL_DATA,
            count = FAKE_INT_DATA
        )
        internal val ROUTINE_SET_ROUTINE_DTO = RoutineSetRoutineDto(
            ROUTINE_DTO,
            ROUTINE_SET_DTO
        )

        object GetRoutine {
            val GET_ROUTINE_RESPONSE_DTO = GetRoutineResponseDto(routine = listOf(ROUTINE_DTO))
        }

        object GetRoutineSetRoutine {
            val GET_ROUTINE_SET_ROUTINE_RESPONSE_DTO =
                GetRoutineSetRoutineResponseDto(routineSetRoutine = listOf(ROUTINE_SET_ROUTINE_DTO))
        }

        object UpdateRoutineSetRoutine {
            val UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO = UpdateRoutineSetRoutineRequestDto(
                id = FAKE_INT_DATA,
                name = FAKE_ROUTINE_NAME_DATA,
                description = FAKE_ROUTINE_DESCRIPTION_DATA,
                picture = FAKE_URL_DATA,
                weekday = FAKE_WEEKDAY_DATA,
                label = FAKE_LABEL_DATA,
                routine = listOf(UPDATE_ROUTINE_DTO)
            )
            val UPDATE_ROUTINE_SET_ROUTINE_RESPONSE_DTO =
                UpdateRoutineSetRoutineResponseDto(result = FAKE_BOOLEAN_DATA)
        }

        object CreateRoutineSetRoutine {
            val CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO = CreateRoutineSetRoutineRequestDto(
                name = FAKE_ROUTINE_NAME_DATA,
                description = FAKE_ROUTINE_DESCRIPTION_DATA,
                picture = FAKE_URL_DATA,
                weekday = FAKE_WEEKDAY_DATA,
                label = FAKE_LABEL_DATA,
                routine = listOf(CREATE_ROUTINE_DTO)
            )
            val CREATE_ROUTINE_SET_ROUTINE_RESPONSE_DTO =
                CreateRoutineSetRoutineResponseDto(result = FAKE_BOOLEAN_DATA)
        }

        object DeleteRoutineSetRoutine {
            val DELETE_ROUTINE_SET_ROUTINE_RESPONSE_DTO =
                DeleteRoutineSetRoutineResponseDto(result = FAKE_BOOLEAN_DATA)
        }

        object UpdateUsedRoutineSet {
            val UPDATE_USED_ROUTINE_SET_REQUEST_DTO = UpdateUsedRoutineSetRequestDto(
                routineSetIdList = listOf(FAKE_INT_DATA),
                usedTimeStamp = FAKE_TIME_STAMP_DATA
            )
            val UPDATE_USED_ROUTINE_SET_RESPONSE_DTO =
                UpdateUsedRoutineSetResponseDto(result = FAKE_BOOLEAN_DATA)
        }

        object GetRoutineSetRoutineByRecent {
            val GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_DTO =
                GetRoutineSetRoutineByRecentResponseDto(
                    routineSetRoutine = listOf(ROUTINE_SET_ROUTINE_DTO)
                )
        }

        object GetRoutineSetRoutineByWeekday {
            val GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_DTO =
                GetRoutineSetRoutineByWeekdayResponseDto(
                    routineSetRoutine = listOf(ROUTINE_SET_ROUTINE_DTO)
                )
        }

        object GetRoutineSetRoutineByLabel {
            val GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_DTO =
                GetRoutineSetRoutineByLabelResponseDto(
                    routineSetRoutine = listOf(ROUTINE_SET_ROUTINE_DTO)
                )
        }

        object GetRoutineSetRoutineByRoutineSetId {
            val GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_DTO =
                GetRoutineSetRoutineByRoutineSetIdResponseDto(
                    routineSetRoutine = listOf(ROUTINE_SET_ROUTINE_DTO)
                )
        }
    }

    object Work {
        internal val WORK_PART_DTO =
            WorkPartDto(id = FAKE_INT_DATA, name = FAKE_WORK_PART_NAME_DATA)

        private val SEQUENCE_CONTENT_DTO = SequenceContentDto(
            title = FAKE_STRING_DATA,
            content = FAKE_STRING_DATA,
        )
        private val EFFECT_CONTENT_DTO = EffectContentDto(
            title = FAKE_STRING_DATA,
            content = FAKE_STRING_DATA,
        )

        internal val WORK_CATEGORY_DTO = WorkCategoryDto(
            id = FAKE_INT_DATA,
            name = FAKE_WORK_CATEGORY_NAME_DATA,
            equipment = FAKE_STRING_DATA,
            searchTag = listOf(FAKE_STRING_DATA),
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            introduce = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            sequence = listOf(SEQUENCE_CONTENT_DTO),
            effect = listOf(EFFECT_CONTENT_DTO),
            caution = listOf(FAKE_STRING_DATA),
        )

        object GetWorkPart {
            internal val GET_WORK_PART_RESPONSE_DTO =
                GetWorkPartResponseDto(workPart = listOf(WORK_PART_DTO))
        }

        object GetWorkCategory {
            internal val GET_WORK_CATEGORY_RESPONSE_DTO =
                GetWorkCategoryResponseDto(workCategory = listOf(WORK_CATEGORY_DTO))
        }

        object GetWorkCategoryById {
            internal val GET_WORK_CATEGORY_BY_ID_RESPONSE_DTO =
                GetWorkCategoryByIdResponseDto(workCategory = WORK_CATEGORY_DTO)
        }

        object GetPopularWorkCategory {
            internal val GET_POPULAR_WORK_CATEGORY_RESPONSE_DTO =
                GetPopularWorkCategoryResponseDto(workCategory = listOf(WORK_CATEGORY_DTO))
        }

        object GetRecommendWorkCategory {
            internal val GET_RECOMMEND_WORK_CATEGORY_RESPONSE_DTO =
                GetRecommendWorkCategoryResponseDto(workCategory = listOf(WORK_CATEGORY_DTO))
        }

        object GetWorkCategoryByWorkPart {
            internal val GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_DTO =
                GetWorkCategoryByWorkPartResponseDto(workCategory = listOf(WORK_CATEGORY_DTO))
        }
    }


    object User {
        private val USER_DETAIL_DTO = UserDetailDto(
            name = FAKE_STRING_DATA,
            gender = FAKE_GENDER_DATA,
            height = FAKE_HEIGHT_DATA,
            weight = FAKE_WEIGHT_DATA,
            profilePicture = FAKE_URL_DATA,
        )

        object GetAuthenticationMethod {
            internal val GET_USER_AUTHENTICATION_METHOD_RESPONSE_DTO =
                GetUserAuthenticationMethodResponseDto(FAKE_AUTHENTICATION_METHOD_DATA)
        }

        object GetUserDetail {
            internal val GET_USER_DETAIL_RESPONSE_DTO = GetUserDetailResponseDto(USER_DETAIL_DTO)
        }

        object UpdateUserDetail {
            internal val UPDATE_USER_DETAIL_REQUEST_DTO =
                UpdateUserDetailRequestDto(USER_DETAIL_DTO)
            internal val UPDATE_USER_DETAIL_RESPONSE_DTO = UpdateUserDetailResponseDto(
                FAKE_BOOLEAN_DATA
            )
        }

        object CreateUserDetail {
            internal val CREATE_USER_DETAIL_REQUEST_DTO =
                CreateUserDetailRequestDto(USER_DETAIL_DTO)
            internal val CREATE_USER_DETAIL_RESPONSE_DTO = CreateUserDetailResponseDto(
                FAKE_BOOLEAN_DATA
            )
        }

        object UpdateUserDetailInfo {
            internal val UPDATE_USER_DETAIL_INFO_REQUEST_DTO = UpdateUserDetailInfoRequestDto(
                gender = FAKE_GENDER_DATA,
                height = FAKE_HEIGHT_DATA,
                weight = FAKE_WEIGHT_DATA,
            )
            internal val UPDATE_USER_DETAIL_INFO_RESPONSE_DTO = UpdateUserDetailInfoResponseDto(
                FAKE_BOOLEAN_DATA
            )
        }

        object UpdateUserDetailProfilePicture {
            internal val UPDATE_USER_DETAIL_PROFILE_PICTURE_REQUEST_DTO =
                UpdateUserDetailProfilePictureRequestDto(
                    profilePicture = FAKE_URL_DATA
                )
            internal val UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_DTO =
                UpdateUserDetailProfilePictureResponseDto(
                    FAKE_BOOLEAN_DATA
                )
        }

        object UpdateUserDetailName {
            internal val UPDATE_USER_DETAIL_NAME_REQUEST_DTO = UpdateUserDetailNameRequestDto(
                name = FAKE_STRING_DATA
            )
            internal val UPDATE_USER_DETAIL_NAME_RESPONSE_DTO = UpdateUserDetailNameResponseDto(
                FAKE_BOOLEAN_DATA
            )
        }

        object ExistUserDetail {
            internal val EXIST_USER_DETAIL_RESPONSE_DTO = ExistUserDetailResponseDto(
                FAKE_BOOLEAN_DATA
            )
        }

        object DeleteUser {
            internal val DELETE_USER_REQUEST_DTO = DeleteUserRequestDto(
                reason = FAKE_STRING_DATA
            )
            internal val DELETE_USER_RESPONSE_DTO = DeleteUserResponseDto(
                result = FAKE_BOOLEAN_DATA
            )
        }

    }


    object Checker {
        object CheckDuplicateName {
            internal val CHECK_DUPLICATE_NAME_RESPONSE_DTO = CheckDuplicateNameResponseDto(
                result = FAKE_BOOLEAN_DATA
            )
        }

        object CheckDuplicateEmail {
            internal val CHECK_DUPLICATE_EMAIL_RESPONSE_DTO = CheckDuplicateEmailResponseDto(
                result = FAKE_BOOLEAN_DATA
            )
        }
    }


    object History {

        internal val HISTORY_ROUTINE_DTO = HistoryRoutineDto(
            historyId = FAKE_INT_DATA,
            historyRoutineId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workWeightList = listOf(
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA
            ),
            workRepetitionList = listOf(
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA
            )
        )

        internal val HISTORY_DTO = HistoryDto(
            historyId = FAKE_INT_DATA,
            comment = FAKE_STRING_DATA,
            score = FAKE_SCORE_DATA,
            progress = FAKE_PROGRESS_DATA,
            workTime = FAKE_TIME_DATA,
            restTime = FAKE_TIME_DATA,
            totalTime = FAKE_TIME_DATA,
            historyTimeStamp = FAKE_TIME_STAMP_DATA,
            historyRoutine = HISTORY_ROUTINE_DTO
        )
        internal val CREATE_HISTORY_ROUTINE_DTO = CreateHistoryRoutineDto(
            workCategory = FAKE_WORK_CATEGORY_NAME_DATA,
            workWeightList = listOf(
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA,
                FAKE_ROUTINE_WEIGHT_DATA
            ),
            workRepetitionList = listOf(
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA,
                FAKE_ROUTINE_REPETITION_DATA
            )
        )


        object GetHistory {
            internal val GET_HISTORY_RESPONSE_DTO = GetHistoryResponseDto(listOf(HISTORY_DTO))
        }

        object CreateHistory {
            internal val CREATE_HISTORY_REQUEST_DTO = CreateHistoryRequestDto(
                comment = FAKE_STRING_DATA,
                score = FAKE_SCORE_DATA,
                progress = FAKE_PROGRESS_DATA,
                workTime = FAKE_TIME_DATA,
                restTime = FAKE_TIME_DATA,
                totalTime = FAKE_TIME_DATA,
                historyTimeStamp = FAKE_TIME_STAMP_DATA,
                historyRoutine = listOf(CREATE_HISTORY_ROUTINE_DTO)
            )
            internal val CREATE_HISTORY_RESPONSE_DTO = CreateHistoryResponseDto(FAKE_BOOLEAN_DATA)
        }

        object DeleteHistory {
            internal val DELETE_HISTORY_RESPONSE_DTO = DeleteHistoryResponseDto(FAKE_BOOLEAN_DATA)
        }

        object GetHistoryByHistoryID {
            internal val GET_HISTORY_BY_HISTORY_ID_RESPONSE_DTO =
                GetHistoryByHistoryIdResponseDto(listOf(HISTORY_DTO))
        }

        object UpdateHistoryInfo {
            internal val UPDATE_HISTORY_INFO_REQUEST_DTO = UpdateHistoryInfoRequestDto(
                historyId = FAKE_INT_DATA,
                comment = FAKE_STRING_DATA,
                score = FAKE_SCORE_DATA
            )
            internal val UPDATE_HISTORY_INFO_RESPONSE_DTO = UpdateHistoryInfoResponseDto(
                FAKE_BOOLEAN_DATA
            )
        }
    }

    object Picture {
        internal val ROUTINE_SET_PICTURE_DTO = RoutineSetPictureDto(
            id = FAKE_INT_DATA,
            category = FAKE_STRING_DATA,
            url = FAKE_URL_DATA
        )

        internal val USER_PROFILE_PICTURE_DTO = UserProfilePictureDto(
            id = FAKE_INT_DATA,
            url = FAKE_URL_DATA
        )

        object GetRoutineSetPicture {
            internal val GET_ROUTINE_SET_PICTURE_RESPONSE_DTO = GetRoutineSetPictureResponseDto(
                listOf(ROUTINE_SET_PICTURE_DTO)
            )
        }

        object GetUserProfilePicture {
            internal val GET_USER_PROFILE_PICTURE_RESPONSE_DTO = GetUserProfilePictureResponseDto(
                listOf(USER_PROFILE_PICTURE_DTO)
            )
        }
    }

    object Notice {
        internal val NOTICE_DTO = NoticeDto(
            id = FAKE_INT_DATA,
            title = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            category = FAKE_STRING_DATA,
            date = FAKE_DATE_DATA
        )

        object GetNotice {
            internal val GET_NOTICE_RESPONSE_DTO = GetNoticeResponseDto(listOf(NOTICE_DTO))
        }

        object GetNoticeById {
            internal val GET_NOTICE_BY_ID_RESPONSE_DTO = GetNoticeByIdResponseDto(NOTICE_DTO)
        }
    }

    object Terms {
        object CreateUserTermsConsent {
            internal val CREATE_USER_TERMS_CONSENT_REQUEST_DTO = CreateUserTermsConsentRequestDto(
                FAKE_BOOLEAN_DATA, FAKE_BOOLEAN_DATA
            )
            internal val CREATE_USER_TERMS_CONSENT_RESPONSE_DTO = CreateUserTermsConsentResponseDto(
                FAKE_BOOLEAN_DATA
            )
        }

        object GetUserMarketingTermsConsent {
            internal val GET_USER_MARKETING_TERMS_CONSENT_RESPONSE_DTO =
                GetUserMarketingTermsConsentResponseDto(
                    FAKE_BOOLEAN_DATA
                )
        }

        object UpdateUserMarketingTermsConsent {
            internal val UPDATE_USER_MARKETING_TERMS_CONSENT_REQUEST_DTO =
                UpdateUserMarketingTermsConsentRequestDto(
                    FAKE_BOOLEAN_DATA
                )
            internal val UPDATE_USER_MARKETING_TERMS_CONSENT_RESPONSE_DTO =
                UpdateUserMarketingTermsConsentResponseDto(
                    FAKE_BOOLEAN_DATA
                )
        }
    }


    object Favorite {
        object GetWorkCategoryFavorite {
            internal val GET_WORK_CATEGORY_FAVORITE_RESPONSE_DTO =
                GetWorkCategoryFavoriteResponseDto(
                    listOf(FAKE_INT_DATA)
                )
        }

        object UpdateWorkCategoryFavorite {
            internal val UPDATE_WORK_CATEGORY_FAVORITE_REQUEST_DTO =
                UpdateWorkCategoryFavoriteRequestDto(
                    FAKE_INT_DATA
                )
            internal val UPDATE_WORK_CATEGORY_FAVORITE_RESPONSE_DTO =
                UpdateWorkCategoryFavoriteResponseDto(
                    FAKE_BOOLEAN_DATA
                )
        }
    }

    object Inquiry {
        object CreateInquiry {
            internal val CREATE_INQUIRY_REQUEST_DTO = CreateInquiryRequestDto(FAKE_STRING_DATA)
            internal val CREATE_INQUIRY_RESPONSE_DTO = CreateInquiryResponseDto(FAKE_BOOLEAN_DATA)
        }

    }

    object Refresh {
        object Refresh {
            internal val REFRESH_RESPONSE_DTO = RefreshResponseDto(
                accessToken = FAKE_ACCESS_TOKEN

            )
        }
    }
}