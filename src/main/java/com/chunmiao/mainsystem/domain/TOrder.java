package com.chunmiao.mainsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 订单实体类
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TOrder implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    private String orderName;

    private String orderUser;

}
