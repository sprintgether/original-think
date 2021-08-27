package com.sprintgether.otserver.controller;

import com.sprintgether.otserver.annotation.CurrentUser;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.StudyTripDto;
import com.sprintgether.otserver.model.dto.TalkDto;
import com.sprintgether.otserver.model.dto.ThesisDto;
import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.enums.ResponseStatus;
import com.sprintgether.otserver.model.payload.RestResponse;
import com.sprintgether.otserver.security.CustomUserDetails;
import com.sprintgether.otserver.service.core.StudyTripService;
import com.sprintgether.otserver.service.core.TalkService;
import com.sprintgether.otserver.service.core.ThesisService;
import com.sprintgether.otserver.service.core.ThinkService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ThinkService thinkService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private TalkService talkService;

    @Autowired
    private StudyTripService studyTripService;

    @ApiOperation(value = "Créer un think sur la plateforme")
    @PostMapping("/think")
    public RestResponse createThink(@CurrentUser CustomUserDetails customUserDetails,
                                    @RequestPart("document") MultipartFile document,
                                    @RequestPart("cover") MultipartFile cover,
                                    @Required @RequestPart("think") ThinkDto thinkDto) throws IOException, OtDBItemNotFoundException {
        return new RestResponse(thinkService.createThink(customUserDetails.getId(), document, cover, thinkDto), "Think crée avec succès", ResponseStatus.SUCCESS, 200);
    }

    @ApiOperation(value = "créer une thesis sur la plateforme")
    @PostMapping("/thesis")
    public RestResponse createThesis(@CurrentUser CustomUserDetails customUserDetails,
                                     @RequestPart("document") MultipartFile document,
                                     @RequestPart("cover") MultipartFile cover,
                                     @Required @RequestPart("thesis") ThesisDto thesisDto) throws IOException, OtDBItemNotFoundException {

        return  new RestResponse(thesisService.createThesis(customUserDetails.getId(), document, cover, thesisDto), "Thesis crée avec succès", ResponseStatus.SUCCESS, 200);
    }


    @ApiOperation(value = "créer un talk sur la plateforme")
    @PostMapping("/talk")
    public RestResponse createTalk(@CurrentUser CustomUserDetails customUserDetails,
                                   @RequestPart("document") MultipartFile document,
                                   @RequestPart("cover") MultipartFile cover,
                                   @Required @RequestPart("talk") TalkDto talkDto) throws IOException, OtDBItemNotFoundException {

        return  new RestResponse(talkService.createTalk(customUserDetails.getId(), document, cover, talkDto), "talk crée avec succès", ResponseStatus.SUCCESS, 200);
    }

    @ApiOperation(value = "créer un studytrip sur la plateforme")
    @PostMapping("/studytrip")
    public RestResponse createStudyTrip(@CurrentUser CustomUserDetails customUserDetails,
                                    @RequestPart("document") MultipartFile document,
                                    @RequestPart("cover") MultipartFile cover,
                                    @Required @RequestPart("studytrip") StudyTripDto studyTripDto) throws IOException, OtDBItemNotFoundException {

        return new RestResponse(studyTripService.createStudyTrip(customUserDetails.getId(), document, cover, studyTripDto), "talk crée avec succès", ResponseStatus.SUCCESS, 200);
    }
}
