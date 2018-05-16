package com.example.demo.dao.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class StudeneJdbcDao extends JdbcTemplate {

    @Autowired
    public StudeneJdbcDao(DataSource dataSource) {
        super(dataSource);
    }

    public Integer getCount(String sql){
        List<Map<String, Object>> maps = super.queryForList(sql);
        return maps.size();
    }
}
