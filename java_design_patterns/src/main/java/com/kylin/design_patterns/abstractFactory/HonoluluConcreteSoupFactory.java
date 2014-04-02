package com.kylin.design_patterns.abstractFactory;

import com.kylin.design_patterns.ClamChowder;
import com.kylin.design_patterns.FishChowder;
import com.kylin.design_patterns.HonoluluClamChowder;
import com.kylin.design_patterns.HonoluluFishChowder;

public class HonoluluConcreteSoupFactory extends AbstractSoupFactory {

	public HonoluluConcreteSoupFactory() {
		factoryLocation = "Honolulu";
	}

	public ClamChowder makeClamChowder() {
		return new HonoluluClamChowder();
	}

	public FishChowder makeFishChowder() {
		return new HonoluluFishChowder();
	}
}
