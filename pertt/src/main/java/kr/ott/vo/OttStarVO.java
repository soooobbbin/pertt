package kr.ott.vo;

public class OttStarVO {
	private int ott_star_num; //별점 id
	private int price; //가성비-별점
	private int usability; //사용성-별점
	private int quality; //콘텐츠-별점
	private int ott_num; //ott id
	private int member_num; //회원번호
	
	
	public int getOtt_star_num() {
		return ott_star_num;
	}
	public void setOtt_star_num(int ott_star_num) {
		this.ott_star_num = ott_star_num;
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
