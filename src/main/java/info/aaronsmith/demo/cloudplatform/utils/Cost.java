package info.aaronsmith.demo.cloudplatform.utils;

public class Cost {

	private Integer pounds = 0;
	private Integer pence = 0;
	
	public Cost(int pence) {
		this.addPence(pence);
	}
	
	public Cost(int pounds, int pence) {
		this.addPounds(pounds);
		this.addPence(pence);
	}
	
	public int getPence() {
		return (this.pounds * 100) + this.pence;
	}
	
	private void addPounds(int pounds) {
		this.pounds += pounds;
	}
	
	private void addPence(int pence) {
		int newPounds = (int) pence / 100;
		int newPence = pence % 100;
		
		this.pounds += newPounds;
		this.pence += newPence;
	}
	
	@Override
	public String toString() {
		String penceString = pence.toString();
		if (this.pence < 10) {
			penceString = "0" + this.pence.toString();
		}
		return "£" + this.pounds + "." + penceString;
	}
	
	
}
