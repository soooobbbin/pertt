package kr.review.vo;

import java.sql.Date;

public class CommentVO {
	private int com_num;
	private String com_contents;
	private Date com_reg_date;
	private int c_review_num;
	private int member_num;
	private int c_num;
	
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
	public String getCom_contents() {
		return com_contents;
	}
	public void setCom_contents(String com_contents) {
		this.com_contents = com_contents;
	}
	public Date getCom_reg_date() {
		return com_reg_date;
	}
	public void setCom_reg_date(Date com_reg_date) {
		this.com_reg_date = com_reg_date;
	}
	public int getC_review_num() {
		return c_review_num;
	}
	public void setC_review_num(int c_review_num) {
		this.c_review_num = c_review_num;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
}


