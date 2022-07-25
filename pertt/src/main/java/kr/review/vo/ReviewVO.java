package kr.review.vo;

import java.sql.Date;

public class ReviewVO {
	private int c_review_num;
	private Date c_review_reg_date;
	private Date c_review_mod_date;
	private String c_review_content;
	private int member_num;
	private int c_num;
	
	//c_star 테이블(별점)
	private int c_star_num;
	private int star;
	
	//join해서 사용
	private String id;
	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getC_review_num() {
		return c_review_num;
	}
	public void setC_review_num(int c_review_num) {
		this.c_review_num = c_review_num;
	}
	public Date getC_review_reg_date() {
		return c_review_reg_date;
	}
	public void setC_review_reg_date(Date c_review_reg_date) {
		this.c_review_reg_date = c_review_reg_date;
	}
	public Date getC_review_mod_date() {
		return c_review_mod_date;
	}
	public void setC_review_mod_date(Date c_review_mod_date) {
		this.c_review_mod_date = c_review_mod_date;
	}
	public String getC_review_content() {
		return c_review_content;
	}
	public void setC_review_content(String c_review_content) {
		this.c_review_content = c_review_content;
	}
	public int getC_star_num() {
		return c_star_num;
	}
	public void setC_star_num(int c_star_num) {
		this.c_star_num = c_star_num;
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


