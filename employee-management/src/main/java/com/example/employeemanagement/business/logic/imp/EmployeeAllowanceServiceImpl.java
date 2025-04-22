package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.EmployeeAllowanceService;
import com.example.employeemanagement.business.validation.api.EmployeeAllowanceServiceValidator;
import com.example.employeemanagement.domain.Allowance;
import com.example.employeemanagement.domain.Employee;
import com.example.employeemanagement.domain.EmployeeAllowance;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.repository.AllowanceRepository;
import com.example.employeemanagement.repository.EmployeeAllowanceRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.utils.dto.EmployeeAllowanceDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;
import com.example.employeemanagement.utils.responses.EmployeeAllowanceResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeAllowanceServiceImpl implements EmployeeAllowanceService {

    private final EmployeeAllowanceRepository employeeAllowanceRepository;
    private final EmployeeAllowanceServiceValidator employeeAllowanceServiceValidator;
    private final EmployeeRepository employeeRepository;
    private final AllowanceRepository allowanceRepository;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public EmployeeAllowanceServiceImpl(EmployeeAllowanceRepository employeeAllowanceRepository,
                                        EmployeeAllowanceServiceValidator employeeAllowanceServiceValidator,
                                        EmployeeRepository employeeRepository, AllowanceRepository allowanceRepository,
                                        MessageService messageService, ModelMapper modelMapper) {
        this.employeeAllowanceRepository = employeeAllowanceRepository;
        this.employeeAllowanceServiceValidator = employeeAllowanceServiceValidator;
        this.employeeRepository = employeeRepository;
        this.allowanceRepository = allowanceRepository;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeAllowanceResponse create(CreateEmployeeAllowanceRequest createEmployeeAllowanceRequest, Locale locale, String username) {

        String message ="";

        boolean isRequestValid = employeeAllowanceServiceValidator.isRequestValid(createEmployeeAllowanceRequest);
        if (!isRequestValid){

            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);

        }

        Optional<Employee> employeeRetrieved = employeeRepository.findByIdAndStatusNot(createEmployeeAllowanceRequest.getEmployeeId(),
                Status.DELETED);
        if (employeeRetrieved.isEmpty()) {
            message = messageService.getMessage(Messages.EMPLOYEE_NOT_FOUND.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<Allowance> allowanceRetrieved = allowanceRepository.findByIdAndStatusNot(createEmployeeAllowanceRequest.getAllowanceId(),
                Status.DELETED);
        if (allowanceRetrieved.isEmpty()) {
            message = messageService.getMessage(Messages.ALLOWANCE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null,
                    null, null);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeAllowance employeeAllowanceToBeSaved = modelMapper.map(createEmployeeAllowanceRequest, EmployeeAllowance.class);
        employeeAllowanceToBeSaved.setAllowance(allowanceRetrieved.get());
        employeeAllowanceToBeSaved.setAllowanceName(allowanceRetrieved.get().getName());
        employeeAllowanceToBeSaved.setEmployee(employeeRetrieved.get());
        employeeAllowanceToBeSaved.setEmployeeName(employeeRetrieved.get().getFullName());

        EmployeeAllowance employeeAllowanceSaved = employeeAllowanceRepository.save(employeeAllowanceToBeSaved);
        EmployeeAllowanceDto employeeAllowanceDtoReturned = modelMapper.map(employeeAllowanceSaved, EmployeeAllowanceDto.class);

        message = messageService.getMessage(Messages.EMPLOYEE_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(201, true, message, employeeAllowanceDtoReturned, null,
                null);

    }

    @Override
    public EmployeeAllowanceResponse findById(Long id, Locale locale) {
        return null;
    }

    @Override
    public EmployeeAllowanceResponse findAll(Locale locale) {
        return null;
    }

    @Override
    public EmployeeAllowanceResponse findByEmployeeId(Long employeeId, Locale locale) {

        String message = "";

        List<EmployeeAllowance> employeeAllowances =
                employeeAllowanceRepository.findByEmployeeIdAndStatusNot(employeeId, Status.DELETED);

        if (employeeAllowances.isEmpty()) {
            message = messageService.getMessage(Messages.MESSAGE_ALLOWANCES_NOT_FOUND.getCode(), new String[]{},
                    locale);
            return buildResponse(404, false, message, null,
                    null, null);
        }

        List<EmployeeAllowanceDto> employeeAllowanceDtoListReturned = modelMapper.map(employeeAllowances,
                new TypeToken<List<EmployeeAllowanceDto>>() {}.getType());

        message = messageService.getMessage(Messages.MESSAGE_EMPLOYEE_ALLOWANCE_RETRIEVED_SUCCESSFULLY.getCode(),
                new String[]{},
                locale);
        return buildResponse(200, true, message, null,
                employeeAllowanceDtoListReturned, null);


    }


    @Override
    public EmployeeAllowanceResponse delete(Long id, Locale locale, String username) {
        return null;
    }

    @Override
    public EmployeeAllowanceResponse viewAsPages(int page, int size, Locale locale) {
        return null;
    }


    EmployeeAllowanceResponse buildResponse(int statusCode, Boolean success, String message,
                                            EmployeeAllowanceDto employeeAllowanceDto, List<EmployeeAllowanceDto>
                                            employeeAllowanceDtoList, Page<EmployeeAllowanceDto> employeeAllowanceDtoPage){

        EmployeeAllowanceResponse employeeAllowanceResponse = new EmployeeAllowanceResponse();
        employeeAllowanceResponse.setStatusCode(statusCode);
        employeeAllowanceResponse.setSuccess(success);
        employeeAllowanceResponse.setMessage(message);
        employeeAllowanceResponse.setEmployeeAllowanceDto(employeeAllowanceDto);
        employeeAllowanceResponse.setEmployeeAllowanceDtoList(employeeAllowanceDtoList);
        employeeAllowanceResponse.setEmployeeAllowanceDtoPage(employeeAllowanceDtoPage);

        return employeeAllowanceResponse;

    }
}
