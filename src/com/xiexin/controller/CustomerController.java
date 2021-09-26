package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Customer;
import com.xiexin.bean.CustomerExample;
import com.xiexin.util.AliSMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
@CrossOrigin(origins = "*") //跨域  注意：* 是来自所有的域名请求，也可以细化
@RestController
@RequestMapping("/api/Customer")
public class CustomerController{
@Autowired(required = false)
private com.xiexin.service.CustomerService CustomerService;

@Autowired
private  JedisPool jedisPool;

//发送验证码
@RequestMapping("/sendCodeNum")
public Map sendCodeNum(String phoneNum) {
    Map codeMap = new HashMap();
    //1、接收到前端传过来的手机号：phoneNum，对其调用 阿里云的发送短信的工具类，去发送验证码
    //随机的六位数字
    int randomNum = new Random().nextInt(999999);// 0-999998  如果 i<100000 那么 + 10000
    if (randomNum < 100000) {
        randomNum = randomNum + 100000;
    }
    //1.1 在发送 验证码之前，需要向redis 中 查询 该手机 有没有验证码存在，如果存在，就直接从redis中读取，而不从阿里云发送，可以节省成本
    String codeFromRedis = jedisPool.getResource().get(phoneNum);
    Boolean b = jedisPool.getResource().exists(phoneNum);
    if (b) {
        codeMap.put("code",0);
        codeMap.put("msg","验证码已存在，请去短信中查询");
        return codeMap;
    }else {
        //2、在 发送验证码 之前，随机 创建 6个 随机数字的验证码
        AliSMSUtil.sendMsg(phoneNum, randomNum);
        //3、发送之后，将 手机号当做 redis key，验证码当做 redis value 存入到 redis 库中
        String setex = jedisPool.getResource().setex(phoneNum, 120, String.valueOf(randomNum));  //setex 的返回值是ok
        System.out.println("setex = " + setex);
        jedisPool.getResource().persist(phoneNum);  //注意这里设置成 -1，在线上环境要删除
        //4、将 验证码 发送给 前端
        if ("OK".equals(setex)) {
            codeMap.put("code", 0);
            codeMap.put("msg", "发送成功");
            //codeMap.put("data", randomNum);  注意：线上不能把验证码 通过json 数据发送给前端，容易被人恶用，验证码只能该手机号看到
            return codeMap;
        } else {
            codeMap.put("code", 500);
            codeMap.put("msg", "发送失败");
            return codeMap;
        }
    }
    // 1. 作业1, 后台为了减少  验证码 多次发送,  每个手机号  120s 内 如果  连续点击验证码, 则 不调用 阿里云发的,
    // 直接 发送  redis 中的值 .

    // 2 作业2 :  前端也需要 拦截多次发送, 在 发送验证码的按钮上,设置定时器, 当点击发送验证码后, 按钮变为  倒计时!!!  , 同时 移除点击事件.
    //  待 120s 过后,  显示 正常 发送验证码按钮!!!!
}

//校验手机号 和 验证码
    @RequestMapping("/customerLogin")  //"/api/Customer/sendCodeNum"
    public Map customerLogin(String phoneNum, String codeNum){
    Map codeMap = new HashMap();
    //1、根据前端 传来的手机号 和 验证码 来和 redis 作对比
        String redisCodeNum = jedisPool.getResource().get(phoneNum);
        if (codeNum.equals(redisCodeNum)){
            //相等，登陆成功
            codeMap.put("code",0);
            codeMap.put("msg","登录成功");
            return  codeMap;
        }else {
            //不相等，登陆失败
            codeMap.put("code",400);
            codeMap.put("msg","登陆失败");
            return  codeMap;
        }
    }

    @RequestMapping("/customerLoginByAxios")  //"/api/Customer/sendCodeNum"
    public Map customerLoginByAxios(@RequestBody Map map){
        System.out.println("map = " + map);
        Map codeMap = new HashMap();
        //1、根据前端 传来的手机号 和 验证码 来和 redis 作对比
        String redisCodeNum = jedisPool.getResource().get((String) map.get("phoneNum"));  // redis中的 验证码
        if (map.get("codeNum").equals(redisCodeNum)){
            //相等，登陆成功
            codeMap.put("code",0);
            codeMap.put("msg","登录成功");
            return  codeMap;
        }else {
            //不相等，登陆失败
            codeMap.put("code",400);
            codeMap.put("msg","登陆失败");
            return  codeMap;
        }
    }

//增
// 后端订单增加 -- 针对layui的 针对前端传 json序列化的
@RequestMapping("/insert")
public Map insert(@RequestBody Customer Customer){ // orders 对象传参, 规则: 前端属性要和后台的属性一致!!!
Map map = new HashMap();
int i =  CustomerService.insertSelective(Customer);
if(i>0){
map.put("code",200);
map.put("msg","添加成功");
return map;
}else{
map.put("code",400);
map.put("msg","添加失败,检查网络再来一次");
return map;
}
}


// 删
// 删除订单  根据 主键 id 删除
@RequestMapping("/deleteById")
public Map deleteById(@RequestParam(value = "id") Integer id) {
Map responseMap = new HashMap();
int i = CustomerService.deleteByPrimaryKey(id);
if (i > 0) {
responseMap.put("code", 200);
responseMap.put("msg", "删除成功");
return responseMap;
} else {
responseMap.put("code", 400);
responseMap.put("msg", "删除失败");
return responseMap;
}
}

// 批量删除
@RequestMapping("/deleteBatch")
public Map deleteBatch(@RequestParam(value = "idList[]") List<Integer> idList) {
    for (Integer integer : idList) {
    this.deleteById(integer);
    }
    Map responseMap = new HashMap();
    responseMap.put("code", 200);
    responseMap.put("msg", "删除成功");
    return responseMap;
    }


// 改
    // 修改订单
    @RequestMapping("/update")
    public Map update(@RequestBody Customer  Customer) {
    Map map = new HashMap();
    int i = CustomerService.updateByPrimaryKeySelective( Customer);
    if (i > 0) {
    map.put("code", 200);
    map.put("msg", "修改成功");
    return map;
    } else {
    map.put("code", 400);
    map.put("msg", "修改失败,检查网络再来一次");
    return map;
    }
    }

// 查--未分页
    // 全查
    @RequestMapping("/selectAll")
    public Map selectAll(){
    List<Customer> Customers =  CustomerService.selectByExample(null);
        Map responseMap = new HashMap();
        responseMap.put("code", 0);
        responseMap.put("msg", "查询成功");
        responseMap.put("Customers", Customers);
        return responseMap;
        }

// 查-- 查询+自身对象的查询 + 分页
// 分页查询
    @RequestMapping("/selectAllByPage")
    public Map selectAllByPage(Customer Customer, @RequestParam(value = "page", defaultValue = "1", required = true) Integer page,
    @RequestParam(value = "limit", required = true) Integer pageSize) {
    // 调用service 层   , 适用于 单表!!!
    PageHelper.startPage(page, pageSize);
    CustomerExample example = new CustomerExample();
    CustomerExample.Criteria criteria = example.createCriteria();

//    if (null!=Customer.getYYYYYYYY()&&!"".equals(Customer.getYYYYYYY())){
//         criteria.andPhoneEqualTo(Customer.getPhone());   // 1
//    }

    List<Customer> CustomersList = CustomerService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(CustomersList);
        long total = pageInfo.getTotal();
        Map responseMap = new HashMap();
        responseMap.put("code", 0);
        responseMap.put("msg", "查询成功");
        responseMap.put("pageInfo", pageInfo);
        responseMap.put("count", total);
        return responseMap;
        }

    }
