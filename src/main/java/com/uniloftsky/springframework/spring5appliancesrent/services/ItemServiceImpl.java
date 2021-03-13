package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.exceptions.NotFoundException;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemPage;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemSearchCriteria;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.ItemRepository;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.item.ItemCriteriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final Comparator<Item> comparatorAscById = new ItemAscComparatorById();
    private final Comparator<Item> comparatorDescById = new ItemDescComparatorById();
    private final UserService userService;
    private final ItemCriteriaRepository itemCriteriaRepository;
    private final ImageService imageService;

    public ItemServiceImpl(ItemRepository itemRepository, UserService userService, ItemCriteriaRepository itemCriteriaRepository, ImageService imageService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.itemCriteriaRepository = itemCriteriaRepository;
        this.imageService = imageService;
    }

    @Override
    public Set<Item> findAll() {
        return new HashSet<>(itemRepository.findAll());
    }

    @Override
    public Set<Item> findAllActive() {
        return findAllSortedById(comparatorAscById).stream().filter(Item::isActive).collect(toCollection(() -> new TreeSet<>(comparatorAscById)));
    }

    @Override
    public TreeSet<Item> findAllSortedById(Comparator<Item> comparator) {
        TreeSet<Item> sortedItems = new TreeSet<>(comparator);
        itemRepository.findAll().stream().iterator().forEachRemaining(sortedItems::add);
        return sortedItems;
    }

    @Override
    public TreeSet<Item> getLimitedCountPosts(Comparator<Item> comparator, int count) {
        return findAllSortedById(comparator).stream().limit(count).collect(toCollection(() -> new TreeSet<>(comparator)));
    }

    @Override
    public TreeSet<Item> getLastPostsIndexPage() {
        TreeSet<Item> treeSet = findAllSortedById(comparatorDescById);
        List<Item> preLastThree = (new ArrayList<>(treeSet)).subList(1, 4);
        return preLastThree.stream()
                .collect(toCollection(() -> new TreeSet<>(comparatorDescById)));
    }

    @Override
    public Set<Item> getSimilarPosts() {
        return getLimitedCountPosts(comparatorDescById, 4);
    }

    @Override
    public Page<Item> getCatalogItems(ItemPage itemPage, ItemSearchCriteria itemSearchCriteria) {
        return itemCriteriaRepository.findAllWithFilters(itemPage, itemSearchCriteria);
    }

    @Override
    public Set<Item> searchBox(String name, String category, String type, String desc) {
        return itemRepository.findAllByNameIsLikeOrCategory_CategoryNameIsLikeOrCategory_Type_TypeNameIsLikeOrDescriptionIsLike("%" + name + "%", "%" + category + "%", "%" + type + "%", "%" + desc + "%");
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Оголошення з заданим ID не зайдено!");
        }
        return optional.get();
    }

    @Override
    public Item save(Item obj) {
        return itemRepository.save(obj);
    }

    @Override
    public Item save(Item obj, Authentication authentication, MultipartFile file) throws IOException {
        User user = userService.findByLogin(authentication.getName());
        obj.setUser(user);
        obj.setDate(LocalDate.now());
        imageService.setItemImage(obj, file);
        return itemRepository.save(obj);
    }

    @Override
    public List<Item> saveAll(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    @Override
    public void delete(Item obj) {
        Optional<Item> itemOptional = itemRepository.findById(obj.getId());
        if(itemOptional.isEmpty()) {
            throw new NotFoundException("Оголошення з заданим ID не знайдено!");
        }
        Item foundItem = itemOptional.get();
        User user = foundItem.getUser();
        user.getItems().remove(foundItem);
        userService.save(user);
        itemRepository.delete(foundItem);
        itemRepository.delete(obj);
    }
}
