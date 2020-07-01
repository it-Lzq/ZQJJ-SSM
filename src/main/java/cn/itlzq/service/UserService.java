package cn.itlzq.service;


import cn.itlzq.db.UserDao;
import cn.itlzq.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/7 22:29
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class UserService {

    @Resource
    private UserDao dao;

    public  boolean regist(User user){
        User user1 = dao.selectOne(user.getUserphone());
        System.out.println(user1);
        if(user1 == null){
            user.setUserPhoto("ntx.png");
            user.setEmail(user.getUserphone()+"@163.com");
            user.setLastTime(new Date(System.currentTimeMillis()));
            return dao.regist(user);
        }else {
            return false;
        }
    }

    public  boolean login(User u){
        User user = dao.selectOne(u.getUserphone());
        if(user==null){
            return false;
        }else{
            if(user.getPassword().equals(u.getPassword())){
                updateLastIp(user);
                return true;
            }
        }
        return false;
    }

    public  User getUser(String userphone){
        return  dao.selectOne(userphone);
    }

    public  void updateLastIp(User u){
        User user = dao.selectOne(u.getUserphone());
        user.setLastTime(new Date(System.currentTimeMillis()));
        dao.update(user);
    }



    public  void updateUserInfo(User u){
        User user = dao.selectOne(u.getUserphone());
        user.setEmail(u.getEmail());
        user.setNickName(u.getNickName());
        dao.update(user);
    }

    public List<User> findByUserName(String username){
        return dao.findByUserName(username);
    }

    public  void update(User user){
        dao.update(user);
    }


    public List<User> getAll(){
        return dao.getAll();
    }


    public void del(int id){
        dao.del(id);
    }
}
