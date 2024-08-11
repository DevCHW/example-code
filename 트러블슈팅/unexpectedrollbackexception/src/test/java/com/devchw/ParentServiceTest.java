package com.devchw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ParentServiceTest {

    @Autowired
    private ParentService parentService;

    @Autowired
    private TestEntityJpaRepository testEntityJpaRepository;

    @BeforeEach
    void setUp() {
        testEntityJpaRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("UnexpectedRollbackException이 발생해야 한다.")
    void UnexpectedRollbackException_occured(){
        assertThatThrownBy(() -> parentService.parentMethod1())
                .isInstanceOf(UnexpectedRollbackException.class);
    }

    @Test
    @DisplayName("부모서비스만 커밋이 완료되어서 TesEntity는 1개가 조회되어야 한다.")
    void propagation_requires_new(){
        // when
        parentService.parentMethod2();;

        List<TestEntity> result = testEntityJpaRepository.findAll();

        // then
        assertThat(result)
                .hasSize(1);
    }

    @Test
    @DisplayName("부모서비스, 자식서비스의 save()가 둘 다 커밋이 완료되어 2개가 조회되어야 한다.")
    void child_service_try_catch(){
        // when
        parentService.parentMethod3();;

        List<TestEntity> result = testEntityJpaRepository.findAll();
        // then
        assertThat(result)
                .hasSize(2);
    }

}