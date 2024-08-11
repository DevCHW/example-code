package com.devchw;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityJpaRepository extends JpaRepository<TestEntity, Long> {
}
