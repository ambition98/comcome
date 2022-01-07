/*
 * package com.gr.comcome.mypage.controller;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * import com.gr.comcome.mypage.model.MypageService; import
 * com.gr.comcome.mypage.model.MypageVO;
 * 
 * 
 * @Controller
 * 
 * @RequestMapping("/mypage") public class MypageProfileController { private
 * static final Logger logger
 * =LoggerFactory.getLogger(MypageProfileController.class);
 * 
 * @Autowired private MypageService mypageService;
 * 
 * @RequestMapping(value="/mypageProFileEdit.do", method=RequestMethod.GET)
 * public String edit_get(@RequestParam(defaultValue = "0") int ACCOUNTNO, Model
 * model) { //1. 파라미터 읽어오기 logger.info("글 수정 화면, 파라미터 accountno={}", ACCOUNTNO);
 * if(ACCOUNTNO==0) { model.addAttribute("msg", "잘못된 url입니다.");
 * model.addAttribute("url", "/mypage/mypageList.do"); return "common/message";
 * }
 * 
 * //2.db작업 MypageVO vo=mypageService.selectByNo(ACCOUNTNO);
 * logger.info("글수정-상세조회 결과 vo={}", vo);
 * 
 * //3.모델에 결과 저장 model.addAttribute("vo", vo);
 * 
 * //4.뷰페이지 리턴 return "mypage/mypageProfileEdit"; }
 * 
 * 
 * @RequestMapping(value="/mypageProfileEdit.do", method = RequestMethod.POST)
 * public String edit_post(@ModelAttribute MypageVO vo, Model model) { //1
 * logger.info("글수정 처리, 파라미터 vo={}", vo);
 * 
 * //2 String msg="글수정 실패",
 * url="/mypage/mypageProfileEdit.do?accountno="+vo.getACCOUNTNO();
 * if(mypageService.checkPwd(vo)) { int cnt=mypageService.updateProfile(vo);
 * if(cnt>0) { msg="글수정되었습니다.";
 * url="/mypage/mypageProfileEdit.do?no="+vo.getACCOUNTNO(); } }else {
 * msg="비밀번호가 일치하지 않습니다."; }
 * 
 * //3 model.addAttribute("msg", msg); model.addAttribute("url", url);
 * 
 * //4 return "common/message"; }
 * 
 * 
 * }
 */