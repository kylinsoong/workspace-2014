package com.kylin.design_patterns.builder;

import com.kylin.design_patterns.ChickenSoup;
import com.kylin.design_patterns.ClamChowder;
import com.kylin.design_patterns.FishChowder;
import com.kylin.design_patterns.Minnestrone;
import com.kylin.design_patterns.PastaFazul;
import com.kylin.design_patterns.TofuSoup;
import com.kylin.design_patterns.VegetableSoup;

public abstract class SoupBuffetBuilder {

	SoupBuffet soupBuffet;

	public SoupBuffet getSoupBuffet() {
		return soupBuffet;
	}

	public void buildSoupBuffet() {
		soupBuffet = new SoupBuffet();
	}

	public abstract void setSoupBuffetName();

	public void buildChickenSoup() {
		soupBuffet.chickenSoup = new ChickenSoup();
	}

	public void buildClamChowder() {
		soupBuffet.clamChowder = new ClamChowder();
	}

	public void buildFishChowder() {
		soupBuffet.fishChowder = new FishChowder();
	}

	public void buildMinnestrone() {
		soupBuffet.minnestrone = new Minnestrone();
	}

	public void buildPastaFazul() {
		soupBuffet.pastaFazul = new PastaFazul();
	}

	public void buildTofuSoup() {
		soupBuffet.tofuSoup = new TofuSoup();
	}

	public void buildVegetableSoup() {
		soupBuffet.vegetableSoup = new VegetableSoup();
	}

}
