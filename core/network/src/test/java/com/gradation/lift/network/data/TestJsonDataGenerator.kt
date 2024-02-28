package com.gradation.lift.network.data

import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_AUTHENTICATION_METHOD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_DATE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_GENDER_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_HEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LABEL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_MESSAGE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PROGRESS_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_REPETITION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_SCORE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STATUS_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_STAMP_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_UNIT_OF_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEEKDAY_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_CATEGORY_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_PART_NAME_DATA

object TestJsonDataGenerator {


    object Auth {
        internal val SIGN_UP_DEFAULT_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()
        internal val SIGN_IN_DEFAULT_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "access_token": "$FAKE_ACCESS_TOKEN",
            "refresh_token": "$FAKE_REFRESH_TOKEN"
          }
        }
    """.trimIndent()

        internal val SIGN_UP_GOOGLE_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()

        internal val SIGN_IN_GOOGLE_RESPONSE_JSON = """
        {
         "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "access_token": "$FAKE_ACCESS_TOKEN",
            "refresh_token": "$FAKE_REFRESH_TOKEN"
          }
        }
    """.trimIndent()


        internal val SIGN_UP_KAKAO_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()

        internal val SIGN_IN_KAKAO_RESPONSE_JSON = """
        {
         "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "access_token": "$FAKE_ACCESS_TOKEN",
            "refresh_token": "$FAKE_REFRESH_TOKEN"
          }
        }
    """.trimIndent()

        internal val SIGN_UP_NAVER_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()

        internal val SIGN_IN_NAVER_RESPONSE_JSON = """
        {
         "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "access_token": "$FAKE_ACCESS_TOKEN",
            "refresh_token": "$FAKE_REFRESH_TOKEN"
          }
        }
    """.trimIndent()


        internal val CHECK_EXIST_USER_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()


        internal val CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()

        internal val VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()

        internal val UPDATE_PASSWORD_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
    """.trimIndent()
    }

    object Badge {
        internal val GET_BADGE_RESPONSE_JSON: String = """
            {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
              "data": {
                "badge": [
                  {
                    "id": $FAKE_INT_DATA,
                    "name": "$FAKE_STRING_DATA",
                    "description": "$FAKE_STRING_DATA",
                    "hint": "$FAKE_STRING_DATA",
                    "url": "$FAKE_URL_DATA",
                    "color": "$FAKE_COLOR_DATA",
                    "background_color": "$FAKE_COLOR_DATA"
                  }
                ]
              }
            }
        """.trimIndent()
        internal val GET_USER_BADGE_RESPONSE_JSON: String = """
            {
                 "status": $FAKE_STATUS_DATA,
               "message": "$FAKE_MESSAGE_DATA",
              "data": {
                "user_badge": [
                  {
                    "badge":
                     {
                     "id": $FAKE_INT_DATA,
                     "name": "$FAKE_STRING_DATA",
                     "description": "$FAKE_STRING_DATA",
                     "hint": "$FAKE_STRING_DATA",
                     "url": "$FAKE_URL_DATA",
                     "color": "$FAKE_COLOR_DATA",
                     "background_color": "$FAKE_COLOR_DATA"
                    },
                    "badge_time_stamp": "$FAKE_TIME_STAMP_DATA",
                    "main_flag": $FAKE_BOOLEAN_DATA
                  }
                ]
              }
            }
        """.trimIndent()

        internal val CREATE_USER_BADGE_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val GET_USER_BADGE_CONDITION_RESPONSE_JSON: String = """
            {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
              "data": {
                "badge":
                  {
                    "id": $FAKE_INT_DATA,
                    "name": "$FAKE_STRING_DATA",
                    "description": "$FAKE_STRING_DATA",
                    "hint": "$FAKE_STRING_DATA",
                    "url": "$FAKE_URL_DATA",
                    "color": "$FAKE_COLOR_DATA",
                    "background_color": "$FAKE_COLOR_DATA"
                  }
              }
            }
        """.trimIndent()


        internal val GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON: String = """
            {
                 "status": $FAKE_STATUS_DATA,
               "message": "$FAKE_MESSAGE_DATA",
              "data": {
                "user_badge": [
                  {
                    "badge":
                     {
                     "id": $FAKE_INT_DATA,
                     "name": "$FAKE_STRING_DATA",
                     "description": "$FAKE_STRING_DATA",
                     "hint": "$FAKE_STRING_DATA",
                     "url": "$FAKE_URL_DATA",
                     "color": "$FAKE_COLOR_DATA",
                     "background_color": "$FAKE_COLOR_DATA"
                    },
                    "badge_time_stamp": "$FAKE_TIME_STAMP_DATA",
                    "main_flag": $FAKE_BOOLEAN_DATA
                  }
                ]
              }
            }
        """.trimIndent()

        internal val UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()
    }


    object Routine {
        internal val GET_ROUTINE_RESPONSE_JSON: String = """
            {
             "status": $FAKE_STATUS_DATA,
             "message": "$FAKE_MESSAGE_DATA",
             "data": {
                    "routine": [
                      {
                        "routine_id": $FAKE_INT_DATA,
                        "routine_set_id": $FAKE_INT_DATA,
                        "work_category": {
                          "id": $FAKE_INT_DATA,
                          "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                          "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                          "introduce": "$FAKE_STRING_DATA",
                          "description": "$FAKE_STRING_DATA"
                        },
                        "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                      }
                    ]
                  }
                }
    """.trimIndent()


        internal val GET_ROUTINE_SET_ROUTINE_RESPONSE_JSON: String = """
        {
        "status": $FAKE_STATUS_DATA,
        "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "routine_set_routine": [
              {
                "routine": {
                   "routine_id": $FAKE_INT_DATA,
                   "routine_set_id": $FAKE_INT_DATA,
                   "work_category": {
                   "id": $FAKE_INT_DATA,
                   "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                   "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                    "introduce": "$FAKE_STRING_DATA",
                    "description": "$FAKE_STRING_DATA"
                   },
                   "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                },
                "routine_set": {
                  "routine_set_id": $FAKE_INT_DATA,
                  "name": "$FAKE_ROUTINE_NAME_DATA",
                  "description": "$FAKE_ROUTINE_DESCRIPTION_DATA",
                  "weekday": "$FAKE_WEEKDAY_DATA",
                  "picture": "$FAKE_URL_DATA",
                  "label":"$FAKE_LABEL_DATA",
                  "count" : $FAKE_INT_DATA
                }
              }
              ]
          }
        }
    """.trimIndent()

        internal val UPDATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val CREATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        internal val DELETE_ROUTINE_SET_ROUTINE_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val UPDATE_USED_ROUTINE_SET_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        internal val GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_JSON: String = """
        {
        "status": $FAKE_STATUS_DATA,
        "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "routine_set_routine": [
              {
                "routine": {
                   "routine_id": $FAKE_INT_DATA,
                   "routine_set_id": $FAKE_INT_DATA,
                   "work_category": {
                   "id": $FAKE_INT_DATA,
                   "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                   "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                    "introduce": "$FAKE_STRING_DATA",
                    "description": "$FAKE_STRING_DATA"
                   },
                   "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                },
                "routine_set": {
                  "routine_set_id": $FAKE_INT_DATA,
                  "name": "$FAKE_ROUTINE_NAME_DATA",
                  "description": "$FAKE_ROUTINE_DESCRIPTION_DATA",
                  "weekday": "$FAKE_WEEKDAY_DATA",
                  "picture": "$FAKE_URL_DATA",
                  "label":"$FAKE_LABEL_DATA",
                  "count" : $FAKE_INT_DATA
                }
              }
              ]
          }
        }
    """.trimIndent()


        internal val GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_JSON: String = """
        {
        "status": $FAKE_STATUS_DATA,
        "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "routine_set_routine": [
              {
                "routine": {
                   "routine_id": $FAKE_INT_DATA,
                   "routine_set_id": $FAKE_INT_DATA,
                   "work_category": {
                   "id": $FAKE_INT_DATA,
                   "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                   "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                    "introduce": "$FAKE_STRING_DATA",
                    "description": "$FAKE_STRING_DATA"
                   },
                  "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                },
                "routine_set": {
                  "routine_set_id": $FAKE_INT_DATA,
                  "name": "$FAKE_ROUTINE_NAME_DATA",
                  "description": "$FAKE_ROUTINE_DESCRIPTION_DATA",
                  "weekday": "$FAKE_WEEKDAY_DATA",
                  "picture": "$FAKE_URL_DATA",
                  "label":"$FAKE_LABEL_DATA",
                  "count" : $FAKE_INT_DATA
                }
              }
              ]
          }
        }
    """.trimIndent()


        internal val GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_JSON: String = """
        {
        "status": $FAKE_STATUS_DATA,
        "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "routine_set_routine": [
              {
                "routine": {
                   "routine_id": $FAKE_INT_DATA,
                   "routine_set_id": $FAKE_INT_DATA,
                   "work_category": {
                   "id": $FAKE_INT_DATA,
                   "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                   "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                    "introduce": "$FAKE_STRING_DATA",
                    "description": "$FAKE_STRING_DATA"
                   },
                 "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                },
                "routine_set": {
                  "routine_set_id": $FAKE_INT_DATA,
                  "name": "$FAKE_ROUTINE_NAME_DATA",
                  "description": "$FAKE_ROUTINE_DESCRIPTION_DATA",
                  "weekday": "$FAKE_WEEKDAY_DATA",
                  "picture": "$FAKE_URL_DATA",
                  "label":"$FAKE_LABEL_DATA",
                  "count" : $FAKE_INT_DATA
                }
              }
              ]
          }
        }
    """.trimIndent()


        internal val GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_JSON: String = """
        {
        "status": $FAKE_STATUS_DATA,
        "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "routine_set_routine": [
              {
                "routine": {
                   "routine_id": $FAKE_INT_DATA,
                   "routine_set_id": $FAKE_INT_DATA,
                   "work_category": {
                   "id": $FAKE_INT_DATA,
                   "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                   "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                    "introduce": "$FAKE_STRING_DATA",
                    "description": "$FAKE_STRING_DATA"
                   },
                  "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                },
                "routine_set": {
                  "routine_set_id": $FAKE_INT_DATA,
                  "name": "$FAKE_ROUTINE_NAME_DATA",
                  "description": "$FAKE_ROUTINE_DESCRIPTION_DATA",
                  "weekday": "$FAKE_WEEKDAY_DATA",
                  "picture": "$FAKE_URL_DATA",
                  "label":"$FAKE_LABEL_DATA",
                  "count" : $FAKE_INT_DATA
                }
              }
              ]
          }
        }
    """.trimIndent()
    }


    object Work {
        internal val GET_WORK_PART_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "work_part": [
              {
                "id": $FAKE_INT_DATA,
                "name": "$FAKE_WORK_PART_NAME_DATA"
              }
            ]
          }
        }
    """.trimIndent()


        internal val GET_WORK_CATEGORY_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "work_category": [
              {
                "id": $FAKE_INT_DATA,
                "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                "introduce": "$FAKE_STRING_DATA",
                "description": "$FAKE_STRING_DATA"
              }
            ]
          }
        }
    """.trimIndent()


        internal val GET_WORK_CATEGORY_BY_ID_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "work_category":  {
                "id": $FAKE_INT_DATA,
                "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                "introduce": "$FAKE_STRING_DATA",
                "description": "$FAKE_STRING_DATA"
              }
          }
        }
    """.trimIndent()


        internal val GET_POPULAR_WORK_CATEGORY_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "work_category": [
              {
                "id": $FAKE_INT_DATA,
                "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                "introduce": "$FAKE_STRING_DATA",
                "description": "$FAKE_STRING_DATA"
              }
            ]
          }
        }
    """.trimIndent()

        internal val GET_RECOMMEND_WORK_CATEGORY_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "work_category": [
              {
                "id": $FAKE_INT_DATA,
                "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                "introduce": "$FAKE_STRING_DATA",
                "description": "$FAKE_STRING_DATA"
              }
            ]
          }
        }
    """.trimIndent()


        internal val GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_JSON: String = """
         {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "work_category": [
              {
                "id": $FAKE_INT_DATA,
                "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                "introduce": "$FAKE_STRING_DATA",
                "description": "$FAKE_STRING_DATA"
              }
            ]
          }
        }
    """.trimIndent()

    }


    object User {

        internal val GET_USER_AUTHENTICATION_METHOD_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "authentication_method": "$FAKE_AUTHENTICATION_METHOD_DATA"
          }
        }
        """.trimIndent()

        internal val GET_USER_DETAIL_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "user_detail": {
              "name": "$FAKE_STRING_DATA",
              "gender": "$FAKE_GENDER_DATA",
              "height": "$FAKE_HEIGHT_DATA",
              "weight": "$FAKE_WEIGHT_DATA",
              "profile_picture": "$FAKE_URL_DATA",
              "unit_of_weight": "$FAKE_UNIT_OF_WEIGHT_DATA"
            }
          }
        }
    """.trimIndent()

        internal val UPDATE_USER_DETAIL_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val CREATE_USER_DETAIL_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        internal val UPDATE_USER_DETAIL_INFO_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val UPDATE_USER_DETAIL_NAME_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        internal val EXIST_USER_DETAIL_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        internal val DELETE_USER_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()
    }


    object Checker {
        internal val CHECK_DUPLICATE_NAME_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        internal val CHECK_DUPLICATE_EMAIL_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()
    }


    object History {
        val GET_HISTORY_RESPONSE_JSON = """
            {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
              "data": {
                "history": [
                  {
                    "history_id": $FAKE_INT_DATA,
                    "comment": "$FAKE_STRING_DATA",
                    "score": $FAKE_SCORE_DATA,
                    "progress": $FAKE_PROGRESS_DATA,
                    "work_time": "$FAKE_TIME_DATA",
                    "rest_time": "$FAKE_TIME_DATA",
                    "total_time": "$FAKE_TIME_DATA",
                    "history_time_stamp": "$FAKE_TIME_STAMP_DATA",
                    "history_routine": {
                      "history_routine_id": $FAKE_INT_DATA,
                      "history_id": $FAKE_INT_DATA,
                      "work_category":  {
                       "id": $FAKE_INT_DATA,
                       "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                       "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                       "introduce": "$FAKE_STRING_DATA",
                       "description": "$FAKE_STRING_DATA"
                        }
                     "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                    }
                  }
                ]
              }
            }
        """.trimIndent()


        internal val CREATE_HISTORY_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val DELETE_HISTORY_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        val GET_HISTORY_BY_HISTORY_ID_RESPONSE_JSON = """
            {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
              "data": {
                "history": [
                  {
                    "history_id": $FAKE_INT_DATA,
                    "comment": "$FAKE_STRING_DATA",
                    "score": $FAKE_SCORE_DATA,
                    "progress": $FAKE_PROGRESS_DATA,
                    "work_time": "$FAKE_TIME_DATA",
                    "rest_time": "$FAKE_TIME_DATA",
                    "total_time": "$FAKE_TIME_DATA",
                    "history_time_stamp": "$FAKE_TIME_STAMP_DATA",
                    "history_routine": {
                      "history_routine_id": $FAKE_INT_DATA,
                      "history_id": $FAKE_INT_DATA,
                      "work_category":  {
                       "id": $FAKE_INT_DATA,
                       "name": "$FAKE_WORK_CATEGORY_NAME_DATA",
                       "work_part": ["$FAKE_WORK_PART_NAME_DATA"],
                       "introduce": "$FAKE_STRING_DATA",
                       "description": "$FAKE_STRING_DATA"
                        }
                     "work_weight_list": [
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f,
                          ${FAKE_ROUTINE_WEIGHT_DATA}f
                        ],
                        "work_repetition_list": [
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA,
                           $FAKE_ROUTINE_REPETITION_DATA
                        ]
                    }
                  }
                ]
              }
            }
        """.trimIndent()


        internal val UPDATE_HISTORY_INFO_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()
    }

    object Picture {
        internal val GET_ROUTINE_SET_PICTURE_RESPONSE_JSON = """
            {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
              "data": {
                "routine_set_picture": [
                  {
                    "id": $FAKE_INT_DATA,
                    "category": "$FAKE_STRING_DATA",
                    "url": "$FAKE_URL_DATA"
                  }
                ]
              }
            }
        """.trimIndent()

        internal val GET_USER_PROFILE_PICTURE_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "user_profile_picture": [
              {
                "id": $FAKE_INT_DATA,
                "url": "$FAKE_URL_DATA"
              }
            ]
          }
        }
    """.trimIndent()

    }

    object Notice {
        internal val GET_NOTICE_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "notice": [
              {
                "id": $FAKE_INT_DATA,
                "title": "$FAKE_STRING_DATA",
                "description": "$FAKE_STRING_DATA",
                "category": "$FAKE_STRING_DATA",
                "date": "$FAKE_DATE_DATA"
              }
            ]
          }
        }
    """.trimIndent()

        internal val GET_NOTICE_BY_ID_RESPONSE_JSON = """
             {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "notice": 
            {
                "id": $FAKE_INT_DATA,
                "title": "$FAKE_STRING_DATA",
                "description": "$FAKE_STRING_DATA",
                "category": "$FAKE_STRING_DATA",
                "date": "$FAKE_DATE_DATA"
              }
          }
        }
        """.trimIndent()
    }


    object Terms {
        internal val CREATE_USER_TERMS_CONSENT_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


        internal val GET_USER_MARKETING_TERMS_CONSENT_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()

        internal val UPDATE_USER_MARKETING_TERMS_CONSENT_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()


    }


    object Favorite {

        internal val GET_WORK_CATEGORY_FAVORITE_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "work_category_id_list": [
                $FAKE_INT_DATA
            ]
          }
        }
        """.trimIndent()

        internal val UPDATE_WORK_CATEGORY_FAVORITE_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()
    }

    object Inquiry {
        internal val CREATE_INQUIRY_RESPONSE_JSON: String = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "result": $FAKE_BOOLEAN_DATA
          }
        }
        """.trimIndent()
    }

    object Refresh {
        internal val REFRESH_RESPONSE_JSON = """
        {
          "status": $FAKE_STATUS_DATA,
          "message": "$FAKE_MESSAGE_DATA",
          "data": {
            "access_token": "$FAKE_ACCESS_TOKEN"
          }
        }
    """.trimIndent()
    }
}