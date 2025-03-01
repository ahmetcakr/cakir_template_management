package com.cakir.templateManagement.service.impl;

import com.cakir.templateManagement.service.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;


    @Override
    public Collection<String> getAllKeys() {
        return cacheManager.getCacheNames();
    }

    @Override
    public void clearCache() {
        cacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(
                        cacheManager.getCache(cacheName)).clear());
    }

    @Override
    public void clearCacheByKey(String key) {
        Objects.requireNonNull(cacheManager.getCache(key)).clear();
    }
}
