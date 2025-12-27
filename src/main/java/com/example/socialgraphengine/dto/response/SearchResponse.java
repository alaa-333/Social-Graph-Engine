package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.dto.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private List<PostResponse> posts;
    private List<CommentResponse> comments;
    private List<AccountRequest> accounts;
}
