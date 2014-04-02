package com.kylin.design_patterns.builder;

import com.kylin.design_patterns.HonoluluClamChowder;
import com.kylin.design_patterns.HonoluluFishChowder;

public class HonoluluSoupBuffetBuilder extends SoupBuffetBuilder {

	public void buildClamChowder() {
		soupBuffet.clamChowder = new HonoluluClamChowder();
	}

	public void buildFishChowder() {
		soupBuffet.fishChowder = new HonoluluFishChowder();
	}

	public void setSoupBuffetName() {
		soupBuffet.soupBuffetName = "Honolulu Soup Buffet";
	}

}
