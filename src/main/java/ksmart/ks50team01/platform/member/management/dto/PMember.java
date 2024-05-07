package ksmart.ks50team01.platform.member.management.dto;

public class PMember {

	private String memberId;
	private String memberGrdNum;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private String memberEmail;
	private String memberTelNum;
	private String memberRegDate;
	private String memberFormDate;
	private String memberLevelName;

	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberGrdNum=" + memberGrdNum + ", memberPw=" + memberPw
				+ ", memberName=" + memberName + ", memberNickname=" + memberNickname + ", memberEmail=" + memberEmail
				+ ", memberTelNum=" + memberTelNum + ", memberRegDate=" + memberRegDate + ", memberFormDate="
				+ memberFormDate + ", memberLevelName=" + memberLevelName + "]";
	}
	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberGrdNum() {
		return memberGrdNum;
	}
	public void setMemberGrdNum(String memberGrdNum) {
		this.memberGrdNum = memberGrdNum;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberTelNum() {
		return memberTelNum;
	}
	public void setMemberTelNum(String memberTelNum) {
		this.memberTelNum = memberTelNum;
	}
	public String getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	public String getMemberFormDate() {
		return memberFormDate;
	}
	public void setMemberFormDate(String memberFormDate) {
		this.memberFormDate = memberFormDate;
	}

	
	
}
