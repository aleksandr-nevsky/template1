package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {


    @Autowired
    MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void whenAnonymousAccessLogin_thenOk() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void whenAnonymousAccessRestrictedEndpoint_thenIsUnauthorized() throws Exception {
        mockMvc.perform(get("/hello-controller/hello"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails()
    public void whenUserAccessUserSecuredEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/user/hello"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails()
    public void whenUserAccessAdminSecuredEndpoint_thenIsForbidden() throws Exception {
        mockMvc.perform(get("/admin/hello"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void whenAdminAccessUserEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/user/hello"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void whenAdminAccessAdminSecuredEndpoint_thenIsOk() throws Exception {
        mockMvc.perform(get("/admin/hello"))
                .andExpect(status().isOk());
    }

}
