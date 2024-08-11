package com.devchw;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {

    private final ChildService childService;
    private final TestEntityJpaRepository testEntityJpaRepository;

    /**
     * UnexpectedRollbackException 발생
     */
    @Transactional
    public void parentMethod1() {
        testEntityJpaRepository.save(new TestEntity("parent"));

        try {
            childService.childMethod1();
        } catch (Exception e) {
            log.info("예외를 잡았으니 롤백 없이 커밋되겠지??");
        }
    }

    /**
     * 전파속성 REQUIRES_NEW, 부모만 커밋, 자식은 롤백
     */
    @Transactional
    public void parentMethod2() {
        testEntityJpaRepository.save(new TestEntity("parent"));

        try {
            childService.childMethod2();
        } catch (Exception e) {
            log.info("예외를 잡았으니 롤백 없이 커밋되겠지??");
        }
    }

    /**
     * 내부 트랜잭션에서 예외처리
     */
    @Transactional
    public void parentMethod3() {
        testEntityJpaRepository.save(new TestEntity("parent"));

        childService.childMethod3();
    }

}
