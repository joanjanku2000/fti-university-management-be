package al.edu.fti.universitymanagement.uniman.core.user.friendship.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dao.FriendshipDao;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import com.jpa.filter.dao.FilterRepo;
import com.jpa.filter.dao.FilterRepoJpaImpl;
import com.jpa.filter.dto.FilterWrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus.FRIENDS;
import static al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus.PENDING;

@Service
public class FriendshipService extends BaseServiceAbstractImpl<FriendshipEntity, FriendshipDto> {


    public FriendshipService(BaseDao<FriendshipEntity, Long> baseDao, BaseConverter<FriendshipDto, FriendshipEntity> baseConverter, BaseValidator<FriendshipDto, FriendshipEntity> baseValidator, FilterRepo filterRepo) {
        super(baseDao, baseConverter, baseValidator, filterRepo);
    }

    /**
     * @param userId Long
     * @param requestDto RequestDto
     * @return the friendships of a user
     */
    public Page<FriendshipDto> friendshipOfUser(Long userId, RequestDto requestDto){
        return  ((FriendshipDao)baseDao)
                .findAllBySenderIdOrReceiverIdAndStatus(userId,userId,FRIENDS,
                        PageRequest.of(requestDto.getPageNumber(),requestDto.getPageSize()))
                .map(baseConverter::toDto);
    }

    /**
     * @param userId Long
     * @return the friendship requests of a user
     */
    public List<FriendshipDto> friendshipRequestsOfUser(Long userId){
        return ((FriendshipDao)baseDao).
                findAllByReceiverIdAndStatus(userId,PENDING).
                stream().
                map(baseConverter::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<FriendshipDto> findAll(FilterWrap filterWrap, Class<FriendshipEntity> clazz) {
        return filterRepo.filter(filterWrap,clazz).stream().map(baseConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<FriendshipDto> findAll(RequestDto requestDto) {
        return null;
    }
}
