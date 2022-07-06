package cn.eassen.mydynamicproxy.service;

import cn.eassen.mydynamicproxy.entity.Student;
import org.springframework.stereotype.Service;

/**
 * @Author eassen
 * @Create 2022/7/6 15:11
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Override
    public void getStudents() {
        System.out.println("say hello");
    }

    public StudentServiceImpl() {
        System.out.println("无参构造StudentServiceImpl");
    }

    @Override
    public void addStudent(Student student) {

    }
}
