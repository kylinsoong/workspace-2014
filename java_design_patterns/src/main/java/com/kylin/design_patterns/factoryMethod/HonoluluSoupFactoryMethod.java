package com.kylin.design_patterns.factoryMethod;

import com.kylin.design_patterns.ClamChowder;
import com.kylin.design_patterns.FishChowder;
import com.kylin.design_patterns.HonoluluClamChowder;
import com.kylin.design_patterns.HonoluluFishChowder;

public class HonoluluSoupFactoryMethod extends SoupFactoryMethod {

	public String makeBuffetName() {
		return "Honolulu Soup Buffet";
	}

	public ClamChowder makeClamChowder() {
		return new HonoluluClamChowder();
	}

	public FishChowder makeFishChowder() {
		return new HonoluluFishChowder();
	}

}
