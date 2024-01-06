package com.tonyydl.springbootmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonyydl.springbootmall.data.dto.UserLoginRequestDTO;
import com.tonyydl.springbootmall.data.dto.UserRegisterRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 註冊新帳號
    @Test
    public void register_success() throws Exception {
        UserRegisterRequestDTO userRegisterRequestDTO = UserRegisterRequestDTO.builder()
                .email("test1@gmail.com")
                .password("123")
                .build();

        String json = objectMapper.writeValueAsString(userRegisterRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.user_id", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo("test1@gmail.com")))
                .andExpect(jsonPath("$.created_date", notNullValue()))
                .andExpect(jsonPath("$.last_modified_date", notNullValue()));

        // 檢查資料庫中的密碼不為明碼
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.user_id", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo("test1@gmail.com")))
                .andExpect(jsonPath("$.created_date", notNullValue()))
                .andExpect(jsonPath("$.last_modified_date", notNullValue()));
    }

    @Test
    public void register_invalidEmailFormat() throws Exception {
        UserRegisterRequestDTO userRegisterRequestDTO = UserRegisterRequestDTO.builder()
                .email("3gd8e7q34l9")
                .password("123")
                .build();

        String json = objectMapper.writeValueAsString(userRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void register_emailAlreadyExist() throws Exception {
        // 先註冊一個帳號
        UserRegisterRequestDTO userRegisterRequestDTO = UserRegisterRequestDTO.builder()
                .email("test2@gmail.com")
                .password("123")
                .build();

        String json = objectMapper.writeValueAsString(userRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));

        // 再次使用同個 email 註冊
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    // 登入
    @Test
    public void login_success() throws Exception {
        // 先註冊新帳號
        UserRegisterRequestDTO userRegisterRequestDTO = UserRegisterRequestDTO.builder()
                .email("test3@gmail.com").password("123").build();

        register(userRegisterRequestDTO);

        // 再測試登入功能
        UserLoginRequestDTO userLoginRequestDTO = UserLoginRequestDTO.builder()
                .email(userRegisterRequestDTO.getEmail())
                .password(userRegisterRequestDTO.getPassword())
                .build();

        String json = objectMapper.writeValueAsString(userLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.user_id", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(userRegisterRequestDTO.getEmail())))
                .andExpect(jsonPath("$.created_date", notNullValue()))
                .andExpect(jsonPath("$.last_modified_date", notNullValue()));
    }

    @Test
    public void login_wrongPassword() throws Exception {
        // 先註冊新帳號
        UserRegisterRequestDTO userRegisterRequestDTO = UserRegisterRequestDTO.builder()
                .email("test4@gmail.com")
                .password("123")
                .build();

        register(userRegisterRequestDTO);

        // 測試密碼輸入錯誤的情況
        UserLoginRequestDTO userLoginRequestDTO = UserLoginRequestDTO.builder()
                .email(userRegisterRequestDTO.getEmail())
                .password("unknown")
                .build();

        String json = objectMapper.writeValueAsString(userLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_invalidEmailFormat() throws Exception {
        UserLoginRequestDTO userLoginRequestDTO = UserLoginRequestDTO.builder()
                .email("hkbudsr324")
                .password("123")
                .build();

        String json = objectMapper.writeValueAsString(userLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_emailNotExist() throws Exception {
        UserLoginRequestDTO userLoginRequestDTO = UserLoginRequestDTO.builder()
                .email("unknown@gmail.com")
                .password("123")
                .build();

        String json = objectMapper.writeValueAsString(userLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    private void register(UserRegisterRequestDTO userRegisterRequestDTO) throws Exception {
        String json = objectMapper.writeValueAsString(userRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }
}