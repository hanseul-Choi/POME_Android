package com.teampome.pome.presentation.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRegisterBinding
import com.teampome.pome.util.CommonUtil
import com.teampome.pome.databinding.PomeRegisterBottomSheetDialogBinding
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val viewModel: RegisterViewModel by viewModels()

    private lateinit var pomeBottomSheetDialog: BottomSheetDialog
    private lateinit var pomeBottomSheetDialogBinding: PomeRegisterBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {
        // pomeBottomSheetDialog 뷰 인플레이션 과정
        pomeBottomSheetDialog = BottomSheetDialog(requireContext())
        pomeBottomSheetDialogBinding = PomeRegisterBottomSheetDialogBinding.inflate(layoutInflater, null, false)
        pomeBottomSheetDialog.setContentView(pomeBottomSheetDialogBinding.root)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        // 키보드 자연스럽게 처리
        binding.registerCl.setOnTouchListener { _, _ ->
            CommonUtil.hideKeyboard(requireActivity())
            false
        }

        // 번호 입력
        binding.registerPhoneAet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    viewModel.registerPhone.value = it.toString()
                }

                settingAgreeButton()
            }
        })

        // 인증 번호
        binding.registerCertNumAet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    viewModel.registerCertNum.value = it.toString()
                }

                settingAgreeButton()
            }
        })

        // 번호 인증 요청
        binding.registerCertPhoneAcb.setOnClickListener {
            Toast.makeText(requireContext(), "번호 인증 요청", Toast.LENGTH_SHORT).show()
            binding.registerCertPhoneAcb.text = "재요청"
        }

        // 동의하고 시작하기 버튼
        binding.registerAgreeAcb.setOnClickListener {
            Toast.makeText(requireContext(), "동의하고 시작하기", Toast.LENGTH_SHORT).show()
            moveToRegisterTerms()
        }

        // 뒤로가기 버튼
        binding.registerBackAiv.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    /**
     *  galleryLauncher를 이용하여 사용자 이미지를 가져오는 작업
     */
    private fun settingAgreeButton() {
        if(checkAgreeBtnEnable()) {
            enableAgreeBtn()
        } else {
            disableAgreeBtn()
        }
    }

    /**
     *  button 활성화 여부 체크
     */
    private fun checkAgreeBtnEnable() : Boolean {
        return (!viewModel.registerPhone.value.isNullOrEmpty()
                && !viewModel.registerCertNum.value.isNullOrEmpty())
    }

    /**
     *  button 활성화 작업
     */
    private fun enableAgreeBtn() {
        binding.registerAgreeAcb.setBackgroundResource(R.drawable.register_profile_name_check_available_btn_background)
        binding.registerAgreeAcb.isClickable = true
    }

    /**
     *  button 비활성화 작업
     */
    private fun disableAgreeBtn() {
        binding.registerAgreeAcb.setBackgroundResource(R.drawable.register_profile_name_check_disable_btn_background)
        binding.registerAgreeAcb.isClickable = false
    }

    /**
     *  register Terms로 이동
     */
    private fun moveToRegisterTerms() {
        val registerToRegisterTermsAction = RegisterFragmentDirections.actionRegisterFragmentToRegisterTermsFragment()
        findNavController().navigate(registerToRegisterTermsAction)
    }
}