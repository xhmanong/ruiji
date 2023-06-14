package com.ecust.pojo;

import com.ecust.dto.SetmealDto;
import lombok.Data;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-08 9:14
 */
@Data
public class SetmealBean {
    private Long total;
    private List<SetmealDto> records;
}
