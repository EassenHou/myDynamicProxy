package cn.eassen.mydynamicproxy.service;

import cn.eassen.mydynamicproxy.entity.Student;

/**
 * @Author eassen
 * @Create 2022/7/6 15:11
 */
public interface StudentService {

    /**
     * get
     */
    void getStudents();

    /**
     * add
     * @param student
     */
    void addStudent(Student student);
}
