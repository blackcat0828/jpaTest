package com.example.alone.domain.userEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHOOL_ID")
    private Long id;
    private String name;
    private String address;
    private String telnumber;

    //학교를 기준으로 생각하면 학생과의 관계는 1:N 관계가 되는데, 기존에 학생 객체를 기준으로
    //조회했던 데이터를 학교 객체를 이용해서 출력해 보자. Student클래스가 다수 쪽이므로 List나 Set과 같은
    //컬랙션을 사용해서 선언한다.
    //mappedBy는 연관 관계의 주인을 명시하기 위해 사용하는데 연관 관계의 주인은 다수 쪽이다.
    //mappedBy의 school값은 school클래스가 아니라 Student.school이라고 생각하면 편한다.
    @OneToMany(mappedBy = "school")
    private Set<Student> students;


    //students에 데이터를 추가 할수 있도록 학생을 등록하는 메서드
    //registerStudent 메서드는 Student를 파라미터로 받아서 Students가 null이면
    //HashSet 인스턴스를 생성하고, null이 아니면 students에 학생 정보를 추가한다.
    public void registerStudent(Student s){
        if(students == null){
            students = new HashSet<Student>();
        }
        students.add(s);
    }

    public School(String name){
        this.name = name;
    }

}
