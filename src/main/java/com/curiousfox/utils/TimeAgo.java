package com.curiousfox.utils;

import java.time.Instant;

public class TimeAgo {
	
	private static int ONE_YEAR_IN_SECONDS = 31536000;
	private static int ONE_MONTH_IN_SECONDS = 2592000;
	private static int ONE_DAY_IN_SECONDS = 86400;
	private static int ONE_HOUR_IN_SECONDS = 3600;
	private static int ONE_MINUTE_IN_SECONDS = 60;
	
	
	public static String howLongAgo(Instant pastTime) {
		Long pastTimeInSeconds = pastTime.getEpochSecond();
		Long NowInSeconds = Instant.now().getEpochSecond();
		
		Long timeAgo = NowInSeconds - pastTimeInSeconds;
		
		if(timeAgo >= ONE_YEAR_IN_SECONDS) {
			return (timeAgo/ONE_YEAR_IN_SECONDS) + "y ago";
		}
		if(timeAgo >= ONE_MONTH_IN_SECONDS) {
			return (timeAgo/ONE_MONTH_IN_SECONDS) + "mo ago";
		}
		if(timeAgo >= ONE_DAY_IN_SECONDS) {
			return (timeAgo/ONE_DAY_IN_SECONDS) + "d ago";
		}
		if(timeAgo >= ONE_HOUR_IN_SECONDS) {
			return (timeAgo/ONE_HOUR_IN_SECONDS) + "h ago";
		}
		if(timeAgo >= ONE_MINUTE_IN_SECONDS) {
			return (timeAgo/ONE_MINUTE_IN_SECONDS) + "min ago";
		}
		
		return "less than 1min ago";
	}
}
