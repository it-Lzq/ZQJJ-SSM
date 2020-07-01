import cn.itlzq.db.CartsDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 19:07
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class T {

    @Resource
    public CartsDao dao;


    @Test
    public void add(){
        System.out.println(dao);
        dao.addCarts(1,68,2);
    }
}
