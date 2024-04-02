import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class management {
    //Ném ra ngoại lệ nếu mã id không hợp lệ
    public static boolean checkID(String id) {
        try {
          if (id.isEmpty())
              throw new InputMismatchException("Lỗi nhập trống mã ID");
          String trueID = "SV\\d+";
          Pattern p = Pattern.compile(trueID);
          Matcher m = p.matcher(id);
          if (!m.find())
              throw new InputMismatchException("Lỗi nhập sai định dạng ID");
          return true;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //Bắt lỗi nếu họ và tên để trống
    public static boolean checkName(String name) {
        try {
            if (name.isEmpty())
                throw new InputMismatchException("Lỗi nhập trống tên");
            return true;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    //Bắt lỗi nếu tuổi <= 0
    public static boolean checkAge(int age) {
        try {
            if (age <= 0)
                throw new ArithmeticException("Lỗi nhập sai tuổi ( Tuổi > 0 )");
            return true;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    //Bắt lỗi nếu điểm số < 0
    public static boolean checkPoint(float point) {
        try {
            if (point < 0)
                throw new ArithmeticException("Lỗi điểm số ( điểm >= 0 )");
            return true;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    //Bắt lỗi nếu định dạng sai email
    public static boolean checkEmail(String email) {
        try {
            String check = "\\w+@gmail\\.com\\.*\\w*";
            Pattern p = Pattern.compile(check);
            Matcher m = p.matcher(email);
            if (!m.find())
                throw new InputMismatchException("Lỗi định dạng email");
            return true;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static void main(String[] args) throws IOException {
        DanhSachSinhVien list = new DanhSachSinhVien();
        //menu
        int answer = 1;
        int choice;
        do {
            System.out.println("1. Hiển thị danh sách sinh viên \n" +
                                "2. Thêm sinh viên vào danh sách \n" +
                                "3. Xóa sinh viên khỏi danh sách bằng mã ID \n" +
                                "4. Sắp xếp sinh viên theo thang điểm giảm dần \n" +
                                "5. Tìm kiếm thông tin sinh viên \n" +
                                "6. Chỉnh sửa thông tin sinh viên \n" +
                                "7. Điển danh sách sinh viên vào file txt \n" +
                                "8. Đọc file txt \n" +
                                "9. Xóa file txt \n" +
                                "0. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1: {
                    list.show();
                    break;
                }
                case 2: {
                    System.out.print("Nhập số sinh viên muốn thêm: ");
                    int n = new Scanner(System.in).nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Mã sinh viên: "); String id = new Scanner(System.in).nextLine();
                        if (!checkID(id))
                            break;
                        System.out.print("Họ và tên:  "); String name = new Scanner(System.in).nextLine();
                        if (!checkName(name))
                            break;
                        System.out.print("Tuổi: "); int age = new Scanner(System.in).nextInt();
                        if (!checkAge(age))
                            break;
                        System.out.print("Điểm: "); float point = new Scanner(System.in).nextFloat();
                        if (!checkPoint(point))
                            break;
                        System.out.print("Email: "); String email = new Scanner(System.in).nextLine();
                        if (!checkEmail(email))
                            break;
                        SinhVien sv = new SinhVien(id, name, age, point, email);
                        if (list.add(sv))
                            System.out.println("Add successful...");
                        else System.out.print("Add failed");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Nhập mã sinh viên cần xóa: ");
                    String findID = new Scanner(System.in).next();
                    SinhVien sv = new SinhVien(findID, "", 0, 0, "");
                    if (list.remove(sv))
                        System.out.println("Remove successful...");
                    else System.out.println("Remove failed");
                    break;
                }
                case 4: {
                    list.sortPoint();
                    break;
                }
                case 5: {
                    System.out.print("Nhập mã sinh viên cần tìm: ");
                    String findId = new Scanner(System.in).next();
                    list.Search(findId);
                    break;
                }
                case 6: {
                    System.out.print("Nhập mã sinh viên cần chỉnh sửa: ");
                    String findID = new Scanner(System.in).next();
                    list.refactor(findID);
                    break;
                }
                case 7: {
                    File file = new File("C:\\Users\\ASUS\\IdeaProjects\\QuanLySinhVienNangCao\\src\\DanhSachSV.txt");
                    if(!file.exists())
                        file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(list);
                    oos.close();
                    fos.close();
                    break;
                }
                case 8: {
                    File file = new File("C:\\Users\\ASUS\\IdeaProjects\\QuanLySinhVienNangCao\\src\\DanhSachSV.txt");
                    if(!file.exists()) {
                        System.out.println("File is not exist");
                        break;
                    }
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    try {
                        DanhSachSinhVien listReader = (DanhSachSinhVien) ois.readObject();
                        System.out.println(listReader.toString());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 9: {
                    File file = new File("C:\\Users\\ASUS\\IdeaProjects\\QuanLySinhVienNangCao\\src\\DanhSachSV.txt");
                    if (file.delete())
                        System.out.println("File txt is deleted");
                    else System.out.println("Deleting failed");
                    break;
                }
                case 0: {
                    answer = 0;
                    break;
                }
                default: {
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
                }
            }
        } while (answer != 0);
    }
}
