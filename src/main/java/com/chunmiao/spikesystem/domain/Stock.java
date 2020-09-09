package com.chunmiao.spikesystem.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 库存实体类
 */
@Data
@Entity
public class Stock implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //货品库存数量
    private Long stock;

}
