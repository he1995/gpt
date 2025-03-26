package com.example.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    @Cacheable("goods")
    public Iterable<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods getGoods(long id) {
        return goodsRepository.findById(id).get();
    }
}
