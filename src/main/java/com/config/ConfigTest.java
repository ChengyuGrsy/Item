package com.config;

import com.entity.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigTest {

    @Bean(name = "mM")
    public Member getO(){
        Member member=new Member();
        member.setId(1);
        member.setName("name");
        System.out.println(member.toString());
        return member;
    }
}
