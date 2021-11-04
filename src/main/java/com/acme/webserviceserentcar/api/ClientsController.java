package com.acme.webserviceserentcar.api;

import com.acme.webserviceserentcar.domain.service.ClientService;
import com.acme.webserviceserentcar.mapping.ClientMapper;
import com.acme.webserviceserentcar.resource.ClientResource;
import com.acme.webserviceserentcar.resource.CreateClientResource;
import com.acme.webserviceserentcar.resource.UpdateClientResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {
    private final ClientService clientService;
    private final ClientMapper mapper;

    public ClientsController(ClientService clientService, ClientMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Clients", description = "Get All Clients on pages", tags = {"Clients"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Clients returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public Page<ClientResource> getAllClients(Pageable pageable) {
        return mapper.modelListToPage(clientService.getAll(), pageable);
    }

    @Operation(summary = "Get Client By Id", description = "Get Client by Id", tags = {"Clients"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("{clientId}")
    public ClientResource getClientById(@PathVariable Long clientId) {
        return mapper.toResource(clientService.getById(clientId));
    }

    @Operation(summary = "Create Client", description = "Create Client", tags = {"Clients"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ClientResource createClient(@Valid @RequestBody CreateClientResource request) {
        return mapper.toResource(clientService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update Client", description = "Update Client", tags = {"Clients"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("{clientId}")
    public ClientResource updateClient(@PathVariable Long clientId, @Valid @RequestBody UpdateClientResource request) {
        return mapper.toResource(clientService.update(clientId, mapper.toModel(request)));
    }

    @Operation(summary = "Update Plan Client", description = "Update Plan Client", tags = {"Clients"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client Plan updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("{clientId}/plan/{planId}")
    public ClientResource updatePlanClient(@PathVariable Long clientId, @PathVariable Long planId) {
        return mapper.toResource(clientService.updatePlan(clientId, planId));
    }

    @Operation(summary = "Delete Plan Client", description = "Delete Plan Client", tags = {"Clients"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client Plan deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientService.delete(clientId);
    }
}