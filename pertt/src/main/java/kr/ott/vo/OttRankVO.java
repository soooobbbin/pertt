package kr.ott.vo;

import java.sql.Date;

public class OttRankVO {
	private int ott_rank_num;
	private int price;
	private int usability;
	private int quality;
	private String ott_review;
	private int ott_num;
	private int member_num;
	private Date reg_date;
	
	
	public int getOtt_rank_num() {
		return ott_rank_num;
	}
	public void setOtt_rank_num(int ott_rank_num) {
		this.ott_rank_num = ott_rank_num;
	}
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
	public String getOtt_review() {
		return ott_review;
	}
	public void setOtt_review(String ott_review) {
		this.ott_review = ott_review;
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
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
