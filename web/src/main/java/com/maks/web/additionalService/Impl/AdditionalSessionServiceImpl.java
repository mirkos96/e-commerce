package com.maks.web.additionalService.Impl;

import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UserDto;
import com.maks.web.additionalService.IAdditionalSessionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Qualifier("additionalSessionService")
public class AdditionalSessionServiceImpl implements IAdditionalSessionService {

    @Override
    public UserDto getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserDto) session.getAttribute("userDto");
    }

    @Override
    public void putUserBackToSession(UserDto userDto, List<OrderItemDto> list, HttpServletRequest request) {
        HttpSession session = request.getSession();
        userDto.setItemDtoList(list);
        session.setAttribute("userDto", userDto);
    }
}

