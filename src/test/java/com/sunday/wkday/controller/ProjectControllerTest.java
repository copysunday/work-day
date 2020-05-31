package com.sunday.wkday.controller;

import com.alibaba.fastjson.JSON;
import com.sunday.wkday.vo.GetProjectListReqVO;
import com.sunday.wkday.vo.JoinProjectReqVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootTest
public class ProjectControllerTest {
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
    public void getJoinProject() throws Exception {
        JoinProjectReqVO joinProjectReqVO = new JoinProjectReqVO();
        joinProjectReqVO.setProjectNo("123");
        joinProjectReqVO.setUserToken("1234");
        mockMvc.perform(MockMvcRequestBuilders.post("/pj/joinProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(joinProjectReqVO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(o -> System.out.println(o.getResponse().getContentAsString()))
//                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
                ;
    }

    @Test
    public void getGetList() throws Exception {
        GetProjectListReqVO req = new GetProjectListReqVO();
        req.setStep(2);
        req.setUserToken("1234");
        mockMvc.perform(MockMvcRequestBuilders.get("/pj/getProjectList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(req)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(o -> System.out.println(o.getResponse().getContentAsString()))
//                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
        ;
    }

}
