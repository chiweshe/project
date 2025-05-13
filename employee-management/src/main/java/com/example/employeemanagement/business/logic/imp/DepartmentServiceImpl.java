package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.DepartmentService;
import com.example.employeemanagement.business.validation.api.DepartmentServiceValidator;
import com.example.employeemanagement.domain.Department;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.utils.dto.DepartmentDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentServiceValidator departmentServiceValidator;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentServiceValidator departmentServiceValidator,
                                 MessageService messageService, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentServiceValidator = departmentServiceValidator;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentResponse saveDepartment(CreateDepartmentRequest createDepartmentRequest, Locale locale, String username) {



        String message = "";

        boolean isRequestValid = departmentServiceValidator.isRequestValid(createDepartmentRequest);

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<Department> departmentRetrieved = departmentRepository.findByNameAndStatusNot(createDepartmentRequest.getName(), Status.DELETED);
        if (departmentRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.DEPARTMENT_ALREADY_EXISTS.getCode(), new String[]{}, locale);
            return buildResponse(400, false, message, null, null, null);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Department departmentToBeSaved = modelMapper.map(createDepartmentRequest, Department.class);
        Department departmentSaved = departmentRepository.save(departmentToBeSaved);
        DepartmentDto departmentDtoReturned = modelMapper.map(departmentSaved, DepartmentDto.class);

        message = messageService.getMessage(Messages.DEPARTMENT_CREATED_SUCCESSFULLY.getCode(), new String[]{},
                locale);

        return buildResponse(201, true, message, departmentDtoReturned, null,
                null);
    }

    @Override
    public DepartmentResponse findDepartmentById(Long id, Locale locale) {
        Optional<Department> department = departmentRepository.findByIdAndStatusNot(id, Status.DELETED);

        if (department.isEmpty()) {
            String message = messageService.getMessage(Messages.DEPARTMENT_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        DepartmentDto departmentDto = modelMapper.map(department.get(), DepartmentDto.class);
        String message = messageService.getMessage(Messages.DEPARTMENT_RETRIEVED_SUCCESSFUL.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, departmentDto, null, null);
    }

    @Override
    public DepartmentResponse deleteDepartmentById(Long id, Locale locale, String username) {
        Optional<Department> departmentRetrieved = departmentRepository.findByIdAndStatusNot(id, Status.DELETED);

        if (departmentRetrieved.isEmpty()) {
            String message = messageService.getMessage(Messages.DEPARTMENT_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        Department department = departmentRetrieved.get();

        department.setStatus(Status.DELETED);

        department.setName(department.getName() + "_deleted_" + LocalDateTime.now());

        Department updatedDepartment = departmentRepository.save(department);

        DepartmentDto departmentDto = modelMapper.map(updatedDepartment, DepartmentDto.class);
        String message = messageService.getMessage(Messages.DEPARTMENT_DELETED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, departmentDto, null, null);
    }

    @Override
    public DepartmentResponse getAllDepartmentsAsList(Locale locale) {
        List<Department> departments = departmentRepository.findAllByStatusNot(Status.DELETED);

        List<DepartmentDto> departmentDtos = departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .toList();

        String message = messageService.getMessage(Messages.DEPARTMENTS_RETRIEVED_SUCCESSFUL.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, departmentDtos, null);
    }

    @Override
    public DepartmentResponse getAllDepartmentsAsPage(Pageable pageable, Locale locale) {
        Page<Department> departmentPage = departmentRepository.findAllByStatusNot(Status.DELETED, pageable);

        Page<DepartmentDto> departmentDtoPage = departmentPage.map(department -> modelMapper.map(department, DepartmentDto.class));

        String message = messageService.getMessage(Messages.DEPARTMENTS_RETRIEVED_SUCCESSFUL.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, departmentDtoPage);
    }

    public DepartmentResponse buildResponse(int statusCode, Boolean success, String message, DepartmentDto departmentDto,
                                            List<DepartmentDto> departmentDtoList, Page<DepartmentDto> departmentDtoPage) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setStatusCode(statusCode);
        departmentResponse.setSuccess(success);
        departmentResponse.setMessage(message);
        departmentResponse.setDepartmentDto(departmentDto);
        departmentResponse.setDepartmentDtoList(departmentDtoList);
        departmentResponse.setDepartmentDtoPage(departmentDtoPage);

        return departmentResponse;
    }
}
