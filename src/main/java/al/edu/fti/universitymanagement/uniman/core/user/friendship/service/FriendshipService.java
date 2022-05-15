package al.edu.fti.universitymanagement.uniman.core.user.friendship.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService extends BaseServiceAbstractImpl<FriendshipEntity, FriendshipDto> {
    public FriendshipService(BaseDao<FriendshipEntity, Long> baseDao, BaseConverter<FriendshipDto, FriendshipEntity> baseConverter, BaseValidator<FriendshipDto, FriendshipEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }
}
