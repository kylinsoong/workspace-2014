package com.kylin.design_patterns;

import java.util.ArrayList;
import java.util.ListIterator;

public abstract class Soup {

	protected ArrayList soupIngredients = new ArrayList();
	
	protected String soupName;

	public String getSoupName() {
		return soupName;
	}

	public String toString() {
		StringBuffer stringOfIngredients = new StringBuffer(soupName);
		stringOfIngredients.append(" Ingredients: ");
		ListIterator soupIterator = soupIngredients.listIterator();
		while (soupIterator.hasNext()) {
			stringOfIngredients.append((String) soupIterator.next());
		}
		return stringOfIngredients.toString();
	}
}
