package com.curiousfox.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DiceBearUrlGenerator {
	private static final List<String> eyes = Arrays.asList("closed", "closed2", "crying","cute","glasses","love","pissed","plain","sad","shades","sleepClose","stars","tearDrop","wink","wink2");
	private static final List<String> mouths = Arrays.asList("cute","drip","faceMask","kissHeart","lilSmile","pissed","plain","sad","shout","shy","sick","smileLol","smileTeeth","tongueOut","wideSmile");
	private static final List<String> colors = Arrays.asList("b6e3f4","c0aede","d1d4f9","ffd5dc","ffdfbf","fcd34d","f87171","f97316","a3e635","86efac", "a7f3d0","c026d3","f472b6","94a3b8");
	
	public static String getUrl() {
		String eye = getRandomElement(eyes);
		String mouth = getRandomElement(mouths);
		String color = getRandomElement(colors);
		
		return"https://api.dicebear.com/5.x/fun-emoji/svg?mouth="+mouth+"&eyes="+eye+"&backgroundColor="+color;
	}
	
	private static String getRandomElement(List<String> list){
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
}
