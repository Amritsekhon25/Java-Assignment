package com.speakingclock.services;

import org.springframework.stereotype.Service; 

@Service
public class SpeakingClockServiceImpl implements SpeakingClockService {

	private static final String[] TENS = {
			"oh ", "", "twenty ", "thirty ", "forty ", "fifty "
	};

	private static final String[] ONES = {
			"twelve ", "one ", "two ", "three ", "four ", "five ",
			"six ", "seven ", "eight ", "nine ", "ten ", "eleven ",
			"twelve ", "thirteen ", "fourteen ", "fifteen ",
			"sixteen ", "seventeen ", "eighteen ", "nineteen "
	};

	@Override
	public String convertTime(String time) {
		boolean status=validateTime(time);
		if(!status)
			return "You need to pass a valid 24 hour format hour";

		String[] hours = time.trim().split(":");

		int hour = Integer.parseInt(hours[0]);
		int minutes = Integer.parseInt(hours[1]);


		
		String hourInWords = convertToWords(hour,minutes);
		if((hour==12||hour==0)&&minutes==0)
			return hourInWords;
		else
		return hour>12?hourInWords +"PM":hourInWords +"AM";	
	}
	private boolean validateTime(String time) {

		try {
			time.trim().split(":");
		} catch (NumberFormatException e) {
			return false;
		}

		String[] hours = time.trim().split(":");

		try {
			int hour=Integer.parseInt(hours[0]);
			int min=Integer.parseInt(hours[1]);
			if(hour<0||hour>23||min<0||min>59)
				return false;
		} catch (NumberFormatException e) {
			return false;

		}
		return true;

	}

	private String convertToWords(int hour, int minutes) {

		StringBuilder result = new StringBuilder();

		if (minutes == 0) {

			if (hour== 12) {
				return result.append("It's Midday").toString();
			}

			if (hour== 0) {
				return result.append("It's Midnight").toString();
			}

			result.append("It's ").append(ONES[hour % 12]);

		} else if (minutes % 10 == 0) {
			result.append("It's ").append(ONES[hour % 12]).append(TENS[minutes / 10]);
		} else if (minutes < 10 || minutes > 20) {
			result.append("It's ").append(ONES[hour % 12]).append(TENS[minutes / 10]).append(ONES[minutes % 10]);
		} else {
			// minutes > 10 && minutes < 20
			result.append("It's ").append(ONES[hour % 12]).append(ONES[minutes]);
		}

		return result.toString();
	}



}
