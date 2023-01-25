package com.springbootproject.reggietakeout.dto;
import com.springbootproject.reggietakeout.entity.Setmeal;
import com.springbootproject.reggietakeout.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
