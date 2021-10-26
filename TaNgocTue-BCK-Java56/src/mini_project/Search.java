package mini_project;

import java.util.ArrayList;
import java.util.Scanner;

public class Search {
	static Scanner sc =new Scanner(System.in);
	public static void searchByID() {
		boolean cont=true;
		System.out.println("Nhap ma sinh vien:");
		String input =sc.nextLine();		
		for (SinhVien sv : DataIO.dsSV) {
			if (input.equals(sv.getMaSinhVien())) {
				cont=true;
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
							else {
								continue;
							}
						}
					}else {
						continue;
					}
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
				break;
			}else {
				cont=false;
				continue;
			}
		}
		if (cont==false) {
			System.out.println("Khong tim thay sinh vien co ma la: ["+input+"]");
		}
	}

	public static void searchByName() {
		int choose=0;
		System.out.println("Nhap ten sinh vien:");
		String input =sc.nextLine();
		SinhVien sv =new SinhVien();
		ArrayList<SinhVien> student = new ArrayList<>();
		for (int i = 0; i < DataIO.dsSV.size(); i++) {
			sv =DataIO.dsSV.get(i);	
			String hodem =sv.getHoDem().toLowerCase();
			String ten = sv.getTen().toLowerCase();
			String hoTen = hodem+" "+ten;			
			if (hoTen.contains(input.toLowerCase())) {			
				student.add(sv);
			}
			else {
				continue;
			}
		}
		
		if (student.size()==0) {
			System.out.println("Khong tim thay sinh vien!");
		}
		else {
			int perPage = 20;
			int totalPages= student.size()/perPage;
			if (student.size()<perPage) {
				totalPages=1;
			} else {
				totalPages = totalPages + 1;
			}
			int  pageNumber = 1;		
			do {
				try {
					int start = (pageNumber-1)*perPage;
					int end = start + perPage;
					System.out.println("---------------------------KET QUA TIM KIEM-----------------------------");
					System.out.println("\t\t--------------Trang: "+pageNumber+"/"+totalPages+"-----------------");
					if (end>student.size()) {
						end = student.size();
					}
					System.out.println("┌─────────┬─────────────────────────┬──────────┬───────────────┬──────────┐");
					System.out.println("│   Ma    │       Ho ten dem        │   Ten    │   Ngay sinh   │Gioi tinh │");
					System.out.println("├─────────┼─────────────────────────┼──────────┼───────────────┼──────────┤");
					for (int i = start; i < end; i++) {					
						sv = student.get(i);
						sv.showTable();			
					}
					System.out.println("└─────────┴─────────────────────────┴──────────┴───────────────┴──────────┘");	
					System.out.println("\t\t--------------Trang: "+pageNumber+"/"+totalPages+"-----------------");
					System.out.println("1. Xem trang tiep theo        2. Tro lai trang truoc             3. Den trang cuoi\r\n"
							+ "4. Den trang dau tien        5. Xem trang cu the         6. Xem chi tiet bang diem\r\n"
							+ "0. Tro ve menu truoc");
					System.out.println("Nhap: ");
					input=sc.nextLine();
					choose=Integer.parseInt(input);
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
									continue;
								}else {						
									break;
								}
							} while (pageNumber>totalPages);
							break;
						}	
						case 6:
							while (!input.equals("exit")) {
									if (input.equals("exit")) {
										break;
									}else {
										searchByID();
										System.out.println("Nhap exit de quay lai!");	
										input =sc.nextLine();
									}							
								} 
							break;
						case 0:							
							System.out.println("[ Tro ve ]");
							break;
						default:{
							System.out.println("Chon sai!");
							break;
						}				
					}
					
				} catch (Exception e) {
					System.out.println("Nhap sai!");
				}
			} while (choose!=0);
		}
	}

}
