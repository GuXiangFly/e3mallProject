package com.guxiang.cart.controller;

import com.guxiang.common.utils.CookieUtils;
import com.guxiang.common.utils.E3Result;
import com.guxiang.common.utils.JsonUtils;
import com.guxiang.pojo.TbItem;
import com.guxiang.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * CartController
 *
 * @author guxiang
 * @date 2018/1/5
 */
@Controller
public class CartController {


    @Value("${COOKIE_CART_EXPIRE}")
    private Integer COOKIE_CART_EXPIRE;

    @Resource
    private ItemService itemService;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue="1")Integer num,
                          HttpServletRequest request, HttpServletResponse response) {

        boolean flag = false;
        //从cookie中取出
        List<TbItem> cartList = getCartListFromCookie(request);
        for (TbItem tbItem : cartList) {
            if (tbItem.getId()==itemId.longValue()){
                tbItem.setNum(tbItem.getNum()+num);
                flag=true;
                break;
            }
        }

        if (!flag){
            TbItem item = itemService.getItemById(itemId);
            item.setNum(num);
            String images = item.getImage();
            if (StringUtils.isNoneBlank(images)){
                item.setImage(images.split(",")[0]);
            }
            cartList.add(item);
        }

        //写入cookie
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);

        return "cartSuccess";
    }


    @RequestMapping("/cart/cart")
    public String showCatList(HttpServletRequest request, HttpServletResponse response) {
        List<TbItem> cartListFromCookie = getCartListFromCookie(request);
        request.setAttribute("cartList",cartListFromCookie);
        return "cart";
    }

    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateCartNum(@PathVariable Long itemId, @PathVariable Integer num
            , HttpServletRequest request , HttpServletResponse response) {
        List<TbItem> cartListFromCookie = getCartListFromCookie(request);
        for (TbItem tbItem : cartListFromCookie) {
            if (tbItem.getId().longValue()==itemId){
                tbItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartListFromCookie),COOKIE_CART_EXPIRE,true);
        return E3Result.ok();
    }

    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
                                 HttpServletResponse response) {
        List<TbItem> cartListFromCookie = getCartListFromCookie(request);
        for (TbItem tbItem : cartListFromCookie) {
            if (tbItem.getId().longValue()==itemId){
               cartListFromCookie.remove(tbItem);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartListFromCookie),COOKIE_CART_EXPIRE,true);
        //返回逻辑视图  如果 是 cart/cart.html 是相对当前的路径还是算起，如果是 /cart/cart.html 是相对 localhost：8090开始算起
        return "redirect:/cart/cart.html";
    }


    private List<TbItem> getCartListFromCookie(HttpServletRequest request){
        String json = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isBlank(json)) {
            return new ArrayList<>();
        }

        List<TbItem> tbItems = JsonUtils.jsonToList(json, TbItem.class);
        return tbItems;
    }
}
