package com.maks.web.additionalService;

import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAdditionalSessionService {

    UserDto getUserFromSession(HttpServletRequest request);

    void putUserBackToSession(UserDto userDto, List<OrderItemDto> list,
                              HttpServletRequest request);
}
