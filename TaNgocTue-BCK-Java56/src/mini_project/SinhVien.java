package mini_project;

public class SinhVien {
	private String maSinhVien,hoDem,ten,ngaySinh,gioiTinh;
	

	public SinhVien(String maSinhVien, String hoDem, String ten, String ngaySinh, String gioiTinh) {
		super();
		this.maSinhVien = maSinhVien;
		this.hoDem = hoDem;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		
	}	
	



	public SinhVien() {
		
	}

	public String getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public String getHoDem() {
		return hoDem;
	}

	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public void showInfo() {
		System.out.println("[ "+maSinhVien+"  "+hoDem+" "+ten+"  "+ngaySinh+" "+gioiTinh+" ]");
	}
	public void showTable() {
		System.out.format("│ %-8s│  %-23s│   %-7s│  %-13s│  %-8s│\n",maSinhVien,hoDem,ten,ngaySinh,gioiTinh);
	}

	public String getLine() {
		return String.format("%s;%s;%s;%s;%s",maSinhVien,hoDem,ten,ngaySinh,gioiTinh);
	}
	
	
}
