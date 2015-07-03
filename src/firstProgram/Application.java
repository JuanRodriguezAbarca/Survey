package firstProgram;

public class Application {

	private Survey sv = new Survey();
	public static void main(String[] args) {
		try {
			new Application().init();
		} catch(Exception e) {
			
		}
	}
	
	public void init() throws Exception {

		sv.questionExecuter();

		
	}
}
