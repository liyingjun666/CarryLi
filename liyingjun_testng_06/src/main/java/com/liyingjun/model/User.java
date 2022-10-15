package com.liyingjun.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id ;
    private String userName ;
    private String password ;
    private String age ;
    private String sex ;
    private String permission ;
    private String isDelete;

    @Override

    public String toString(){
        return (
                "{id"+id+","+
                        "userName"+userName+","+
                        "id"+password+","+
                        "id"+age+","+
                        "id"+sex+","+
                        "id"+permission+","+
                        "id"+isDelete+"}"
        );
    }
}
