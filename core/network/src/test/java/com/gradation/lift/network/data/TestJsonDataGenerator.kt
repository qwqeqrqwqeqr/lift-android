package com.gradation.lift.network.data

import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA

object TestJsonDataGenerator {


    object Auth {
        internal val signInDefaultResponseJson = """
        {
          "status": true,
          "message": "",
          "data": {
            "access_token": "$FAKE_ACCESS_TOKEN",
            "refresh_token": "$FAKE_REFRESH_TOKEN"
          }
        }
    """.trimIndent()
    }

    object Refresh {
        internal val refreshResponseJson = """
        {
          "status": true,
          "message": "",
          "data": {
            "access_token": "$FAKE_ACCESS_TOKEN"
          }
        }
    """.trimIndent()
    }

    object WorkPart {
        val workPartResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "work_part": [
              {
                "id": 1,
                "name": "어깨"
              },
              {
                "id": 2,
                "name": "등"
              }
            ]
          }
        }
    """.trimIndent()
    }

    object WorkCategory {

        val workCategoryResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "work_category": [
              {
                "id": 1,
                "name": "숄더프레스",
                "work_part": {
                  "id": 1,
                  "name": "어깨"
                },
                "introduce": "Lorem ipsum dolor sit amet",
                "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
              },
              {
                "id": 2,
                "name": "데드리프트",
                "work_part": {
                  "id": 2,
                  "name": "등"
                },
                "introduce": "Lorem ipsum dolor sit amet",
                "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
              }
            ]
          }
        }
    """.trimIndent()
    }


    object Common {
        val resultResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "result": true
          }
        }
    """.trimIndent()
    }


    object User {
        val userDetailResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "user_detail": {
              "name": "$FAKE_STRING_DATA",
              "gender": "male",
              "height": 180.0,
              "weight": 83.3,
              "profile_picture": "$FAKE_URL_DATA",
              "unit_of_weight": "kg"
            }
          }
        }
    """.trimIndent()


    }

    object Routine {
        val routineResponseJson: String = """
                       {
                  "status": true,
                  "message": "",
                  "data": {
                    "routine": [
                      {
                        "routine_id": 1,
                        "routine_set_id": 1,
                        "work_category": {
                          "id": 1,
                          "name": "숄더프레스",
                          "work_part": {
                            "id": 1,
                            "name": "어깨"
                          },
                          "introduce": "Lorem ipsum dolor sit amet",
                          "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        },
                        "work_weight_list": [
                          10,
                          10,
                          10,
                          10,
                          10
                        ],
                        "work_repetition_list": [
                          12,
                          12,
                          12,
                          12,
                          12
                        ]
                      },
                      {
                        "routine_id": 2,
                        "routine_set_id": 2,
                        "work_category": {
                          "id": 2,
                          "name": "데드리프트",
                          "work_part": {
                            "id": 2,
                            "name": "등"
                          },
                          "introduce": "Lorem ipsum dolor sit amet",
                          "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        },
                        "work_weight_list": [
                          10,
                          10,
                          10,
                          10,
                          10
                        ],
                        "work_repetition_list": [
                          12,
                          12,
                          12,
                          12,
                          12
                        ]
                      }
                    ]
                  }
                }
    """.trimIndent()
    }

    object RoutineSetRoutine {
        val routineSetRoutineResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "routine_set_routine": [
              {
                "routine": {
                  "routine_id": 1,
                  "routine_set_id": 1,
                  "work_category": {
                    "id": 1,
                    "name": "숄더프레스",
                    "work_part": {
                      "id": 1,
                      "name": "어깨"
                    },
                    "introduce": "Lorem ipsum dolor sit amet",
                    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                  },
                  "work_weight_list": [
                    10,
                    10,
                    10,
                    10,
                    10
                  ],
                  "work_repetition_list": [
                    12,
                    12,
                    12,
                    12,
                    12
                  ]
                },
                "routine_set": {
                  "routine_set_id": 1,
                  "name": "$FAKE_ROUTINE_NAME_DATA",
                  "description": $FAKE_ROUTINE_DESCRIPTION_DATA,
                  "weekday": "Mon",
                  "picture": "$FAKE_URL_DATA"
                }
              },
              {
                "routine": {
                  "routine_id": 2,
                  "routine_set_id": 2,
                  "work_category": {
                    "id": 2,
                    "name": "데드리프트",
                    "work_part": {
                      "id": 2,
                      "name": "등"
                    },
                    "introduce": "Lorem ipsum dolor sit amet",
                    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                  },
                  "work_weight_list": [
                    10,
                    10,
                    10,
                    10,
                    10
                  ],
                  "work_repetition_list": [
                    12,
                    12,
                    12,
                    12,
                    12
                  ]
                },
                "routine_set": {
                  "routine_set_id": 2,
                  "name": "$FAKE_ROUTINE_NAME_DATA",
                  "description": "$FAKE_ROUTINE_DESCRIPTION_DATA",
                  "weekday": "Mon",
                  "picture": "$FAKE_URL_DATA"
                }
              }
            ]
          }
        }
    """.trimIndent()
    }

    object Picture {
        val routineSetPictureResponseJson = """
            {
              "status": true,
              "message": "",
              "data": {
                "routine_set_picture": [
                  {
                    "id": 1,
                    "category": "$FAKE_STRING_DATA",
                    "url": "$FAKE_URL_DATA"
                  },
                  {
                    "id": 2,
                    "category": "$FAKE_STRING_DATA",
                    "url": "$FAKE_URL_DATA"
                  }
                ]
              }
            }
        """.trimIndent()
    }

    val userProfilePictureResponseJson = """
        {
          "status": true,
          "message": "",
          "data": {
            "user_profile_picture": [
              {
                "id": 1,
                "url": "$FAKE_URL_DATA"
              },
              {
                "id": 2,
                "url": "$FAKE_URL_DATA"
              }
            ]
          }
        }
    """.trimIndent()


    object History {
        val historyResponseJson = """
            {
              "status": true,
              "message": "",
              "data": {
                "history": [
                  {
                    "history_id": 1,
                    "comment": "보람찬 하루",
                    "score": 5,
                    "work_time": 1200,
                    "rest_time": 600,
                    "total_time": 1800,
                    "history_time_stamp": "2023-08-31T00:00:00",
                    "history_routine": {
                      "history_routine_id": 1,
                      "history_id": 1,
                      "work_category": {
                        "id": 1,
                        "name": "숄더프레스",
                        "work_part": {
                          "id": 1,
                          "name": "어깨"
                        },
                        "introduce": "Lorem ipsum dolor sit amet",
                        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                      },
                      "work_weight_list": [
                        10,
                        10,
                        10,
                        10,
                        10
                      ],
                      "work_repetition_list": [
                        12,
                        12,
                        12,
                        12,
                        12
                      ]
                    }
                  },
                  {
                    "history_id": 2,
                    "comment": "행복한 하루",
                    "score": 2,
                    "work_time": 1200,
                    "rest_time": 600,
                    "total_time": 1800,
                    "history_time_stamp": "2023-08-31T00:00:00",
                    "history_routine": {
                      "history_routine_id": 2,
                      "history_id": 2,
                      "work_category": {
                        "id": 2,
                        "name": "데드리프트",
                        "work_part": {
                          "id": 2,
                          "name": "등"
                        },
                        "introduce": "Lorem ipsum dolor sit amet",
                        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                      },
                      "work_weight_list": [
                        10,
                        10,
                        10,
                        10,
                        10
                      ],
                      "work_repetition_list": [
                        12,
                        12,
                        12,
                        12,
                        12
                      ]
                    }
                  }
                ]
              }
            }
        """.trimIndent()
    }
}