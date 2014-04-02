package com.kylin.design_patterns.abstractFactory;

import com.kylin.design_patterns.BostonClamChowder;
import com.kylin.design_patterns.BostonFishChowder;
import com.kylin.design_patterns.ClamChowder;
import com.kylin.design_patterns.FishChowder;

public class BostonConcreteSoupFactory extends AbstractSoupFactory {

	public BostonConcreteSoupFactory() {
		factoryLocation = "Boston";
	}

	public ClamChowder makeClamChowder() {
		return new BostonClamChowder();
	}

	public FishChowder makeFishChowder() {
		return new BostonFishChowder();
	}
}
