package com.sprintgether.otserver.controller;

import com.sprintgether.otserver.annotation.CurrentUser;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.enums.ResponseStatus;
import com.sprintgether.otserver.model.payload.RestResponse;
import com.sprintgether.otserver.security.CustomUserDetails;
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

    @ApiOperation(value = "Créer un think sur la plateforme")
    @PostMapping("/think")
    public RestResponse createThink(@CurrentUser CustomUserDetails customUserDetails,
                                    @RequestPart("document") MultipartFile document,
                                    @RequestPart("cover") MultipartFile cover,
                                    @Required @RequestPart("think") ThinkDto thinkDto) throws IOException, OtDBItemNotFoundException {
        return new RestResponse(thinkService.createThink(customUserDetails.getId(), document, cover, thinkDto), "Think crée avec succès", ResponseStatus.SUCCESS, 200);
    }
}
