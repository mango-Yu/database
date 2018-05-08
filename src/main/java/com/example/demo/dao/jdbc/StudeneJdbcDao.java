package com.example.demo.dao.jdbc;
import com.example.demo.domain.Student;
import com.sun.xml.internal.fastinfoset.util.PrefixArray;
import org.hibernate.SessionFactory;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.crypto.MacSpi;
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
