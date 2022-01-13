package com.gr.comcome.mypage.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
import com.gr.comcome.common.ConstUtil;
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
	@Autowired
	private MessageBoxService messageboxService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MypageService mypageService;
	
	@RequestMapping("/index")
	public String index (@ModelAttribute SearchVO searchVo,Model model) {
	
		logger.info("쪽지 목록 화면, searchVo={}", searchVo);
		
		//2
	      PaginationInfo pagingInfo = new PaginationInfo();
	      pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
	      pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
	      pagingInfo.setCurrentPage(searchVo.getCurrentPage());
	      
	      //3
	      searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
	      searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
	      logger.info("값 셋팅 후 searchVo={}", searchVo);
	      
	      
	      List<MessageBoxVO> list = messageboxService.selectAll(searchVo);
	      logger.info("전체조회 결과 list.size={}", list.size());
	      for(MessageBoxVO vo : list) {
	    	  logger.info(vo.toString());
	      }
	      //4
	      int totalRecord=messageboxService.selectTotalRecord(searchVo);
	      pagingInfo.setTotalRecord(totalRecord);
	      
	      model.addAttribute("list", list);
	      model.addAttribute("pagingInfo", pagingInfo);
	      
	  	return "mypageinc/mypageindex";
	     
		
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
	@PostMapping("/profile/prifileEdit")
	public String pfEdit(@RequestParam String email, @RequestParam(required = false) String password,
			@RequestParam(required = false) String password1, Model model) throws NoSuchAlgorithmException {
		String msg ="프로필 수정 실패", url="/mypage/profile/profileEdit";
		
		if (password == null || password.isEmpty() || password1 == null || password1.isEmpty()) {
			model.addAttribute("msg", "비밀번호/비밀번호 확인을 입력해주세요");
			model.addAttribute("url", "/profile/profileEdit");
			return "common/message";
		} else if (!password.equals(password1)) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다. 다시 입력하세요");
			model.addAttribute("url", "/profile/profileEdit");
			return "common/message";
		} else if (password.equals(password1)) {
			logger.info("프로필수정처리");
			
			//email로 account_no 가져온다
			AccountVO accountVO = loginService.selectByEmail(email);
			
			int CheckPwd = mypageService.CheckPwd( accountVO.getAccountNo(), password);
			
			
			if(CheckPwd ==mypageService.PWD_OK) { //비밀번호가 일치할시
				//session 제거	
				//6. 비밀번호 삭제에 성공했으면
			int result = mypageService.UpdateAccount( accountVO.getAccountNo());
                if(result > 0){
                    msg ="회원 수정에 성공했습니다 ";
                    url = "/mypageinc/mypageindex"; //마이페이지 인덱스로 돌아가기
                    
                //7. 비밀번호 삭제에 실패했으면                    
                }else if(result < 0){
                   msg ="회원 수정에 실패했습니다";
                   url = "/mypage/profile/profileEdit";
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
			@RequestParam(required = false) String password1, Model model) throws NoSuchAlgorithmException {
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
	
			
	
}
