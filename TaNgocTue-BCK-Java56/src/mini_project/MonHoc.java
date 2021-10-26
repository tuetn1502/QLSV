package mini_project;

public class MonHoc {
	private String maMonHoc,tenMonHoc;
	private double heSoMon;
	public MonHoc(String maMonHoc, String tenMonHoc, Double heSoMon) {
		super();
		this.maMonHoc = maMonHoc;
		this.tenMonHoc = tenMonHoc;
		this.heSoMon = heSoMon;
	}
	

	public MonHoc() {
	}


	public String getMaMonHoc() {
		return maMonHoc;
	}

	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}

	public String getTenMonHoc() {
		return tenMonHoc;
	}

	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}

	public double getHeSoMon() {
		return heSoMon;
	}

	public void setHeSoMon(Double heSoMon) {
		this.heSoMon = heSoMon;
	}

	public String getLine() {	
		return String.format("%s;%s;%s",maMonHoc,tenMonHoc,heSoMon);
	}

	public void showInfo() {
		System.out.println(maMonHoc+" "+tenMonHoc+" "+heSoMon);
		
	}
	public void showTable() {
		System.out.format("│%-5s│%-25s│%-8s│\n",maMonHoc,tenMonHoc,heSoMon);
	}

	
}
