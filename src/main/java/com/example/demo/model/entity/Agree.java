package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl
 * @Date 2022/3/20 16:48
 */
@Data
@TableName("t_agree")
@NoArgsConstructor
@AllArgsConstructor
public class Agree {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String aid;
    private String uid;
    private Integer agreeCount;
}
