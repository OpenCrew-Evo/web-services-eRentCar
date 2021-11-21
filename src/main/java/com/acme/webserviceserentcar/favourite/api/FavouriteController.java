package com.acme.webserviceserentcar.favourite.api;

import com.acme.webserviceserentcar.favourite.domain.service.FavouriteService;
import com.acme.webserviceserentcar.favourite.mapping.FavouriteMapper;
import com.acme.webserviceserentcar.favourite.resource.CreateFavouriteResource;
import com.acme.webserviceserentcar.favourite.resource.FavouriteResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteController {

    private final FavouriteService favouriteService;
    private final FavouriteMapper mapper;

    public FavouriteController(FavouriteService favouriteService, FavouriteMapper mapper) {
        this.favouriteService = favouriteService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<FavouriteResource> getAllFavourite(Pageable pageable) {
        return mapper.modelListToPage(favouriteService.getAll(), pageable);
    }

    @GetMapping("{favouriteId}")
    public FavouriteResource getFavouriteById(@PathVariable Long favouriteId) {
        return mapper.toResource(favouriteService.getById(favouriteId));
    }

    @PostMapping("client/{clientId}/car/{carId}")
    public FavouriteResource createFavourite(@PathVariable Long clientId,
                                             @PathVariable Long carId,
                                             @Valid @RequestBody CreateFavouriteResource request) {
        return mapper.toResource(favouriteService.create(clientId, carId, mapper.toModel(request)));
    }

    @DeleteMapping("{favouriteId}")
    public ResponseEntity<?> deleteFavourite(@PathVariable Long favouriteId) {
        return favouriteService.delete(favouriteId); }
}
