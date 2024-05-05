package com.pack.democodes;

import java.util.*;

public class CountryComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return o1.getCountry().compareTo(o2.getCountry());
	}

}
