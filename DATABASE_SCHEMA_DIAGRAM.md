# 🗄️ Database Schema

## Entity Relationships

```mermaid
erDiagram
    USER ||--|| ACCOUNT : has
    USER {
        Long id PK
        String email UK
        String password
        Boolean enabled
        Boolean accountNonLocked
        LocalDateTime createdAt
        LocalDateTime updatedAt
    }
    
    ACCOUNT ||--o{ POST : creates
    ACCOUNT ||--o{ COMMENT : writes
    ACCOUNT ||--o{ REACT : performs
    ACCOUNT ||--o{ FRIENDSHIP : has
    ACCOUNT ||--o{ FOLLOW : initiates
    ACCOUNT ||--o{ FRIEND_REQUEST : sends
    ACCOUNT ||--o{ FRIEND_REQUEST : receives
    ACCOUNT ||--o{ NOTIFICATION : receives
    ACCOUNT ||--o{ MESSAGE : sends
    ACCOUNT ||--o{ MESSAGE : receives
    ACCOUNT ||--o{ ACTION : performs
    ACCOUNT ||--|| ACCOUNT_DETAILS : has
    
    ACCOUNT {
        Long id PK
        Long userId FK "UK"
        ProfileType profileType
        String job
        String profilePictureUrl
        String backgroundPictureUrl
        LocalDateTime createdAt
        LocalDateTime updatedAt
        Boolean deleted
    }
    
    POST ||--o{ COMMENT : has
    POST ||--o{ REACT : receives
    POST {
        Long id PK
        Long accountId FK
        String content "CLOB"
        String mediaUrl
        MediaType mediaType
        Integer commentsCount
        Integer reactsCount
        LocalDateTime createdAt
        Boolean deleted
    }
    
    COMMENT {
        Long id PK
        Long postId FK
        Long accountId FK
        String text
        LocalDateTime createdAt
        Boolean deleted
    }
    
    REACT {
        Long id PK
        Long postId FK
        Long accountId FK "UK(accountId,postId)"
        ReactType reactType
        LocalDateTime createdAt
    }
    
    FRIENDSHIP {
        Long id PK
        Long accountId FK "UK(accountId,friendId)"
        Long friendId FK
        Boolean mutual
        LocalDateTime createdAt
        Boolean deleted
    }
    
    FOLLOW {
        Long id PK
        Long followerId FK "UK(followerId,followingId)"
        Long followingId FK
        LocalDateTime createdAt
        Boolean deleted
    }
    
    FRIEND_REQUEST {
        Long id PK
        Long senderId FK
        Long receiverId FK
        RequestStatus status
        Boolean read
        LocalDateTime createdAt
        Boolean deleted
    }
    
    NOTIFICATION {
        Long id PK
        Long accountId FK
        NotificationType type
        String message
        String link
        Boolean read
        LocalDateTime createdAt
        Boolean deleted
    }
    
    MESSAGE {
        Long id PK
        Long senderId FK
        Long receiverId FK
        String content "TEXT"
        Boolean read
        String attachmentUrl
        LocalDateTime createdAt
        Boolean deleted
    }
    
    ACTION {
        Long id PK
        Long accountId FK
        ActionType actionType
        String details "TEXT"
        String ipAddress
        String userAgent
        LocalDateTime createdAt
    }
    
    ACCOUNT_DETAILS ||--o{ WORK_EXPERIENCE : contains
    ACCOUNT_DETAILS {
        Long id PK
        Long accountId FK
        String firstName
        String lastName
        String middleName
        Date dateOfBirth
        String street
        String city
        String state
        String country
        String languages "COLLECTION"
        LocalDateTime createdAt
        Boolean deleted
    }
    
    WORK_EXPERIENCE {
        Long id PK
        Long accountDetailsId FK
        String companyName
        String title
        String companyLogo
        Date startDate
        Date endDate
        Boolean present
        String description "TEXT"
        LocalDateTime createdAt
        Boolean deleted
    }
    
    CONTACT {
        Long id PK
        Long accountId FK
        String name
        String email
        String phone
        String message
        LocalDateTime createdAt
        Boolean deleted
    }
```

## 📊 Data Flow Diagrams

### User Registration & Authentication Flow

```mermaid
sequenceDiagram
    participant Client
    participant AuthController
    participant UserService
    participant AccountService
    participant UserRepository
    participant AccountRepository
    participant PasswordEncoder
    
    Client->>AuthController: POST /api/auth/register
    AuthController->>AuthController: Validate RegisterDTO
    AuthController->>UserService: registerUser(dto)
    
    UserService->>UserService: Check email exists
    UserService->>PasswordEncoder: encode(password)
    PasswordEncoder-->>UserService: Encoded password
    
    UserService->>UserRepository: save(user)
    UserRepository-->>UserService: Saved User
    
    UserService->>AccountService: createAccount(user)
    AccountService->>AccountRepository: save(account)
    AccountRepository-->>AccountService: Saved Account
    
    AccountService-->>UserService: Account
    UserService->>UserService: Map to DTO
    UserService-->>AuthController: UserResponseDTO
    AuthController-->>Client: 201 Created
```

### Friend Request Workflow

```mermaid
sequenceDiagram
    participant Sender
    participant FriendController
    participant FriendRequestService
    participant FriendshipService
    participant NotificationService
    participant Receiver
    
    Sender->>FriendController: POST /api/friends/request
    FriendController->>FriendRequestService: sendRequest(senderId, receiverId)
    
    FriendRequestService->>FriendRequestService: Check existing request
    FriendRequestService->>FriendRequestService: Check already friends
    FriendRequestService->>FriendRequestService: Create request (PENDING)
    FriendRequestService->>NotificationService: Create notification
    NotificationService->>Receiver: FRIEND_REQUEST notification
    
    FriendRequestService-->>FriendController: RequestDTO
    FriendController-->>Sender: 201 Created
    
    Note over Receiver: User reviews request
    
    Receiver->>FriendController: PUT /api/friends/request/{id}/accept
    FriendController->>FriendRequestService: acceptRequest(requestId)
    
    FriendRequestService->>FriendRequestService: Update status (ACCEPTED)
    FriendRequestService->>FriendshipService: createFriendship(sender, receiver)
    FriendshipService->>FriendshipService: Create bidirectional friendship
    FriendshipService->>FriendshipService: Set mutual=true
    
    FriendRequestService->>NotificationService: Notify sender
    NotificationService->>Sender: REQUEST_ACCEPTED notification
    
    FriendRequestService-->>FriendController: Success
    FriendController-->>Receiver: 200 OK
```

### Post Creation & Interaction Flow

```mermaid
sequenceDiagram
    participant User
    participant PostController
    participant PostService
    participant PostRepository
    participant FollowService
    participant NotificationService
    participant Followers
    
    User->>PostController: POST /api/posts
    PostController->>PostController: Validate PostDTO
    PostController->>PostService: createPost(dto, accountId)
    
    PostService->>PostService: Process media upload
    PostService->>PostService: Set counters to 0
    PostService->>PostRepository: save(post)
    PostRepository-->>PostService: Saved Post
    
    PostService->>FollowService: getFollowers(accountId)
    FollowService-->>PostService: List of followers
    
    loop For each follower
        PostService->>NotificationService: Create notification
        NotificationService->>Followers: NEW_POST notification
    end
    
    PostService-->>PostController: PostResponseDTO
    PostController-->>User: 201 Created
    
    Note over User: Another user reacts to post
    
    participant Reactor
    Reactor->>PostController: POST /api/posts/{id}/react
    PostController->>PostService: addReact(postId, accountId, type)
    
    PostService->>PostService: Check existing react
    PostService->>PostService: Create/Update react
    PostService->>PostService: Increment reactsCount
    PostService->>PostRepository: update(post)
    
    PostService->>NotificationService: Notify post owner
    NotificationService->>User: POST_LIKED notification
    
    PostService-->>PostController: ReactDTO
    PostController-->>Reactor: 200 OK
```

### Timeline Feed Generation

```mermaid
sequenceDiagram
    participant User
    participant FeedController
    participant FeedService
    participant FollowService
    participant FriendshipService
    participant PostService
    participant Cache
    
    User->>FeedController: GET /api/feed
    FeedController->>FeedService: generateFeed(accountId, page)
    
    FeedService->>Cache: Check cached feed
    
    alt Cache Hit
        Cache-->>FeedService: Cached posts
    else Cache Miss
        FeedService->>FollowService: getFollowing(accountId)
        FollowService-->>FeedService: Following list
        
        FeedService->>FriendshipService: getFriends(accountId)
        FriendshipService-->>FeedService: Friends list
        
        FeedService->>FeedService: Combine & deduplicate IDs
        
        FeedService->>PostService: getPostsByAccounts(ids, page)
        PostService-->>FeedService: Posts list
        
        FeedService->>FeedService: Sort by createdAt DESC
        FeedService->>Cache: Store in cache
    end
    
    FeedService->>FeedService: Filter deleted posts
    FeedService->>FeedService: Map to DTOs
    FeedService-->>FeedController: Page of PostDTOs
    FeedController-->>User: 200 OK with posts
```

### Message Exchange Flow

```mermaid
sequenceDiagram
    participant Sender
    participant MessageController
    participant MessageService
    participant MessageRepository
    participant NotificationService
    participant WebSocket
    participant Receiver
    
    Sender->>MessageController: POST /api/messages
    MessageController->>MessageService: sendMessage(dto)
    
    MessageService->>MessageService: Validate sender & receiver
    MessageService->>MessageService: Process attachment
    MessageService->>MessageService: Set read=false
    MessageService->>MessageRepository: save(message)
    MessageRepository-->>MessageService: Saved Message
    
    MessageService->>NotificationService: Create notification
    NotificationService->>Receiver: MESSAGE_RECEIVED notification
    
    MessageService->>WebSocket: Emit message event
    WebSocket->>Receiver: Real-time message
    
    MessageService-->>MessageController: MessageDTO
    MessageController-->>Sender: 201 Created
    
    Note over Receiver: User opens conversation
    
    Receiver->>MessageController: GET /api/messages/conversation/{senderId}
    MessageController->>MessageService: getConversation(receiverId, senderId)
    
    MessageService->>MessageService: Get messages between users
    MessageService->>MessageService: Mark as read
    MessageService->>MessageRepository: update messages
    
    MessageService-->>MessageController: List of MessageDTOs
    MessageController-->>Receiver: 200 OK
```

### Action Logging & Audit Flow

```mermaid
sequenceDiagram
    participant User
    participant Controller
    participant Service
    participant AuditService
    participant ActionRepository
    participant Request
    
    User->>Controller: Any HTTP Request
    Controller->>Service: Business operation
    
    Service->>Service: Execute operation
    
    Service->>AuditService: logAction(accountId, actionType, details)
    
    AuditService->>Request: Extract IP address
    Request-->>AuditService: IP address
    
    AuditService->>Request: Extract User-Agent
    Request-->>AuditService: User-Agent string
    
    AuditService->>AuditService: Build Action entity
    AuditService->>ActionRepository: save(action)
    ActionRepository-->>AuditService: Saved Action
    
    AuditService-->>Service: Logged
    Service-->>Controller: Operation result
    Controller-->>User: HTTP Response
```

## 🔍 Key Design Patterns

### Soft Delete Pattern
```mermaid
flowchart TD
    A[Delete Request] --> B{Check Entity Type}
    B -->|User Content| C[Set deleted = true]
    B -->|System Data| D[Set deleted = true]
    C --> E[Set deletedAt = now]
    D --> E
    E --> F[Keep in Database]
    F --> G[Filter in Queries]
    G --> H{Recovery Needed?}
    H -->|Yes| I[Set deleted = false]
    H -->|No| J[Permanent Delete After 30 days]
```

### Counter Denormalization Pattern
```mermaid
flowchart LR
    A[User Comments on Post] --> B[Save Comment]
    B --> C[Increment Post.commentsCount]
    C --> D[Update Post]
    D --> E[Fast Count Query]
    
    F[User Reacts to Post] --> G[Save React]
    G --> H[Increment Post.reactsCount]
    H --> I[Update Post]
    I --> J[No Need for COUNT Query]
```

## 📈 Database Indexes Strategy

### Timeline Queries
```sql
-- Optimized for feed generation
CREATE INDEX idx_post_account_created ON posts(account_id, created_at DESC);

-- Optimized for follow relationships
CREATE INDEX idx_follow_follower_created ON follows(follower_id, created_at DESC);
CREATE INDEX idx_follow_following_created ON follows(following_id, created_at DESC);
```

### Social Graph Queries
```sql
-- Friendship lookups
CREATE INDEX idx_friendship_account_friend ON friendships(account_id, friend_id);
CREATE INDEX idx_friendship_friend_account ON friendships(friend_id, account_id);

-- Friend request status
CREATE INDEX idx_request_receiver_status ON friend_requests(receiver_id, status);
CREATE INDEX idx_request_sender_status ON friend_requests(sender_id, status);
```

### Notification Queries
```sql
-- Unread notifications
CREATE INDEX idx_notification_account_read ON notifications(account_id, read);
CREATE INDEX idx_notification_created ON notifications(created_at DESC);
```

### Message Queries
```sql
-- Conversation loading
CREATE INDEX idx_message_sender_receiver_created 
    ON messages(sender_id, receiver_id, created_at DESC);

-- Unread messages
CREATE INDEX idx_message_receiver_read ON messages(receiver_id, read);
```

## 🎯 Enumerations Reference

### RequestStatus
```
PENDING → ACCEPTED → Creates Friendship
   ↓
REJECTED (Terminal State)
```

### ReactType
```
LIKE (👍) | LOVE (❤️) | HAHA (😂)
WOW (😮) | SAD (😢) | ANGRY (😠)
```

### MediaType
```
IMAGE | VIDEO | AUDIO | DOCUMENT
```

### NotificationType
```
FRIEND_REQUEST | POST_LIKED | POST_COMMENTED
NEW_FOLLOWER | MENTION | MESSAGE_RECEIVED
```

### ActionType (30+ types)
```
Authentication: LOGIN, LOGOUT
Content: POST_CREATED, POST_UPDATED, POST_DELETED
Social: FRIEND_REQUEST_SENT, FRIEND_REQUEST_ACCEPTED
Profile: PROFILE_UPDATED, SETTINGS_CHANGED
Messaging: MESSAGE_SENT, MESSAGE_READ
```

## 📊 Database Statistics

| Metric | Count |
|--------|-------|
| **Total Entities** | 15 |
| **Total Relationships** | 20+ |
| **Total Indexes** | 25+ |
| **Unique Constraints** | 6 |
| **Enum Types** | 8 |
| **Soft Delete Enabled** | All Entities |

## 🚀 Performance Optimizations

- **Denormalized Counters**: `commentsCount`, `reactsCount` on posts
- **Composite Indexes**: Multi-column query optimization
- **Soft Deletes**: Data recovery without backup restoration
- **Bidirectional Relationships**: Optimized friend/follow queries
- **Read Flags**: Quick unread count calculations
- **Timestamp Indexes**: Efficient timeline generation