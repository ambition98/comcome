package com.gr.comcome.mypage.model;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.account.model.HashVO;
import com.gr.comcome.common.HashingUtil;
import com.gr.comcome.login.model.LoginDAO;

@Service
public class MypageServiceImpl implements MypageService {
	private final MypageDAO mypageDao;
	private final LoginDAO loginDAO;
	private HashingUtil hashingUtil;



	public MypageServiceImpl(MypageDAO mypageDao, LoginDAO loginDAO, HashingUtil hashingUtil) {

		this.mypageDao = mypageDao;
		this.loginDAO = loginDAO;
		this.hashingUtil = hashingUtil;
	}

	@Override
	public List<MypageVO> selectList(String name) {
		return mypageDao.selectList(name);

	}

	@Override
	public MypageVO selectByNo(int mypageNo) {
		return mypageDao.selectByNo(mypageNo);

	}

	// 프로필 닉네임
	public MypageVO selectByName(String name) {
		return mypageDao.selectByName(name);

	}

	@Override
	public int updateProfile(MypageVO vo) {
		return mypageDao.updateProfile(vo);
	}

	@Override
	public List<MypageVO> selectMainNotice() {

		return mypageDao.selectMainNotice();
	}

	@Override //controller => 
	public int CheckPwd(int accountNo, String password) throws NoSuchAlgorithmException  {		
		int result = 0;

		//4.(카카오톡 말고) 비밀번호가 설정되어있다면
		Integer checkPwd = loginDAO.countPwd(accountNo);

		// 4. 비밀번호 있나 없나 확인 처리 (카카오톡은 비밀번호 설정이 안되어있음)
		if(checkPwd.equals(0)) { //비밀번호가 없어서  0 
			result=PWD_NONE;
		}else {
			HashVO hashVO = loginDAO.selectHash(accountNo);
			String digestnew = hashingUtil.hashing(password, hashVO.getSalt());

			if(digestnew.equals(hashVO.getDigest())){

				result=PWD_OK; //일치
			}else if(!digestnew.equals(hashVO.getDigest())){

				result =DISAGREE_PWD; //불일치
			}

		}

		///사용자가 회원 가입할 떄 입력했던 비밀번호 + accountNo를 통해서 가져온
		// HashVO 내에 있는 회원의 salt를 암호화 한 digest와 비교 

		return result;
	}

	@Override
	public int DeleteAccount(int accountNo) { //5. 비밀번호 먼저 삭제하고 회원 테이블 삭제 
		//5.1 해쉬테이블 먼저삭제 accountNo가 foreign key기 때문에
		int result = mypageDao.deleteHash(accountNo);
		int result2=0;
		int result3=0;
		
		if(result >0) {
			result2= mypageDao.deleteAccountbyNo(accountNo);	
			if(result2>0) {
				result3=1;
			}else if(result2<0){
				result3=0;
			}
		}else if(result<0) {
			result3=0;

		}
		return result3;
		
	}

	@Override //프로필 수정
	public int UpdateAccount(int accountNo) {
		//5.1 해쉬테이블 먼저수정 accountNo가 foreign key기 때문에
				int result = mypageDao.UpdateHash(accountNo);
				int result2=0;
				int result3=0;
				
				if(result >0) {
					result2= mypageDao.UpdateAccountbyNo(accountNo);	
					if(result2>0) {
						result3=1;
					}else if(result2<0){
						result3=0;
					}
				}else if(result<0) {
					result3=0;

				}
				return result3;	
	}

	@Override
	public AccountVO selectByaccountNo(int accountNo) {
		
		return mypageDao.selectByaccountNo(accountNo);
	}

	@Override
	public int UpdateAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public int UpdateAccount(AccountVO vo) {
//		
//		return mypageDao.UpdateAccountbyNo(PWD_OK);
//	}
//
//	@Override
//	public boolean CheckPwd(AccountVO vo) {
//	
//		return false;
//	}
	
	
}

// 1 비밀번호 있나 없나 확인 처리 (카카오톡은 비밀번호 설정
