---
description: Model Layer Enhancement Implementation Plan
---

# Model Layer Enhancement Implementation Plan

## Phase 1: Fix BaseEntity & Core Infrastructure
1. ✅ Enhance BaseEntity with soft delete and audit fields
2. ✅ Create new enum types (ActionType, NotificationType)

## Phase 2: Fix User-Account Relationship
3. ✅ Update User entity to link with Account
4. ✅ Update Account entity to link with User
5. ✅ Fix Role relationship (ManyToMany → ElementCollection)

## Phase 3: Create Missing Social Graph Entities
6. ✅ Create Friendship entity
7. ✅ Create Follow entity
8. ✅ Update Account to remove followersCount (will be calculated)

## Phase 4: Fix Existing Entities
9. ✅ Convert WorkExperience from Embeddable to Entity
10. ✅ Update AccountDetails to use WorkExperience entity
11. ✅ Fix Posts fetch strategies and add count fields
12. ✅ Add indexes to all entities
13. ✅ Update Comments, Reacts, FriendRequest with indexes

## Phase 5: Enhance Action & Add New Features
14. ✅ Enhance Action entity with ActionType enum
15. ✅ Create Notification entity
16. ✅ Create Message entity

## Phase 6: Update Repositories & Services
17. ✅ Create new repositories for new entities
18. ✅ Update existing repositories with custom queries
19. ✅ Update service layer to handle new relationships

## Phase 7: Testing & Validation
20. ✅ Test database schema generation
21. ✅ Verify all relationships
22. ✅ Check for circular dependencies
