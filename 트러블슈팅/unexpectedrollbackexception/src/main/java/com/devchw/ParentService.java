package com.devchw;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {

    private final ChildService childService;
    private final TestEntityJpaRepository testEntityJpaRepository;

    @Transactional
    public void parentMethod() {
        String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("currentTransactionName = " + currentTransactionName);
        testEntityJpaRepository.save(new TestEntity("filed"));

        try {
            childService.childMethod();
        } catch (Exception e) {
            log.info("개발자의 의도 : 예외를 잡았으니 롤백 없이 커밋되겠지??");
        }
    }

}
