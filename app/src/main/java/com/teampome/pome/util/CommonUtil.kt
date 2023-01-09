package com.teampome.pome.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import com.teampome.pome.R
import com.teampome.pome.presentation.record.DayDecorator
import com.teampome.pome.viewmodel.Emotion
import org.threeten.bp.DateTimeUtils
import org.threeten.bp.DayOfWeek
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import java.text.SimpleDateFormat

object CommonUtil {

    /**
     *  키보드 자연스럽게 처리를 위한 메소드 (키보드 바깥쪽 클릭시 키보드 hide)
     */
    fun hideKeyboard(activity: Activity) {
        if(activity.currentFocus != null) {
            val inputManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    // Todo : 화면밀기 동작 안함
    /**
     *  keyboard가 화면을 밀게 설정
     */
    fun inputModePan(activity: Activity) {
        activity.window.setSoftInputMode(LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    /**
     *  keyboard가 화면을 못밀게 설정
     */
    fun inputModeNothing(activity: Activity) {
        activity.window.setSoftInputMode(LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }

    /**
     *  emotion 정보를 가져오기
     */
    fun getEmotionData(emotionStr: String) : Emotion? {
        return when(emotionStr) {
            Constants.HAPPY_EMOTION -> {
                Emotion.HAPPY_EMOTION
            }
            Constants.WHAT_EMOTION -> {
                Emotion.WHAT_EMOTION
            }
            Constants.SAD_EMOTION -> {
                Emotion.SAD_EMOTION
            }
            else -> {
                null
            }
        }
    }

    fun getPixelToDp(context: Context, wantDp: Int): Int {
        val scale = context.resources.displayMetrics.density

        return (wantDp * scale + 0.5f).toInt()
    }

    // device size
    fun getDeviceSize(context: Context): IntArray {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        return intArrayOf(size.x, size.y)
    }

    // setting calendar
    fun settingCalendarBottomSheetDialog(
        context: Context,
        calendar : MaterialCalendarView,
        checkBtn : Button,
        dateCallback: (String) -> Unit,
        btnCallback: () -> Unit
    ) {
        var isButtonEnabled = false

        // 초기 캘린더 버튼 설정
        disabledCalendarBtn(checkBtn)

        calendar.apply {
            // 첫 시작 요일 - 월요일
            state().edit().setFirstDayOfWeek(DayOfWeek.MONDAY).commit()

            // 한글 설정
            setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
            setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

            // 헤더 폰트 설정
            setHeaderTextAppearance(R.style.Pome_SemiBold_16)

            // 선택시 드로어블 적용
            addDecorators(DayDecorator(context))

            isDynamicHeightEnabled = true

            setOnDateChangedListener { _, date, _ ->
                val sdf = SimpleDateFormat("yy.MM.dd")
                val realDate = DateTimeUtils.toDate(date.date.atStartOfDay(ZoneId.systemDefault()).toInstant())

                val dateStr = sdf.format(realDate)

                dateCallback(dateStr)

                if(!isButtonEnabled) {
                    isButtonEnabled = true

                    enabledCalendarBtn(checkBtn)
                }
            }

            setTitleFormatter(object : TitleFormatter {
                override fun format(day: CalendarDay?): CharSequence {
                    day?.let {
                        val calendarElement = it.date.toString().split("-")
                        return "${calendarElement[0]}년 ${calendarElement[1]}월"
                    } ?: return ""
                }
            })
        }

        checkBtn.setOnClickListener {
            btnCallback()
        }
    }

    private fun enabledCalendarBtn(
        button: Button
    ) {
        button.isClickable = true
        button.isEnabled = true
        button.setBackgroundResource(R.drawable.register_profile_name_check_available_btn_background)
    }

    private fun disabledCalendarBtn(
        button: Button
    ) {
        button.isClickable = false
        button.isEnabled = false
        button.setBackgroundResource(R.drawable.register_profile_name_check_disable_btn_background)
    }

    fun settingAlreadySelectedCalendarBottomSheetDialog(
        context: Context,
        calendar: MaterialCalendarView,
        checkBtn: Button,
        localDate: LocalDate,
        dateCallback: (String) -> Unit,
        btnCallback: () -> Unit
    ) {
        settingCalendarBottomSheetDialog(
            context,
            calendar,
            checkBtn,
            dateCallback,
            btnCallback
        )

        // 현재 선택중인 날짜 설정
        calendar.setSelectedDate(localDate)
    }
}