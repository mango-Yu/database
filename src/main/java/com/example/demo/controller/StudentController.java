package com.example.demo.controller;

import com.example.demo.dao.StudentDao;
import com.example.demo.domain.Student;
import com.example.demo.service.StudentServiceImpl;
import com.example.demo.utils.ResponseEntity;
import com.sun.deploy.net.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping(value = "/findall")
    public ResponseEntity find(){
        return studentService.find();
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        studentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "save")
    public ResponseEntity save(
            String name,
            String sex,
            String telephone,
            String symptom,
            String medicine
    ){
        Student student = new Student(UUID.randomUUID().toString(), name, sex, telephone, symptom, medicine, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        Student save = studentService.save(student);
        return new ResponseEntity(HttpStatus.OK,save);
    }

    /*
    修改方法
     */
    @PostMapping(value = "update")
    public ResponseEntity update(
            String id,
            String sex,
            String name,
            String telephone,
            String symptom,
            String medicine
    ){

        Student student = studentService.update(id, sex, name, telephone, symptom, medicine);
        return new ResponseEntity(HttpStatus.OK,student);
    }

    /*
    带条件的分页查询
     */


}
