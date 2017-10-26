package gg;

public class ceshi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "发生范德萨富士达富士达从";
		
		System.out.println(substring(s1,2,5));
	}
	public static String substring(String s1,int beginIndex,int endIndex){
	
		String s2;
		char[] sss= s1.toCharArray();
		StringBuffer sd = new StringBuffer();
		for(int i=beginIndex;i<endIndex;i++){
			sd.append(sss[i]);
		}
		s2=sd.toString();
		return s2;
	}

}
