package com.gradation.lift.network.data

import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_REFRESH_TOKEN

object TestJsonDataGenerator {


    object Auth {
        internal val signInDefaultResponseJson = """
        {
          "status": true,
          "message": "",
          "data": {
            "access_token": $FAKE_ACCESS_TOKEN,
            "refresh_token": $FAKE_REFRESH_TOKEN
          }
        }
    """.trimIndent()
    }

    object Checker{

    }

    object Refresh {
        internal val refreshResponseJson = """
        {
          "status": true,
          "message": "",
          "data": {
            "access_token": $FAKE_ACCESS_TOKEN
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
              "name": "test",
              "gender": "male",
              "height": 180.0,
              "weight": 83.3,
              "profile_picture": null,
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

    object RoutineSetRoutine{
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
                  "name": "행복한 월요일 루틴",
                  "description": "가볍게 하는 운동",
                  "weekday": "Mon",
                  "picture": null
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
                  "name": "등 단련 루틴",
                  "description": "집중 등 운동",
                  "weekday": "Mon",
                  "picture": null
                }
              }
            ]
          }
        }
    """.trimIndent()
    }
}