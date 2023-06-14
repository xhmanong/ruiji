package com.ecust.dto;


import com.ecust.pojo.Setmeal;
import com.ecust.pojo.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
