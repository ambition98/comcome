package com.gr.comcome.mypage.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;


import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.account.model.HashVO;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.HashingUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.login.model.LoginService;
import com.gr.comcome.messagebox.controller.MessageBoxController;
import com.gr.comcome.messagebox.model.MessageBoxService;
import com.gr.comcome.messagebox.model.MessageBoxVO;
import com.gr.comcome.mypage.model.MypageService;
import com.gr.comcome.mypage.model.MypageVO;


@Controller
@RequestMapping("/mypage")
public class MypageController {
	private static final
	Logger logger =LoggerFactory.getLogger(MessageBoxController.class);
	//http//localhost:9091/comcome/mypage/index

	@GetMapping("/index")
	public String index () {
		return "mypageinc/mypageindex";
	}

	@Autowired
	private MessageBoxService messageboxService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private HashingUtil hashingUtil;


	@GetMapping("/mypageMain")
	public String mypagemain () {
		return "mypageinc/mypageMain";
	}

	@Autowired
	private MypageService mypageService;

	@GetMapping("/mypagemain")
	public String messagebox() {

		return "mypageinc/mypageMain";
	}
	
	//localhost:9091/comcome/mypage/index2
	@GetMapping("/index2")
	public String index2 () {
		return "/index2";
	}
	
	
	@GetMapping("/mypageEdit")
	public String profileEdit(HttpSession session, Model model) {
		String name=(String) session.getAttribute("name");
		logger.info("프로필 수정 화면, 파라미터 name={}", name);
		
		MypageVO vo=mypageService.selectByName(name);
		logger.info("프로필 수정 - 조회 결과 vo ={}",vo);
		
		model.addAttribute("vo",vo);
    
		return "mypage/mypageEdit";
	}
	
	@PostMapping("/mypageEdit")
	public String edit_post(@ModelAttribute MypageVO vo, 
			@RequestParam int Account_no , HttpSession session,
			Model model) {
		String name=(String) session.getAttribute("name");
		vo.setNAME(name);
		logger.info("프로필 수정처리 , 파라미터 vo={}",vo);
		
		if(vo.getNAME()==null || vo.getNAME().isEmpty()) {
			vo.setNAME("");
		}
		      return "mypageinc/mypageMain";
		     

		}
	//메인 프로필 view
	@GetMapping("/profile/profileDetail")
	public String profile () {
		return "mypage/profile/profileDetail";
	}


	/*
	 * //기본정보수정 프로필 view
	 * 
	 * @RequestMapping("/profile/profileMain") public String profileMain() { return
	 * "mypage/profile/profileMain";
	 * 
	 * }
	 */

	//프로필 기본정보수정
	@GetMapping("/profile/profileEdit")
	public String profileEdit () {		
		return "mypage/profile/profileEdit";

	}
	//프로필 기본정보 수정 처리
	@PostMapping("/profile/profileEdit") //@ModelAttribute 를 사용 하면 한테이블에(dto or vo) 있는 모든 정보를 불러올수 있다.
	public String pfEdit(@RequestParam String name,@RequestParam String email, Model model) throws NoSuchAlgorithmException {
		String msg ="프로필 수정 실패", url="/mypage/profile/profileEdit";


		logger.info("프로필 수정처리, email={},name={}",email,name);


		AccountVO  accountVO = new AccountVO(); //가방이라 생각하면 됨 
		accountVO.setEmail(email);
		accountVO.setName(name);		

		int result = mypageService.UpdateAccount(accountVO);   //메서드 이름을 친다 빨간줄 누르고 create 메서드 누르고 자동으로 서비스로넘어감
		//25줄
		logger.info("프로필 결과처리,result={}",result);
		
			if(result > 0){
				
			msg ="회원 수정에 성공했습니다 ";
			url = "/mypage/index"; //마이페이지 인덱스로 돌아가기

			//7. 비밀번호 삭제에 실패했으면                    
		}else if(result < 0){
			msg ="회원 수정에 실패했습니다";
			url = "/mypage/profile/profileEdit";
		} 



		model.addAttribute("msg",msg);
		model.addAttribute("url",url);

		return "common/message";
	} 

	//	//글수정 화면
	//	@RequestMapping("/profile/prifileEdit")
	//	public String pfEdit (@RequestParam(defaultValue = "0")int accountNo,Model model) {
	//		//1파라미터 읽어오기
	//		logger.info("프로필 수정 화면, 파라미터 accountNo={}", accountNo);
	//		if(accountNo==0) {
	//			model.addAttribute("msg", "잘못된 url입니다.");
	//			model.addAttribute("url", "/profile/profileDetail");
	//			return "common/message";
	//		}
	//		
	//		//2 db작업
	//		AccountVO vo =mypageService.selectByaccountNo(accountNo);
	//		logger.info("프로필수정-상세조회 결과 vo={}", vo);
	//		
	//		//3.모델에 결과 저장
	//		model.addAttribute("vo", vo);
	//		
	//		//4.뷰페이지 리턴
	//		return "mypage/profile/prifileEdit";
	//		
	//	}
	//	//프로필 수정처리
	//	@RequestMapping("profile/prifileEdit")
	//	public String Edit(@ModelAttribute AccountVO vo, Model model) {
	//		logger.info("프로필수정 처리, 파라미터 vo={}", vo);
	//		
	//		String msg="프로필수정 실패", url="/mypage/profile/profileDetail?no="+vo.getAccountNo();
	//		if(mypageService.CheckPwd(vo)) {
	//			int cnt=mypageService.UpdateAccount(vo);
	//			if(cnt>0) {
	//				msg="글수정되었습니다.";
	//				url="/mypage/profile/profileDetail?no="+vo.getAccountNo();
	//			}
	//		}else {
	//			msg="비밀번호가 일치하지 않습니다.";
	//		}
	//		
	//		//3
	//		model.addAttribute("msg", msg);
	//		model.addAttribute("url", url);
	//		
	//		//4
	//		return "common/message";
	//	}
	//		




	//회원탈퇴
	@GetMapping("/member/memberDelete")
	public String memberDelete () {
		return "mypage/member/memberDelete";

	}

	//회원탈퇴 처리 
	@PostMapping("/member/memberDelete")
	public String Delete (@RequestParam String email, @RequestParam(required = false) String password,
			@RequestParam(required = false) String password1, Model model, HttpServletRequest request) throws NoSuchAlgorithmException {
		String msg ="회원탈퇴 실패", url="/mypage/member/memberDelete";

		if (password == null || password.isEmpty() || password1 == null || password1.isEmpty()) {
			model.addAttribute("msg", "비밀번호/비밀번호 확인을 입력해주세요");
			model.addAttribute("url", "/member/memberDelete");
			return "common/message";
		} else if (!password.equals(password1)) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다. 다시 입력하세요");
			model.addAttribute("url", "/member/memberDelete");
			return "common/message";
		} else if (password.equals(password1)) {
			logger.info("회원탈퇴처리");

			//email로 account_no 가져온다
			AccountVO accountVO = loginService.selectByEmail(email);

			int CheckPwd = mypageService.CheckPwd( accountVO.getAccountNo(), password);


			if(CheckPwd ==mypageService.PWD_OK) { //비밀번호가 일치할시
				//session 제거	
				//6. 비밀번호 삭제에 성공했으면
				int result = mypageService.DeleteAccount( accountVO.getAccountNo());
				if(result > 0){
					msg ="회원 탈퇴에 성공했습니다 ";
					url = "/"; //인덱스로 돌아가기
					request.getSession().invalidate();
					//7. 비밀번호 삭제에 실패했으면                    
				}else if(result < 0){
					msg ="회원 탈퇴에 실패했습니다";
					url = "/mypage/member/memberDelete";
				} 


			}else if(CheckPwd==mypageService.DISAGREE_PWD){
				msg ="비밀번호 불일치";
			}else if(CheckPwd==mypageService.PWD_NONE) {
				msg="비밀번호 불일치";

			}          


		}	
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);

		return "common/message";
	}

	//비밀번호 재설정 view
	@GetMapping("/pwd/pwd")
	public String pwd () {
		return "mypage/pwd/pwd";

	}
	@PostMapping("/pwd/pwd")
	public String updatePwd(@RequestParam String email, @RequestParam(required = false) String password,
			@RequestParam(required = false) String passwordCk, Model model) throws NoSuchAlgorithmException {
		if (password == null || password.isEmpty() || passwordCk == null || passwordCk.isEmpty()) {
			model.addAttribute("msg", "비밀번호/비밀번호 확인을 입력해주세요");
			model.addAttribute("url", "/mypage/pwd/pwd");
			return "common/message";
		} else if (!password.equals(passwordCk)) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다. 다시 입력하세요");
			model.addAttribute("url", "/mypage/pwd/pwd");
			return "common/message";
		} else if (password.equals(passwordCk)) {
			logger.info("비밀번호 재설정 처리");
			// 비밀번호 재설정 !
			// 이메일을 통해서 account_no를 가져온다!
			AccountVO accountVO = loginService.selectByEmail(email);

			logger.info("비밀번호 재설정 처리, accountVO={}",accountVO.toString());
			// 비밀번호 재설정 !
			// salt 만들기 ..salt
			String salt = hashingUtil.makeNewSalt();
			// 암호화 된 digest .. update hash set salt = salt , digest = saltPwd where
			// account_no =?
			String digest = hashingUtil.hashing(password, salt);

			HashVO hashvo = new HashVO();
			hashvo.setAccountNo(accountVO.getAccountNo());
			hashvo.setDigest(digest);
			hashvo.setSalt(salt);

			logger.info("비밀번호 재설정 처리, hashvo={}",hashvo.toString());
			String msg = "비밀번호 재설정이 실패하였습니다", url = "/mypage/pwd/pwd";

			int result = loginService.updatePassword(hashvo);
			logger.info("비밀번호 재설정 처리, result={}",result);
			if (result > 0) {
				msg = "비밀번호가 성공적으로 변경되었습니다";
				url = "/mypage/index";
			}
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			return "common/message";

		}

		return "common/message";
	}


}
