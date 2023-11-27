package com.myc.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myc.boot.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectMenuPermsByUserId(Long userId);

}
