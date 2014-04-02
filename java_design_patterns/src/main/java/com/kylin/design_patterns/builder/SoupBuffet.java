package com.kylin.design_patterns.builder;

import com.kylin.design_patterns.ChickenSoup;
import com.kylin.design_patterns.ClamChowder;
import com.kylin.design_patterns.FishChowder;
import com.kylin.design_patterns.Minnestrone;
import com.kylin.design_patterns.PastaFazul;
import com.kylin.design_patterns.TofuSoup;
import com.kylin.design_patterns.VegetableSoup;


public class SoupBuffet {

	protected String soupBuffetName;

	protected ChickenSoup chickenSoup;
	protected ClamChowder clamChowder;
	protected FishChowder fishChowder;
	protected Minnestrone minnestrone;
	protected PastaFazul pastaFazul;
	protected TofuSoup tofuSoup;
	protected VegetableSoup vegetableSoup;

	public String getSoupBuffetName() {
		return soupBuffetName;
	}

	public void setSoupBuffetName(String soupBuffetNameIn) {
		soupBuffetName = soupBuffetNameIn;
	}

	public void setChickenSoup(ChickenSoup chickenSoupIn) {
		chickenSoup = chickenSoupIn;
	}

	public void setClamChowder(ClamChowder clamChowderIn) {
		clamChowder = clamChowderIn;
	}

	public void setFishChowder(FishChowder fishChowderIn) {
		fishChowder = fishChowderIn;
	}

	public void setMinnestrone(Minnestrone minnestroneIn) {
		minnestrone = minnestroneIn;
	}

	public void setPastaFazul(PastaFazul pastaFazulIn) {
		pastaFazul = pastaFazulIn;
	}

	public void setTofuSoup(TofuSoup tofuSoupIn) {
		tofuSoup = tofuSoupIn;
	}

	public void setVegetableSoup(VegetableSoup vegetableSoupIn) {
		vegetableSoup = vegetableSoupIn;
	}

	public String getTodaysSoups() {
		StringBuffer stringOfSoups = new StringBuffer();
		stringOfSoups.append(" Today's Soups!  ");
		stringOfSoups.append(" Chicken Soup: ");
		stringOfSoups.append(this.chickenSoup.getSoupName());
		stringOfSoups.append(" Clam Chowder: ");
		stringOfSoups.append(this.clamChowder.getSoupName());
		stringOfSoups.append(" Fish Chowder: ");
		stringOfSoups.append(this.fishChowder.getSoupName());
		stringOfSoups.append(" Minnestrone: ");
		stringOfSoups.append(this.minnestrone.getSoupName());
		stringOfSoups.append(" Pasta Fazul: ");
		stringOfSoups.append(this.pastaFazul.getSoupName());
		stringOfSoups.append(" Tofu Soup: ");
		stringOfSoups.append(this.tofuSoup.getSoupName());
		stringOfSoups.append(" Vegetable Soup: ");
		stringOfSoups.append(this.vegetableSoup.getSoupName());
		return stringOfSoups.toString();
	}

}
