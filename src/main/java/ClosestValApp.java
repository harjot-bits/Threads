public class ClosestValApp implements Runnable {
	private String[] searchStrings;
	public ClosestValApp(String[] searches) {
		searchStrings = searches;
	}
	private static ClosestValService service = new ClosestValService();
	public void run() {
		for(String word : searchStrings) {
			System.out.println(service.getClosestVal(word));
		}
	}

}
