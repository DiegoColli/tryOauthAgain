/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.superlogic.tryoauthagain.service;

import com.br.superlogic.tryoauthagain.model.Foo;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface FooService {

    Foo findFooById(long id);

    Foo findFooByName(String name);

    void saveFoo(Foo user);

    void updateFoo(Foo user);

    void deleteFoosById(long id);

    List<Foo> findAllFoos();

    void deleteAlFoos();

    public boolean isFooExist(Foo user);

}
