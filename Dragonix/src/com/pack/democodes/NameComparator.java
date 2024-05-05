package com.pack.democodes;

import java.util.*;

public class NameComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
