package TDDE22.lab1;


public class Test {

	public static void main(String[] args) {
		
		SymbolTable st = new SymbolTable();
		
		if(st.hash("het") == 6) {
			System.out.println("Test 1 ok ☑️");
			System.out.println();
		}
		
		
		st.put("het", 'c');
		st.put("the", 'd');

	
		if(st.get("the").equals('d')) {
			System.out.println("Test 2 ok ☑️");
			System.out.println();
		}
		
		st.put("the", 'i');

		
		if(st.hash("info") == 1) {
			System.out.println("Test 3 ok ☑️");
			System.out.println();
		}
		st.put("info", 'd');

		
		if(st.hash("fusk") == 0) {
			System.out.println("Test 4 ok ☑️");
			System.out.println();
		}
		
		st.put("fusk", 'c');
		

		
		st.delete("het");
		
		System.out.println();

		
		if(st.get("het") == null) {
			System.out.println("Test 5 ok ☑️");
			System.out.println();
		}
		
		st = new SymbolTable();
		st.put("hej", 'a');
		st.put("hej", null);
		if(st.get("hej") == null) {
			System.out.println("Test 6 ok ☑️");
			System.out.println();
		}
	}

}
