package com.example.alone.domain.userEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;
    private String userName;
    private String grade;

    //학생이 N이고 학교가 1 이므로, @ManyToOne 어노테이션 사용
    //@ManyToOne, @OneToMany와 같은 연관 관계 어노테이션들은 각각 다른 로딩 방식을 가지고 있는데
    //@ManyToOne 어노테이션은 즉시 로딩(EAGER)이 기본값이다.
    //즉시 로딩으로 실행될 때는 연결된 엔티티 정보까지 한 번에 가져오려고 하므로 성능에 문제가
    //발생할 수 있다. 그래서 @ManyToOne을 사용할 때는 FetchType.LAZY를 지정해서 지연 로딩되도록
    //하는 것이 좋다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    public School getSchool(){
        return school;
    }

    public Student(String userName){
        this.userName = userName;
    }
}
