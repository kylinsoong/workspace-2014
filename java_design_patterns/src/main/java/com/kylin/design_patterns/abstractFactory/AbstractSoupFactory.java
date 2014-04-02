package com.kylin.design_patterns.abstractFactory;

import com.kylin.design_patterns.ChickenSoup;
import com.kylin.design_patterns.ClamChowder;
import com.kylin.design_patterns.FishChowder;
import com.kylin.design_patterns.Minnestrone;
import com.kylin.design_patterns.PastaFazul;
import com.kylin.design_patterns.TofuSoup;
import com.kylin.design_patterns.VegetableSoup;

public abstract class AbstractSoupFactory {

	protected String factoryLocation;

	public String getFactoryLocation() {
		return factoryLocation;
	}

	public ChickenSoup makeChickenSoup() {
		return new ChickenSoup();
	}

	public ClamChowder makeClamChowder() {
		return new ClamChowder();
	}

	public FishChowder makeFishChowder() {
		return new FishChowder();
	}

	public Minnestrone makeMinnestrone() {
		return new Minnestrone();
	}

	public PastaFazul makePastaFazul() {
		return new PastaFazul();
	}

	public TofuSoup makeTofuSoup() {
		return new TofuSoup();
	}

	public VegetableSoup makeVegetableSoup() {
		return new VegetableSoup();
	}

}
