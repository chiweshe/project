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
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
            message = messageService.getMessage(Messages.DEPARTMENT_ALREADY_EXISTS.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);

        }

        return null;
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
