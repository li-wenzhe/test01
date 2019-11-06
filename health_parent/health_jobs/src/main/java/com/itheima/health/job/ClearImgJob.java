package com.itheima.health.job;

import com.itheima.health.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

//任务类
public class ClearImgJob {

    @Autowired
    JedisPool jedisPool;

    //执行任务类的方法
    public void executeJob(){
        //计算缓存中两个集合的差值,清理图片
        Set<String> set = jedisPool.getResource().sdiff("setmealPicResources", "setmealPicDbResources");
        Iterator<String> iterator = set.iterator();//使用方法iterator()要求容器返回一个Iterator(迭代器)
        while (iterator.hasNext()){
            String picName = iterator.next();
            System.out.println("删除的图片名称:"+picName);
            //1.删除七牛云的数据
            QiniuUtils.deleteFileFromQiniu(picName);
            //2.删除Redis集合中key为setmealPicDbResources的数据
            jedisPool.getResource().srem("setmealPicResources",picName);
        }
    }
}
