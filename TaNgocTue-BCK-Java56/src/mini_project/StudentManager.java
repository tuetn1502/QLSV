package mini_project;



import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class StudentManager {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void updateStudentList() {
		String choose="";
		try {
			do {
				System.out.println("┌──────────────────────────────────────┐");
				System.out.println("│       DANH SACH SINH VIEN            │");
				System.out.println("├──────────────────────────────────────┤");
				System.out.println("│1. Them sinh vien                     │");
				System.out.println("│2. Sua thong tin sinh vien            │");				
				System.out.println("│3. Xoa sinh vien                      │");				
				System.out.println("│4. Hien thi danh sach sinh vien       │");				
				System.out.println("│0. Tro ve menu truoc                  │");
				System.out.println("└──────────────────────────────────────┘");
				System.out.println("\nNhap lua chon: ");					
				choose=sc.nextLine();
				switch (choose) {
					case "1":{
						addInfo();				
						break;
					}										
					case "2":
						editInfo();
						break;
					case "3":
						delete();
						break;
					case "4":
						showStudentList();
						break;
					case "0":
						System.out.println("[ Tro ve ]");
						choose="0";
						break;
					default:{
						System.out.println("Nhap sai!");
						System.out.println("Moi ban nhap lai!!");
						break;
					}
				}
			} while (choose!="0");
		} catch (Exception e) {
		}		
	}
	public static void addInfo() {
		String input="";
		String temp="";
		System.out.println("Nhap thong tin sinh vien theo dang: ");
		System.out.println("[ho va ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]");
		System.out.println("De quay lai thi nhap: exit ");
		System.out.println("Nhap:");		
		try {
			input = sc.nextLine();
			do {
				if(input.equals("exit")) {
					return;
				}else{				
					
						int maSV =DataIO.dsSV.size()+1;
						String id ="SV"+String.format("%05d", maSV);			
						String part[] =input.split(";");
						String fullName = part[0];
						String birthday = part[1];
						String gender = part[2];
						fullName=fullName.trim();
						int index=fullName.lastIndexOf(" ");
						String firstName=fullName.substring(index+1);
						String surname=fullName.substring(0, index);
						SinhVien sv =new SinhVien(id,surname,firstName,birthday,gender);
						DataIO.dsSV.add(sv);
						DataIO.writeFileSV();
						System.out.println("Them thanh cong!!!");
						sv.showInfo();
						System.out.println("Co muon nhap nua khong?(yes/no)");	
						do {
							temp =sc.nextLine();
							if (temp.toLowerCase().equals("no")) {
								input="exit";
								System.out.println("[Tro ve]");
								break;
							}else if (temp.toLowerCase().equals("yes")) {
								System.out.println("Nhap thong tin sinh vien theo dang: ");
								System.out.println("[ho va ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]");
								System.out.println("De quay lai thi nhap: exit ");
								System.out.println("Nhap:");
								input = sc.nextLine();
								break;
							}else {
								System.out.println("Nhap 'yes' hoac 'no'): ");
								System.out.println("Nhap:");						
							}
						} while (!temp.toLowerCase().equals("no"));
				}
			} while (!input.equals("exit"));		
		} catch (Exception e) {
			System.out.println("Nhap sai!");
			System.out.println("Them sinh vien that bai!");
		}
		
		
	}
	public static void editInfo() {
		System.out.println("Nhap ma sinh vien can sua: ");
		String id=sc.nextLine();		
		String surname ;
		String firstName;
		int check=0;	
		SinhVien sv= new SinhVien();	
		for (int i = 0; i < DataIO.dsSV.size(); i++) {	
			sv = DataIO.dsSV.get(i);
			if (!id.equals(sv.getMaSinhVien())){				
				check=0;
			}else {
				System.out.println("Tim thay thong tin sinh vien: ");	
				sv.showInfo();
				System.out.println("\n");
				System.out.println("Sua thong tin sinh vien: ");	
				System.out.println("Nhap thong tin sinh vien theo dang: ");
				System.out.println("[ho va ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]");
				try {
					check++;
					String line=sc.nextLine();
					line=line+" ";
					String[] part=line.split(";",3) ;
					String fullName =part[0];
					String birthday=part[1];
					String gender=part[2];
					if (fullName.equals("")) {
						firstName=sv.getTen();
						surname=sv.getHoDem();
						sv.setTen(firstName);
						sv.setHoDem(surname);				
					} else {
						fullName=fullName.trim();
						int index=fullName.lastIndexOf(" ");
						firstName=fullName.substring(index+1);
						surname=fullName.substring(0, index);
						sv.setTen(firstName);
						sv.setHoDem(surname);
					}
					if (!birthday.equals("")) {
						sv.setNgaySinh(birthday);
					}else {
						birthday=sv.getNgaySinh();
						sv.setNgaySinh(birthday);
					}
					if (gender.equals(" ")) {
						gender=sv.getGioiTinh();
						sv.setGioiTinh(gender);
					}else if (gender.contains(";")) {
						throw new Exception();
					} else {
						sv.setGioiTinh(gender);
					}	
				} catch (Exception e) {
					System.out.println("Nhap sai!");
					check=-1;
				}
				break;					
			}
		}	
		if (check==0) {
			System.out.println("Khong tim thay sinh vien!");
		}else if (check==-1) {
			System.out.println("Sua khong thanh cong!");
		}else {
			System.out.println("Sua thanh cong!");
			sv.showInfo();
			DataIO.writeFileSV();
		}
		
	}
	public static void delete() {
		System.out.println("NHap ma sinh vien muon xoa: ");
		System.out.println("Nhap: ");
		try {
			String id = sc.nextLine();
			SinhVien sv = null;
			boolean existed = false;
			for (int i = 0; i <DataIO.dsSV.size(); i++) {		
				if (id.equals(DataIO.dsSV.get(i).getMaSinhVien())) {
					System.out.println("Tim thay:");
					DataIO.dsSV.get(i).showInfo();
					sv=DataIO.dsSV.get(i);
					break;
				}
			}	
			for (int i = 0; i < DataIO.dsDiem.size(); i++) {
				if (id.equals(DataIO.dsDiem.get(i).getMaSinhVien())) {
					existed =true;
					break;
				}
			}
			System.out.println("\n");
			if (sv!=null&&existed==false) {
				DataIO.dsSV.remove(sv);
				System.out.println("Da xoa sinh vien!!!");
				DataIO.writeFileSV();
			} else if (sv!=null&&existed==true) {
				System.out.println("Sinh vien nay da co diem, khong the xoa!");
			}else {
				System.out.println("Khong tim thay sinh vien!");
			}
		} catch (Exception e) {			
		}
	}
	public static void showStudentList() {
		Locale vn =new Locale("vi");
		Collator vnCollator = Collator.getInstance(vn);
		vnCollator.compare("abc","ABC");
		Comparator<SinhVien> comparator =new Comparator<SinhVien>() {

			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				return vnCollator.compare(o1.getTen(),o2.getTen());
			}
		};
		DataIO.dsSV.sort(comparator);
		String input="";
		int check=0;
		int perPage = 30;
		int totalPages= DataIO.dsSV.size()/perPage;
		if (DataIO.dsSV.size()<perPage) {
			totalPages=1;
		} else {
			totalPages = totalPages + 1;
		}
		int  pageNumber = 1;	
		SinhVien sv =new SinhVien();	
		while (input!="0") {
			int start = (pageNumber-1)*perPage;
			int end = start + perPage;
			System.out.println("===========================DANH SACH SINH VIEN============================");
			System.out.println("\t\t--------------Trang: "+pageNumber+"/"+totalPages+"-----------------");
			if (end>DataIO.dsSV.size()) {
				end = DataIO.dsSV.size();
			}
			System.out.println("┌─────────┬─────────────────────────┬──────────┬───────────────┬──────────┐");
			System.out.println("│   Ma    │       Ho ten dem        │   Ten    │   Ngay sinh   │Gioi tinh │");
			System.out.println("├─────────┼─────────────────────────┼──────────┼───────────────┼──────────┤");
			for (int i = start; i < end; i++) {					
				sv = DataIO.dsSV.get(i);
				sv.showTable();			
			}
			System.out.println("└─────────┴─────────────────────────┴──────────┴───────────────┴──────────┘");	
			System.out.println("\t\t--------------Trang: "+pageNumber+"/"+totalPages+"-----------------");
			System.out.println("1. Xem trang tiep theo        2. Tro lai trang truoc             3. Den trang cuoi\r\n"
					+ "4. Den trang dau tien        5. Xem trang cu the         0. Tro ve menu truoc");
			System.out.println("Nhap: ");		
			try {
				input = sc.nextLine();
				int choose=Integer.parseInt(input);
				switch (choose) {
					case 1:
						if (pageNumber<totalPages) {
							pageNumber+=1;
						}else {
							pageNumber=totalPages;
						}			
						break;
					case 2:
						if (pageNumber>1) {
							pageNumber-=1;
						}else {
							pageNumber=1;
						}
						break;
					case 3:
						pageNumber=totalPages;
						break;
					case 4:
						pageNumber=1;
						break;
					case 5:
						System.out.println("Nhap trang can xem: ");
						do {
							pageNumber=Integer.parseInt(sc.nextLine());	
							if (pageNumber>totalPages) {
								System.out.println("so trang ko hop le");
								System.out.println("Nhap trang can xem: ");
								continue;
							}else {						
								break;
							}
						} while (pageNumber>totalPages);
						break;		
					case 0:
						System.out.println("[ Tro ve ]");
						input="0";
						break;
					default:{
						System.out.println("chon sai");
						break;
					}
				}
			} catch (Exception e) {
				check=1;
			}
			if (check!=0) {
				System.out.println("Nhap sai!");
				break;
			}
		 }
	}
	
	public static void  showSubjectList(){	
		String input="";
		int check=0;
		Locale vn =new Locale("vi");
		Collator vnCollator = Collator.getInstance(vn);
		vnCollator.compare("abc","ABC");
		Comparator<MonHoc> comparator = new Comparator<MonHoc>() {

			@Override
			public int compare(MonHoc o1, MonHoc o2) {
				
				return vnCollator.compare(o1.getTenMonHoc(),o2.getTenMonHoc());
			}
		};
		DataIO.dsMH.sort(comparator);		
		int perPage = 30;
		int totalPages =0;;
		if (DataIO.dsMH.size()%perPage==0) {
			totalPages = DataIO.dsMH.size()/perPage;
		}else if (DataIO.dsMH.size()<perPage) {
			totalPages=1;
		}else {
			totalPages = DataIO.dsMH.size()/perPage+1;
		} 
		int  pageNumber = 1;	
		MonHoc mh =new MonHoc();
		while (input!="0") {
			try {
				int start = (pageNumber-1)*perPage;
				int end = start + perPage;
				if (end>DataIO.dsMH.size()) {
					end = DataIO.dsMH.size();
				}
				System.out.println("============DANH SACH MON HOC=============");
				System.out.println("\t-------Trang: "+pageNumber+"/"+totalPages+"----------");
				System.out.println("┌─────┬─────────────────────────┬────────┐");
				System.out.println("│ Ma  │       Ten mon hoc       │ He so  │");
				System.out.println("├─────┼─────────────────────────┼────────┤");
				for (int i = start; i < end; i++) {					
					mh = DataIO.dsMH.get(i);
					mh.showTable();			
				}
				System.out.println("└─────┴─────────────────────────┴────────┘");
				System.out.println("\t-------Trang: "+pageNumber+"/"+totalPages+"----------");
				System.out.println("1. Xem trang tiep theo        2. Tro lai trang truoc             3. Den trang cuoi\r\n"
						+ "4. Den trang dau tien        5. Xem trang cu the         0. Tro ve menu truoc");
				System.out.println("Nhap: ");
				input=sc.nextLine();
				int choose=Integer.parseInt(input);
				switch (choose) {
					case 1:
						if (pageNumber<totalPages) {
							pageNumber+=1;
						}else {
							pageNumber=totalPages;
						}			
						break;
					case 2:
						if (pageNumber>1) {
							pageNumber-=1;
						}else {
							pageNumber=1;
						}
						break;
					case 3:
						pageNumber=totalPages;
						break;
					case 4:
						pageNumber=1;
						break;
					case 5:
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
					case 0:		
						input="0";
						System.out.println("[ Tro ve ]");
						break;
					default:
						System.out.println("chon sai");
						break;
				}
			} catch (Exception e) {
				check++;
			}
			if (check!=0) {
				System.out.println("Nhap sai!");
				break;
			}
		}
	}
	
}
