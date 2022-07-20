package kr.member.vo;

import java.sql.Date;

public class MemberVO {
   private int member_num;
   private String member_id;
   private int auth;
   private String name;
   private String passwd;
   private String phone;
   private String email;
   private String birth;
   private Date reg_date;
   private Date mod_date;
   
   //비밀번호 일치 여부 체크
   public boolean isCheckedPassword(String memberPasswd) {
      //회원등급(auth) : 0탈퇴,1정지,2일반,3관리자
      if(auth > 1 && passwd.equals(memberPasswd)) {
         return true;
      }
      return false;
   }
   
   
   public int getMember_num() {
      return member_num;
   }
   public void setMember_num(int member_num) {
      this.member_num = member_num;
   }
   public int getAuth() {
      return auth;
   }
   public void setAuth(int auth) {
      this.auth = auth;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getPasswd() {
      return passwd;
   }
   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getBirth() {
      return birth;
   }

   public void setBirth(String birth) {
      this.birth = birth;
   }
   public Date getReg_date() {
      return reg_date;
   }
   public void setReg_date(Date reg_date) {
      this.reg_date = reg_date;
   }
   public Date getMod_date() {
      return mod_date;
   }
   public void setMod_date(Date mod_date) {
      this.mod_date = mod_date;
   }

   public String getMember_id() {
      return member_id;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }



}