package com.maks.service.converter;

import com.maks.repository.enumRole.EnumOrderStatus;
import com.maks.repository.enumRole.EnumUserRole;
import com.maks.repository.model.*;
import com.maks.service.modelDto.*;

import java.util.List;

public interface IConvertDtoAndPersistentEntities {

    List<UserDto> convertEntityToDtoUser(List<User> userList);

    User convertUserDtoToEntity(UserDto userDto);

    UserDto convertEntityToDtoUser(User user);

    OrderItem convertUploadedDtoToEntityOrderItem(UploadedOrderItemDto uploadedOrderItemDto);

    List<OrderItemDto> convertOrderItemsIntoDto(List<OrderItem> orderItemList);

    List<OrderItem> convertDtoToOrderItems(List<OrderItemDto> orderItemDtoList);

    List<OrderDto> convertOrderEntityToDto(List<Order> orderList);

    List<MessageDto> convertMessageEntitiesToDto(List<MessageFromUser> messageListEntity);

    MessageFromUser convertMessageDtoIntoPersistentObject(MessageDto messageDto);

    List<NewsDto> convertNewsEntityToDto(List<News> newsList);

    Comment convertToCommentEntity(Long newsId, String userLogin, String commentBody);

    EnumOrderStatus convertStringStatusToEnumOrder(String orderStatus);

    List<RoleDto> convertEnumRoleToString(List<EnumUserRole> roleEnumList);

    EnumUserRole convertStringToEnumUserRole(String userRole);

    News setParametersToNewsEntity(String newsHead, String newsBody);

    ReplyOnUserMessage convertDtoToEntityReply(ReplyOnUserMessageDto replyDto);

}
