package com.cakir.templateManagement.service;

import java.util.Collection;
import java.util.List;

public interface CacheService {

    Collection<String> getAllKeys();

    void clearCache();

    void clearCacheByKey(String key);
}
