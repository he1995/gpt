package com.example.gpt.goods;

import com.example.gpt.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("all")
    public ResponseResult<Iterable<Goods>> getAllGoods() {
        return ResponseResult.success(goodsService.getAllGoods());
    }

    @GetMapping("/{id}")
    public ResponseResult<Goods> getGoods(@PathVariable int id) {
        return ResponseResult.success(goodsService.getGoods(id));
    }
}
