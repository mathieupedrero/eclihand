package com.pedrero.eclihand.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = { IRestWebService.APPLICATION_JSON }, consumes = { IRestWebService.APPLICATION_JSON })
public abstract class AbstractWs implements IRestWebService {

}