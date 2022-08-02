package kr.ott.vo;

import java.sql.Date;

public class OttReviewVO {

	private int ott_review_num;
	private String ott_re_content;
	private Date ott_re_reg_date;
	private int ott_num;
	private int member_num;
	private String id;
	
	//ott_star 테이블
	private int ott_star_num; //별점 id
	private int price; //가성비-별점
	private int usability; //사용성-별점
	private int quality; //콘텐츠-별점
	private int star_avg;
	
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getUsability() {
		return usability;
	}
	public void setUsability(int usability) {
		this.usability = usability;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOtt_review_num() {
		return ott_review_num;
	}
	public void setOtt_review_num(int ott_review_num) {
		this.ott_review_num = ott_review_num;
	}
	
	public String getOtt_re_content() {
		return ott_re_content;
	}
	public void setOtt_re_content(String ott_re_content) {
		this.ott_re_content = ott_re_content;
	}
	
	public Date getOtt_re_reg_date() {
		return ott_re_reg_date;
	}
	public void setOtt_re_reg_date(Date ott_re_reg_date) {
		this.ott_re_reg_date = ott_re_reg_date;
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
	public int getStar_avg() {
		return star_avg;
	}
	public void setStar_avg(int star_avg) {
		this.star_avg = star_avg;
	}
}
