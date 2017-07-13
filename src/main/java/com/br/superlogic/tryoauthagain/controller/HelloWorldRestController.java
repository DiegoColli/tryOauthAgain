/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.superlogic.tryoauthagain.controller;

import com.br.superlogic.tryoauthagain.model.Foo;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.br.superlogic.tryoauthagain.service.FooService;

/**
 *
 * @author Diego
 */
@RestController
public class HelloWorldRestController {
 
    @Autowired
    FooService fooService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<Foo>> listAllUsers() {
        List<Foo> users = fooService.findAllFoos();
        if(users.isEmpty()){
            return new ResponseEntity<List<Foo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Foo>>(users, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Foo--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Foo> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Foo user = fooService.findFooById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Foo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Foo>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Foo--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Foo user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());
 
        if (fooService.isFooExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        fooService.saveFoo(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Foo --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Foo> updateUser(@PathVariable("id") long id, @RequestBody Foo user) {
        System.out.println("Updating User " + id);
         
        Foo currentUser = fooService.findFooById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Foo>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
         
        fooService.updateFoo(currentUser);
        return new ResponseEntity<Foo>(currentUser, HttpStatus.OK);
    }
 
    //------------------- Delete a Foo --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Foo> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Foo user = fooService.findFooById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Foo>(HttpStatus.NOT_FOUND);
        }
 
        fooService.deleteFoosById(id);
        return new ResponseEntity<Foo>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<Foo> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        fooService.deleteAlFoos();
        return new ResponseEntity<Foo>(HttpStatus.NO_CONTENT);
    }
 
}