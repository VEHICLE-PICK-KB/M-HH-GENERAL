package app.testcases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.Mockito;
import app.controller.FlightController;
import app.model.AppUserRepository;
import app.model.Flight;
import app.model.FlightRepository;
import app.model.TypeRepository;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(FlightController.class)
public class RestMethodTesting {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private  CommandLineRunner flightTest;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private TypeRepository categoryRepository;
    
    @MockBean
    private AppUserRepository appuserRepository;

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void testFlightListPage() throws Exception {
        
        Mockito.when(flightRepository.findAll()).thenReturn(Arrays.asList(new Flight("Flight X", null, 0, null, null, null), new Flight("Flight Y", null, 0, null, null, null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/flightlist"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("flightlist"));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void testFlightListRest() throws Exception {
        
        Mockito.when(flightRepository.findAll()).thenReturn(Arrays.asList(new Flight("Flight X", null, 0, null, null, null), new Flight("Flight Y", null, 0, null, null, null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/flights"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void testFindFlightRest() throws Exception {
        
        Long flightId = 1L;
        Flight flight = new Flight("Test Flight", null, 0, null, null, null);
        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        mockMvc.perform(MockMvcRequestBuilders.get("/flight/{id}", flightId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.number").value("Test Flight"));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void testAddFlightPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("addflight"));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void testSaveFlight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/save"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) 
            .andExpect(MockMvcResultMatchers.redirectedUrl("/flightlist"));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void testDeleteFlight() throws Exception {
        Long flightId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/delete/{id}", flightId))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) 
            .andExpect(MockMvcResultMatchers.redirectedUrl("/flightlist"));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void testEditFlightPage() throws Exception {
        Long flightId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/edit/{id}", flightId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("editflight"));
    }

    
}