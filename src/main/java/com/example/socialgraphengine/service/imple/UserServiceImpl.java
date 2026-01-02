package com.example.socialgraphengine.service.imple;
import com.example.socialgraphengine.dto.response.PaginationResponse;
import com.example.socialgraphengine.dto.request.UserCreateRequest;
import com.example.socialgraphengine.dto.request.UserUpdateRequest;
import com.example.socialgraphengine.dto.request.PaginationRequest;
import com.example.socialgraphengine.dto.response.UserResponse;
import com.example.socialgraphengine.exception.ErrorCode;
import com.example.socialgraphengine.exception.ResourceNotFoundException;
import com.example.socialgraphengine.mapper.UserMapper;
import com.example.socialgraphengine.model.User;
import com.example.socialgraphengine.repository.UserRepository;
import com.example.socialgraphengine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        return userRepository.save(user);
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = getUser(id);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUserById(Long id, UserUpdateRequest request) {
        User user = getUser(id);
        userMapper.updateUserFromDto(user, request);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
    }


    // ====== Helper Methods ===========
    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public ResponseEntity<PaginationResponse<User>> searchByQuery(String query, PaginationRequest request) {


        Page<User> users = userRepository.searchUsers(query, request.toPageable());
        return ResponseEntity.ok(
                new PaginationResponse.PageMetadata().of(users)
        );

    }
}
