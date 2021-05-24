package com.example.alone.domain.userEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table (name = "tb_user")
public class UserEntityMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private Integer age;
    private Date createdAt;

    @Column(name="role")
    //@Enumerated 어노테이션 속성으로 @EnumType을 지정할때 ORDINAL로 하면 값이 int로 할당되고
    //STRING으로 지정하면 ENUM의 이름으로 할당된다.
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    @PrePersist
    public void beforeCreate(){
        createdAt = new Date();
    }

    public UserEntityMain(String userName, Integer age, UserRole role){
        this.userName = userName;
        this.age = age;
        this.role = role;
    }


}
