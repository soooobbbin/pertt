package kr.ott.vo;

import java.sql.Date;

public class OttReviewVO {

	private int ott_review_num;
	private String ott_review;
	private Date reg_date;
	private int ott_star_num;
	private int ott_num;
	private int member_num;
	
	
	public int getOtt_review_num() {
		return ott_review_num;
	}
	public void setOtt_review_num(int ott_review_num) {
		this.ott_review_num = ott_review_num;
	}
	public String getOtt_review() {
		return ott_review;
	}
	public void setOtt_review(String ott_review) {
		this.ott_review = ott_review;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getOtt_star_num() {
		return ott_star_num;
	}
	public void setOtt_star_num(int ott_star_num) {
		this.ott_star_num = ott_star_num;
	}
	public int getOtt_num() {
		return ott_num;
	}
	public void setOtt_num(int ott_num) {
		this.ott_num = ott_num;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
}
