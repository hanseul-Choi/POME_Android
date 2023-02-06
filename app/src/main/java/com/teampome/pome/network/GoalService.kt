package com.teampome.pome.network

import com.teampome.pome.model.base.BasePomeResponse
import com.teampome.pome.model.goal.AllGoalData
import com.teampome.pome.model.goal.GoalData
import com.teampome.pome.model.request.GoalDataBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GoalService {

    /**
     *  모든 목표 조회
     */
    @GET("api/v1/goals/users")
    suspend fun findAllGoalByUser() : Response<BasePomeResponse<AllGoalData>>

    /**
     *  goalId로 하나의 목표 조회
     */
    @GET("api/v1/goals/{goalId}")
    suspend fun getGoalByGoalId(
        @Path("goalId") goalId: String
    ) : Response<BasePomeResponse<GoalData>>

    /**
     *  목표 생성
     */
    @POST("api/v1/goals")
    suspend fun makeGoal(
        @Body goalDataBody: GoalDataBody
    ) : Response<BasePomeResponse<GoalData>>
}