package com.example.order;

import com.example.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @PostMapping("create")
    public ResponseResult<OrderInfo> createOrder(@RequestBody OrderRequest orderRequest)
             {
        String orderId = UUID.randomUUID().toString();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(orderId);
        orderInfo.setEmail(orderRequest.email());
        orderInfo.setPayMethod(orderRequest.payMethod());
        orderInfo.setName(orderRequest.name());
        orderInfo.setPrice(orderRequest.price());
        orderInfo.setDelivery(orderRequest.delivery());
        orderInfo.setCreateTime(new Date().toString());
        orderInfo.setStatus(OrderStatus.UNPAID);
        orderInfo.setUsername(orderRequest.username());
        orderService.createOrder(orderInfo);

        log.info("{}:订单：【{}】创建成功",new Date(), orderId);
        //10 seconds expire for testing
        redisTemplate.opsForValue().set(orderId, "1",10, TimeUnit.MINUTES);

        return ResponseResult.success(orderInfo);
    }

    @RequestMapping("/{pageNum}")   // localhost:8080/order/0?pageNumber=6&pageSize=2
    public ResponseResult<Iterable<OrderInfo>> getOrders(@PathVariable int pageNum, @RequestParam int pageSize) {
        return ResponseResult.success(orderService.getOrders(PageRequest.of(pageNum, pageSize)).getContent());
    }

    @RequestMapping("/find")
    public ResponseResult<Page<OrderInfo>> findByEmail(
            @RequestParam String email,
            @RequestParam int pageNum,
            @RequestParam int pageSize) {
        return ResponseResult.success(orderService.findByEmail(email, PageRequest.of(pageNum, pageSize)));
    }
}
