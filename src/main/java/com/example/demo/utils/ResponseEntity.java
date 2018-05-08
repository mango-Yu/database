package com.example.demo.utils;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

/**
 * <pre>
 * Title:通用response对象
 * Description: 程序功能的描述
 * </pre>
 */
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = 6165541018961966443L;
    private Integer           code=0;                                   // httpStatus code
    private Integer           count;                                   // httpStatus code
    private T                 data;
    private String            msg="";

    public ResponseEntity() {}

    public ResponseEntity( HttpStatus httpStatus ) {
        this.code = httpStatus.value();
    }

    public ResponseEntity( HttpStatus httpStatus, T data ) {
        this.code = httpStatus.value();
        this.data = data;
    }

    public ResponseEntity( HttpStatus httpStatus, T data, String msg ) {
        this.code = httpStatus.value();
        this.data = data;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode( Integer code ) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData( T data ) {
        this.data = data;
    }
    public Object getCount() {
        return count;
    }
    public void setCount( Integer count ) {
        this.count = count;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }
}
