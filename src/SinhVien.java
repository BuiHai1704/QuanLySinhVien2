import java.io.Serializable;

public class SinhVien implements Serializable {
    private String id;
    private String name;
    private int age;
    private float point;
    private String email;

    public SinhVien() {
    }

    public SinhVien(String id, String name, int age, float point, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.point = point;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", point=" + point +
                ", email='" + email + '\'' +
                '}';
    }
}
