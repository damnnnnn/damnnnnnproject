package com.chen.model;

public class Students
{
    private  int  sid;
    
    private String sname;
    
    private  String  spwd;
    
    private String ssex;
    
    private String  sbirthday;
    
    private double money; 
    
    private double  bonus;

	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
	}

	public String getSpwd()
	{
		return spwd;
	}

	public void setSpwd(String spwd)
	{
		this.spwd = spwd;
	}

	public String getSsex()
	{
		return ssex;
	}

	public void setSsex(String ssex)
	{
		this.ssex = ssex;
	}

	public String getSbirthday()
	{
		return sbirthday;
	}

	public void setSbirthday(String sbirthday)
	{
		this.sbirthday = sbirthday;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

	public double getBonus()
	{
		return bonus;
	}

	public void setBonus(double bonus)
	{
		this.bonus = bonus;
	}
}
