package com.ewolff.microservice.catalog.cdc;

import com.ewolff.microservice.catalog.Item;
import com.ewolff.microservice.catalog.ItemRepository;
import com.ewolff.microservice.catalog.SpringRestDataConfig;
import com.ewolff.microservice.catalog.web.CatalogController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Customer test class.
 *
 * @author chakrav
 * @since 2019-08-20
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringRestDataConfig.class, loader = AnnotationConfigContextLoader.class)
public class CatalogControllerTest {
    @Mock
    private ItemRepository itemRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new CatalogController(itemRepository))
                .build();

        when(itemRepository.findById(anyString())).thenReturn(Optional.of(dummyItem()));
        when(itemRepository.findAll()).thenReturn(Arrays.asList(dummyItem()));
    }

    @Test
    public void testFindById() throws Exception {
        mockMvc.perform(get("/1.html")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("item"));
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/list.html")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("itemlist"));
    }

    @Test
    public void testForm() throws Exception {
        mockMvc.perform(get("/form.html")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("item"));
    }

    @Test
    public void testFormPost() throws Exception {
        mockMvc.perform(post("/form.html")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));
    }

    @Test
    public void testFindByIdPut() throws Exception {
        mockMvc.perform(put("/1.html")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));
    }

    @Test
    public void testSearchForm() throws Exception {
        mockMvc.perform(get("/searchForm.html")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("searchForm"));
    }

    @Test
    public void testSearchFormByName() throws Exception {
        mockMvc.perform(get("/searchByName.html")
                .param("query", "testparam")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("itemlist"));
    }

    @Test
    public void testFindByIdDelete() throws Exception {
        mockMvc.perform(delete("/1.html")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));
    }

    private Item dummyItem() {
        return new Item();
    }

}
