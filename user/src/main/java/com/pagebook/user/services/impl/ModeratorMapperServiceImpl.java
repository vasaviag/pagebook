package com.pagebook.user.services.impl;
import com.pagebook.user.entity.ModeratorMapper;
import com.pagebook.user.entity.User;
import com.pagebook.user.repository.IModeratorMapperRepository;
import com.pagebook.user.repository.IUserRepository;
import com.pagebook.user.services.IModeratorMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModeratorMapperServiceImpl implements IModeratorMapperService {

    @Autowired
    IModeratorMapperRepository iModeratorMapperRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Override
    public ModeratorMapper save(ModeratorMapper moderatorMapper) {
        return iModeratorMapperRepository.save(moderatorMapper);
    }

    @Override
    public boolean isModeratorFor(String moderatorId, String businessId) {
        try{
            return iModeratorMapperRepository.isModerator(moderatorId, businessId);
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public boolean isModerator(String moderatorId) {
        Iterable<ModeratorMapper> iterable = iModeratorMapperRepository.findByModeratorId(moderatorId);
        List<ModeratorMapper> moderatorMappers = new ArrayList<>();
        for (ModeratorMapper moderatorMapper : iterable) {
            moderatorMappers.add(moderatorMapper);
        }
        return (moderatorMappers.size()>0)?true:false;
    }

    @Override
    public List<User> pagesManaged(String userId) {
        Iterable<ModeratorMapper> moderatorMapperIterable = iModeratorMapperRepository.findByModeratorId(userId);
        List<ModeratorMapper> moderatorMapperList = new ArrayList<>();
        moderatorMapperIterable.forEach(moderatorMapperList::add);

        List<User> userList = new ArrayList<>();
        for (ModeratorMapper moderatorMapper : moderatorMapperList) {
            User user = iUserRepository.findById(moderatorMapper.getBusinessId()).get();
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> allModerators(String userId) {
        Iterable<ModeratorMapper> moderatorMapperIterable = iModeratorMapperRepository.findByBusinessId(userId);
        List<ModeratorMapper> moderatorMapperList = new ArrayList<>();
        moderatorMapperIterable.forEach(moderatorMapperList::add);

        List<User> userList = new ArrayList<>();
        for (ModeratorMapper moderatorMapper : moderatorMapperList) {
            User user = iUserRepository.findById(moderatorMapper.getModeratorId()).get();
            userList.add(user);
        }
        return userList;
    }

    @Override
    public void deleteModerator(ModeratorMapper moderatorMapper) {
        String businessId = moderatorMapper.getBusinessId();
        String moderatorId = moderatorMapper.getModeratorId();
        iModeratorMapperRepository.deleteByBusinessIdAndModeratorId(businessId, moderatorId);
    }
}
