package com.myc.boot;

import com.myc.boot.domain.Tree;
import com.myc.boot.domain.User;
import com.myc.boot.mapper.MenuMapper;
import com.myc.boot.mapper.TreeMapper;
import com.myc.boot.mapper.UserMapper;
import com.myc.boot.quartz.HelloJob;
import com.myc.boot.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class Boot2ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TreeMapper treeMapper;


    @Test
    void test3() throws Exception {
        String token = JwtUtil.createJWT("123");
        String jwtStr = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiYThmZmYwNGRmMTA0MGU4YmFmZWU5MmJkZWMwZGE2YyIsInN1YiI6IjEyMyIsImlzcyI6InNnIiwiaWF0IjoxNzAwNzQ2NzM4LCJleHAiOjE3MDA3NTAzMzh9.uiFXFNX5H46MpE2t_IBabF-k6zJ5qCGVUSHSe4HHJRA";
        System.out.println(token);
        Claims claims = JwtUtil.parseJWT(jwtStr);
        System.out.println(claims.getSubject());
    }

    @Test
    void test2() {

        String encode = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(encode2);
        boolean matches = passwordEncoder.matches("123456", encode);
        System.out.println(matches);
    }

    @Test
    void test() {

        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void selectMenuPermsByUserId() {
        List<String> strings = menuMapper.selectMenuPermsByUserId(3L);
        System.out.println(strings);
    }

    @Test
    void testTree() {
        // 所有值
        List<Tree> trees = treeMapper.selectList(null);

        // 所有省
        List<Tree> collectByOne = trees.stream().filter(tree -> tree.getPid() == null).collect(Collectors.toList());

        // 所有市
        List<Tree> collectByTwo = new ArrayList<>();

        for (Tree tree : collectByOne) {
            List<Tree> collect = trees.stream().filter(t -> t.getPid() != null && t.getPid().equals(tree.getId())).collect(Collectors.toList());
            collectByTwo.addAll(collect);
        }

        // 所有区
        List<Tree> collectByThree = new ArrayList<>();

        for (Tree tree : collectByTwo) {
            List<Tree> collect = trees.stream().filter(t -> t.getPid() != null && t.getPid().equals(tree.getId())).collect(Collectors.toList());
            collectByThree.addAll(collect);
        }


        for (Tree one : collectByOne) {
            // 获取省下的市
            List<Tree> collect = collectByTwo.stream().filter(t -> t.getPid() != null && t.getPid().equals(one.getId())).collect(Collectors.toList());

            // collect中是否存在name以同级别市结尾的
            boolean b = collect.stream().anyMatch(t -> t.getName().endsWith("同级别市"));

            if (!collect.isEmpty() && !b) {
                // 有值，添加市
                Tree tree = new Tree();
                tree.setPid(one.getId());
                tree.setName(collect.get(0).getName() + "同级别市");
                treeMapper.insertTree(tree);
            }
        }

        for (Tree two : collectByTwo) {
            // 获取市下的区
            List<Tree> collect = collectByThree.stream().filter(t -> t.getPid() != null && t.getPid().equals(two.getId())).collect(Collectors.toList());

            // collect中是否存在name以同级别区结尾的
            boolean b = collect.stream().anyMatch(t -> t.getName().endsWith("同级别区"));


            if (!collect.isEmpty() && !b) {
                // 有值，添加区
                Tree tree = new Tree();
                tree.setPid(two.getId());
                tree.setName(collect.get(0).getName() + "同级别区");
                treeMapper.insertTree(tree);
            }
        }

    }

    @Test
    void testQuartz() throws SchedulerException {

        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();

        defaultScheduler.start();

        // jobDetail
        JobDetail build = JobBuilder.newJob(HelloJob.class).build();

        // trigger
        SimpleTrigger build1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        defaultScheduler.scheduleJob(build, build1);

        // sleep 10分钟
        try {
            Thread.sleep(1000 * 60 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        defaultScheduler.shutdown();
    }

}
