package com.kylin.design_patterns.factoryMethod;

import com.kylin.design_patterns.BostonClamChowder;
import com.kylin.design_patterns.BostonFishChowder;
import com.kylin.design_patterns.ClamChowder;
import com.kylin.design_patterns.FishChowder;

public class BostonSoupFactoryMethod extends SoupFactoryMethod {

	public String makeBuffetName() {
		return "Boston Soup Buffet";
	}

	public ClamChowder makeClamChowder() {
		return new BostonClamChowder();
	}

	public FishChowder makeFishChowder() {
		return new BostonFishChowder();
	}
}
