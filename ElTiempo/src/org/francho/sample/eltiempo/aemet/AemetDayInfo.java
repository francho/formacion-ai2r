package org.francho.sample.eltiempo.aemet;

import java.util.Date;

public class AemetDayInfo {
	private Date date;
	private int maxTemp;
	private int minTemp;
	public Date getDate() {
		return date;
	}
	public void setDate(Date theDate) {
		this.date = theDate;
	}
	public int getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}
	public int getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}
	@Override
	public String toString() {
		return "AemetDayInfo [date=" + date + ", maxTemp=" + maxTemp
				+ ", minTemp=" + minTemp + "]";
	}
	
	
}
