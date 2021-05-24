package com.example.alone.service;

import com.example.alone.domain.userEntity.School;
import com.example.alone.domain.userEntity.SchoolRepository;
import com.example.alone.domain.userEntity.Student;
import com.example.alone.domain.userEntity.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentRepository studentRepository;


    //Lazy로 로딩하면서 다른 Entity 정보를 함께 조회하려고 하면 could not initialize proxy - no Session
    //오류가 발생한다. 오류 메시지 중에 Proxy가 있는 이유는 지연 로딩 사용 시에는 JPA가 한번에 모든 데이터를 다
    //로딩하지 않고 proxy를 이용해서 처리하기 때문이다. 이렇게 두 개 이상의 Entity들을 제어하는 메서드들을
    //사용할 때는 해당 메서드들을 SchoolService의 findStudentInfo 메서드와 같이
    //@Transactional 어노테이션을 사용해서 하나의 트랜잭션 안에서 사용하도록 하면 된다.
    //그리고 @Transactional 어노테이션을 사용하면 해당 메서드 실행 중 예외를 발생 시 롤백시켜 준다.
//    @Transactional
//    public void findStudentInfo(){
//        School school = new School("매력고");
//        schoolRepository.save(school);
//
//        Student stu1 = new Student("나라");
//        Student stu2 = new Student("민하");
//        Student stu3 = new Student("동민");
//
//        stu1.setSchool(school);
//        stu2.setSchool(school);
//        stu3.setSchool(school);
//
//        studentRepository.save(stu1);
//        studentRepository.save(stu2);
//        studentRepository.save(stu3);
//
//        List<Student> students = studentRepository.findAll();
//
//        for (Student s : students){
//            System.out.println(s.getUserName() + "," + s.getSchool().getName());
//        }
//    }


    @Transactional
    public void findSchoolInfo(){
        School sc1 = new School("예체능고");
        sc1.registerStudent(new Student("홍길동"));
        sc1.registerStudent(new Student("유재석"));

        School sc2 = new School("매력고");
        sc2.registerStudent(new Student("나라"));
        sc2.registerStudent(new Student("민하"));

        schoolRepository.saveAll(new HashSet<School>() {{
            add(sc1);
            add(sc2);
        }});

        List<School> schools = schoolRepository.findAll();

        for(School s : schools){
            System.out.println(s.toString());
        }
    }
}
