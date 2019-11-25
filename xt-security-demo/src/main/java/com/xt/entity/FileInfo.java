package com.xt.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class FileInfo implements Serializable {

    private String path;

    public FileInfo(String path) {
        this.path = path;
    }
}
