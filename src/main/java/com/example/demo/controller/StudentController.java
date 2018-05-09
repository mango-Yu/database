package com.example.demo.controller;

import com.example.demo.dao.StudentDao;
import com.example.demo.domain.Student;
import com.example.demo.service.StudentServiceImpl;
import com.example.demo.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
//    @GetMapping(value="update")
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
    根据id查询
     */
    /*@PostMapping(value = "findbyid")
    public ResponseEntity findbyid(String id){
        return new ResponseEntity(HttpStatus.OK,studentService.findById(id));
    }*/
    @GetMapping(value = "findbyid/{id}")
    public ResponseEntity findbyid(@PathVariable("id") String id){
        return studentService.findById(id);
    }
    /*
    带条件的分页查询
     */
    @GetMapping(value="findpage")
    public ResponseEntity findpage(
    		String name, 
			String telephone, 
			String symptom,
			String beginDt,
			String endDt,
			String singleDate,
			Integer pageNmuber,
			Integer pageSize
    		){
        ResponseEntity responseEntity = studentService.findpage(name, telephone, symptom, beginDt,endDt,pageNmuber, pageSize);
    	return responseEntity;
    }
}
