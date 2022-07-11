package cn.eassen.proxys.asmProxy;

import cn.eassen.mydynamicproxy.entity.Student;
import cn.eassen.mydynamicproxy.service.StudentService;

/**
 * @Author eassen
 * @Create 2022/7/11 16:25
 */
public class HelloASMProxy implements StudentService {

    private StudentService delegate;

    public void SingerProxy(StudentService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void getStudents() {
        System.out.println("ASM before sing");
        this.delegate.getStudents();
        System.out.println("ASM after sing");
    }

    @Override
    public void addStudent(Student student) {

    }
}