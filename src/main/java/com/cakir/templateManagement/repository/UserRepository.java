package com.cakir.templateManagement.repository;

import com.cakir.templateManagement.entity.UserEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Cacheable(key = "'findByEmail:' + #p0", unless = "#result == null")
    Optional<UserEntity> findByEmail(String email);

    @CacheEvict(key = "'findByEmail:' + #email")
    void deleteByEmail(String email);
}
