package kz.bitlab.redis.middle03redis.controller;

import kz.bitlab.redis.middle03redis.model.Item;
import kz.bitlab.redis.middle03redis.service.ItemService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/{id}")
    public Item getItem(@PathVariable(name = "id") Long id) {
        return itemService.getItem(id);
    }

    @PutMapping()
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable(name = "id") Long id) {
        itemService.deleteItem(id);
    }

}
