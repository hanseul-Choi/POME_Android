package com.teampome.pome.presentation.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRegisterTermsBinding
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.viewmodel.RegisterTermsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterTermsFragment : BaseFragment<FragmentRegisterTermsBinding>(R.layout.fragment_register_terms) {
    private val viewModel by viewModels<RegisterTermsViewModel>()

    private val checkColor by lazy {
        resources.getColor(R.color.main, null)
    }

    private val notCheckColor by lazy {
        resources.getColor(R.color.grey_3, null)
    }

    override fun initListener() {
        // 전체 동의 클릭
        binding.registerAllAgreeCheckAiv.setOnClickListener {

            if(viewModel.agreeAllCheck.value == true) {
                viewModel._agreeAllCheck.value = false
                viewModel._agreeUsingTermsCheck.value = false
                viewModel._agreePrivacyCheck.value = false
                viewModel._agreeMarketingCheck.value = false
            } else {
                viewModel._agreeAllCheck.value = true
                viewModel._agreeUsingTermsCheck.value = true
                viewModel._agreePrivacyCheck.value = true
                viewModel._agreeMarketingCheck.value = true
            }
        }

        // 이용약관 동의 클릭
        binding.registerAgreeUsingTermsCheckAiv.setOnClickListener {
            viewModel._agreeUsingTermsCheck.value = viewModel.agreeUsingTermsCheck.value != true
        }

        // 개인정보 수집 동의 클릭
        binding.registerAgreePrivacyCheckAiv.setOnClickListener {
            viewModel._agreePrivacyCheck.value = viewModel.agreePrivacyCheck.value != true
        }

        // 마케팅 정보 수집 동의 클릭
        binding.registerAgreeMarketingCheckAiv.setOnClickListener {
            viewModel._agreeMarketingCheck.value = viewModel.agreeMarketingCheck.value != true
        }

        // 전체동의 체크시 뷰
        viewModel.agreeAllCheck.observe(viewLifecycleOwner) {
            if(it) {
                binding.registerAllAgreeCheckAiv.setColorFilter(checkColor)
            } else {
                binding.registerAllAgreeCheckAiv.setColorFilter(notCheckColor)
            }

            checkAgreeButtonActive()
        }

        // 이용약관 동의 체크 시 뷰
        viewModel.agreeUsingTermsCheck.observe(viewLifecycleOwner) {
            if(it) {
                binding.registerAgreeUsingTermsCheckAiv.setColorFilter(checkColor)
            } else {
                binding.registerAgreeUsingTermsCheckAiv.setColorFilter(notCheckColor)
            }

            checkAllAgreeChecking()
            checkAgreeButtonActive()
        }

        // 개인정보 동의 체크 시 뷰
        viewModel.agreePrivacyCheck.observe(viewLifecycleOwner) {
            if(it) {
                binding.registerAgreePrivacyCheckAiv.setColorFilter(checkColor)
            } else {
                binding.registerAgreePrivacyCheckAiv.setColorFilter(notCheckColor)
            }

            checkAllAgreeChecking()
            checkAgreeButtonActive()
        }

        // 마케팅 동의 체크 시 뷰
        viewModel.agreeMarketingCheck.observe(viewLifecycleOwner) {
            if(it) {
                binding.registerAgreeMarketingCheckAiv.setColorFilter(checkColor)
            } else {
                binding.registerAgreeMarketingCheckAiv.setColorFilter(notCheckColor)
            }

            checkAllAgreeChecking()
            checkAgreeButtonActive()
        }

        binding.registerAgreeAcb.setOnClickListener {
            moveToRegisterProfile()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    /**
     *  동의했어요 버튼 활성화 여부 판단 (한 메소드에 한가지 역할!)
     */
    private fun isAgreeButtonActive() : Boolean {
        // 필수가 다 켜진 경우에만 버튼 활성화
        return viewModel.agreeUsingTermsCheck.value == true && viewModel.agreePrivacyCheck.value == true
    }

    /**
     *  동의했어요 버튼 활성화 여부에 따른 뷰 변경
     */
    private fun checkAgreeButtonActive() {
        if(isAgreeButtonActive()) {
            ableAgreeButton()
        } else {
            disableAgreeButton()
        }
    }

    /**
     *  동의 버튼 비활성화
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun disableAgreeButton() {
        binding.registerAgreeAcb.setBackgroundDrawable(resources.getDrawable(R.drawable.register_profile_name_check_disable_btn_background, null))
    }

    /**
     *  동의 버튼 활성화
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun ableAgreeButton() {
        binding.registerAgreeAcb.setBackgroundDrawable(resources.getDrawable(R.drawable.register_profile_name_check_available_btn_background, null))
    }

    /**
     *  부분 동의 확인하여 전체 동의 체크 여부
     */
    private fun isAllAgreeChecking() : Boolean {
        return viewModel.agreeUsingTermsCheck.value == true
            && viewModel.agreePrivacyCheck.value == true
                && viewModel.agreeMarketingCheck.value == true
    }

    /**
     *  부분 동의 확인 후 전체 동의 뷰 변경
     */
    private fun checkAllAgreeChecking() {
        viewModel._agreeAllCheck.value = isAllAgreeChecking()
    }

    /**
     *  profile register로 이동
     */
    private fun moveToRegisterProfile() {
        val registerTermsToRegisterProfileAction = RegisterTermsFragmentDirections.actionRegisterTermsFragmentToRegisterProfileFragment()
        findNavController().navigate(registerTermsToRegisterProfileAction)
    }
}