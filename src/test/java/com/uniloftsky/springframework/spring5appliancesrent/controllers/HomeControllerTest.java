package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    UserService userService;

    @Mock
    ItemService itemService;

    @InjectMocks
    HomeController controller;

    MockMvc mockMvc;

    @Mock
    Comparator<Item> comparator;

    Set<Item> itemList = new HashSet<>();
    Set<User> usersList = new HashSet<>();
    TreeSet<Item> sortedItems = new TreeSet<>(comparator);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getIndexPage() throws Exception {
        itemList.add(new Item());
        usersList.add(new User());
        sortedItems.add(Item.builder().id(1L).build());

        given(itemService.findAll()).willReturn(itemList);
        given(itemService.findAllSortedById(any())).willReturn(sortedItems);
        given(userService.getPopularUsers()).willReturn(usersList);


        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("items", "lastPost", "popularUsers"))
                .andExpect(view().name("redirect:/catalogFilter?type.typeName=&category.categoryName="));
    }

    @Test
    void filterItems() {
    }

    @Test
    void searchBox() {
    }

    @Test
    void forbiddenHandler() {
    }

    @Test
    void getHelpPage() {
    }
}