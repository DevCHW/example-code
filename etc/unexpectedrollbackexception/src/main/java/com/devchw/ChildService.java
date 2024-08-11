package com.devchw;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChildService {

    private final TestEntityJpaRepository testEntityJpaRepository;

    @Transactional
    public void childMethod1() {
        testEntityJpaRepository.save(new TestEntity("child"));
        throw new RuntimeException("예상치 못한 예외가 발생했습니다...");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void childMethod2() {
        testEntityJpaRepository.save(new TestEntity("child"));
        throw new RuntimeException("예상치 못한 예외가 발생했습니다...");
    }

    @Transactional
    public void childMethod3() {
        try {
            testEntityJpaRepository.save(new TestEntity("child"));
            throw new RuntimeException("예상치 못한 예외가 발생했습니다...");
        } catch (Exception e) {
            log.info("예외를 잡았으니 롤백 없이 커밋되겠지??");
        }
    }

}
