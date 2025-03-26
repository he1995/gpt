package com.example.goods;


public interface IGoodsService {

    Iterable<Goods> getAllGoods();

    Goods getGoods(long id);
}
