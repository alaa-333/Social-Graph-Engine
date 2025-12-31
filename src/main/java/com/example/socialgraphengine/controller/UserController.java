package com.example.socialgraphengine.controller;

import com.example.socialgraphengine.dto.request.UserUpdateRequest;
import com.example.socialgraphengine.dto.response.PaginationResponse;
import com.example.socialgraphengine.dto.response.UserResponse;
import com.example.socialgraphengine.service.imple.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable(name = "id")  @Positive Long id
    )
    {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateById(
            @PathVariable(name = "id") @Positive Long id,
            @Valid UserUpdateRequest request
    )
    {
        return ResponseEntity.ok(userService.updateUserById(id , request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable(name = "id") @Positive Long id
    )
    {
       userService.deleteUserById(id);
       return ResponseEntity.noContent().build();
    }

//    @GetMapping("/search")
//    public ResponseEntity<Page<PaginationResponse>> searchByQuery(@RequestParam("query")  String query)
//    {
//        return userService.searchByQuery(query);
//    }

}
