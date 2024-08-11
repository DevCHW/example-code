package com.devchw;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ParentServiceTest {

    @Autowired
    private ParentService parentService;

    @Test
    @DisplayName("UnexpectedRollbackException이 발생해야 한다.")
    void UnexpectedRollbackException_occured(){
        assertThatThrownBy(() -> parentService.parentMethod())
                .isInstanceOf(UnexpectedRollbackException.class);
    }

}