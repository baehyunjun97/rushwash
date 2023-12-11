package com.rushwash.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.rushwash.admin.app.db.util.JDBCTemplate;
import com.rushwash.app.member.vo.MemberVo;

public class MemberDao {
	
	//회원가입
	public int join(Connection conn, MemberVo vo) throws Exception {

		String sql = "INSERT INTO MEMBER(NO, ID, PWD, EMAIL, ADDRESS, NAME, TEL) VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberId());
		pstmt.setString(2, vo.getMemberPwd());
		String email = vo.getMemberEmail();
		if (email == null) {
		    email = "default@email.com";
		}
		pstmt.setString(3, email);
		pstmt.setString(4, vo.getMemberAddress());
		pstmt.setString(5, vo.getMemberName());		
		pstmt.setString(6, vo.getMemberTel());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	
	}

	//로그인
	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		//sql
		String sql = "SELECT M.NO ,M.ID ,M.PWD ,M.EMAIL ,M.ADDRESS ,M.NAME ,M.TEL ,M.ENROLL_DATE ,M.MODIFY_DATE, M.POINT, M.DEL_YN ,C.CARD_COMPANY ,C.CARD_NO ,G. SUB_GRADE FROM MEMBER M LEFT JOIN PLAN_INFO P ON M.NO = P.MEMBER_NO LEFT JOIN PLAN_GRADE G ON G.NO = P.GRADE_NO LEFT JOIN CARD_INFO C ON M.NO = C.MEMBER_NO WHERE M.ID = ? AND M.PWD = ? AND M.DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberId());
		pstmt.setString(2, vo.getMemberPwd());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		MemberVo loginMember = null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String email = rs.getString("EMAIL");
			String address = rs.getString("ADDRESS");
			String name = rs.getString("NAME");
			String tel = rs.getString("TEL");
			String enrollDate = rs.getString("ENROLL_DATE");
			String delYn = rs.getString("DEL_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String point = rs.getString("POINT");
			String planName = rs.getString("SUB_GRADE");
			String cardNo = rs.getString("CARD_NO");
			String cardCompany = rs.getString("CARD_COMPANY");

			loginMember = new MemberVo();
			loginMember.setNo(no);
			loginMember.setMemberId(id);
			loginMember.setMemberPwd(pwd);
			loginMember.setMemberEmail(email);
			loginMember.setMemberAddress(address);
			loginMember.setMemberName(name);
			loginMember.setMemberTel(tel);
			loginMember.setEnrollDate(enrollDate);
			loginMember.setDelYn(delYn);
			loginMember.setModifyDate(modifyDate);
			loginMember.setPoint(point);
			loginMember.setSubGrade(planName);
			loginMember.setCardNo(cardNo);
			loginMember.setCardCompany(cardCompany);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return loginMember;
	}

	//중복체크
	public boolean checkId(Connection conn, String memberId) throws Exception {
	
		//sql
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		boolean result = true;
		if(rs.next()) {
			result = false;
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//아이디변경
	public int idChange(Connection conn, String id, String newId) throws Exception {
	    //sql
	    String sql = "UPDATE MEMBER SET ID = ?, MODIFY_DATE = SYSDATE WHERE ID = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, newId); 
	    pstmt.setString(2, id); 
	    int result = pstmt.executeUpdate();

	    //close
	    JDBCTemplate.close(pstmt);

	    return result;
	}
	
	//비번변경
	public int pwdChange(Connection conn, String id, String pwd, String newPwd) throws Exception {
		//sql
		String sql = "UPDATE MEMBER SET PWD = ?, MODIFY_DATE = SYSDATE WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPwd); 
		pstmt.setString(2, id); 
		pstmt.setString(3, pwd); 
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//주소 변경
	public int addrChange(Connection conn, String id, String addr, String newAddr) throws Exception {
	    //sql
	    String sql = "UPDATE MEMBER SET ADDRESS = ?, MODIFY_DATE = SYSDATE WHERE ID = ? AND (ADDRESS = ? OR ADDRESS IS NULL)";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, newAddr); 
	    pstmt.setString(2, id); 
	    pstmt.setString(3, addr); 
	    int result = pstmt.executeUpdate();

	    //close
	    JDBCTemplate.close(pstmt);

	    return result;
	}

	//회원 탈퇴
	public int delete(Connection conn, String id, String pwd) throws Exception {

		//SQL
		String sql = "UPDATE MEMBER SET DEL_YN = 'Y' WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		int result = pstmt.executeUpdate();
      
		//close
		JDBCTemplate.close(pstmt);
      
		return result;
	}

	//이메일 중복체크
	public boolean checkEmail(Connection conn, String memberEmail) throws Exception {
		//sql
		String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberEmail);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		boolean result = true;
		if(rs.next()) {
			result = false;
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//아이디 찾기
	public String idFind(Connection conn, MemberVo vo) throws Exception {
		
		//sql
		String sql = "SELECT ID FROM MEMBER WHERE NAME = ? AND PWD = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberName());
		pstmt.setString(2, vo.getMemberPwd());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		String memberId = null;
		if(rs.next()) {
			memberId = rs.getString("ID");
		}
		
		//close
		JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);
	    
	    return memberId;
	}

	//비밀번호 찾기
	public String pwdFind(Connection conn, MemberVo vo) throws Exception {
		//sql
		String sql = "SELECT PWD FROM MEMBER WHERE NAME = ? AND ID = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberName());
		pstmt.setString(2, vo.getMemberId());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		String memberPwd = null;
		if(rs.next()) {
			memberPwd = rs.getString("PWD");
		}
		
		//close
		JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);
	    
	    return memberPwd;
	}
}
