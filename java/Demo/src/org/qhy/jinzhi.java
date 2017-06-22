package demo;


public class jinzhi {
	private final static char[] digits_16 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	private final static int jinzhi_16 = 16;
	
	private final static char[] digits_64 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '/' };
	private final static int jinzhi_64 = 64;
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long val = 63;
		String result = digit16Convert(val);
		String result2 = bitDigit16Convert(val);
		System.out.println("转为为16进制，执行时间:"+(System.currentTimeMillis()- start)+"\tresult:"+result+"\t result2:"+result2);
		
		start = System.currentTimeMillis();
		result = digit64Convert(val);
		result2 = bitDigit64Convert(val);
		System.out.println("转为为64进制，执行时间:"+(System.currentTimeMillis()- start)+"\tresult:"+result+"\t result2:"+result2);
	}


	private static String digit16Convert(long val) {
		char[] arrayResult = new char[jinzhi_16];
		int count=0;
		for(count=0;count<jinzhi_16;count++){
			if(val == 0){break;}
			int yushu = (int) (val%jinzhi_16);//循环取余数往最后一位放
			arrayResult[jinzhi_16-count-1]=digits_16[yushu];
			val = val/jinzhi_16;
		}
		return new String(arrayResult,jinzhi_16-count,count);
	}
	
	private static String bitDigit16Convert(long val) {
		char[] arrayResult = new char[jinzhi_16];
		int count=0;
		for(count=0;count<jinzhi_16;count++){
			if(val == 0){break;}
			int yushu = (int) (val%jinzhi_16);//循环取余数往最后一位放
			arrayResult[jinzhi_16-count-1]=digits_16[yushu];
			val = val>>4;
		}
		return new String(arrayResult,jinzhi_16-count,count);
	}
	
	
	private static String digit64Convert(long val) {
		char[] arrayResult = new char[jinzhi_64];
		int count=0;
		for(count=0;count<jinzhi_64;count++){
			if(val == 0){break;}
			int yushu = (int) (val%jinzhi_64);//循环取余数往最后一位放
			arrayResult[jinzhi_64-count-1]=digits_64[yushu];
			val = val/jinzhi_64;
		}
		return new String(arrayResult,jinzhi_64-count,count);
	}
	
	private static String bitDigit64Convert(long val) {
		char[] arrayResult = new char[jinzhi_64];
		int count=0;
		int radix = jinzhi_64-1;
		for(count=0;count<jinzhi_64;count++){
			if(val == 0){break;}
//			int yushu = (int) (val%jinzhi_64);//循环取余数往最后一位放
			int yushu = (int) (val&radix);
			arrayResult[jinzhi_64-count-1]=digits_64[yushu];
			val = val>>6;
		}
		return new String(arrayResult,jinzhi_64-count,count);
	}
	
	
	
}

