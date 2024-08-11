package com.devchw;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final TestEntityJpaRepository testEntityJpaRepository;

    @Transactional
    public void childMethod() {
        String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("currentTransactionName = " + currentTransactionName);

        testEntityJpaRepository.save(new TestEntity("filed"));
        throw new RuntimeException("예상치 못한 예외가 발생했습니다...");
    }
}
