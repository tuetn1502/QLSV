package mini_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataIO {
	static ArrayList<SinhVien> dsSV =new ArrayList<>();
	static ArrayList<MonHoc> dsMH =new ArrayList<>();
	static ArrayList<BangDiem> dsDiem =new ArrayList<>();
	static String root="C:\\QuanLySinhVien\\data";
	static File sinh_vien =new File(root+"\\sinhvien_en.txt");
	static File mon_hoc =new File(root+"\\monhoc_en.txt");
	static File bang_diem =new File(root+"\\diem.txt");
//	static File sinh_vien_1 =new File(root+"\\sinhvien1.txt");
//	static File mon_hoc_1 =new File(root+"\\monhoc1.txt");
//	static File bang_diem_1=new File(root+"\\diem1.txt");
	static FileReader fr = null;
	static BufferedReader br= null;
	static FileWriter fw = null;
	static BufferedWriter bw = null;
	static void readData() {
		dsSV = readFileSV();
		dsMH = readFileMH();
		dsDiem = readFileBD();
	}
	static void writeData() {
		dsSV = writeFileSV();
		dsMH = writeFileMH();
		dsDiem = WriteFileBD();
	}
	public static ArrayList<BangDiem> readFileBD() {	
		try {
			fr = new FileReader(bang_diem);
			br = new BufferedReader(fr);
			String line ;
			while ((line = br.readLine())!=null) {
				if (line.startsWith("#")) {
					continue;
				}
				String part[]=line.split(";");
				String maSV = part[0];
				String maMH = part[1];
				double diem = Double.parseDouble(part[2]);
				BangDiem mark = new BangDiem(maSV, maMH, diem);
				dsDiem.add(mark);				
			}
		} catch (Exception e) {		
		} finally {
			if (br!=null) {
				try {
					br.close();
				} catch (IOException e) {}		
			}
		}
		return dsDiem;
	}
	public static ArrayList<MonHoc> readFileMH() {
		try {
			fr = new FileReader(mon_hoc);
			br = new BufferedReader(fr);
			String line ;
			while ((line = br.readLine())!=null) {
				if (line.startsWith("#")) {
					continue;
				}
				String part[]=line.split(";");				
				String maMH = part[0];
				String tenMH = part[1];
				double heSo = Double.parseDouble(part[2]);
				MonHoc mH =new MonHoc(maMH, tenMH, heSo);
				dsMH.add(mH);
			}
		} catch (Exception e) {
		} finally {
			if (br!=null) {
				try {
					br.close();
				} catch (IOException e) {}		
			}
		}
		return dsMH;
	}
	public static ArrayList<SinhVien> readFileSV() {
		try {
			fr = new FileReader(sinh_vien);
			br = new BufferedReader(fr);
			String line ;
			while ((line = br.readLine())!=null) {
				if (line.startsWith("#")) {
					continue;
				}
				String part[]=line.split(";");				
				String maSV = part[0];
				String hoDem = part[1];
				String ten = part[2];
				String ngaySinh = part[3];
				String gioiTinh = part[4];
				SinhVien sv =new SinhVien(maSV, hoDem, ten, ngaySinh, gioiTinh);
				dsSV.add(sv);
			}
		} catch (Exception e) {
		} finally {
			if (br!=null) {
				try {
					br.close();
				} catch (IOException e) {}	
			}
		}
		return dsSV;
	}
	
	public static ArrayList<SinhVien> writeFileSV() {
		try {
			fw = new FileWriter(sinh_vien);
			bw = new BufferedWriter(fw);
			for (SinhVien sv : dsSV) {
				bw.write(sv.getLine());
				bw.newLine();
			}
		} catch (Exception e) {
			
		}finally {
			if (bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
		return dsSV;
	}
	public static ArrayList<MonHoc> writeFileMH() {
		try {
			fw = new FileWriter(mon_hoc);
			bw = new BufferedWriter(fw);
			for (MonHoc mH : dsMH) {
				bw.write(mH.getLine());
				bw.newLine();
			}
		} catch (Exception e) {
			
		}finally {
			if (bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
		return dsMH;	}
	public static ArrayList<BangDiem> WriteFileBD() {
		try {
			fw = new FileWriter(bang_diem);
			bw = new BufferedWriter(fw);
			for (BangDiem mark : dsDiem) {
				bw.write(mark.getLine());
				bw.newLine();
			}
		} catch (Exception e) {
			
		}finally {
			if (bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
		return dsDiem;	
	}
	
	
}
