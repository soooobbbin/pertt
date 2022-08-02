package kr.review.vo;
//
public class CommentVO {
	private int com_num;
	private String com_content;
	private String com_reg_date;
	private int c_review_num;
	private int member_num;
	private int c_num;
	private String id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}
	public String getCom_reg_date() {
		return com_reg_date;
	}
	public void setCom_reg_date(String com_reg_date) {
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


