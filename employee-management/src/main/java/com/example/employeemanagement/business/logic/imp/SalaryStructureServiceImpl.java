package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.SalaryStructureService;
import com.example.employeemanagement.business.validation.api.SalaryStructureServiceValidator;
import com.example.employeemanagement.domain.Employee;
import com.example.employeemanagement.domain.SalaryStructure;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.SalaryStructureRepository;
import com.example.employeemanagement.utils.dto.SalaryStructureDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateSalaryStructureRequest;
import com.example.employeemanagement.utils.responses.SalaryStructureResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SalaryStructureServiceImpl implements SalaryStructureService {

    private final SalaryStructureRepository salaryStructureRepository;
    private final SalaryStructureServiceValidator salaryStructureServiceValidator;
    private final EmployeeRepository employeeRepository;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public SalaryStructureServiceImpl(SalaryStructureRepository salaryStructureRepository,
                                      SalaryStructureServiceValidator salaryStructureServiceValidator, EmployeeRepository employeeRepository,
                                      MessageService messageService, ModelMapper modelMapper) {
        this.salaryStructureRepository = salaryStructureRepository;
        this.salaryStructureServiceValidator = salaryStructureServiceValidator;
        this.employeeRepository = employeeRepository;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public SalaryStructureResponse create(CreateSalaryStructureRequest createSalaryStructureRequest, Locale locale, String username) {

        String message = "";

        boolean isRequestValid = salaryStructureServiceValidator.isRequestValid(createSalaryStructureRequest);
        if (!isRequestValid) {

            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);

        }

        Optional<SalaryStructure> salaryStructureRetrieved = salaryStructureRepository.findByEmployeeIdAndStatusNot(createSalaryStructureRequest.getEmployeeId(),
                Status.DELETED);

        if (salaryStructureRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.EMPLOYEE_SALARY_STRUCTURE_ALREADY_EXISTS.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<Employee> employee = employeeRepository.findByIdAndStatusNot(createSalaryStructureRequest.getEmployeeId(),
                Status.DELETED);

        if (employee.isEmpty()) {
            message = messageService.getMessage(Messages.EMPLOYEE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null,
                    null);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SalaryStructure salaryStructureToBeSaved = modelMapper.map(createSalaryStructureRequest, SalaryStructure.class);
        salaryStructureToBeSaved.setEmployee(employee.get());
        salaryStructureToBeSaved.setEmployeeName(employee.get().getFullName());

        SalaryStructure salaryStructureSaved = salaryStructureRepository.save(salaryStructureToBeSaved);
        SalaryStructureDto salaryStructureDtoReturned = modelMapper.map(salaryStructureSaved, SalaryStructureDto.class);

        message = messageService.getMessage(Messages.SALARY_STRUCTURE_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(201, true, message, salaryStructureDtoReturned, null, null);

    }

    @Override
    public SalaryStructureResponse findAllAsPage(Pageable pageable, Locale locale) {
        String message = "";
        Page<SalaryStructure> salaryStructurePage = salaryStructureRepository.findAllByStatusNot(Status.DELETED, pageable);

        Page<SalaryStructureDto> salaryStructureDtoPage = salaryStructurePage.map(salaryStructure ->
                modelMapper.map(salaryStructure, SalaryStructureDto.class));

         message = messageService.getMessage(Messages.SALARY_STRUCTURES_RETRIEVED_SUCCESSFULLY.getCode(), new String[]{}, locale);

        return buildResponse(200, true, message, null, null, salaryStructureDtoPage);

    }

    SalaryStructureResponse buildResponse(int statusCode, Boolean success, String message,
                                          SalaryStructureDto salaryStructureDto, List<SalaryStructureDto> salaryStructureDtoList,
                                          Page<SalaryStructureDto> salaryStructureDtoPage) {
        SalaryStructureResponse salaryStructureResponse = new SalaryStructureResponse();
        salaryStructureResponse.setStatusCode(statusCode);
        salaryStructureResponse.setSuccess(success);
        salaryStructureResponse.setMessage(message);
        salaryStructureResponse.setSalaryStructureDto(salaryStructureDto);
        salaryStructureResponse.setSalaryStructureDtoList(salaryStructureDtoList);
        salaryStructureResponse.setSalaryStructureDtoPage(salaryStructureDtoPage);
        return salaryStructureResponse;
    }

}
