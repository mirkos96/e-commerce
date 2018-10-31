package com.maks.service.converter.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationItemPicture;
import com.maks.repository.daoService.IAdditionalDatabaseOperationUser;
import com.maks.repository.daoService.IAdditionalDatabaseOperationUserRole;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.enumRole.EnumOrderStatus;
import com.maks.repository.enumRole.EnumUserRole;
import com.maks.repository.enumStatusConverter.OrderStatusConverter;
import com.maks.repository.enumStatusConverter.UserRoleConverter;
import com.maks.repository.model.*;
import com.maks.service.modelDto.*;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@PropertySource(value = {"classpath:pathToStaticFilesToUpload.properties"})
public class ConvertDtoAndPersistentEntityImpl extends OrderStatusConverter implements
        IConvertDtoAndPersistentEntities {

    public static final Logger log = Logger.getLogger
            (ConvertDtoAndPersistentEntityImpl.class);
    private final Environment environment;
    private final UserRoleConverter userRoleConverter;
    private final IAdditionalDatabaseOperationUser<User, Long> userAdditionalDao;
    private final ICrudOperation<News, Long> newsDao;
    private final ICrudOperation<OrderItemPicture, Long> orderItemPictureDao;
    private final IAdditionalDatabaseOperationItemPicture<OrderItemPicture, Long> itemPictureAdditionalDao;
    private final IAdditionalDatabaseOperationUserRole<UserRole, Long> userRoleAdditionalDao;
    private final ICrudOperation<User, Long> userDao;
    private final ICrudOperation<MessageFromUser, Long> messageFromUserDao;

    @Autowired
    public ConvertDtoAndPersistentEntityImpl(Environment environment,
                                             UserRoleConverter userRoleConverter,
                                             @Qualifier("userDao")
                                                     IAdditionalDatabaseOperationUser<User, Long> userAdditionalDao,
                                             @Qualifier("newsDao")
                                                     ICrudOperation<News, Long> newsDao,
                                             @Qualifier("orderItemPictureDao")
                                                     ICrudOperation<OrderItemPicture, Long> orderItemPictureDao,
                                             @Qualifier("orderItemPictureDao")
                                                     IAdditionalDatabaseOperationItemPicture<OrderItemPicture, Long>
                                                     itemPictureAdditionalDao,
                                             @Qualifier("userRoleDao")
                                                     IAdditionalDatabaseOperationUserRole<UserRole, Long>
                                                     userRoleAdditionalDao,
                                             @Qualifier("userDao")
                                                     ICrudOperation<User, Long> userDao,
                                             @Qualifier("messageFromUserDao")
                                                     ICrudOperation<MessageFromUser, Long>
                                                     messageFromUserDao) {
        this.environment = environment;
        this.userRoleConverter = userRoleConverter;
        this.userAdditionalDao = userAdditionalDao;
        this.newsDao = newsDao;
        this.orderItemPictureDao = orderItemPictureDao;
        this.itemPictureAdditionalDao = itemPictureAdditionalDao;
        this.userRoleAdditionalDao = userRoleAdditionalDao;
        this.userDao = userDao;
        this.messageFromUserDao = messageFromUserDao;
    }

    @Override
    public List<UserDto> convertEntityToDtoUser(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(convertEntityToDtoUser(user));
        }
        return userDtoList;
    }

    @Override
    public User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setUserLogin(userDto.getUserLogin());
        user.setPassword(userDto.getPassword());

        Verification verification = new Verification();
        verification.setVerificationToken
                (String.valueOf(userDto.getUserLogin().hashCode()));

        Account account = new Account();
        account.setActivated(false);
        account.setBlocked(false);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserPhone(userDto.getPhone());
        userInfo.setUserAddress(userDto.getAddress());
        userInfo.setNameSurname(userDto.getNameSurname());

        user.setAccount(account);
        user.setUserRole(setUserRole());
        user.setUserInfo(userInfo);
        user.setVerification(verification);
        return user;
    }

    @Override
    public UserDto convertEntityToDtoUser(User user) {
        return setValuesFromUserEntityToDto(user);
    }

    @Override
    public OrderItem convertUploadedDtoToEntityOrderItem(UploadedOrderItemDto uploadedOrderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderName(uploadedOrderItemDto.getOrderItemName());
        orderItem.setOrderDescription(uploadedOrderItemDto.getOrderItemDescription());
        saveNewPicture(uploadedOrderItemDto);
        orderItem.setPicture(getPictureByName(uploadedOrderItemDto));
        orderItem.setOrderPrice(uploadedOrderItemDto.getOrderItemPrice());
        return orderItem;
    }

    @Override
    public List<OrderItemDto> convertOrderItemsIntoDto
            (List<OrderItem> orderItemList) {
        List<OrderItemDto> listOfItems = new ArrayList<>();
        for (OrderItem item : orderItemList) {
            listOfItems.add(setValuesFromEntityToDtoOrderItem(item));
        }
        return listOfItems;
    }

    @Override
    public List<OrderItem> convertDtoToOrderItems(List<OrderItemDto> orderItemDtoList) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemDto dto : orderItemDtoList) {
            for (int i = 1; i <= dto.getOrderItemAmount(); i++) {
                orderItemList.add(setValuesFromDtoToEntityOrderItem(dto));
            }
        }
        return orderItemList;
    }

    @Override
    public List<OrderDto> convertOrderEntityToDto(List<Order> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            orderDtoList.add(setValuesFromEntityToDtoOrder(order));
        }
        return orderDtoList;
    }

    @Override
    public List<MessageDto> convertMessageEntitiesToDto
            (List<MessageFromUser> messageListEntity) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        for (MessageFromUser messageFromUser : messageListEntity) {
            messageDtoList.add(setValuesFromMessageEntityToDto(messageFromUser));
        }
        return messageDtoList;
    }

    @Override
    public MessageFromUser convertMessageDtoIntoPersistentObject(MessageDto messageDto) {
        return setValuesFromMessageDtoToMessageEntity(messageDto);
    }

    @Override
    public List<NewsDto> convertNewsEntityToDto(List<News> newsList) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        for (News newsEntity : newsList) {
            newsDtoList.add(setValuesFromEntityToDtoNews(newsEntity));
        }
        return newsDtoList;
    }

    @Override
    public Comment convertToCommentEntity(Long newsId, String userLogin, String commentBody) {
        Comment comment = new Comment();
        comment.setNews(getNewsById(newsId));
        comment.setUser(getUserByLogin(userLogin));
        comment.setCommentBody(commentBody);
        return comment;
    }

    @Override
    public EnumOrderStatus convertStringStatusToEnumOrder(String orderStatus) {
        return convertToEntityAttribute(orderStatus);
    }

    @Override
    public List<RoleDto> convertEnumRoleToString(List<EnumUserRole> roleEnumList) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (EnumUserRole enumUserRole : roleEnumList) {
            String role = (userRoleConverter.convertToDatabaseColumn(enumUserRole));
            switch (role) {
                case "ROLE_USER":
                    roleDtoList.add(new RoleDto("ROLE_USER"));
                    break;
                case "ROLE_ADMIN":
                    roleDtoList.add(new RoleDto("ROLE_ADMIN"));
                    break;
                case "ROLE_SUPERADMIN":
                    roleDtoList.add(new RoleDto("ROLE_SUPERADMIN"));
                    break;
            }
        }
        return roleDtoList;
    }

    @Override
    public EnumUserRole convertStringToEnumUserRole(String userRole) {
        return userRoleConverter.convertToEntityAttribute(userRole);
    }

    @Override
    public News setParametersToNewsEntity(String newsHead, String newsBody) {
        News news = new News();
        news.setNewsHead(newsHead);
        news.setNewsBody(newsBody);
        return news;
    }

    @Override
    public ReplyOnUserMessage convertDtoToEntityReply(ReplyOnUserMessageDto replyDto) {
        ReplyOnUserMessage replyOnUserMessage = new ReplyOnUserMessage();
        replyOnUserMessage.setReplyBody(replyDto.getReplyBody());
        replyOnUserMessage.setMessageFromUser(messageFromUserDao.readEntity(replyDto.getUserMessageId()));
        replyOnUserMessage.setUser(userDao.readEntity(replyDto.getUserId()));
        return replyOnUserMessage;
    }

    private NewsDto setValuesFromEntityToDtoNews(News newsEntity) {
        NewsDto newsDto = new NewsDto();
        newsDto.setNewsId(newsEntity.getNewsId());
        newsDto.setNewsBody(newsEntity.getNewsBody());
        newsDto.setNewsHead(newsEntity.getNewsHead());
        newsDto.setCommentDtoList(setValuesFromListEntityToDtoComment(
                newsEntity.getCommentList()));
        return newsDto;
    }

    private List<CommentDto> setValuesFromListEntityToDtoComment
            (List<Comment> commentEntityList) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment commentEntity : commentEntityList) {
            commentDtoList.add(setValuesFromEntityToDtoComment(commentEntity));
        }
        return commentDtoList;
    }

    private CommentDto setValuesFromEntityToDtoComment(Comment commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setUserDto(convertEntityToDtoUser(commentEntity.getUser()));
        commentDto.setCommentId(commentEntity.getCommentId());
        commentDto.setCommentBody(commentEntity.getCommentBody());
        commentDto.setNewsId(commentEntity.getNews().getNewsId());
        return commentDto;
    }

    private OrderItem setValuesFromDtoToEntityOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(orderItemDto.getOrderItemId());
        orderItem.setOrderDescription(orderItemDto.getOrderItemDescription());
        orderItem.setOrderName(orderItemDto.getOrderItemName());
        orderItem.setOrderPrice(orderItemDto.getOrderItemPrice());
        orderItem.setPicture(null);
        return orderItem;
    }

    private OrderItemDto setValuesFromEntityToDtoOrderItem(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderItemId(orderItem.getOrderItemId());
        orderItemDto.setOrderItemName(orderItem.getOrderName());
        orderItemDto.setOrderItemDescription(orderItem.getOrderDescription());
        orderItemDto.setOrderItemPrice(orderItem.getOrderPrice());
        orderItemDto.setOrderItemPictureName(setPathToPictureOrderItem(orderItem));
        return orderItemDto;
    }

    private String setPathToPictureOrderItem(OrderItem orderItem) {
        String path = environment.getProperty("pathToSpecifiedFolder");
        String imageName = orderItem.getPicture().getPictureName();
        String suffix = (".jpg");
        log.info(path + imageName + suffix);
        return path + imageName + suffix;
    }

    private OrderDto setValuesFromEntityToDtoOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setOrderStatus(order.getOrderStatus().getOrderStatus());
        List<OrderItem> orderItemList = order.getOrderOrderItemList();
        orderDto.setPizzas(convertOrderItemsIntoDto(orderItemList));
        orderDto.setDate(setTime());
        return orderDto;
    }

    private MessageDto setValuesFromMessageEntityToDto(MessageFromUser message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setUserId(message.getUser().getUserId());
        messageDto.setMessageId(message.getMessageId());
        messageDto.setMessageTheme(message.getMessageTheme());
        messageDto.setMessageBody(message.getMessageBody());
        messageDto.setReceivingDate(String.valueOf(setTime()));
        messageDto.setUserLogin(message.getUser().getUserLogin());
        return messageDto;
    }

    private MessageFromUser setValuesFromMessageDtoToMessageEntity(MessageDto messageDto) {
        MessageFromUser messageFromUser = new MessageFromUser();
        messageFromUser.setMessageId(messageDto.getMessageId());
        messageFromUser.setUser(getUserByLogin(messageDto.getUserLogin()));
        messageFromUser.setMessageTheme(messageDto.getMessageTheme());
        messageFromUser.setMessageBody(messageDto.getMessageBody());
        messageFromUser.setReceivingDate(setTime().toString());
        return messageFromUser;
    }

    private UserDto setValuesFromUserEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setPhone(user.getUserInfo().getUserPhone());
        userDto.setNameSurname(user.getUserInfo().getNameSurname());
        userDto.setAddress(user.getUserInfo().getUserAddress());
        userDto.setUserLogin(user.getUserLogin());
        userDto.setBlocked(user.getAccount().getBlocked());
        return userDto;
    }

    @Transactional
    public void saveNewPicture(UploadedOrderItemDto uploadedOrderItemDto) {
        OrderItemPicture orderItemPicture = new OrderItemPicture();
        orderItemPicture.setPictureName(uploadedOrderItemDto.getOrderItemPictureName());
        orderItemPictureDao.createEntity(orderItemPicture);
    }

    @Transactional
    public OrderItemPicture getPictureByName(UploadedOrderItemDto uploadedOrderItemDto) {
        return itemPictureAdditionalDao.getByName(uploadedOrderItemDto.getOrderItemPictureName());
    }

    @Transactional
    public News getNewsById(Long newsId) {
        return newsDao.readEntity(newsId);
    }

    @Transactional
    public User getUserByLogin(String userLogin) {
        return userAdditionalDao.getByName(userLogin);
    }

    @Transactional
    public UserRole setUserRole() {
        return userRoleAdditionalDao.getStandardUserRole();
    }

    private Date setTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        return date;
    }
}
