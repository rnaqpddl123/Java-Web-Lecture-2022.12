package ch07;

import java.time.LocalDate;

public class Player {
	private int backnum;
	private String name;
	private String position;
	private LocalDate birthday;
	private int height;
	private int isDeleted;
	
	Player(){}
	Player(int backnum, String name, String position, String birthday, int height) {
		this.backnum = backnum;
		this.name = name;
		this.position = position;
		this.birthday = LocalDate.parse(birthday);
		this.height = height;
	}
	Player(int backnum, String name, String position, String birthday, int height, int isDeleted) {
		super();
		this.backnum = backnum;
		this.name = name;
		this.position = position;
		this.birthday = LocalDate.parse(birthday);
		this.height = height;
		this.isDeleted = isDeleted;
	}
	public int getBacknum() {
		return backnum;
	}
	public void setBacknum(int backnum) {
		this.backnum = backnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public String toString() {
		return "Player [backnum=" + backnum + ", name=" + name + ", position=" + position + ", birthday=" + birthday
				+ ", height=" + height + ", isDeleted=" + isDeleted + "]";
	}
	
	
	

}
