/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.superlogic.tryoauthagain;

/**
 *
 * @author Diego
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Diego
 * Just the RootConfigClass
 * Scan all beans
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.br.superlogic.tryoauthagain"})
public class App {

}