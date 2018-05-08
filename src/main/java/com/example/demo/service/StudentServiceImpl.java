package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.dao.jdbc.StudeneJdbcDao;
import com.example.demo.domain.Student;
import com.example.demo.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl {

    @Autowired(required = false)
    private StudentDao studentDao;

    @Autowired
    private StudeneJdbcDao studeneJdbcDao;

    @Transactional(readOnly = false)
    public Student save(Student student) {
        return studentDao.save(student);
    };

    @Transactional(readOnly = false)
    public void delete(String id) {
        studentDao.deleteById(id);
    };

    public ResponseEntity find() {
        List<Student> list = studentDao.findAll();

        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(list);
        responseEntity.setCount(list.size());
        return responseEntity;
    };

    /*
     * 修改
     */
    public Student update(String id, String sex, String name, String telephone, String symptom, String medicine) {
        Student student = null;
        Optional<Student> byId = studentDao.findById(id);
        if (byId != null) {
            student = byId.get();
            if (student != null) {
                if (sex != null) {
                    student.setSex(sex);
                }
                if (name != null) {
                    student.setName(name);
                }
                if (telephone != null) {
                    student.setTelephone(telephone);
                }
                if (symptom != null) {
                    student.setSymptom(symptom);
                }
                if (medicine != null) {
                    student.setMedicine(medicine);
                }
                save(student);
            }
        }
        return student;
    }

    /*
     * 待条件的分页查询
     */
    public ResponseEntity findpage(
                                          String name,
                                          String telephone,
                                          String symptom,
                                          Integer pageNmuber,
                                          Integer pageSize
    ) {

        @SuppressWarnings("serial")
        Specification<Student> specification = new Specification<Student>() {

            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (name != null && name != "") {
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                if (telephone != null && telephone != "") {
                    predicates.add(criteriaBuilder.equal(root.get("telephone").as(String.class), telephone));
                }
                if (symptom != null && symptom != "") {
                    predicates.add(criteriaBuilder.like(root.get("symptom").as(String.class), "%" + symptom + "%"));
                }
                Predicate[] p = new Predicate[predicates.size()];
                p = predicates.toArray(p);
                Predicate predicate = criteriaBuilder.and(p);
                return predicate;
            }
        };
        if (pageNmuber == null || pageNmuber<0) {
            pageNmuber = 1;
        }
        if (pageSize == null || pageSize <0) {
            pageSize = 10;
        }
        PageRequest pageRequest = new PageRequest(pageNmuber-1, pageSize);
        Page<Student> page = studentDao.findAll(specification, pageRequest);
        List<Student> content = page.getContent();
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(content);
        String sql = "select student.id from student where 1 = 1 ";
        if (name!=null && telephone != ""){
            sql += "and name like "+"'%"+name+"%' ";
        }
        if (telephone!=null && telephone !=""){
            sql+= "and telephone = "+"'"+telephone+"' ";
        }
        if (symptom!=null && symptom != ""){
            sql+= "and symptom like "+"'%"+symptom+"%'";
        }
        Integer count = studeneJdbcDao.getCount(sql);
        responseEntity.setCount(count);
        return responseEntity;
    }
}
