package kz.bitlab.redis.middle03redis.service;

import kz.bitlab.redis.middle03redis.model.Item;
import kz.bitlab.redis.middle03redis.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CacheService cacheService;

    public ItemService(ItemRepository itemRepository, CacheService cacheService) {
        this.itemRepository = itemRepository;
        this.cacheService = cacheService;
    }

    public Item getItem(Long id) {
        final String cacheKey = "item:" + id;
        Item cachedItem = (Item) cacheService.getCacheObject(cacheKey);

        if (cachedItem != null) {
            System.out.println("return cachedItem: " + cachedItem);
            return cachedItem;
        }

        Optional<Item> optionalItem = itemRepository.findById(id);
        optionalItem.ifPresent(
                it -> cacheService.cacheObject(cacheKey, it, 60, TimeUnit.SECONDS)
        );
        return optionalItem.orElse(null);
    }

    public Item updateItem(Item item) {
        itemRepository.save(item);
        cacheService.cacheObject("item:" + item.getId(), item, 60, TimeUnit.SECONDS);
        return item;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
        cacheService.deleteCacheObject("item:" + id);
    }
}
