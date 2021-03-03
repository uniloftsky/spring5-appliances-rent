package com.uniloftsky.springframework.spring5appliancesrent.bootstrap;

import com.uniloftsky.springframework.spring5appliancesrent.model.*;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.RoleRepository;
import com.uniloftsky.springframework.spring5appliancesrent.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        User user = loadUsers(roles);

        List<Type> types = loadTypes();

        List<Category> categories1 = loadFirstCategories(types);

        List<Category> categories2 = loadSecondCategories(types);

        loadItems(user, categories1, categories2);
    }

    private void loadItems(User user, List<Category> categories1, List<Category> categories2) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(categories1.get(0), user, "name", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), user, "name1", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), user, "name2", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), user, "name3", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", false));
        items.add(new Item(categories1.get(0), user, "name4", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), user, "name5", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories1.get(0), user, "name6", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), user, "name7", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), user, "name8", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), user, "name9", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), user, "name10", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), user, "name11", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), user, "name12", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));
        items.add(new Item(categories2.get(0), user, "name13", "img", "location", new BigDecimal("2.0"), LocalDate.now(), "desc", true));

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

    private User loadUsers(List<Role> roles) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User("login", "123456", "380971279332", "uniloftsky@gmail.com", "Anton", "Kulyk", LocalDate.now());
        String password = encoder.encode("123456");
        user.setPassword(password);
        user.getRoles().add(roles.get(1));
        user.getRoles().add(roles.get(0));

        User user1 = new User("login1", "123456", "380971279332", "uniloftsky@gmail.com", "Anton", "Kulyk", LocalDate.now());
        String password1 = encoder.encode("123456");
        user1.setPassword(password1);
        user1.getRoles().add(roles.get(0));

        userService.save(user);
        userService.save(user1);
        return user;
    }

    private List<Role> loadRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        roles.add(new Role("ADMIN"));
        roleRepository.saveAll(roles);
        return roles;
    }

}
