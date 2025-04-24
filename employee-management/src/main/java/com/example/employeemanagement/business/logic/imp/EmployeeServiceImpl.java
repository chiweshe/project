package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.EmployeeService;
import com.example.employeemanagement.business.validation.api.EmployeeServiceValidator;
import com.example.employeemanagement.domain.Department;
import com.example.employeemanagement.domain.Employee;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.utils.dto.EmployeeDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final EmployeeServiceValidator employeeServiceValidator;
    private final MessageService messageService;
    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;
    ;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeServiceValidator employeeServiceValidator,
                               MessageService messageService, ModelMapper modelMapper,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeServiceValidator = employeeServiceValidator;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponse create(CreateEmployeeRequest createEmployeeRequest, Locale locale, String username) {

        String message = "";

        boolean isRequestValid = employeeServiceValidator.isRequestValid(createEmployeeRequest);
        if (!isRequestValid) {

            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);

        }

        Optional<Employee> employeeRetrieved = employeeRepository.findByEmployeeCodeAndEmailAndPhone(createEmployeeRequest.getEmployeeCode(),
                createEmployeeRequest.getEmail(), createEmployeeRequest.getPhone()
                );
        if (employeeRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.EMPLOYEE_ALREADY_EXISTS.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<Department> department = departmentRepository.findByIdAndStatusNot(createEmployeeRequest.getDepartmentId(),
                Status.DELETED);
        if (department.isEmpty()) {
            message = messageService.getMessage(Messages.DEPARTMENT_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null,
                    null);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Employee employeeToBeSaved = modelMapper.map(createEmployeeRequest, Employee.class);
        employeeToBeSaved.setFullName(createEmployeeRequest.getFirstName() + " " + createEmployeeRequest.getLastName());
        employeeToBeSaved.setDepartment(department.get());
        Employee employeeSaved = employeeRepository.save(employeeToBeSaved);
        EmployeeDto employeeDtoReturned = modelMapper.map(employeeSaved, EmployeeDto.class);

        message = messageService.getMessage(Messages.EMPLOYEE_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(201, true, message, employeeDtoReturned, null, null);

    }

    @Override
    public EmployeeResponse findById(Long id, Locale locale) {
        Optional<Employee> employee = employeeRepository.findByIdAndStatusNot(id, Status.DELETED);
        if (employee.isEmpty()) {
            String message = messageService.getMessage(Messages.EMPLOYEE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        EmployeeDto employeeDto = modelMapper.map(employee.get(), EmployeeDto.class);
        String message = messageService.getMessage(Messages.EMPLOYEE_RETRIEVED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, employeeDto, null, null);
    }

    @Override
    public EmployeeResponse delete(Long id, Locale locale, String username) {
        Optional<Employee> employee = employeeRepository.findByIdAndStatusNot(id, Status.DELETED);
        if (employee.isEmpty()) {
            String message = messageService.getMessage(Messages.EMPLOYEE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        Employee employeeToDelete = employee.get();
        employeeToDelete.setStatus(Status.DELETED);
        employeeRepository.save(employeeToDelete);

        String message = messageService.getMessage(Messages.EMPLOYEE_DELETED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, null);
    }

    @Override
    public EmployeeResponse findAllAsList(Locale locale) {
        List<Employee> employees = employeeRepository.findByStatusNot(Status.DELETED);
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();

        String message = messageService.getMessage(Messages.EMPLOYEES_RETRIEVED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, employeeDtos, null);
    }

    @Override
    public EmployeeResponse findAllAsPage(Pageable pageable, Locale locale) {

            Page<Employee> employeePage = employeeRepository.findAllByStatusNot(Status.DELETED, pageable);

        Page<EmployeeDto> employeeDtoPage = employeePage.map(employee -> modelMapper.map(employee, EmployeeDto.class));

        String message = messageService.getMessage(Messages.EMPLOYEES_RETRIEVED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, employeeDtoPage);
    }

       public EmployeeResponse buildResponse(int statusCode, Boolean success, String message, EmployeeDto employeeDto,
                                       List<EmployeeDto> employeeDtoList, Page<EmployeeDto> employeeDtoPage){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setStatusCode(statusCode);
        employeeResponse.setSuccess(success);
        employeeResponse.setMessage(message);
        employeeResponse.setEmployeeDto(employeeDto);
        employeeResponse.setEmployeeDtoList(employeeDtoList);
        employeeResponse.setEmployeeDtoPage(employeeDtoPage);

        return employeeResponse;

        }

}
