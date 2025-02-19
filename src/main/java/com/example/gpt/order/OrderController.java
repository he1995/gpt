package com.example.gpt.order;

import cn.dev33.satoken.stp.StpUtil;
import com.example.gpt.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("add")
    public ResponseResult<OrderItem> addOrder(
            @RequestParam String email,
            @RequestParam String payMethod) {
        String orderId = UUID.randomUUID().toString();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderId);
        orderItem.setEmail(email);
        orderItem.setPayMethod(payMethod);
        orderItem.setName("智能助手月度会员");
        orderItem.setPrice("￥20");
        orderItem.setDelivery("自动发货");
        orderItem.setCreateTime(new Date().toString());
        orderItem.setStatus(OrderStatus.UNPAID.getCode());
        orderItem.setUsername((String) StpUtil.getLoginId());
        orderService.createOrder(orderItem);

        log.info("{}:订单：【{}】创建成功",new Date(), orderId);
        redisTemplate.opsForValue().set(orderId, "1",10, TimeUnit.SECONDS);

        return ResponseResult.success(orderItem);
    }

    @RequestMapping("all")
    public ResponseResult<Iterable<OrderItem>> getAllGoods() {
        return ResponseResult.success(orderService.getOrders());
    }
}
