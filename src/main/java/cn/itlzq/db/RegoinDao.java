package cn.itlzq.db;


import cn.itlzq.model.CnRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/17 20:56
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface RegoinDao {

    @Select("SELECT * FROM CN_REGION WHERE LEVEL = #{level}")
    List<CnRegion> findByLevel(int level);


    @Select("SELECT * FROM CN_REGION WHERE CODE = #{code}")
    CnRegion findByCode(String code);


    @Select("SELECT * FROM CN_REGION WHERE SUPERIOR_CODE = #{parentCode}")
    List<CnRegion> findByParentCode(String parentCode);
}
