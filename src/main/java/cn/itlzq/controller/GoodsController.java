package cn.itlzq.controller;

import cn.itlzq.model.Goods;
import cn.itlzq.model.GoodsClass;
import cn.itlzq.service.GoodsClassService;
import cn.itlzq.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/23 23:10
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Controller
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodsClassService goodsClassService;


    @RequestMapping("/findGoodsClass")
    public String goodsClass(HttpServletRequest request){
        String type = request.getParameter("type");
        String classId1 = request.getParameter("classId1");
        String classId2 = request.getParameter("classId2");
        String goodsName = request.getParameter("goodsName");
        String orderBy = request.getParameter("orderBy");
        int orderByInt = orderBy==null?0:Integer.parseInt(orderBy);
        GoodsClass goodsClassShow = new GoodsClass(0,0,goodsName);
        GoodsClass goodsClassShow2 = new GoodsClass(0,0,"");
        if(type == null){
            type = "";
        }
        if( classId1!=null ) {
            if(goodsName!=null && goodsName.equals("All Goods")){
                type = "0";
            }else{
                type = "1";
            }

        }
        System.out.println(classId1);
        List<Goods> goodsList = null;
        switch (type){
            case "0":
                return "redirect:/index";
            case "1":
                //查询一级分类商品
                goodsList = goodsService.findGoodsByClass1(Integer.parseInt(classId1),1,50,orderByInt);
                goodsClassShow = goodsClassService.findById(Integer.parseInt(classId1));
                break;
            case "2":
                //查询二级分类商品
                goodsList = goodsService.findGoodsByClass2(Integer.parseInt(classId2),1,50,orderByInt);
                goodsClassShow2 = goodsClassService.findById(Integer.parseInt(classId2));
                goodsClassShow = goodsClassService.findById(goodsClassShow2.getParentId());
                break;
            case "3":
                //模糊查询商品
                goodsList = goodsService.findGoodsLikeName(goodsName,1,50,orderByInt);
                goodsClassShow = new GoodsClass(0,0,goodsName);
                break;
        }
        System.out.println(goodsList);
        request.setAttribute("type",type);
        request.setAttribute("goodsList",goodsList);
        request.setAttribute("goodsClassShow",goodsClassShow);
        request.setAttribute("goodsClassShow2",goodsClassShow2);
        return "goodsClass";
    }

    @RequestMapping("findGoods")
    public String findGoods(HttpServletRequest request){
       //1.    接收请求的参数 : 商品id
        String goodsId = request.getParameter("goodsId");
        int id = Integer.parseInt(goodsId);
        //2.    调用Service , 查询商品信息
        Goods goods = goodsService.findGoodsById(id);
        if( goods != null){
            //2.    查询商品一级分类
            GoodsClass class1 = goodsClassService.findById(goods.getClassid1());
            //3.    查询商品二级分类
            GoodsClass class2 = goodsClassService.findById(goods.getClassid2());
            //4.    将查询到的三个数据存储在请求对象中
            request.setAttribute("goods",goods);
            request.setAttribute("class1",class1);
            request.setAttribute("class2",class2);
        }
        return "goods";
    }
}
