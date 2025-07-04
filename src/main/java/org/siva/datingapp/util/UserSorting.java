package org.siva.datingapp.util;


import java.util.Comparator;

import org.siva.datingapp.dto.MatchingUsers;

public class UserSorting implements Comparator<MatchingUsers>{

	@Override
	public int compare(MatchingUsers o1, MatchingUsers o2) {
		
		if(o1.getAgeDifference() < o2.getAgeDifference())
			return -1; // accending order 
		else if(o1.getAgeDifference() > o2.getAgeDifference())
			return 1;
		else {
			// if we have same age difference so we should check which user have most 
			// matching interests 
			// so we sort them descending order who have same difference
			if(o1.getMatchInterestCount() < o2.getMatchInterestCount())
				return 1; // descending order 
			
			else if(o1.getMatchInterestCount() < o2.getMatchInterestCount())
				return -1;
			else
				return 0;
			
		}
	}

}
