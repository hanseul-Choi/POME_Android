package com.teampome.pome.presentation.record.finish

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.navigation.fragment.findNavController
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRecordGoalFinishBinding
import com.teampome.pome.model.ContentCardItem
import com.teampome.pome.presentation.remind.RemindContentsCardAdapter
import com.teampome.pome.util.base.BaseFragment

class RecordGoalFinishFragment : BaseFragment<FragmentRecordGoalFinishBinding>(R.layout.fragment_record_goal_finish) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recordAmountProgressAsb.apply {
            isEnabled = false

            binding.recordAmountProgressTextTv.text = getString(R.string.record_progress_percent).format(progress)

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val thumbBounds = thumb.bounds
                    val progressTextWidth = binding.recordAmountProgressTextTv.width

                    Log.d("progress", "thumbBounds center : ${thumbBounds.exactCenterX()}, progressTextWidth : $progressTextWidth")

                    // thumb의 중간 - (progressTv 길이 / 2) -1f => 1f는 살짝 왼쪽으로 조정하는 값
                    binding.recordAmountProgressTextTv.x = thumbBounds.exactCenterX() - (progressTextWidth.toFloat() / 2f) - 1f
                }
            })

            binding.recordGoalFinishCheckButtonAcb.setOnClickListener {
                moveToGoalFinishComment()
            }
        }

        binding.recordGoalEmotionCardListRv.adapter = RemindContentsCardAdapter().apply {
            submitList(listOf(
                ContentCardItem(
                    firstThink = "후회해요",
                    lastThink = "행복해요",
                    money = "16,000원",
                    contentText = "아휴 힘빠져 이젠 진짜 포기다 포기 도대체 뭐가 문제일까 현실을 되돌아볼 필요를 느낀다ㅠ 이정도 노력했으면 된거 아닌가 진짜 개 힘빠지네 그래서 오늘은 물 대신 라떼 한잔을 마셨습니다 ㅋ 라뗴 존맛탱~~ 다들 나흐바 시그니쳐 커피를 마셔주세요 설탕 솔솔 뿌려서 개맛있음",
                    name = "커피 대신 물을 마시자",
                    time = "44분 전",
                    friendEmotions = listOf(
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                    )
                ),
                ContentCardItem(
                    firstThink = "후회해요",
                    lastThink = "행복해요",
                    money = "16,000원",
                    contentText = "아휴 힘빠져 이젠 진짜 포기다 포기 도대체 뭐가 문제일까 현실을 되돌아볼 필요를 느낀다ㅠ 이정도 노력했으면 된거 아닌가 진짜 개 힘빠지네 그래서 오늘은 물 대신 라떼 한잔을 마셨습니다 ㅋ 라뗴 존맛탱~~ 다들 나흐바 시그니쳐 커피를 마셔주세요 설탕 솔솔 뿌려서 개맛있음",
                    name = "커피 대신 물을 마시자",
                    time = "44분 전",
                    friendEmotions = listOf(
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                    )
                ),
                ContentCardItem(
                    firstThink = "후회해요",
                    lastThink = "행복해요",
                    money = "16,000원",
                    contentText = "아휴 힘빠져 이젠 진짜 포기다 포기 도대체 뭐가 문제일까 현실을 되돌아볼 필요를 느낀다ㅠ 이정도 노력했으면 된거 아닌가 진짜 개 힘빠지네 그래서 오늘은 물 대신 라떼 한잔을 마셨습니다 ㅋ 라뗴 존맛탱~~ 다들 나흐바 시그니쳐 커피를 마셔주세요 설탕 솔솔 뿌려서 개맛있음",
                    name = "커피 대신 물을 마시자",
                    time = "44분 전",
                    friendEmotions = listOf(
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                    )
                ),
                ContentCardItem(
                    firstThink = "후회해요",
                    lastThink = "행복해요",
                    money = "16,000원",
                    contentText = "아휴 힘빠져 이젠 진짜 포기다 포기 도대체 뭐가 문제일까 현실을 되돌아볼 필요를 느낀다ㅠ 이정도 노력했으면 된거 아닌가 진짜 개 힘빠지네 그래서 오늘은 물 대신 라떼 한잔을 마셨습니다 ㅋ 라뗴 존맛탱~~ 다들 나흐바 시그니쳐 커피를 마셔주세요 설탕 솔솔 뿌려서 개맛있음",
                    name = "커피 대신 물을 마시자",
                    time = "44분 전",
                    friendEmotions = listOf(
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                    )
                ),
                ContentCardItem(
                    firstThink = "후회해요",
                    lastThink = "행복해요",
                    money = "16,000원",
                    contentText = "아휴 힘빠져 이젠 진짜 포기다 포기 도대체 뭐가 문제일까 현실을 되돌아볼 필요를 느낀다ㅠ 이정도 노력했으면 된거 아닌가 진짜 개 힘빠지네 그래서 오늘은 물 대신 라떼 한잔을 마셨습니다 ㅋ 라뗴 존맛탱~~ 다들 나흐바 시그니쳐 커피를 마셔주세요 설탕 솔솔 뿌려서 개맛있음",
                    name = "커피 대신 물을 마시자",
                    time = "44분 전",
                    friendEmotions = listOf(
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                        "모르겠어요",
                    )
                )
            ))
        }

        binding.recordGoalFinishBackButtonIv.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initListener() {

    }

    private fun moveToGoalFinishComment() {
        val action = RecordGoalFinishFragmentDirections.actionRecordGoalFinishFragmentToRecordGoalFinishCommentFragment()

        findNavController().navigate(action)
    }
}