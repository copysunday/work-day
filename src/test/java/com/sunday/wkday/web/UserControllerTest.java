package com.sunday.wkday.web;

import com.alibaba.fastjson.JSON;
import com.sunday.wkday.vo.RegisterReqVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @PostConstruct
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(((request, response, chain) -> {
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        })).build();
    }

    @Test
    public void testLoginAndRegister() throws Exception {
        RegisterReqVO req = new RegisterReqVO();
        req.setAvatarUrl("123");
        req.setUserToken("123");
        req.setUserName("小明");
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(req)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(o -> System.out.println(o.getResponse().getContentAsString()))
//                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
        ;
    }

}
