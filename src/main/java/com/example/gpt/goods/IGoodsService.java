package com.example.gpt.goods;

public interface IGoodsService {

    Iterable<Goods> getAllGoods();

    Goods getGoods(long id);
}
