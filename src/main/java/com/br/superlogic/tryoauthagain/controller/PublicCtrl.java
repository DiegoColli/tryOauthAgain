/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.superlogic.tryoauthagain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */
@RestController
@RequestMapping("public")
public class PublicCtrl {
 
        @RequestMapping(value = {"/", "/test", "/index"}, method = RequestMethod.GET)
    public String show() {

        return "PUBLIC";
    }
    
}
