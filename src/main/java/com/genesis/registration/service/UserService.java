
package com.genesis.registration.service;

import com.genesis.registration.dto.*;

        import java.util.List;

public interface UserService {

    UserDetailDTO createUser(CreateUserDTO dto);

    UserShortDTO getUserByIdShort(Long id);

    UserDetailDTO getUserByIdDetail(Long id);

    List<UserShortDTO> getAllUsersShort();

    List<UserDetailDTO> getAllUsersDetail();

    UserShortDTO updateUser(UpdateUserDTO dto);

    void deleteUser(Long id);
}
