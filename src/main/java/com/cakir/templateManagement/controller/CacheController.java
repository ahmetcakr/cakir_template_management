package com.cakir.templateManagement.controller;

import com.cakir.templateManagement.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/cache")
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @GetMapping("/clear")
    public void clearCache(){
        cacheService.clearCache();
    }

    @GetMapping("/getAllKeys")
    public Collection<String> getAllKeys(){
        return cacheService.getAllKeys();
    }

    @PostMapping("/clearByKey")
    public void clearByKey(@RequestParam String key){
        cacheService.clearCacheByKey(key);
    }

}
