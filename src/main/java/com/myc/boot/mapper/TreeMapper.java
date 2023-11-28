package com.myc.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myc.boot.domain.Tree;

public interface TreeMapper extends BaseMapper<Tree> {

    int insertTree(Tree record);
}
