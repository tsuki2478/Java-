package cn.hd.model;

import java.sql.Date;

/**
 * 
 * JavaBean 前台用户
 * @author Administrator
 *
 */
public class User {
	//编号
private int    userid;
//	账号
private String username;
//	密码
private String password;
//  姓名
private String truename;
//   性别
private String usersex;
//   生日
private Date   birthday;
//   邮箱
private String  email;
//   电话号码
private String  phoneno;
//   邮编
private String  postcade;
// 地址
private String  address;
//  注册时间
private Date   regdate;
//     锁定状态
private String  lockstate;
// 		最后锁定时间
private Date    lastaccess;
//     登录的次数
private int    login;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTruename() {
	return truename;
}
public void setTruename(String truename) {
	this.truename = truename;
}
public String getUsersex() {
	return usersex;
}
public void setUsersex(String usersex) {
	this.usersex = usersex;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneno() {
	return phoneno;
}
public void setPhoneno(String phoneno) {
	this.phoneno = phoneno;
}
public String getPostcade() {
	return postcade;
}
public void setPostcade(String postcade) {
	this.postcade = postcade;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}
public String getLockstate() {
	return lockstate;
}
public void setLockstate(String lockstate) {
	this.lockstate = lockstate;
}
public Date getLastaccess() {
	return lastaccess;
}
public void setLastaccess(Date lastaccess) {
	this.lastaccess = lastaccess;
}
public int getLogin() {
	return login;
}
public void setLogin(int login) {
	this.login = login;
}
public User(int userid, String username, String password, String truename, String usersex, Date birthday, String email,
		String phoneno, String postcade, String address, Date regdate, String lockstate, Date lastaccess, int login) {
	super();
	this.userid = userid;
	this.username = username;
	this.password = password;
	this.truename = truename;
	this.usersex = usersex;
	this.birthday = birthday;
	this.email = email;
	this.phoneno = phoneno;
	this.postcade = postcade;
	this.address = address;
	this.regdate = regdate;
	this.lockstate = lockstate;
	this.lastaccess = lastaccess;
	this.login = login;
}
public User() {
	super();
}
@Override
public String toString() {
	return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", truename=" + truename
			+ ", usersex=" + usersex + ", birthday=" + birthday + ", email=" + email + ", phoneno=" + phoneno
			+ ", postcade=" + postcade + ", address=" + address + ", regdate=" + regdate + ", lockstate=" + lockstate
			+ ", lastaccess=" + lastaccess + ", login=" + login + "]";
}
	

}
