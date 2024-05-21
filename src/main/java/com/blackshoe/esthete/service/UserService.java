package com.blackshoe.esthete.service;


import com.blackshoe.esthete.dto.EditUserProfileDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    EditUserProfileDto.EditUserProfileImgResponse editUserProfileImg(String authorizationHeader, MultipartFile multipartFile);
    EditUserProfileDto.EditUserProfileInfosResponse editUserProfileInfos(String authorizationHeader, EditUserProfileDto.EditUserProfileInfosRequest editUserProfileInfosRequest);
}