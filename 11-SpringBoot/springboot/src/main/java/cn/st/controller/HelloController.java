package cn.st.controller;

import cn.st.pojo.User;
import cn.st.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private DataSource dataSource;

    @Value("${sttest.name}")
    private String name;

    @Value("${sttest.age}")
    private String age;

//    @Value("${sttest.hobby}")
    @Value("#{'${sttest.hobby}'.split(',')}")
    private String[] hobbys;

    @Autowired
    private UserService userService;

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/user/{id}")
    public User queryById(@PathVariable Long id){
        return userService.queryById(id);
    }

    @GetMapping("hello")
    public String hello(){
        System.out.println("dataSource:" + dataSource);
        System.out.println("name:"+name);
        System.out.println("age:"+age);


        System.out.println("hobby:"+hobbys);
        for (String hobby: hobbys) {
            System.out.println(hobby);
        }
        return  "Hello, Spring";
    }
}
