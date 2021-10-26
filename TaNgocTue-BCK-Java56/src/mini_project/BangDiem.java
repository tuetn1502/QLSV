package mini_project;

public class BangDiem {
	private String maSinhVien,maMonHoc;
	private double diemSo;
	public BangDiem(String maSinhVien, String maMonHoc, Double diemSo) {
		super();
		this.maSinhVien = maSinhVien;
		this.maMonHoc = maMonHoc;
		this.diemSo = diemSo;
	}

	public BangDiem() {		
	}

	public String getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public String getMaMonHoc() {
		return maMonHoc;
	}

	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}

	public double getDiemSo() {
		return diemSo;
	}

	public void setDiemSo(Double diemSo) {
		this.diemSo = diemSo;
	}

	public void showInfo() {
		System.out.println(maSinhVien+";"+maMonHoc+";"+diemSo);
		
	}
//	public void showTable() {
//		System.out.format("│%-5s│%-25s│%-8s│\n",maSinhVien,,diemSo);
//	}
	public String getLine() {
		return String.format("%s;%s;%f",maSinhVien,maMonHoc,diemSo);
	}
}
