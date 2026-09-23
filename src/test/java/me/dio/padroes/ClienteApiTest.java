package me.dio.padroes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarEBuscarCliente() throws Exception {
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Maria","cep":"66000000"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Maria"))
                .andExpect(jsonPath("$.endereco.cidade").value("Belém"));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria"));
    }

    @Test
    void deveRejeitarCepInvalido() throws Exception {
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"João","cep":"123"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.cep").exists());
    }
}
