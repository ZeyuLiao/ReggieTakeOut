package com.springbootproject.reggietakeout.dto;
import com.springbootproject.reggietakeout.entity.Combo;
import com.springbootproject.reggietakeout.entity.ComboDish;
import lombok.Data;

import java.util.List;

@Data
public class ComboDto extends Combo {

    private List<ComboDish> comboDishes;

    private String categoryName;
}
