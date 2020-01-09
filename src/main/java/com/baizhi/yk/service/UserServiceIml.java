package com.baizhi.yk.service;
import com.baizhi.yk.dao.UserDao;
import com.baizhi.yk.entity.MapDto;
import com.baizhi.yk.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceIml implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public Integer selectSex(String sex, Integer day) {
        Integer integer = userDao.selectSex(sex, day);
        return integer;
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    //地图
    public List<MapDto> selectMap(String sex){
        List<MapDto> mapDtos = userDao.selectMap(sex);
        return mapDtos;
    }

    //查一个
    public User selectOne(String phone){
        User user = new User();
        user.setPhone(phone);
        User user1 = userDao.selectOne(user);
        return user1;
    }
//    //添加
//    public void insertUser(User user){
//        userDao.insert(user);
//    }

    //修改
    public void updateUser(User user){
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delect(String[] id) {
        userDao.deleteByIdList(Arrays.asList(id));
    }

    @Override
    public Map showAllUser(Integer page, Integer rows) {
        HashMap hashMap = new HashMap();
        int i = userDao.selectCount(null);
        Integer total = i%rows==0?i/rows:i/rows+1;
        List<User> users = userDao.selectByRowBounds(null, new RowBounds((page - 1) * rows,rows));
        hashMap.put("records",i);
        hashMap.put("total",total);
        hashMap.put("rows",users);
        hashMap.put("page",page);
        return hashMap;
    }
}
