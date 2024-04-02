import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DanhSachSinhVien implements Serializable {
    private ArrayList<SinhVien> list;
    public DanhSachSinhVien() {
        list = new ArrayList<>();
    }

    public DanhSachSinhVien(ArrayList<SinhVien> list) {
        this.list = list;
    }

    public ArrayList<SinhVien> getList() {
        return list;
    }

    public void setList(ArrayList<SinhVien> list) {
        this.list = list;
    }
    //add a student to list
    public boolean add(SinhVien sv) {
        return list.add(sv);
    }
    //show the student list
    public void show() {
        System.out.printf("%-30s\n", "Danh sách sinh viên");
        list.forEach(System.out::println);
    }
    //remove a student with id
    public boolean remove(SinhVien sv) {
        return list.removeIf(sinhVien -> sv.getId().equals(sinhVien.getId()));
    }
    //sort student list by point
    public void sortPoint() {
        list.sort(new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien o1, SinhVien o2) {
                return Float.compare(o1.getPoint(), o2.getPoint());
            }
        });
    }
    //search info student in the list
    public void Search(String findID) {
        list.stream().filter(sv -> sv.getId().equals(findID)).map(SinhVien::toString).forEach(System.out::println);
    }
    //refactor information of a student with find ID
    public void refactor(String findID) {
        for (SinhVien sv: list) {
            if (sv.getId().equals(findID)) {
                System.out.println("Bắt đầu chỉnh sửa thông tin sinh viên");
                System.out.print("Mã sinh viên mới: "); sv.setId(new Scanner(System.in).next());
                System.out.print("Tên sinh viên mới: "); sv.setName(new Scanner(System.in).nextLine());
                System.out.print("Tuổi mới: "); sv.setAge(new Scanner(System.in).nextInt());
                System.out.print("Điểm mới: "); sv.setPoint(new Scanner(System.in).nextFloat());
                System.out.print("Email mới: "); sv.setEmail(new Scanner(System.in).next());
            }
        }
    }

    @Override
    public String toString() {
        return "DanhSachSinhVien{" +
                "list=" + list +
                '}';
    }
}
