package com.adelfo.springcloud.msvc.items.services;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adelfo.springcloud.msvc.items.clients.ProductFeignClient;
import com.adelfo.springcloud.msvc.items.models.Item;

import feign.FeignException;

@Service
public class ItemServiceFeign implements ItemService{

    @Autowired
    private ProductFeignClient client;

    @Override
    public List<Item> findAll() {
        return client.findAll().stream().map(producto -> {
            Random random = new Random();

            return new Item(producto, random.nextInt(10) + 1);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {
        try{
            // Product product = client.details(id);
            return Optional.of(new Item(client.details(id), new Random().nextInt(10) + 1));
        
        } catch(FeignException err){
            return Optional.empty();
        }        
    }

}
