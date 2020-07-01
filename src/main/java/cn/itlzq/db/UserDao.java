package cn.itlzq.db;

import cn.itlzq.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 9:13
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface UserDao {

    /**
     * 用于注册用户
     * @param user 要注册的用户信息
     * @return 注册结果
     */
    @Insert("INSERT INTO JJ_USERS(USERPHONE,PASSWORD,NICKNAME,USERPHOTO,CREATETIME) VALUES (#{userphone},#{password},#{nickName},#{userPhoto},#{createTime})")
    boolean regist(User user);

    /**
     *  用于登陆验证
     * @param id 登录的的用户id
     * @return 登陆成功时，此对象中其他值会自动赋值
     */
    @Select("SELECT * FROM JJ_USERS WHERE USERPHONE = #{id}")
    User selectOne(String id);


    @Update("UPDATE JJ_USERS  SET USERPHONE=#{userphone},EMAIL=#{email},PASSWORD=#{password},NICKNAME=#{nickName},USERPHOTO=#{userPhoto},LASTIP=#{lastIp},LASTTIME=#{lastTime},CREATETIME=#{createTime} WHERE ID = #{id}")
    boolean update(User user);

    @Select("SELECT * FROM JJ_USERS")
    List<User> getAll();

    @Delete("delete from jj_users where id = #{id}")
    void del(int id);

    @Select("select * from jj_users where nickname like ${username}")
    List<User> findByUserName(String username);
}
