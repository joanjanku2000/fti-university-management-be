package al.edu.fti.universitymanagement.uniman.core.user.friendship.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dao.FriendshipDao;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus.FRIENDS;
import static al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus.PENDING;

@Service
public class FriendshipService extends BaseServiceAbstractImpl<FriendshipEntity, FriendshipDto> {
    public FriendshipService(BaseDao<FriendshipEntity, Long> baseDao, BaseConverter<FriendshipDto, FriendshipEntity> baseConverter, BaseValidator<FriendshipDto, FriendshipEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }


    public Page<FriendshipDto> friendshipOfUser(Long userId, RequestDto requestDto){
        return  ((FriendshipDao)baseDao)
                .findAllBySenderIdOrReceiverIdAndStatus(userId,userId,FRIENDS,
                        PageRequest.of(requestDto.getPageNumber(),requestDto.getPageSize()))
                .map(baseConverter::toDto);
    }

    public List<FriendshipDto> friendshipRequestsOfUser(Long userId){
        return ((FriendshipDao)baseDao).
                findAllByReceiverIdAndStatus(userId,PENDING).
                stream().
                map(baseConverter::toDto)
                .collect(Collectors.toList());

    }
}
