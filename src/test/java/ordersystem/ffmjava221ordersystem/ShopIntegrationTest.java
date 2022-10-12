package ordersystem.ffmjava221ordersystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import ordersystem.ffmjava221ordersystem.shop.model.ProductToAdd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShopIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addProduct() throws Exception {
        //given
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                        {
                                            "productNumber": 11,
                                            "name": "Eis",
                                            "price": 4.99
                                        }
                                        """))
                                .andExpect(status().isOk())
                                .andReturn().getResponse().getContentAsString();

        ProductToAdd Product = objectMapper.readValue(content, ProductToAdd.class);
    }
}