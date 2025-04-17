package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.TaxSlabService;
import com.example.employeemanagement.business.validation.api.TaxSlabServiceValidator;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.domain.TaxSlab;
import com.example.employeemanagement.repository.TaxSlabRepository;
import com.example.employeemanagement.utils.dto.TaxSlabDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateTaxSlabRequest;
import com.example.employeemanagement.utils.responses.TaxSlabResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.Page;
import java.util.List;


@Service
public class TaxSlabServiceImpl implements TaxSlabService {

    private final TaxSlabRepository taxSlabRepository;
    private final TaxSlabServiceValidator taxSlabServiceValidator;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public TaxSlabServiceImpl(TaxSlabRepository taxSlabRepository, TaxSlabServiceValidator taxSlabServiceValidator,
                              MessageService messageService, ModelMapper modelMapper) {
        this.taxSlabRepository = taxSlabRepository;
        this.taxSlabServiceValidator = taxSlabServiceValidator;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaxSlabResponse create(CreateTaxSlabRequest createTaxSlabRequest, Locale locale, String username) {

        String message = "";

        boolean isRequestValid = taxSlabServiceValidator.validate(createTaxSlabRequest);

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<TaxSlab> taxSlabRetrieved = taxSlabRepository.findByLowerBoundAndUpperBoundAndRateAndStatusNot(
                createTaxSlabRequest.getLowerBound(), createTaxSlabRequest.getUpperBound(), createTaxSlabRequest.getRate(),
                Status.DELETED);
        if (taxSlabRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.TAX_SLAB_ALREADY_EXISTS.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }


        TaxSlab taxSlab = modelMapper.map(createTaxSlabRequest, TaxSlab.class);
        TaxSlab taxSlabSaved = taxSlabRepository.save(taxSlab);
        TaxSlabDto taxSlabDtoReturned = modelMapper.map(taxSlabSaved, TaxSlabDto.class);

        message = messageService.getMessage(Messages.TAX_SLAB_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);

        return buildResponse(201, true, message, taxSlabDtoReturned, null, null);
    }

    @Override
    public TaxSlabResponse findById(Long id, Locale locale) {

        String message = "";


        Optional<TaxSlab> taxSlab = taxSlabRepository.findByIdAndStatusNot(id, Status.DELETED);
        if (taxSlab.isEmpty()) {
            message  = messageService.getMessage(Messages.TAX_SLAB_NOT_FOUND.getCode(), new String[]{}, locale);

            return buildResponse(404, false, message, null, null, null);
        }

        TaxSlabDto taxSlabDto = modelMapper.map(taxSlab.get(), TaxSlabDto.class);
        message = messageService.getMessage(Messages.TAX_SLAB_FOUND.getCode(), new String[]{}, locale);

        return buildResponse(200, true, message, taxSlabDto, null, null);
    }

    @Override
    public TaxSlabResponse findAll(Locale locale) {

        String message = "";

        List<TaxSlab> taxSlabs = taxSlabRepository.findAllByStatusNot(Status.DELETED);
        List<TaxSlabDto> dtoList = taxSlabs.stream()
                .map(s -> modelMapper.map(s, TaxSlabDto.class))
                .toList();

        message = messageService.getMessage(Messages.TAX_SLAB_LIST_FETCHED.getCode(), new String[]{}, locale);

        return buildResponse(200, true, message, null, dtoList, null);
    }

    @Override
    public TaxSlabResponse findAllAsPages(Pageable pageable, Locale locale) {

        String message = "";

        Page<TaxSlab> page = taxSlabRepository.findAllByStatusNot(Status.DELETED, pageable);

        Page<TaxSlabDto> dtoPage = page.map(s -> modelMapper.map(s, TaxSlabDto.class));

        message = messageService.getMessage(Messages.TAX_SLAB_LIST_PAGED.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, dtoPage);
    }

    @Override
    public TaxSlabResponse delete(Long id, Locale locale) {

        String message = "";

        Optional<TaxSlab> slab = taxSlabRepository.findByIdAndStatusNot(id, Status.DELETED);
        if (slab.isEmpty()) {
            message = messageService.getMessage(Messages.TAX_SLAB_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        TaxSlab taxSlabToBeDeleted = slab.get();
        taxSlabToBeDeleted.setStatus(Status.DELETED);
        taxSlabRepository.save(taxSlabToBeDeleted);

        message = messageService.getMessage(Messages.TAX_SLAB_DELETED_SUCCESSFULLY.getCode(), new String[]{}, locale);

        return buildResponse(200, true, message, null, null, null);
    }


    private TaxSlabResponse buildResponse(int statusCode, Boolean success, String message,
                                          TaxSlabDto taxSlabDto, List<TaxSlabDto> taxSlabDtoList, Page<TaxSlabDto> taxSlabDtoPage) {
        TaxSlabResponse taxSlabResponse = new TaxSlabResponse();
        taxSlabResponse.setStatusCode(statusCode);
        taxSlabResponse.setSuccess(success);
        taxSlabResponse.setMessage(message);
        taxSlabResponse.setTaxSlabDto(taxSlabDto);
        taxSlabResponse.setTaxSlabDtoList(taxSlabDtoList);
        taxSlabResponse.setTaxSlabDtoPage(taxSlabDtoPage);

        return taxSlabResponse;
    }
}
