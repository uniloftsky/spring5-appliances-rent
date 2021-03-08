package com.uniloftsky.springframework.spring5appliancesrent.bootstrap;

import com.uniloftsky.springframework.spring5appliancesrent.model.*;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.RoleRepository;
import com.uniloftsky.springframework.spring5appliancesrent.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final RentingService rentingService;
    private final TypeService typeService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public DataLoader(ItemService itemService, CategoryService categoryService, RentingService rentingService, TypeService typeService, UserService userService, RoleRepository roleRepository) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.rentingService = rentingService;
        this.typeService = typeService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        dataLoading();
    }

    public void dataLoading() {

        List<Role> roles = loadRoles();

        List<User> users = loadUsers(roles);

        List<Type> types = loadTypes();

        List<Category> categories1 = loadFirstCategories(types);

        List<Category> categories2 = loadSecondCategories(types);

        loadItems(users, categories1, categories2);
    }

    private void loadItems(List<User> userList, List<Category> categories1, List<Category> categories2) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(categories1.get(0), userList.get(0), "name", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), userList.get(0), "name1", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), userList.get(0), "name2", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), userList.get(0), "name3", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", false));
        items.add(new Item(categories1.get(0), userList.get(1), "name4", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), userList.get(1), "name5", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), userList.get(1), "name6", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), userList.get(2), "name7", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), userList.get(2), "name8", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), userList.get(2), "name9", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), userList.get(3), "name10", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), userList.get(3), "name11", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), userList.get(0), "name12", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), userList.get(0), "name13", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));

        itemService.saveAll(items);
    }

    private List<Category> loadSecondCategories(List<Type> types) {
        List<Category> categories2 = new ArrayList<>();
        categories2.add(new Category(types.get(1), "Комп'ютери"));
        categories2.add(new Category(types.get(1), "Телефони"));
        categories2.add(new Category(types.get(1), "Смартфони"));
        categories2.add(new Category(types.get(1), "Телевізори"));
        categories2.add(new Category(types.get(1), "Ігрові консолі"));

        categoryService.saveAll(categories2);
        return categories2;
    }

    private List<Category> loadFirstCategories(List<Type> types) {
        List<Category> categories1 = new ArrayList<>();
        categories1.add(new Category(types.get(0), "Холодильники"));
        categories1.add(new Category(types.get(0), "Пральні машинки"));
        categories1.add(new Category(types.get(0), "Кондиціонери"));
        categories1.add(new Category(types.get(0), "Мультиварки"));
        categories1.add(new Category(types.get(0), "Пилососи"));
        categories1.add(new Category(types.get(0), "Бойлери"));
        categories1.add(new Category(types.get(0), "Морозильні камери"));
        categories1.add(new Category(types.get(0), "Вбудовані духові шафи"));
        categories1.add(new Category(types.get(0), "Конвектори"));
        categories1.add(new Category(types.get(0), "Витяжки"));

        categoryService.saveAll(categories1);
        return categories1;
    }

    private List<Type> loadTypes() {
        List<Type> types = new ArrayList<>();
        types.add(new Type("Побутова техніка"));
        types.add(new Type("Високотехнологічна техніка"));

        typeService.saveAll(types);
        return types;
    }

    private List<User> loadUsers(List<Role> roles) {
        List<User> userList = new ArrayList<>();
        User user = new User("login", "123456", "380971279332", "uniloftsky@gmail.com", "Anton", "Kulyk", LocalDate.now());
        user.getRoles().add(roles.get(1));
        user.getRoles().add(roles.get(0));

        User user1 = new User("login1", "123456", "380971279332", "uniloftsky@gmail.com", "Anton", "Kulyk", LocalDate.now());
        user1.getRoles().add(roles.get(0));

        User user2 = new User("login2", "123456", "380971279332", "uniloftsky@gmail.com", "Anton", "Kulyk", LocalDate.now());
        user1.getRoles().add(roles.get(0));

        User user3 = new User("login3", "123456", "380971279332", "uniloftsky@gmail.com", "Anton", "Kulyk", LocalDate.now());
        user1.getRoles().add(roles.get(0));

        userService.save(user);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }

    private List<Role> loadRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        roles.add(new Role("ADMIN"));
        roleRepository.saveAll(roles);
        return roles;
    }

}
