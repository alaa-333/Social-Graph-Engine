# 🗄️ Database Schema Documentation

> **Enhanced Entity Relationship Diagram** - A comprehensive overview of the social platform database architecture

---

## 📋 Table of Contents

- [Core Authentication & Profile](#core-authentication--profile)
- [Social Graph Relationships](#social-graph-relationships)
- [Friend Requests](#friend-requests)
- [Content & Interactions](#content--interactions)
- [Profile Details](#profile-details)
- [Notifications & Messaging](#notifications--messaging)
- [Audit & Activity Tracking](#audit--activity-tracking)
- [Base Entity](#base-entity)
- [Enumerations](#enumerations)
- [Database Indexes](#database-indexes)
- [Unique Constraints](#unique-constraints)
- [Relationship Summary](#relationship-summary)

---

## 🔐 Core Authentication & Profile

```
┌─────────────────────────────────────────────────────────────────────────┐
│                         AUTHENTICATION FLOW                              │
└─────────────────────────────────────────────────────────────────────────┘

                    ┏━━━━━━━━━━━━━━━━━━━━━┓
                    ┃       USER          ┃
                    ┣━━━━━━━━━━━━━━━━━━━━━┫
                    ┃ 🔑 id (PK)          ┃
                    ┃ 📧 email (UNIQUE)   ┃
                    ┃ 🔒 password         ┃
                    ┃ ✅ enabled          ┃
                    ┃ 🔓 accountNonLocked ┃
                    ┃ 📅 createdAt        ┃
                    ┃ 🔄 updatedAt        ┃
                    ┗━━━━━━━━┳━━━━━━━━━━━┛
                             │
                             │ 1:1 Relationship
                             ▼
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃        ACCOUNT             ┃
                    ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
                    ┃ 🔑 id (PK)                 ┃
                    ┃ 🔗 user_id (FK, UNIQUE)    ┃
                    ┃ 👤 profileType             ┃
                    ┃ 💼 job                     ┃
                    ┃ 🖼️  profilePictureUrl      ┃
                    ┃ 🎨 backgroundPictureUrl    ┃
                    ┃ 📦 + BaseEntity fields     ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

**Key Points:**
- Each `USER` has exactly one `ACCOUNT` (1:1 relationship)
- Email must be unique across the system
- Account lockout mechanism for security
- Soft delete capability through BaseEntity

---

## 👥 Social Graph Relationships

### Friendship System (Bidirectional)

```
┌────────────────────────────────────────────────────────────────────────┐
│                      FRIENDSHIP NETWORK                                 │
└────────────────────────────────────────────────────────────────────────┘

        ┏━━━━━━━━━━━━━━━━━━━━━┓                ┏━━━━━━━━━━━━━━━━━━━━━┓
        ┃   FRIENDSHIP        ┃                ┃      FOLLOW         ┃
        ┣━━━━━━━━━━━━━━━━━━━━━┫                ┣━━━━━━━━━━━━━━━━━━━━━┫
        ┃ 🔑 id (PK)          ┃                ┃ 🔑 id (PK)          ┃
        ┃ 🔗 account_id (FK)  ┃                ┃ 🔗 follower_id (FK) ┃
        ┃ 🔗 friend_id (FK)   ┃                ┃ 🔗 following_id(FK) ┃
        ┃ 🤝 mutual           ┃                ┃ 📦 + BaseEntity     ┃
        ┃ 📦 + BaseEntity     ┃                ┗━━━━━━━━┳━━━━━━━━━━━┛
        ┗━━━━━━━━┳━━━━━━━━━━━┛                         │
                 │                                      │
                 │ Many                                 │ Many
                 │                                      │
         ┌───────┴──────────────────┐          ┌───────┴────────┐
         │                          │          │                 │
         ▼                          ▼          ▼                 ▼
┏━━━━━━━━━━━━━━━┓          ┏━━━━━━━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━┓
┃   Account     ┃          ┃   Account     ┃  ┃   Account     ┃
┃ (friendships) ┃          ┃  (friendOf)   ┃  ┃  (following)  ┃
┗━━━━━━━━━━━━━━━┛          ┗━━━━━━━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━┛
```

**Relationship Types:**

| Type | Description | Cardinality |
|------|-------------|-------------|
| **FRIENDSHIP** | Mutual connection between users | Many-to-Many |
| **FOLLOW** | Unidirectional subscription | Many-to-Many |

**Features:**
- ✅ Mutual flag indicates reciprocal friendships
- 🔍 Efficient queries with composite indexes
- 📊 Track both directions of relationships

---

## 📨 Friend Requests

```
┌────────────────────────────────────────────────────────────────────────┐
│                     FRIEND REQUEST WORKFLOW                             │
└────────────────────────────────────────────────────────────────────────┘

                    ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃   FRIEND_REQUEST       ┃
                    ┣━━━━━━━━━━━━━━━━━━━━━━━━┫
                    ┃ 🔑 id (PK)             ┃
                    ┃ 📤 sender_id (FK)      ┃
                    ┃ 📥 receiver_id (FK)    ┃
                    ┃ 📊 status (ENUM)       ┃
                    ┃    • PENDING           ┃
                    ┃    • ACCEPTED          ┃
                    ┃    • REJECTED          ┃
                    ┃ 👁️  read               ┃
                    ┃ 📦 + BaseEntity        ┃
                    ┗━━━━━━━━┳━━━━━━━━━━━━━━━┛
                             │
                             │ Many
                             │
                    ┌────────┴────────┐
                    │                 │
                    ▼                 ▼
          ┏━━━━━━━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━┓
          ┃   Account     ┃  ┃    Account      ┃
          ┃(friendRequest)┃  ┃(receivedRequests┃
          ┗━━━━━━━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━┛
```

**Status Flow:**
```
PENDING → ACCEPTED → Creates FRIENDSHIP
   ↓
REJECTED (Terminal State)
```

---

## 📝 Content & Interactions

```
┌────────────────────────────────────────────────────────────────────────┐
│                      CONTENT MANAGEMENT SYSTEM                          │
└────────────────────────────────────────────────────────────────────────┘

                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃        POSTS            ┃
                    ┣━━━━━━━━━━━━━━━━━━━━━━━━━┫
                    ┃ 🔑 id (PK)              ┃
                    ┃ 🔗 account_id (FK)      ┃
                    ┃ 📄 content (CLOB)       ┃
                    ┃ 🖼️  mediaUrl            ┃
                    ┃ 🎬 mediaType (ENUM)     ┃
                    ┃ 💬 commentsCount        ┃
                    ┃ ❤️  reactsCount         ┃
                    ┃ 📦 + BaseEntity         ┃
                    ┗━━━━┳━━━━━━━━━━━┳━━━━━━━━┛
                         │           │
              ┌──────────┘           └──────────┐
              │ 1:Many                    1:Many │
              ▼                                  ▼
    ┏━━━━━━━━━━━━━━━━━┓              ┏━━━━━━━━━━━━━━━━━┓
    ┃   COMMENTS      ┃              ┃     REACTS      ┃
    ┣━━━━━━━━━━━━━━━━━┫              ┣━━━━━━━━━━━━━━━━━┫
    ┃ 🔑 id (PK)      ┃              ┃ 🔑 id (PK)      ┃
    ┃ 🔗 post_id (FK) ┃              ┃ 🔗 post_id (FK) ┃
    ┃ 🔗 account (FK) ┃              ┃ 🔗 account (FK) ┃
    ┃ 💭 text         ┃              ┃ 😊 reactType    ┃
    ┃ 📦 + BaseEntity ┃              ┃    • LIKE       ┃
    ┗━━━━━━━━━━━━━━━━━┛              ┃    • LOVE       ┃
                                     ┃    • HAHA       ┃
                                     ┃    • WOW        ┃
                                     ┃    • SAD        ┃
                                     ┃    • ANGRY      ┃
                                     ┃ 📦 + BaseEntity ┃
                                     ┗━━━━━━━━━━━━━━━━━┛
```

**Media Types Supported:**
- 📷 IMAGE
- 🎥 VIDEO
- 🎵 AUDIO
- 📄 DOCUMENT

**Performance Optimizations:**
- Denormalized counters (`commentsCount`, `reactsCount`)
- Indexed by creation date for timeline queries
- CLOB for large text content

---

## 👔 Profile Details

```
┌────────────────────────────────────────────────────────────────────────┐
│                    PROFESSIONAL PROFILE SYSTEM                          │
└────────────────────────────────────────────────────────────────────────┘

            ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
            ┃    ACCOUNT_DETAILS          ┃
            ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
            ┃ 🔑 id (PK)                  ┃
            ┃ 🔗 account_id (FK)          ┃
            ┃                             ┃
            ┃ 📋 Embedded Objects:        ┃
            ┃ ┌─────────────────────────┐ ┃
            ┃ │ 👤 personalInfo         │ ┃
            ┃ │   • firstName           │ ┃
            ┃ │   • lastName            │ ┃
            ┃ │   • middleName          │ ┃
            ┃ │   • dateOfBirth         │ ┃
            ┃ └─────────────────────────┘ ┃
            ┃ ┌─────────────────────────┐ ┃
            ┃ │ 🏠 address              │ ┃
            ┃ │   • street              │ ┃
            ┃ │   • city                │ ┃
            ┃ │   • state               │ ┃
            ┃ │   • country             │ ┃
            ┃ └─────────────────────────┘ ┃
            ┃                             ┃
            ┃ 🌍 languages (COLLECTION)   ┃
            ┃ 📦 + BaseEntity             ┃
            ┗━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┛
                        │
                        │ 1:Many
                        ▼
            ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
            ┃    WORK_EXPERIENCE          ┃
            ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
            ┃ 🔑 id (PK)                  ┃
            ┃ 🔗 account_details_id (FK)  ┃
            ┃ 🏢 companyName              ┃
            ┃ 💼 title                    ┃
            ┃ 🖼️  companyLogo             ┃
            ┃ 📅 startDate                ┃
            ┃ 📅 endDate                  ┃
            ┃ ✅ present                  ┃
            ┃ 📝 description (TEXT)       ┃
            ┃ 📦 + BaseEntity             ┃
            ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

**Embedded Objects:**
- **PersonalInfo**: Core identity information
- **Address**: Geographic location data
- **Languages**: Multi-valued collection

---

## 📬 Notifications & Messaging

```
┌────────────────────────────────────────────────────────────────────────┐
│                   COMMUNICATION INFRASTRUCTURE                          │
└────────────────────────────────────────────────────────────────────────┘

  ┏━━━━━━━━━━━━━━━━━━━━━┓            ┏━━━━━━━━━━━━━━━━━━━━━┓
  ┃   NOTIFICATION      ┃            ┃      MESSAGE        ┃
  ┣━━━━━━━━━━━━━━━━━━━━━┫            ┣━━━━━━━━━━━━━━━━━━━━━┫
  ┃ 🔑 id (PK)          ┃            ┃ 🔑 id (PK)          ┃
  ┃ 🔗 account_id (FK)  ┃            ┃ 📤 sender_id (FK)   ┃
  ┃ 🏷️  type (ENUM)     ┃            ┃ 📥 receiver_id (FK) ┃
  ┃    • FRIEND_REQUEST ┃            ┃ 💬 content (TEXT)   ┃
  ┃    • POST_LIKED     ┃            ┃ 👁️  read            ┃
  ┃    • NEW_FOLLOWER   ┃            ┃ 📎 attachmentUrl    ┃
  ┃    • COMMENT_ADDED  ┃            ┃ 📦 + BaseEntity     ┃
  ┃    • MENTION        ┃            ┗━━━━━━━━┳━━━━━━━━━━━┛
  ┃ 📝 message          ┃                     │
  ┃ 🔗 link             ┃                     │ Many
  ┃ 👁️  read            ┃                     │
  ┃ 📦 + BaseEntity     ┃            ┌────────┴────────┐
  ┗━━━━━━━━┳━━━━━━━━━━━┛            │                 │
           │                         ▼                 ▼
           │ Many            ┏━━━━━━━━━━━━┓  ┏━━━━━━━━━━━━━┓
           │                 ┃  Account   ┃  ┃   Account   ┃
  ┌────────┴────────┐        ┃(sent       ┃  ┃(received    ┃
  │                 │        ┃ Messages)  ┃  ┃ Messages)   ┃
  ▼                 │        ┗━━━━━━━━━━━━┛  ┗━━━━━━━━━━━━━┛
┏━━━━━━━━━━━━━━┓    │
┃   Account    ┃    │
┃(notifications┃    │
┗━━━━━━━━━━━━━━┛    │
```

**Notification Types:**
- 🤝 FRIEND_REQUEST
- ❤️ POST_LIKED
- 👤 NEW_FOLLOWER
- 💬 COMMENT_ADDED
- @️⃣ MENTION
- 🔔 And more...

---

## 📊 Audit & Activity Tracking

```
┌────────────────────────────────────────────────────────────────────────┐
│                        AUDIT LOG SYSTEM                                 │
└────────────────────────────────────────────────────────────────────────┘

  ┏━━━━━━━━━━━━━━━━━━━━━┓            ┏━━━━━━━━━━━━━━━━━━━━━┓
  ┃      ACTION         ┃            ┃      CONTACT        ┃
  ┣━━━━━━━━━━━━━━━━━━━━━┫            ┣━━━━━━━━━━━━━━━━━━━━━┫
  ┃ 🔑 id (PK)          ┃            ┃ 🔑 id (PK)          ┃
  ┃ 🔗 account_id (FK)  ┃            ┃ 🔗 account_id (FK)  ┃
  ┃ 🎯 actionType       ┃            ┃ 👤 name             ┃
  ┃    • LOGIN          ┃            ┃ 📧 email            ┃
  ┃    • LOGOUT         ┃            ┃ 📱 phone            ┃
  ┃    • POST_CREATED   ┃            ┃ 💬 message          ┃
  ┃    • FRIEND_REQUEST ┃            ┃ 📦 + BaseEntity     ┃
  ┃    • PROFILE_UPDATE ┃            ┗━━━━━━━━━━━━━━━━━━━━━┛
  ┃    • (30+ types)    ┃
  ┃ 📝 details (TEXT)   ┃
  ┃ 🌐 ipAddress        ┃
  ┃ 🖥️  userAgent       ┃
  ┃ 📦 + BaseEntity     ┃
  ┗━━━━━━━━━━━━━━━━━━━━━┛
```

**Tracked Actions:**
- 🔐 Authentication events
- 📝 Content creation/modification
- 👥 Social interactions
- ⚙️ Settings changes
- 🔍 Search queries

---

## 🏗️ Base Entity

```
┌────────────────────────────────────────────────────────────────────────┐
│            BASE ENTITY (Inherited by All Entities)                      │
└────────────────────────────────────────────────────────────────────────┘

                    ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃    BASE_ENTITY         ┃
                    ┣━━━━━━━━━━━━━━━━━━━━━━━━┫
                    ┃ 🔑 id (PK)             ┃
                    ┃ 📅 createdAt           ┃
                    ┃ 🔄 updatedAt           ┃
                    ┃ 🗑️  deleted            ┃
                    ┃ ❌ deletedAt           ┃
                    ┃ 👤 createdBy           ┃
                    ┃ 👤 lastModifiedBy      ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
```

**Features:**
- ✅ Automatic timestamp management
- 🗑️ Soft delete support
- 👤 Audit trail (who created/modified)
- 🔄 Version tracking ready

---

## 🏷️ Enumerations

### Role Enumeration
```java
enum Role {
    ROLE_USER,
    ROLE_ADMIN
}
```

### Request Status
```java
enum RequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED
}
```

### React Types
```java
enum ReactType {
    LIKE,    // 👍
    LOVE,    // ❤️
    HAHA,    // 😂
    WOW,     // 😮
    SAD,     // 😢
    ANGRY    // 😠
}
```

### Profile & Media Types
```java
enum ProfileType { /* ... */ }
enum AccountJobType { /* ... */ }
enum Language { /* ... */ }
enum MediaType {
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT
}
```

### Action Types (30+ types)
```java
enum ActionType {
    LOGIN,
    LOGOUT,
    POST_CREATED,
    POST_UPDATED,
    POST_DELETED,
    COMMENT_CREATED,
    FRIEND_REQUEST_SENT,
    FRIEND_REQUEST_ACCEPTED,
    PROFILE_UPDATED,
    MESSAGE_SENT,
    // ... and more
}
```

### Notification Types
```java
enum NotificationType {
    FRIEND_REQUEST,
    POST_LIKED,
    POST_COMMENTED,
    NEW_FOLLOWER,
    MENTION,
    MESSAGE_RECEIVED
}
```

---

## 🔍 Database Indexes

### High-Performance Indexes

#### **Accounts**
```sql
CREATE INDEX idx_profile_type ON accounts(profile_type);
CREATE INDEX idx_job_type ON accounts(job_type);
CREATE INDEX idx_user_id ON accounts(user_id);
```

#### **Posts** (Timeline Queries)
```sql
CREATE INDEX idx_account_created ON posts(account_id, created_at DESC);
CREATE INDEX idx_deleted ON posts(deleted);
CREATE INDEX idx_media_type ON posts(media_type);
```

#### **Comments** (Nested Loading)
```sql
CREATE INDEX idx_post_created ON comments(post_id, created_at DESC);
CREATE INDEX idx_account ON comments(account_id);
```

#### **Friend Requests** (Pending Requests)
```sql
CREATE INDEX idx_receiver_status ON friend_requests(receiver_id, status);
CREATE INDEX idx_sender_status ON friend_requests(sender_id, status);
CREATE INDEX idx_status_read ON friend_requests(status, read);
```

#### **Friendships** (Social Graph)
```sql
CREATE INDEX idx_account_friend ON friendships(account_id, friend_id);
CREATE INDEX idx_friend_account ON friendships(friend_id, account_id);
CREATE INDEX idx_created_at ON friendships(created_at DESC);
```

#### **Follows** (Feed Generation)
```sql
CREATE INDEX idx_follower ON follows(follower_id, created_at DESC);
CREATE INDEX idx_following ON follows(following_id, created_at DESC);
```

#### **Notifications** (Unread Count)
```sql
CREATE INDEX idx_account_read ON notifications(account_id, read);
CREATE INDEX idx_created_at ON notifications(created_at DESC);
CREATE INDEX idx_type ON notifications(type);
```

#### **Messages** (Conversation Loading)
```sql
CREATE INDEX idx_sender_receiver_created 
    ON messages(sender_id, receiver_id, created_at DESC);
CREATE INDEX idx_receiver_read ON messages(receiver_id, read);
CREATE INDEX idx_created_at ON messages(created_at DESC);
```

#### **Actions** (Audit Logs)
```sql
CREATE INDEX idx_account_action_type ON actions(account_id, action_type);
CREATE INDEX idx_created_at ON actions(created_at DESC);
CREATE INDEX idx_action_type ON actions(action_type);
```

---

## 🔒 Unique Constraints

```sql
-- Prevent duplicate users
ALTER TABLE users ADD CONSTRAINT uk_email UNIQUE (email);

-- One account per user
ALTER TABLE accounts ADD CONSTRAINT uk_user_id UNIQUE (user_id);

-- One react per user per post
ALTER TABLE reacts ADD CONSTRAINT uk_account_post 
    UNIQUE (account_id, post_id);

-- Prevent duplicate friendships
ALTER TABLE friendships ADD CONSTRAINT uk_account_friend 
    UNIQUE (account_id, friend_id);

-- Prevent duplicate follows
ALTER TABLE follows ADD CONSTRAINT uk_follower_following 
    UNIQUE (follower_id, following_id);
```

---

## 📊 Relationship Summary

### One-to-One Relationships
| Parent | Child | Description |
|--------|-------|-------------|
| User | Account | Authentication to Profile |

### One-to-Many Relationships
| Parent | Child | Description |
|--------|-------|-------------|
| Account | Posts | User's content |
| Account | Comments | User's comments |
| Account | Reacts | User's reactions |
| Account | FriendRequests (sent) | Outgoing requests |
| Account | FriendRequests (received) | Incoming requests |
| Account | Actions | Activity log |
| Account | Contacts | Contact form submissions |
| Account | Friendships | Friend connections |
| Account | Follows | Following relationships |
| Account | Notifications | User notifications |
| Account | Messages (sent) | Sent messages |
| Account | Messages (received) | Received messages |
| AccountDetails | WorkExperiences | Career history |
| Post | Comments | Post comments |
| Post | Reacts | Post reactions |

### Embedded Objects
| Entity | Embeddable | Fields |
|--------|------------|--------|
| AccountDetails | PersonalInfo | firstName, lastName, middleName, dateOfBirth |
| AccountDetails | Address | street, city, state, country |

---

## 📈 Database Statistics

| Metric | Count |
|--------|-------|
| **Total Entities** | 15 |
| **Total Relationships** | 20+ |
| **Total Indexes** | 25+ |
| **Total Unique Constraints** | 6 |
| **Enum Types** | 8 |
| **Embedded Objects** | 2 |

---

## 🎯 Design Principles

### ✅ Best Practices Implemented

1. **Soft Deletes**: All entities inherit `deleted` flag for data recovery
2. **Audit Trail**: Automatic tracking of creation and modification
3. **Denormalization**: Counter fields for performance (e.g., `commentsCount`)
4. **Composite Indexes**: Optimized for common query patterns
5. **Unique Constraints**: Prevent duplicate data at database level
6. **Embeddables**: Group related fields logically
7. **Enums**: Type-safe status and category management

### 🚀 Performance Optimizations

- **Indexed Foreign Keys**: Fast joins and lookups
- **Composite Indexes**: Multi-column query optimization
- **Covering Indexes**: Include frequently selected columns
- **Timestamp Indexes**: Efficient date range queries
- **Read Flag Indexes**: Quick unread count queries

---

## 📝 Notes

> **Version**: 1.0   
> **Database**: Oracle  
> **ORM**: JPA/Hibernate Compatible  

---

<div align="center">


[⬆ Back to Top](#️-database-schema-documentation)

</div>