package cn.itlzq.db;


import cn.itlzq.model.Address;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/18 20:47
 * @email 邮箱:905866484@qq.com
 * @description 描述：地址
 */
@Mapper
@Component
public interface AddressDao {

    /**
     * 查询用户的所有收货地址
     * @param userId 用户id
     * @return 用户地址集合
     */
    @Select("SELECT * FROM JJ_ADDRESS WHERE USERID = #{userId}")
    List<Address> findByUserId(int userId);

    /**
     * 查询某个收获地址
     * @param id 地址id
     * @return 查询地址
     */
    @Select("SELECT * FROM JJ_ADDRESS WHERE ID = #{id}")
    Address findAddressById(int id);

    /**
     * 地址信息的插入
     * @param address 地址对象
     * @return 插入结果
     */
    @Insert("INSERT INTO JJ_ADDRESS(USERID,USERNAME,USERPHONE,PROVINCEID,CITYID,AREAID,STREEID,USERADDRESS,ISDEFAULT,CREATETIME) " +
            "VALUES(#{userId},#{userName},#{userPhone},#{provinceId},#{cityId},#{areaId},#{streeId},#{userAddress},#{isDefault},#{createTime})")
    boolean insertAddress(Address address);
    /**
     * 地址信息的修改
     * @param newAddress 地址对象
     * @return 修改结果
     */
    @Update("UPDATE JJ_ADDRESS SET USERNAME = #{userName},USERPHONE = #{userPhone},PROVINCEID = #{provinceId},CITYID = #{cityId},AREAID = #{areaId},STREEID = #{streeId},USERADDRESS = #{userAddress},ISDEFAULT = #{isDefault},CREATETIME = #{createTime} WHERE ID = #{ids}")
    boolean updateAddress(int ids, Address newAddress);
    /**
     * 地址信息的删除
     * @param id 地址id
     * @return 删除结果
     */
    @Delete("DELETE FROM JJ_ADDRESS WHERE ID = #{id}")
    boolean deleteAddress(@Param("id") int id);


    @Update("UPDATE  JJ_ADDRESS SET ISDEFAULT = 0 WHERE ISDEFAULT = 1")
    void setDefault0();
    /**
     * 设置默认
     * @param id 默认id
     * @return 结果
     */
    @Update("UPDATE  JJ_ADDRESS SET ISDEFAULT = 1 WHERE ID = #{id}")
    boolean setDefault(@Param("id") int id);

    @Select("SELECT ID FROM JJ_ADDRESS WHERE ISDEFAULT = 1")
    int getDefault();
}
