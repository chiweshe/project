package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.AllowanceService;
import com.example.employeemanagement.business.validation.api.AllowanceServiceValidator;
import com.example.employeemanagement.domain.Allowance;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.repository.AllowanceRepository;
import com.example.employeemanagement.utils.dto.AllowanceDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AllowanceServiceImpl implements AllowanceService {

    private final AllowanceRepository allowanceRepository;
    private final AllowanceServiceValidator allowanceServiceValidator;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public AllowanceServiceImpl(AllowanceRepository allowanceRepository, AllowanceServiceValidator allowanceServiceValidator,
                                MessageService messageService, ModelMapper modelMapper) {
        this.allowanceRepository = allowanceRepository;
        this.allowanceServiceValidator = allowanceServiceValidator;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AllowanceResponse create(CreateAllowanceRequest request, Locale locale, String username) {

        String message = "";

        boolean isRequestValid = allowanceServiceValidator.isRequestValid(request);

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<Allowance> allowanceRetrieved = allowanceRepository.findByNameAndStatusNot(request.getName(), Status.DELETED);
        if (allowanceRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.ALLOWANCE_ALREADY_EXISTS.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Allowance allowanceToBeSaved = modelMapper.map(request, Allowance.class);
        Allowance allowanceSaved = allowanceRepository.save(allowanceToBeSaved);
        AllowanceDto allowanceDtoReturned = modelMapper.map(allowanceSaved, AllowanceDto.class);

        message = messageService.getMessage(Messages.ALLOWANCE_CREATED_SUCCESSFULLY.getCode(), new String[]{},
                locale);

        return buildResponse(201, true, message, allowanceDtoReturned, null,
                null);
    }
    @Override
    public AllowanceResponse findById(Long id, Locale locale) {
        Optional<Allowance> allowance = allowanceRepository.findByIdAndStatusNot(id, Status.DELETED);

        if (allowance.isEmpty()) {
            String message = messageService.getMessage(Messages.ALLOWANCE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        AllowanceDto allowanceDto = modelMapper.map(allowance.get(), AllowanceDto.class);
        String message = messageService.getMessage(Messages.ALLOWANCE_FOUND.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, allowanceDto, null, null);
    }

    @Override
    public AllowanceResponse findAll(Locale locale) {
        List<Allowance> allowances = allowanceRepository.findAllByStatusNot(Status.DELETED);
        List<AllowanceDto> dtos = allowances.stream()
                .map(a -> modelMapper.map(a, AllowanceDto.class))
                .toList();

        String message = messageService.getMessage(Messages.ALLOWANCE_LIST_FETCHED.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, dtos, null);
    }

    @Override
    public AllowanceResponse findAllAsPages(Pageable pageable, Locale locale) {
        Page<Allowance> page = allowanceRepository.findAllByStatusNot(Status.DELETED, pageable);
        Page<AllowanceDto> dtoPage = page.map(a -> modelMapper.map(a, AllowanceDto.class));

        String message = messageService.getMessage(Messages.ALLOWANCE_LIST_FETCHED.getCode(),
                new String[]{}, locale);
        return buildResponse(200, true, message, null, null, dtoPage);
    }

    @Override
    public AllowanceResponse delete(Long id, Locale locale) {
        Optional<Allowance> allowance = allowanceRepository.findByIdAndStatusNot(id, Status.DELETED);
        if (allowance.isEmpty()) {
            String message = messageService.getMessage(Messages.ALLOWANCE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        Allowance allowanceRetrieved = allowance.get();
        allowanceRetrieved.setStatus(Status.DELETED);
        allowanceRepository.save(allowanceRetrieved);

        String message = messageService.getMessage(Messages.ALLOWANCE_DELETED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, null);
    }

    public AllowanceResponse buildResponse(int statusCode, Boolean success, String message, AllowanceDto allowanceDto,
                                            List<AllowanceDto> allowanceDtoList, Page<AllowanceDto> allowanceDtoPage) {
        AllowanceResponse allowanceResponse = new AllowanceResponse();
        allowanceResponse.setStatusCode(statusCode);
        allowanceResponse.setSuccess(success);
        allowanceResponse.setMessage(message);
        allowanceResponse.setAllowanceDto(allowanceDto);
        allowanceResponse.setAllowanceDtoList(allowanceDtoList);
        allowanceResponse.setAllowanceDtoPage(allowanceDtoPage);

        return allowanceResponse;
    }
}
