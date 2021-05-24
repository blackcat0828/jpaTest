package com.example.alone;

import com.example.alone.domain.dslTest.Academy;
import com.example.alone.domain.dslTest.AcademyQueryRepository;
import com.example.alone.domain.dslTest.AcademyRepository;
import com.example.alone.domain.dslTest.AcademyRepositorySupport;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class dslTest {
    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyQueryRepository academyQueryRepository;

    @After
    public void tearDown() throws Exception{
        academyRepository.deleteAllInBatch();
    }

    @Test
    public void querydsl_기본_기능_확인(){
        //given
        String name = "김동민";
        String address = "watb88@gmail.com";
        academyRepository.save(new Academy(name, address));

        //when
        List<Academy> result = academyQueryRepository.findByName(name);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getAddress()).isEqualTo(address);
    }
}
