package com.kylin.design_patterns.builder;

import com.kylin.design_patterns.BostonClamChowder;
import com.kylin.design_patterns.BostonFishChowder;

public class BostonSoupBuffetBuilder extends SoupBuffetBuilder {

	public void setSoupBuffetName() {
		soupBuffet.soupBuffetName = "Boston Soup Buffet";
	}

	public void buildClamChowder() {
		soupBuffet.clamChowder = new BostonClamChowder();
	}

	public void buildFishChowder() {
		soupBuffet.fishChowder = new BostonFishChowder();
	}

	

}
