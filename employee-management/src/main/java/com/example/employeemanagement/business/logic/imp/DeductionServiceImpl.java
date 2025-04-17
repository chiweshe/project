package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.DeductionService;
import com.example.employeemanagement.business.validation.api.DeductionServiceValidator;
import com.example.employeemanagement.domain.Deduction;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.repository.DeductionsRepository;
import com.example.employeemanagement.utils.dto.DeductionDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateDeductionRequest;
import com.example.employeemanagement.utils.responses.DeductionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DeductionServiceImpl implements DeductionService {

    private final DeductionsRepository deductionsRepository;
    private final DeductionServiceValidator deductionServiceValidator;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public DeductionServiceImpl(DeductionsRepository deductionsRepository, DeductionServiceValidator deductionServiceValidator,
                                MessageService messageService, ModelMapper modelMapper) {
        this.deductionsRepository = deductionsRepository;
        this.deductionServiceValidator = deductionServiceValidator;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeductionResponse create(CreateDeductionRequest createDeductionRequest, Locale locale, String username) {
        String message;

        boolean isRequestValid = deductionServiceValidator.isRequestValid(createDeductionRequest);

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{}, locale);
            return buildResponse(400, false, message, null, null, null);
        }

        Optional<Deduction> deductionRetrieved = deductionsRepository.findByNameAndStatusNot(createDeductionRequest.getName(),
                Status.DELETED);
        if (deductionRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.DEDUCTION_ALREADY_EXISTS.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }
        Deduction deductionToBeSaved = modelMapper.map(createDeductionRequest, Deduction.class);
        Deduction deductionSaved = deductionsRepository.save(deductionToBeSaved);
        DeductionDto deductionDtoReturned = modelMapper.map(deductionSaved, DeductionDto.class);

        message = messageService.getMessage(Messages.DEDUCTION_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(201, true, message, deductionDtoReturned, null, null);

    }

    @Override
    public DeductionResponse findById(Long id, Locale locale) {
        Optional<Deduction> deduction = deductionsRepository.findByIdAndStatusNot(id, Status.DELETED);

        if (deduction.isEmpty()) {
            String message = messageService.getMessage(Messages.DEDUCTION_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        DeductionDto dto = modelMapper.map(deduction.get(), DeductionDto.class);
        String message = messageService.getMessage(Messages.DEDUCTION_FOUND.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, dto, null, null);
    }

    @Override
    public DeductionResponse findAll(Locale locale) {

        String message = "";

        List<Deduction> list = deductionsRepository.findAllByStatusNot(Status.DELETED);
        List<DeductionDto> dtoList = list.stream()
                .map(d -> modelMapper.map(d, DeductionDto.class))
                .toList();

         message = messageService.getMessage(Messages.DEDUCTION_LIST_FETCHED.getCode(), new String[]{}, locale);

        return buildResponse(200, true, message, null, dtoList, null);
    }

    @Override
    public DeductionResponse findAllAsPages(Pageable pageable, Locale locale) {

        String message ="";

        Page<Deduction> page = deductionsRepository.findAllByStatusNot(Status.DELETED, pageable);
        Page<DeductionDto> dtoPage = page.map(d -> modelMapper.map(d, DeductionDto.class));

         message = messageService.getMessage(Messages.DEDUCTION_LIST_PAGED.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, dtoPage);
    }

    @Override
    public DeductionResponse delete(Long id, Locale locale) {
        Optional<Deduction> deduction = deductionsRepository.findByIdAndStatusNot(id, Status.DELETED);

        if (deduction.isEmpty()) {
            String message = messageService.getMessage(Messages.DEDUCTION_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        Deduction deductionRetrieved = deduction.get();
        deductionRetrieved.setStatus(Status.DELETED);
        deductionsRepository.save(deductionRetrieved);

        String message = messageService.getMessage(Messages.DEDUCTION_DELETED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, null);
    }

    public DeductionResponse buildResponse(int statusCode, Boolean success, String message,
                                           DeductionDto deductionDto, List<DeductionDto> deductionDtoList,
                                           Page<DeductionDto> deductionDtoPage) {
        DeductionResponse deductionResponse = new DeductionResponse();
        deductionResponse.setStatusCode(statusCode);
        deductionResponse.setSuccess(success);
        deductionResponse.setMessage(message);
        deductionResponse.setDeductionDto(deductionDto);
        deductionResponse.setDeductionDtoList(deductionDtoList);
        deductionResponse.setDeductionDtoPage(deductionDtoPage);
        return deductionResponse;
    }
}
