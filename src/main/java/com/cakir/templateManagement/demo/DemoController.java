package com.cakir.templateManagement.demo;

import com.cakir.templateManagement.common.dto.DocumentTestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<DocumentTestDTO> demo() {
        DocumentTestDTO documentTestDTO = new DocumentTestDTO();
        documentTestDTO.setP1("1");
        documentTestDTO.setP2("2");
        documentTestDTO.setP3("3");
        documentTestDTO.setP4("4");
        documentTestDTO.setP5("5");
        documentTestDTO.setP6("6");
        documentTestDTO.setP7("7");
        documentTestDTO.setP8("8");
        documentTestDTO.setP9("9");
        documentTestDTO.setP10("10");

        return ResponseEntity.ok(documentTestDTO);
    }
}
