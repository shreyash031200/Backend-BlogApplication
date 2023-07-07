module blogapplication {
    requires java.persistence;
    requires java.validation;
    requires jjwt;
    requires modelmapper;
    requires org.apache.tomcat.embed.core;
    requires spring.aspects;
    requires spring.beans;
    requires spring.boot.autoconfigure;
//    requires spring.boot.devtools;
    requires spring.context;
    requires spring.core;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.security.config;
    requires spring.security.core;
    requires spring.security.crypto;
    requires spring.security.web;
    requires spring.web;
    requires spring.webmvc;
    requires spring.boot;
    requires lombok;
    requires java.desktop;
}