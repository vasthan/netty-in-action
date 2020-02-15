package com.adc.thrift;

import org.apache.thrift.TException;

public class UserServiceImpl implements UserService.Iface {

    @Override
    public User getByUsername(String username) throws UserException, TException {
        User user = new User();
        user.setUsername(username);
        user.setAge(20);
        user.setMarried(false);
        return user;
    }

    @Override
    public void save(User user) throws UserException, TException {
        System.out.println(user.toString());
    }
}
