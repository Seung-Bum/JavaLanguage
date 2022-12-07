package ocp.study;
// https://www.youtube.com/watch?v=gMZNFF0NvV4

public class OcpStudy {

	public static void main(String[] args) {
		OcpStudy ocpStudy = new OcpStudy();
		ocpStudy.autoLoad("Testing1");
		ocpStudy.autoLoad("Testing2");
	}
	
	public Object autoLoad(String className){
		try {
			
			try {
				
				// System.out.println(OcpStudy.class.getPackage().getName() + "." + className);
				// OcpStudy.class.getPackage().getName() -> ocp.study
				// .newInstance()로 클래스 객체 호출
				
				// Testing1 testing1 = (Testing1) Class.forName(OcpStudy.class.getPackage().getName() + "." + className).newInstance()
				// 이런식으로 담아서 사용할 수도 있다.
				return Class.forName(OcpStudy.class.getPackage().getName() + "." + className).newInstance();
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
