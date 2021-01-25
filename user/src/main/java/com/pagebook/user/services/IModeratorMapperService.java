package com.pagebook.user.services;

import com.pagebook.user.entity.ModeratorMapper;
import com.pagebook.user.entity.User;

import java.util.List;

public interface IModeratorMapperService {
    ModeratorMapper save(ModeratorMapper moderatorMapper);

    List<User> pagesManaged(String userId);

    List<User> allModerators(String userId);

    boolean isModeratorFor(String moderatorId, String businessId);

    void deleteModerator(ModeratorMapper moderatorMapper);

    boolean isModerator(String moderatorId);
}
