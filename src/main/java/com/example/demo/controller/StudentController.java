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
//    @PostMapping(value = "update")
    @GetMapping(value="update")
    public ResponseEntity update(
                                        String id,
                                        String sex,
                                        String name,
                                        String telephone,
                                        String symptom,
                                        String medicine
    ){
        id = "1";
        sex = "不难不拿";
        Student student = studentService.update(id, sex, name, telephone, symptom, medicine);
        return new ResponseEntity(HttpStatus.OK,student);
    }

    /*
    带条件的分页查询
     */
    @GetMapping(value="findpages")
    public ResponseEntity findpage(
                                          String name,
                                          String telephone,
                                          String symptom,
                                          Integer pageNmuber,
                                          Integer pageSize
    ){
        name="1";
        Page<Student> page = studentService.findpage(name, telephone, symptom, pageNmuber, pageSize);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
        responseEntity.setData(page);
        return responseEntity;
    }


}
