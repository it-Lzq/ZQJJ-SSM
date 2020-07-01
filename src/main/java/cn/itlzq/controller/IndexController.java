package cn.itlzq.controller;

import cn.itlzq.model.Goods;
import cn.itlzq.model.GoodsClass;
import cn.itlzq.service.GoodsClassService;
import cn.itlzq.service.GoodsService;
import cn.itlzq.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/23 17:55
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Controller
public class IndexController {

    @Resource
    private GoodsClassService goodsClassService;
    @Resource
    private GoodsService goodsService;


    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        init();
        List<Goods> goodsList = goodsService.fingAll();
        request.setAttribute("goodsList",goodsList);
        request.setAttribute("goodsClassShow","All Goods");
        return "index";
    }

    @RequestMapping("/tologin")
    public String login(){
        return "login";
    }

    @RequestMapping("/toreg")
    public String reg(){
        return "reg";
    }

    //缓存商品
    private void init(){
        //从缓存中取出所有分类数据
        List<HashMap<String, Object>> class1 = (List<HashMap<String, Object>>) DataUtil.data.get("class");
        if (class1 == null) {
            //从未存储过，从数据库查询
            List<GoodsClass> goodsClassList = goodsClassService.findAll();
            //创建存储一级分类集合 class1
            class1 = new ArrayList<>();
            //从所有分类中将一级分类取出
            for (int i = 0; i < goodsClassList.size(); i++) {
                //类对象
                GoodsClass goodsClass = goodsClassList.get(i);
                if (goodsClass.getParentId() == 0) {
                    HashMap<String, Object> map = new HashMap<>();
                    //存储二级分类的集合class2
                    List<GoodsClass> class2 = new ArrayList<>();
                    //遍历存储
                    for (int j = 0; j < goodsClassList.size(); j++) {
                        if (goodsClassList.get(j).getParentId() == goodsClass.getId()) {
                            class2.add(goodsClassList.get(j));
                        }
                    }
                    map.put("class1", goodsClass);
                    map.put("class2", class2);
                    class1.add(map);
                }
            }
            DataUtil.data.put("class", class1);
        }
    }
}
