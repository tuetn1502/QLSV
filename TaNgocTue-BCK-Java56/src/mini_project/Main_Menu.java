package mini_project;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
public class Main_Menu {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("\t\t╔════════════════════════════════╗");
		System.out.println("\t\t║                                ║");
		System.out.println("\t\t║       Quan Ly Sinh Vien        ║");
		System.out.println("\t\t║                                ║");
		System.out.println("\t\t╚════════════════════════════════╝");
		System.out.println("\n\n");
		DataIO.readData();
		menu();
	}
	public static void menu() {
		String input="";
		while (input!="0") {
			try {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           MENU     	   │");
				System.out.println("├──────────────────────────┤");
				System.out.println("│1. Cap Nhat Danh Sach	   │");
				System.out.println("│2. Hien Thi Bang Diem     │");
				System.out.println("│3. Tim Kiem               │");
				System.out.println("│0. Thoat                  │");
				System.out.println("└──────────────────────────┘");
				System.out.println("\nNhap lua chon: ");
				input=sc.nextLine();
				int choose = Integer.parseInt(input);
				
				switch (choose) {
					case 1:
						updateList();				
						break;
					case 2:
						showScore();
						break;
					case 3:
						search();
						break;
					case 0:
						input="0";
						DataIO.writeData();
						System.out.println("---Finished Program---");
						break;
					default:{
						System.out.println("Nhap sai! Moi chon lai!");
						break;
					}
				}
				
			} catch (Exception e) {		
				System.out.println("Nhap sai");
			}
		}
	}
	public static void search() {		
		String input="";
		while (input!="0") {
			try {
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│        Tim Kiem     	       │");
				System.out.println("├──────────────────────────────┤");
				System.out.println("│1. Tim kiem theo ma sinh vien │");
				System.out.println("│2. Tim kiem theo ten          │");
				System.out.println("│0. Tro ve menu truoc          │");
				System.out.println("└──────────────────────────────┘");
				System.out.println("\nNhap lua chon: ");
				input=sc.nextLine();
				int choose =Integer.parseInt(input);		
				switch (choose) {
					case 1:
						Search.searchByID();				
						break;
					case 2:
						Search.searchByName();
						break;
					case 0:
						input="0";
						System.out.println("[ Tro ve ]");
						break;
					default:{
						System.out.println("Nhap sai! Moi ban chon lai!");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Nhap sai");
			}
		}
		
	}
	public static void updateList() {
		String input="";
		while (input!="0") {
			try {
				System.out.println("┌──────────────────────────────────────┐");
				System.out.println("│        Cap nhat danh sach            │");
				System.out.println("├──────────────────────────────────────┤");
				System.out.println("│1. Cap nhat danh sach sinh vien       │");
				System.out.println("│2. Hien thi danh sach mon hoc         │");				
				System.out.println("│0. Tro ve menu truoc                  │");
				System.out.println("└──────────────────────────────────────┘");
				System.out.println("\nNhap lua chon: ");
				input=sc.nextLine();
				int choose =Integer.parseInt(input);		
				switch (choose) {
					case 1:
						StudentManager.updateStudentList();
						break;
					case 2:
						StudentManager.showSubjectList();
						break;
					case 0:
						input="0";
						System.out.println("[ Tro ve ]");
						break;
					default:{
						System.out.println("Nhap sai");
						System.out.println("Moi ban nhap lai");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Nhap sai");
			}
		}
	}
	public static void showScore() {
		String input="";
		Comparator<SinhVien> comparator =new Comparator<SinhVien>() {

			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				
				return o1.getMaSinhVien().compareTo(o2.getMaSinhVien());
			}
		};
		DataIO.dsSV.sort(comparator);
		int perPage = 10;
		int totalPages =0;;
		if (DataIO.dsSV.size()%perPage==0) {
			totalPages = DataIO.dsSV.size()/perPage;
		}else if (DataIO.dsSV.size()<perPage) {
			totalPages=1;
		}else {
			totalPages = DataIO.dsSV.size()/perPage+1;
		} 
		int  pageNumber = 1;	
		SinhVien sv =new SinhVien();
		
		while (input!="0") {
			int start = (pageNumber-1)*perPage;
			int end = start + perPage;
			System.out.println("=================DANH SACH SINH VIEN===============");
			System.out.println("\t------------Trang: "+pageNumber+"/"+totalPages+"-------------");
			if (end>DataIO.dsSV.size()) {  end = DataIO.dsSV.size();    }
			for (int i = start; i <end; i++) {
				sv = DataIO.dsSV.get(i);
				double heSo=0;
				double dTK=0;
				int check=0;
			    System.out.println("┌────────┬────────────────────────────────┬──────────┐");
				 System.out.format("│%-8s│%-32s│%-10s│\n", sv.getMaSinhVien(),sv.getHoDem()+" "+sv.getTen(),sv.getNgaySinh());
				System.out.println("├────────┴────────────────────────────────┴──────────┤");
				for (int j = 0; j < DataIO.dsDiem.size(); j++) {
					if (DataIO.dsDiem.get(j).getMaSinhVien().equals(sv.getMaSinhVien())) {
						check++;
						for (MonHoc mh: DataIO.dsMH) {
							if (mh.getMaMonHoc().equals(DataIO.dsDiem.get(j).getMaMonHoc())) {
								heSo = heSo+mh.getHeSoMon();
								dTK = dTK+ DataIO.dsDiem.get(j).getDiemSo()*mh.getHeSoMon();
							}
							else {     continue;}
						}
					}else {      continue;	}
				}
				if (check==0) {
					System.out.format("│DTK:%48s│\n",0.00);
					System.out.println("├────────────────────────────────────────────────────┤");
					System.out.format("│%-52s│\n","Sinh vien chua co diem mon nao");
				}else {
					double diemTK =dTK/heSo;		
					System.out.format("│DTK:%48.2f│\n",diemTK);
					System.out.println("├────────────────────────────────────────────────────┤");
					for (int j = 0; j < DataIO.dsDiem.size(); j++) {
						if (DataIO.dsDiem.get(j).getMaSinhVien().equals(sv.getMaSinhVien())) {
							for (MonHoc mh : DataIO.dsMH) {
								if (mh.getMaMonHoc().equals(DataIO.dsDiem.get(j).getMaMonHoc())) {
									System.out.format("│%-8s %-32s %10s│\n",mh.getMaMonHoc(),mh.getTenMonHoc(),DataIO.dsDiem.get(j).getDiemSo());									
								}
								else {
									continue;
								}
							}
						}else {
							continue;
						}											
					}
				}
				System.out.println("└────────────────────────────────────────────────────┘");
				System.out.println("\n");	
			}	
			System.out.println("\t---------Trang: "+pageNumber+"/"+totalPages+"----------");
			System.out.println("1. Xem trang tiep theo        2. Tro lai trang truoc             3. Den trang cuoi\r\n"
					+ "4. Den trang dau tien        5. Xem trang cu the         0. Tro ve menu truoc");
			System.out.println("Nhap: ");
			try {
				input=sc.nextLine();
				int choose =Integer.parseInt(input);	
				switch (choose) {
					case 1:{
						if (pageNumber<totalPages) {
							pageNumber+=1;
						}else {
							pageNumber=totalPages;
						}			
						break;
					}
					case 2:{
						if (pageNumber>1) {
							pageNumber-=1;
						}else {
							pageNumber=1;
						}
						break;
					}
					case 3:
						pageNumber=totalPages;
						break;
					case 4:
						pageNumber=1;
						break;
					case 5:{
						System.out.println("Nhap trang can xem: ");
						do {
							pageNumber=sc.nextInt();	
							if (pageNumber>totalPages) {
								System.out.println("so trang ko hop le");
								System.out.println("Nhap trang can xem: ");
							}else {						
								break;
							}
						} while (pageNumber>totalPages);
						break;
					}		
					case 0:	
						input="0";
						System.out.println("[ Tro ve ]");
						break;
					default:{
						System.out.println("Chon sai:");
						break;
					}				
				}
			} catch (Exception e) {
				System.out.println("Nhap sai!");
				input="0";
			}
		}
	}			
	
}
