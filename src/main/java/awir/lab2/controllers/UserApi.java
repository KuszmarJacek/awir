package awir.lab2.controllers;

import awir.lab2.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Tag(name = "UserRestController", description = "REST endpoint responsible for the User entity")
public interface UserApi {
    @Operation(summary = "Add user", description = "This endpoint is responsible for adding a user to DB", tags = { "UserRestController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "403 FORBIDDEN. Failed to add user."),
            @ApiResponse(responseCode = "200", description = "200 OK. Added user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            })
    })
    public ResponseEntity addUser(@RequestBody User user);

    @Operation(summary = "Delete user", description = "This endpoint is responsible for removing a user from DB", tags = { "UserRestController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "403 FORBIDDEN. Failed to delete user."),
            @ApiResponse(responseCode = "200", description = "200 OK. Deleted user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            })
    })
    public ResponseEntity deleteUser(@PathVariable String name);

    @Operation(summary = "Get user", description = "This endpoint is responsible for returning a user from DB", tags = { "UserRestController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "403 FORBIDDEN. Failed to get user."),
            @ApiResponse(responseCode = "200", description = "200 OK. Returned user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            })
    })
    public ResponseEntity getUser(@PathVariable String name);

    @Operation(summary = "Update user", description = "This endpoint is responsible for updating a user in DB", tags = { "UserRestController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "403 FORBIDDEN. Failed to update user."),
            @ApiResponse(responseCode = "200", description = "200 OK. Updated user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            })
    })
    public ResponseEntity updateuser(@PathVariable String name, @RequestBody User user);
}
