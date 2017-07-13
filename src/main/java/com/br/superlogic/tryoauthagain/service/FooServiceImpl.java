/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.superlogic.tryoauthagain.service;

import com.br.superlogic.tryoauthagain.model.Foo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service("fooService")
public class FooServiceImpl implements FooService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Foo> foos;

    static {
        foos = populateDummyUsers();
    }

    @Override
    public List<Foo> findAllFoos() {
        return foos;
    }

    @Override
    public Foo findFooById(long id) {
        for (Foo user : foos) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Foo findFooByName(String name) {
        for (Foo user : foos) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveFoo(Foo user) {
        user.setId(counter.incrementAndGet());
        foos.add(user);
    }

    @Override
    public void updateFoo(Foo user) {
        int index = foos.indexOf(user);
        foos.set(index, user);
    }

    @Override
    public void deleteFoosById(long id) {

        for (Iterator<Foo> iterator = foos.iterator(); iterator.hasNext();) {
            Foo user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean isFooExist(Foo user) {
        return findFooByName(user.getName()) != null;
    }

    @Override
    public void deleteAlFoos() {
        foos.clear();
    }

    private static List<Foo> populateDummyUsers() {
        List<Foo> _users = new ArrayList<>();
        _users.add(new Foo(counter.incrementAndGet(), "Sam", 30, 70000));
        _users.add(new Foo(counter.incrementAndGet(), "Tom", 40, 50000));
        _users.add(new Foo(counter.incrementAndGet(), "Jerome", 45, 30000));
        _users.add(new Foo(counter.incrementAndGet(), "Silvia", 50, 40000));
        _users.add(new Foo(counter.incrementAndGet(), "GORILA", 60, 666));
        return _users;
    }

}
