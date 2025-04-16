package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.AllowanceService;
import com.example.employeemanagement.business.validation.api.AllowanceServiceValidator;
import com.example.employeemanagement.repository.AllowanceRepository;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Locale;

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
        return null;
    }
}
