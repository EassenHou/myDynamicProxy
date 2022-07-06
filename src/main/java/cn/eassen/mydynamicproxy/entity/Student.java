package cn.eassen.mydynamicproxy.entity;

/**
 * @Author eassen
 * @Create 2022/7/6 14:48
 */
public class Student {
    public long id;
    public String name;

    public Student() {
    }

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
